import { Injectable } from '@angular/core';
import { ProdukteComponent } from './produkte/produkte.component';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  username = ['Oliver','Zaid','Besmir ']
  postProduct = ['Fernseher', 'Fernseher', 'Fernseher']
  postPrice = ['300€', '290€', '320€']


  constructor() { }
}
