import { AsyncPipe, NgForOf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CallRequest } from '../../models/call-request.model';
import { ElevatorConstants } from '../../models/elevator.constants';
import { Elevator } from '../../models/elevator.model';
import { ElevatorService } from '../../services/elevator.service';
import { WebSocketService } from '../../services/web-socket.service';
import { FloorComponent } from '../floor/floor.component';

@Component({
  selector: 'app-building',
  imports: [
    NgForOf,
    AsyncPipe,
    FloorComponent
  ],
  templateUrl: './building.component.html',
  styleUrl: './building.component.scss'
})
export class BuildingComponent implements OnInit {

  protected readonly NUMBER_OF_FLOORS = ElevatorConstants.NUMBER_OF_FLOORS;
  elevators$: Observable<Elevator[]> = new Observable();

  constructor(private elevatorService: ElevatorService,
              private webSocketService: WebSocketService) {
  }

  ngOnInit(): void {
    this.elevators$ = this.webSocketService.listenTo('/topic/elevators')
  }

  callElevator(callRequest: CallRequest) {
    this.elevatorService.callElevator(callRequest).subscribe({
      next: response => console.log('Elevator called successfully:', response),
      error: error => console.error('Failed to call elevator:', error)
    });
  }

  selectFloor(elevatorId: number, floor: number) {
    this.elevatorService.selectFloor(elevatorId, floor).subscribe({
      next: () => console.log(`Floor ${floor} selected for elevator ${elevatorId}`),
      error: (error) => console.error(`Error selecting floor ${floor} for elevator ${elevatorId}:`, error)
    });
  }
}
