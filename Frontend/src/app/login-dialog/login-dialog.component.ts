import { Component, Output, EventEmitter } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import { transition } from '@angular/animations';
import { UserDto } from 'src/models/login.modules';
import { UserapiService } from '../userapi.service';
import {BehaviorSubject, of} from 'rxjs';
import { Dialog } from '@angular/cdk/dialog';
import { CreateproductComponent } from '../createproduct/createproduct.component';
import { ToastrService } from 'ngx-toastr';



@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent{
  @Output() dataEvent = new EventEmitter<string>();
  isPasswordVisiblePassword: boolean = false;
  registerForm: FormGroup;


  constructor(private matDialog:MatDialog, private formBuilder: FormBuilder, private userObserv: UserapiService, private dialogref: MatDialogRef<CreateproductComponent>, private toastr: ToastrService) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });

  }

  togglePasswordVisibilityPassword(): void {
    this.isPasswordVisiblePassword = !this.isPasswordVisiblePassword;
  }

  closeDialog() {
    this.matDialog.closeAll();
  }


  openDialogRegister() {
    this.matDialog.closeAll()
    this.matDialog.open(RegisterDialogComponent)
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const offerData: UserDto = this.registerForm.value;
      console.log(offerData)
      this.userObserv.getLogin(offerData).subscribe(
        (isLoggedIn: boolean) => {
          this.userObserv.updateLoginRequest(isLoggedIn);
          localStorage.setItem('isLoggedIn', isLoggedIn.toString());
          if (isLoggedIn) {
            this.userObserv.getUserByUsername(offerData).subscribe(
              (response: UserDto) => {
                localStorage.setItem('currentUser', JSON.stringify(response));
                localStorage.setItem('user_id',JSON.stringify(response.id));
                this.userObserv.updateSharedData(
                  response.username,
                  response.name,
                  response.email,
                  response.password,
                  response.profilePic,
                  response.id,
                  response.role
                );

                console.log(response.username, response.name, response.email, response.password, response.profilePic, response.id, response.role);
              }
            );
          }

          if (isLoggedIn) {
            this.toastr.success("Login was successful", "", { positionClass: 'toast-top-center' });
          } else {
            this.toastr.error("Login failed", "", { positionClass: 'toast-top-center' });
          }

          this.closeDialog();
        },
        (error) => {
          this.toastr.error("Login failed", "", { positionClass: 'toast-top-center' });
          this.closeDialog();
          console.error('Error during login:', error);
        }
      );
    }
  }
}
