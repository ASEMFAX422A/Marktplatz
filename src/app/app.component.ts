import { Component } from '@angular/core';
import { MessagesService } from './messages.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  constructor (mServ: MessagesService) {}
  username = ['Oliver','Zaid','Besmir ']
  postProduct = ['Fernseher', 'Fernseher', 'Fernseher']
  postPrice = ['300€', '290€', '320€']

  title = 'EbayTest';
}
