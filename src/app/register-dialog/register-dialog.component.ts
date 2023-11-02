import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-register-dialog',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.scss']
})
export class RegisterDialogComponent {
  password: string = '';
  isPasswordVisiblePassword: boolean = false;
  isPasswordVisiblePasswordcnfm: boolean = false;


  togglePasswordVisibilityPassword(): void {
    this.isPasswordVisiblePassword = !this.isPasswordVisiblePassword;
  }
  togglePasswordVisibilityPasswordCnfm(): void {
    this.isPasswordVisiblePasswordcnfm = !this.isPasswordVisiblePasswordcnfm;
  }

  constructor(private matDialog:MatDialog) {}
  closeDialog(){
    this.matDialog.closeAll()
  }
}
