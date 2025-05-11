package el.ev.business.domain.service;

import el.ev.business.domain.model.Direction;
import el.ev.business.domain.model.Elevator;

import java.util.Collection;

public interface ElevatorAssignmentStrategy {

    /**
     * Chooses the best elevator based on the current state of all elevators and the requested floor and direction.
     *
     * @param elevators         The collection of elevators to choose from.
     * @param requestedFloor    The floor where the call was made.
     * @param requestedDirection The direction in which the elevator is requested to go.
     * @return The chosen elevator.
     */
    Elevator chooseElevator(Collection<Elevator> elevators, int requestedFloor, Direction requestedDirection);
}
