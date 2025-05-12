import { Direction } from './direction.enum';

export interface CallRequest {
  floor: number;
  direction: Direction.UP | Direction.DOWN;
}
