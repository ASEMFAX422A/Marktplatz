import { Component, Input, inject } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { CreateproductComponent } from '../createproduct/createproduct.component';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { bootstrapApplication } from '@angular/platform-browser';
import { SidebarstatusService } from '../sidebarstatus.service';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import { RegisteredMessage } from '@angular/cdk/a11y';
import { UserapiService } from '../userapi.service';

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.scss']
})
export class MainNavComponent {
  private breakpointObserver = inject(BreakpointObserver);
  anmeldungboolean :boolean = false;
  username: string = "";
  name: string = "";
  email: string = "";
  password: string = "";
  profilPic: string = "";

  constructor (private dialog:MatDialog,private sidebarServ: SidebarstatusService, private userobserver: UserapiService) {}

  ngOnInit() {
    this.userobserver.loginRequest$.subscribe((status) => {
      this.anmeldungboolean = status;
    });

    this.userobserver.username$.subscribe((username) => {
      this.username = username;
    });

    this.userobserver.name$.subscribe((name) => {
      this.name = name;
    });

    this.userobserver.email$.subscribe((email) => {
      this.email = email;
    });

    this.userobserver.password$.subscribe((password) => {
      this.password = password;
    });

    this.userobserver.profilePic$.subscribe((profilePic) => {
      this.profilPic = profilePic;
    });
  }

  openDialogcreateProduct(){
    this.dialog.open(CreateproductComponent);

  }
  openDialogLogin() {
    this.dialog.open(LoginDialogComponent);
  }
  openDialogRegister() {
    this.dialog.open(RegisterDialogComponent)
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

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

}
