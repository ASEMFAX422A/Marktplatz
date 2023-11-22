import { Component } from '@angular/core';
import { UserapiService } from '../userapi.service';
import { UserDto } from 'src/models/login.modules';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-accountsettings',
  templateUrl: './accountsettings.component.html',
  styleUrls: ['./accountsettings.component.scss'],
})
export class AccountsettingsComponent {
  isPasswordVisiblePassword: boolean = false;
  anmeldungboolean: boolean = false;
  username: string = '';
  name: string = '';
  email: string = '';
  password: string = '';
  profilePic: string = '';
  role: string = '';
  id: number = 0;

  constructor(private userObserver: UserapiService, private router:Router) {}

  ngOnInit() {
    this.userObserver.loginRequest$.subscribe((status) => {
      this.anmeldungboolean = status;
    });

    this.userObserver.username$.subscribe((value) => {
      this.username = value;
    });
    this.userObserver.email$.subscribe((value) => {
      this.email = value;
    });
    this.userObserver.id$.subscribe((value) => {
      this.id = value;
    });
    this.userObserver.password$.subscribe((value) => {
      this.password = value;
    });
    this.userObserver.name$.subscribe((value) => {
      this.name = value;
    });
    this.userObserver.role$.subscribe((value) => {
      this.role = value;
    });
    this.userObserver.profilePic$.subscribe((value) => {
      this.profilePic = value;
    });

  }

  logout(): void {
    this.userObserver.updateLoginRequest(false);
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('currentUser');
  }

  togglePasswordVisibilityPassword(): void {
    this.isPasswordVisiblePassword = !this.isPasswordVisiblePassword;
  }
}
