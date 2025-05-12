export interface Elevator {
  id: number;
  currentFloor: number;
  direction: 'UP' | 'DOWN' | 'IDLE';
  targetFloors: number[];
}
