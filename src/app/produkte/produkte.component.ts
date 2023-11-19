import { Component, Input } from '@angular/core';


@Component({
  selector: 'app-produkte',
  templateUrl: './produkte.component.html',
  styleUrls: ['./produkte.component.scss'],
})

export class ProdukteComponent {
  @Input() user:string ='';
  @Input() product:string ='';
  @Input() price:number =0;
  @Input() image:string ='';
  constructor() {}
}
