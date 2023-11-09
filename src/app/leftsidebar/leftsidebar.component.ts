import { Component } from '@angular/core';
import { CreateproductComponent } from '../createproduct/createproduct.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-leftsidebar',
  templateUrl: './leftsidebar.component.html',
  styleUrls: ['./leftsidebar.component.scss']
})
export class LeftsidebarComponent {
  showFiller = false;

  constructor (private dialog:MatDialog) {}

  openDialogcreateProduct(){
    this.dialog.open(CreateproductComponent)
  }
}
