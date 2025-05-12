import { NgForOf, NgIf } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CallRequest } from '../../models/call-request.model';
import { Direction } from '../../models/direction.enum';
import { Elevator } from '../../models/elevator.model';

@Component({
  selector: 'tr[app-floor]',
  imports: [
    NgIf,
    NgForOf
  ],
  templateUrl: './floor.component.html',
  styleUrl: './floor.component.scss'
})
export class FloorComponent {
  protected readonly Direction = Direction;

  @Input() numberOfFloors: number = 0;
  @Input() floorIndex: number = 0;
  @Input() elevators: Elevator[] | null = null;

  @Output() callElevator: EventEmitter<CallRequest> = new EventEmitter<CallRequest>

  isElevatorActive(elevator: Elevator): boolean {
    return elevator.currentFloor === this.floorIndex;
  }

  goesUp(elevator: Elevator): boolean {
    return elevator.direction === Direction.UP;
  }

  goesDown(elevator: Elevator): boolean {
    return elevator.direction === Direction.DOWN;
  }

  callElevatorToFloor(floorIndex: number, direction: Direction.UP | Direction.DOWN): void {
    const callRequest: CallRequest = {
      floor: floorIndex,
      direction: direction
    }
    this.callElevator.emit(callRequest)
  }
}
