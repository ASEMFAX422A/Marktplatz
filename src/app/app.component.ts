import { Component, Inject, Input, OnInit, OnDestroy } from '@angular/core';
import { SidebarstatusService } from './sidebarstatus.service';
import { MediaObserver, MediaChange} from '@angular/flex-layout';
import { Subscription} from 'rxjs';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  messagesObserver = this.sidebarServ.messagesObserver;
  productObserver = this.sidebarServ.productObserver;
  accountObserver = this.sidebarServ.accountObserver;
  hompageObserver = this.sidebarServ.hompageObserver;

  constructor (private sidebarServ: SidebarstatusService, public mediaObserver:MediaObserver) {}



  username = ['Oliver','Zaid','Besmir ']
  postProduct = ['Fernseher', 'Fernseher', 'Fernseher']
  postPrice = ['300€', '290€', '320€']
  title = 'EbayTest';


}
