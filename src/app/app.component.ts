import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  username = ['Oliver','Zaid','Besmir ']
  postProduct = ['Fernseher', 'Fernseher', 'Fernseher']
  postPrice = ['300€', '290€', '320€']

  title = 'EbayTest';
}
