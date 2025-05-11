package el.ev.business.domain.service;

import el.ev.business.api.ElevatorSocketPublisher;
import el.ev.business.domain.ElevatorConstants;
import el.ev.business.domain.dto.CallRequest;
import el.ev.business.domain.model.Direction;
import el.ev.business.domain.model.Elevator;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class ElevatorSystemServiceAdapter implements ElevatorSystemService {

    private final ElevatorSocketPublisher elevatorSocketPublisher;
    private final ElevatorAssignmentStrategy elevatorAssignmentStrategy;
    private final Map<Integer, Elevator> elevators = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        for (int i = 1; i <= ElevatorConstants.NUMBER_OF_ELEVATORS; i++) {
            Elevator e = new Elevator();
            e.setId(i);
            e.setCurrentFloor(1);
            elevators.put(i, e);
        }
    }

    @Override
    public void callElevator(CallRequest request) {
        validateFloor(request.floor());
        Elevator selected = findBestElevator(request.floor(), request.direction());
        selected.getTargetFloors().add(request.floor());
    }

    @Override
    public void selectFloor(int elevatorId, int floor) {
        validateFloor(floor);
        validateElevatorId(elevatorId);

        elevators.get(elevatorId).getTargetFloors().add(floor);
    }

    @Override
    public List<Elevator> getAllElevators() {
        return new ArrayList<>(elevators.values());
    }

    private Elevator findBestElevator(int floor, Direction direction) {
        return elevatorAssignmentStrategy.chooseElevator(getAllElevators(), floor, direction);
    }

    private void moveElevator(Elevator e) {
        if (e.getTargetFloors().isEmpty()) {
            e.setDirection(Direction.IDLE);
            return;
        }

        int target = e.getTargetFloors().peek();

        if (e.getCurrentFloor() < target) {
            e.setCurrentFloor(e.getCurrentFloor() + 1);
            e.setDirection(Direction.UP);
        } else if (e.getCurrentFloor() > target) {
            e.setCurrentFloor(e.getCurrentFloor() - 1);
            e.setDirection(Direction.DOWN);
        } else {
            e.setDoorOpen(true);
            e.getTargetFloors().poll();
            e.setDirection(e.getTargetFloors().isEmpty() ? Direction.IDLE : e.getDirection());
        }
    }

    @Scheduled(fixedRate = 1000)
    private void step() {
        for (Elevator e : elevators.values()) {
            moveElevator(e);
        }

        elevatorSocketPublisher.publishElevatorsState(getAllElevators());
    }

    private void validateFloor(int floor) {
        if (floor > ElevatorConstants.NUMBER_OF_FLOORS) {
            throw new IllegalArgumentException("Invalid floor: " + floor);
        }
    }

    private void validateElevatorId(int elevatorId) {
        if (elevatorId > ElevatorConstants.NUMBER_OF_ELEVATORS) {
            throw new IllegalArgumentException("Invalid elevator ID: " + elevatorId);
        }
    }
}
