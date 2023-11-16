import { Component, Inject, Input, OnInit, OnDestroy } from '@angular/core';
import { SidebarstatusService } from './sidebarstatus.service';
import { MediaObserver, MediaChange } from '@angular/flex-layout';
import { Subscription} from 'rxjs';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
  export class AppComponent implements OnInit, OnDestroy {
    messagesObserver = this.sidebarServ.messagesObserver;
    productObserver = this.sidebarServ.productObserver;
    accountObserver = this.sidebarServ.accountObserver;
    hompageObserver = this.sidebarServ.hompageObserver;
    username = ['Oliver','Zaid','Besmir ']
    postProduct = ['Fernseher', 'Fernseher', 'Fernseher']
    postPrice = ['300€', '290€', '320€']
    title = 'EbayTest';

    mediaSub:Subscription;

    constructor(private sidebarServ: SidebarstatusService, public mediaObserver: MediaObserver) {
      console.log('MediaObserver:', mediaObserver);
      this.mediaSub = new Subscription();
    }


    ngOnInit(): void {
      this.mediaSub = this.mediaObserver.media$.subscribe((result:MediaChange) =>{
        console.log(result.mqAlias)
      })
    }

    ngOnDestroy(): void {
      this.mediaSub.unsubscribe();
    }
  }
