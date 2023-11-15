import { Injectable, Input, ValueEqualityFn } from '@angular/core';
import { BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class MessagesService {
  private booleanMessage = new BehaviorSubject<boolean>(false);
  messagesObserver = this.booleanMessage.asObservable();
  constructor() { }

  setMessageStatus(status: boolean): void {
    this.booleanMessage.next(status)
    console.log("Im Service auf True", + status)
  }
}
