import { Component, Inject, Input, OnInit, OnDestroy } from '@angular/core';
import { SidebarstatusService } from './sidebarstatus.service';
import { MediaObserver, MediaChange } from '@angular/flex-layout';
import { Subscription} from 'rxjs';
import { AnzeigeDto } from 'src/models/anzeige.models';
import { ProductapiService } from './productapi.service';
import { UserapiService } from './userapi.service';
import { UserDto } from 'src/models/login.modules';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
  export class AppComponent{
    messagesObserver = this.sidebarServ.messagesObserver;
    productObserver = this.sidebarServ.productObserver;
    accountObserver = this.sidebarServ.accountObserver;
    hompageObserver = this.sidebarServ.hompageObserver;
    username = ['Oliver','Zaid','Besmir ', 'Oliver','Zaid','Besmir ']
    postProduct = ['Fernseher', 'Fernseher', 'Fernseher', 'Fernseher', 'Fernseher', 'Fernseher']
    postPrice = ['300€', '290€', '320€', '300€', '290€', '320€']
    postImage = ['/src/assets/img/profilepic/2.jpg' ]
    title = 'EbayTest';

    anzeigen: AnzeigeDto [] = [];


    constructor(private sidebarServ: SidebarstatusService,private productService: ProductapiService,private userObserv: UserapiService ) {}

    ngOnInit(): void {
      this.productService.getAllAnzeigen().subscribe(data => {
        this.anzeigen = data;
      })
      const isLoggedInString = localStorage.getItem('isLoggedIn');
  if (isLoggedInString) {
    // Konvertiere den gespeicherten String in einen boolean-Wert
    const isLoggedIn = JSON.parse(isLoggedInString);

    // Setze den Anmeldestatus im Service
    this.userObserv.updateLoginRequest(isLoggedIn);

    // Wenn angemeldet, lade den Benutzer aus dem lokalen Speicher
    if (isLoggedIn) {
      const currentUserString = localStorage.getItem('currentUser');
      if (currentUserString) {
        const currentUser: UserDto = JSON.parse(currentUserString);
        this.userObserv.updateSharedData(
          currentUser.username,
          currentUser.name,
          currentUser.email,
          currentUser.password,
          currentUser.profilePic,
          currentUser.id,
          currentUser.role
        );

        console.log(currentUser.username, currentUser.name, currentUser.email, currentUser.password, currentUser.profilePic, currentUser.id, currentUser.role);
      }
    }
  }
  }
}
