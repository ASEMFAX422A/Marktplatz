import { Injectable, ValueEqualityFn } from '@angular/core';
import { LeftsidebarComponent } from './leftsidebar/leftsidebar.component';
import { AppComponent } from './app.component';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {
  messagesObserver: boolean = false;
  constructor() { }

  setMessageStatus(value: boolean): void {
    this.messagesObserver = value;
  }
}
