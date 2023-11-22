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
    this.productService.getAllAnzeigen().subscribe(data => {
      this.anzeigen = data;
    })}
}
