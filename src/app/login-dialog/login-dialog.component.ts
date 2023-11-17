import { Component, Output, EventEmitter } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import { transition } from '@angular/animations';
import { UserDto } from 'src/models/login.modules';
import { UserapiService } from '../userapi.service';
import { BehaviorSubject } from 'rxjs';
import { Dialog } from '@angular/cdk/dialog';
import { CreateproductComponent } from '../createproduct/createproduct.component';



@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent{
  @Output() dataEvent = new EventEmitter<string>();
  isPasswordVisiblePassword: boolean = false;
  registerForm: FormGroup;




  constructor(private matDialog:MatDialog, private formBuilder: FormBuilder, private userObserv: UserapiService, private dialogref: MatDialogRef<CreateproductComponent>) {
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
      this.userObserv.getLogin(offerData).subscribe(
        (response: boolean) => {
          this.userObserv.updateLoginRequest(response);
          console.log('Anzeige erfolgreich hinzugefügt:', response);
          this.closeDialog();
        },
        (error) => {
          console.error('Fehler beim Hinzufügen der Anzeige:', error);
        }
      );
      this.userObserv.getUserByUsername(offerData).subscribe(
        (response: UserDto) => {
          this.userObserv.updateSharedData(response.username, response.name, response.email, response.password, response.profilePic, response.id, response.role);
          console.log(response.username, response.name, response.email, response.password, response.profilePic, response.id, response.role)
        }
      );
    }
  }
}
