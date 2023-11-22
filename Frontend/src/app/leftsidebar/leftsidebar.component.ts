import { Component } from '@angular/core';
import { CreateproductComponent } from '../createproduct/createproduct.component';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { bootstrapApplication } from '@angular/platform-browser';
import { SidebarstatusService } from '../sidebarstatus.service';

@Component({
  selector: 'app-leftsidebar',
  templateUrl: './leftsidebar.component.html',
  styleUrls: ['./leftsidebar.component.scss']
})
export class LeftsidebarComponent {
  constructor (private dialog:MatDialog,private sidebarServ: SidebarstatusService) {}

  openDialogcreateProduct(){
    this.dialog.open(CreateproductComponent);
  }
  setMessagesTrue() {
    this.sidebarServ.setMessageStatus(true);
    this.sidebarServ.setProductStatus(false);
    this.sidebarServ.setAccountStatus(false);
    this.sidebarServ.setHompageStatus(false);
  }
  setProductsTrue() {
    this.sidebarServ.setProductStatus(true);
    this.sidebarServ.setMessageStatus(false);
    this.sidebarServ.setAccountStatus(false);
    this.sidebarServ.setHompageStatus(false);
  }
  setAccountTrue() {
    this.sidebarServ.setAccountStatus(true);
    this.sidebarServ.setProductStatus(false);
    this.sidebarServ.setMessageStatus(false);
    this.sidebarServ.setHompageStatus(false);
  }
  setHomepageTrue() {
    this.sidebarServ.setHompageStatus(true);
    this.sidebarServ.setAccountStatus(false);
    this.sidebarServ.setProductStatus(false);
    this.sidebarServ.setMessageStatus(false);
  }
}
