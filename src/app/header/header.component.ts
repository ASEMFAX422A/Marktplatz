import { Component } from '@angular/core';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  constructor(private matDialog:MatDialog) {}
  openDialogRegister(){
    this.matDialog.open(RegisterDialogComponent,{
    })
  }
  closeDialog(){
    this.matDialog.closeAll()
  }

  openDialogLogin(){
    this.matDialog.open(LoginDialogComponent,{
    })
  }
}
