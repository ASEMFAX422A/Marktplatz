import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FlexLayoutModule } from '@angular/flex-layout';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { ProdukteComponent } from './produkte/produkte.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { LeftsidebarComponent } from './leftsidebar/leftsidebar.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSidenavModule } from '@angular/material/sidenav';
import { NgIf, NgFor } from '@angular/common';
import { FooterComponent } from './footer/footer.component';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatDialogModule } from '@angular/material/dialog';
import { LoginDialogComponent } from './login-dialog/login-dialog.component';
import { RegisterDialogComponent } from './register-dialog/register-dialog.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CreateproductComponent } from './createproduct/createproduct.component';
import { HttpClientModule } from '@angular/common/http';
import { MessagesComponent } from './messages/messages.component';
import { PersonalproductsComponent } from './personalproducts/personalproducts.component';
import { AccountsettingsComponent } from './accountsettings/accountsettings.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ProdukteComponent,
    LeftsidebarComponent,
    FooterComponent,
    LoginDialogComponent,
    RegisterDialogComponent,
    CreateproductComponent,
    MessagesComponent,
    PersonalproductsComponent,
    AccountsettingsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatCheckboxModule,
    MatSidenavModule,
    NgIf,
    NgFor,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatGridListModule,
    MatDialogModule,
    ReactiveFormsModule,
    HttpClientModule,
    FlexLayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

