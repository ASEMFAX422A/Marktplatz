import { Component } from '@angular/core';
import { AnzeigeDto } from 'src/models/anzeige.models';
import { ProductapiService } from '../productapi.service';

@Component({
  selector: 'app-personalproducts',
  templateUrl: './personalproducts.component.html',
  styleUrls: ['./personalproducts.component.scss']
})
export class PersonalproductsComponent {
  anzeigen: AnzeigeDto[] = []


  constructor (private productService:ProductapiService){}

  ngOnInit(): void {
    this.productService.getAllAnzeigeByUser().subscribe(data => {
      this.anzeigen = data;
      console.log(this.anzeigen);
      console.log(localStorage.getItem('user_id'));
    })}
}
