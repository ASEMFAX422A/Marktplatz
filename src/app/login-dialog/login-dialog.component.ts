import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import { transition } from '@angular/animations';


@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent {
  password: string = '';
  isPasswordVisiblePassword: boolean = false;
  registerForm: FormGroup;



  constructor(private matDialog:MatDialog, private formBuilder: FormBuilder) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }


  togglePasswordVisibilityPassword(): void {
    this.isPasswordVisiblePassword = !this.isPasswordVisiblePassword;
  }

  closeDialog(){
    this.matDialog.closeAll()
  }

  openDialogRegister() {
    this.matDialog.closeAll()
    this.matDialog.open(RegisterDialogComponent)
  }
}
