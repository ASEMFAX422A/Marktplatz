import { Component, Input } from '@angular/core';
import { ProductService } from '../product.service';


@Component({
  selector: 'app-produkte',
  templateUrl: './produkte.component.html',
  styleUrls: ['./produkte.component.scss'],
})

export class ProdukteComponent {
  @Input() user:string ='';
  @Input() product:string ='';
  @Input() price:string ='';
  @Input() image:string ='';
  constructor(public pd: ProductService) {}
}
