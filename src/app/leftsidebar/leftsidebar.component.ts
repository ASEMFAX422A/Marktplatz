import { Component } from '@angular/core';
import { CreateproductComponent } from '../createproduct/createproduct.component';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { bootstrapApplication } from '@angular/platform-browser';
import { MessagesService } from '../messages.service';

@Component({
  selector: 'app-leftsidebar',
  templateUrl: './leftsidebar.component.html',
  styleUrls: ['./leftsidebar.component.scss']
})
export class LeftsidebarComponent {
  constructor (private dialog:MatDialog,private mService: MessagesService) {}

  openDialogcreateProduct(){
    this.dialog.open(CreateproductComponent);
  }
  setMessagesTrue() {
    this.mService.setMessageStatus(true);
  }
}
