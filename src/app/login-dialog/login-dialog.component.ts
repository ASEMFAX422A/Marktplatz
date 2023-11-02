import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent {
  password: string = '';
  isPasswordVisiblePassword: boolean = false;

  togglePasswordVisibilityPassword(): void {
    this.isPasswordVisiblePassword = !this.isPasswordVisiblePassword;
  }

  constructor(private matDialog:MatDialog) {}
  closeDialog(){
    this.matDialog.closeAll()
  }
}
