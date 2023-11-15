import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class SidebarstatusService {
  private booleanMessage = new BehaviorSubject<boolean>(false);
  messagesObserver = this.booleanMessage.asObservable();
  private booleanProduct = new BehaviorSubject<boolean>(false);
  productObserver = this.booleanProduct.asObservable();
  private booleanAccount = new BehaviorSubject<boolean>(false);
  accountObserver = this.booleanAccount.asObservable();
  private booleanHompage = new BehaviorSubject<boolean>(true);
  hompageObserver = this.booleanHompage.asObservable();

  constructor() { }

  setMessageStatus(status: boolean): void {
    this.booleanMessage.next(status)
    console.log("Messagestatus im Service geändert: ", + status)
  }
  setProductStatus(status: boolean): void {
    this.booleanProduct.next(status)
    console.log("Productstatus im Service geändert:", + status)
  }
  setAccountStatus(status: boolean): void {
    this.booleanAccount.next(status)
    console.log("Accountstatus im Service geändert: ", + status)
  }
  setHompageStatus(status:boolean): void {
    this.booleanHompage.next(status)
    console.log("Homepagestatus im Service geändert: ", + status)
  }
}

