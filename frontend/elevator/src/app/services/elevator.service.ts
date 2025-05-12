import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CallRequest } from '../models/call-request.model';

@Injectable({ providedIn: 'root' })
export class ElevatorService {

  private baseUrl = 'http://localhost:8089/api/elevators';

  constructor(private http: HttpClient) {}

  callElevator(request: CallRequest): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/call`, request);
  }

  selectFloor(elevatorId: number, floor: number): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/${elevatorId}/select?floor=${floor}`, {});
  }
}
