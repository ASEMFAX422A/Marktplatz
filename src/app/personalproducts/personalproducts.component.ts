import { Component } from '@angular/core';

@Component({
  selector: 'app-personalproducts',
  templateUrl: './personalproducts.component.html',
  styleUrls: ['./personalproducts.component.scss']
})
export class PersonalproductsComponent {
  username = ['Oliver','Zaid','Besmir ', 'Oliver','Zaid','Besmir ']
  postProduct = ['Fernseher', 'Fernseher', 'Fernseher', 'Fernseher', 'Fernseher', 'Fernseher']
  postPrice = ['300€', '290€', '320€', '300€', '290€', '320€']
  postImage = []
}
