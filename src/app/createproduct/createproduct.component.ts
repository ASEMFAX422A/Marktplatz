import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-createproduct',
  templateUrl: './createproduct.component.html',
  styleUrls: ['./createproduct.component.scss']
})
export class CreateproductComponent {
  account: boolean = false;
  productform: FormGroup;

  constructor(private dialog:MatDialog, private formBuilder: FormBuilder) {
    this.productform = this.formBuilder.group({
      product: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
    })
  }

  openDialogLogin() {
    this.dialog.open(LoginDialogComponent)
  }

  openDialogRegister() {
    this.dialog.open(RegisterDialogComponent)
  }

  closeDialog() {
    this.dialog.closeAll()
  }
}
