package el.ev.business.domain.service;

import el.ev.business.domain.dto.CallRequest;
import el.ev.business.domain.model.Elevator;

import java.util.List;

/**
 * ElevatorSystemService defines the core operations for managing the state,
 * movement, and interactions of elevators within a building simulation.
 */
public interface ElevatorSystemService {

    /**
     * Handles an external elevator call using a structured request.
     *
     * @param request the call request containing floor and direction
     */
    void callElevator(CallRequest request);

    /**
     * Handles an internal request made from within an elevator to go to a specific floor.
     *
     * @param elevatorId the ID of the elevator receiving the request
     * @param floor      the target floor selected inside the elevator
     */
    void selectFloor(int elevatorId, int floor);

    /**
     * Returns the current state of all elevators in the system.
     *
     * @return a list of Elevator objects representing their current positions,
     *         directions, and queued requests
     */
    List<Elevator> getAllElevators();
}
