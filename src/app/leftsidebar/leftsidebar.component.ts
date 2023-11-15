import { Component } from '@angular/core';
import { CreateproductComponent } from '../createproduct/createproduct.component';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';

@Component({
  selector: 'app-leftsidebar',
  templateUrl: './leftsidebar.component.html',
  styleUrls: ['./leftsidebar.component.scss']
})
export class LeftsidebarComponent {

  constructor (private dialog:MatDialog) {}

  openDialogcreateProduct(){
    this.dialog.open(CreateproductComponent)
  }
}
