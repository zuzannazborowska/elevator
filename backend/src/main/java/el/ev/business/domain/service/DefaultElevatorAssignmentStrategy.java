package el.ev.business.domain.service;

import el.ev.business.domain.model.Direction;
import el.ev.business.domain.model.Elevator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class DefaultElevatorAssignmentStrategy implements ElevatorAssignmentStrategy {

    @Override
    public Elevator chooseElevator(Collection<Elevator> elevators, int requestedFloor, Direction requestedDirection) {
        List<Elevator> suitable = new ArrayList<>();
        List<Elevator> idle = new ArrayList<>();

        for (Elevator e : elevators) {
            Direction dir = e.getDirection();
            int current = e.getCurrentFloor();

            if (dir == Direction.IDLE) {
                idle.add(e);
            } else if (dir == requestedDirection) {
                if (movesInRightDirection(requestedFloor, dir, current)) {
                    suitable.add(e);
                }
            }
        }

        //  Right direction
        if (!suitable.isEmpty()) {
            return chooseClosest(suitable, requestedFloor);
        }

        // Idle elevator
        if (!idle.isEmpty()) {
            return chooseClosest(idle, requestedFloor);
        }

        // Any – smallest number of targets and closest
        return elevators.stream().min(Comparator.comparingInt((Elevator e) -> e.getTargetFloors().size())
                                                .thenComparingInt(e -> Math.abs(e.getCurrentFloor() - requestedFloor)))
                .orElseThrow(() -> new IllegalStateException("No elevators available"));
    }

    private static boolean movesInRightDirection(int requestedFloor, Direction dir, int current) {
        return (dir == Direction.UP && current <= requestedFloor)
                || (dir == Direction.DOWN && current >= requestedFloor);
    }

    private Elevator chooseClosest(List<Elevator> list, int floor) {
        return list.stream()
                .min(Comparator.comparingInt(e -> Math.abs(e.getCurrentFloor() - floor)))
                .orElseThrow();
    }
}
