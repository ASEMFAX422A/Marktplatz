import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';

@Component({
  selector: 'app-createproduct',
  templateUrl: './createproduct.component.html',
  styleUrls: ['./createproduct.component.scss']
})
export class CreateproductComponent {
  account: boolean = false;

  constructor(private dialog:MatDialog) {}

  openDialogLogin() {
    this.dialog.open(LoginDialogComponent)
  }

  openDialogRegister() {
    this.dialog.open(RegisterDialogComponent)
  }
}
