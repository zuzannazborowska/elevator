package el.ev.business.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
public class Elevator {
    private int id;
    private int currentFloor;
    private Direction direction;
    private Queue<Integer> targetFloors;
    private boolean isDoorOpen;

    public Elevator() {
        direction = Direction.IDLE;
        isDoorOpen = false;
        targetFloors = new LinkedList<>();
        currentFloor = 0;
    }
}
