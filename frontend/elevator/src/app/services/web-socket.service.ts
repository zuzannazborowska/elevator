import { Injectable } from '@angular/core';
import { RxStomp } from '@stomp/rx-stomp';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  private rxStomp: RxStomp;

  constructor() {
    this.rxStomp = new RxStomp();
    this.initializeWebSocket();
  }

  listenTo<T>(destination: string): Observable<T> {
    if (this.rxStomp?.active) {
      this.rxStomp.configure({
        correlateErrors: () => destination
      });
      return this.rxStomp.watch({ destination: destination }).pipe(map(message => JSON.parse(message['body']) as T));
    }
    return of();
  }

  private initializeWebSocket(): void {
    const webSocketUrl: string = `ws://localhost:8089/api/ws`;
    this.rxStomp.configure({
      brokerURL: webSocketUrl
    });
    this.rxStomp.activate();
  }
}
