import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import { SidebarstatusService } from '../sidebarstatus.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
constructor (private mtDialog: MatDialog, sidebarServ: SidebarstatusService){}

openDialogLogin() {
  this.mtDialog.open(LoginDialogComponent)
}
openDialogRegister() {
  this.mtDialog.open(RegisterDialogComponent)
}
setHomepageStatus() {

}
}
