import { Component, Output, EventEmitter } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import { transition } from '@angular/animations';
import { UserDto } from 'src/models/login.modules';
import { UserapiService } from '../userapi.service';
import { BehaviorSubject } from 'rxjs';



@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent{
  @Output() dataEvent = new EventEmitter<string>();
  isPasswordVisiblePassword: boolean = false;
  registerForm: FormGroup;
  public username: string ="";


  constructor(private matDialog:MatDialog, private formBuilder: FormBuilder, private userObserv: UserapiService) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });

  }

  togglePasswordVisibilityPassword(): void {
    this.isPasswordVisiblePassword = !this.isPasswordVisiblePassword;
  }

  closeDialog(){
    this.matDialog.closeAll()
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
          this.username = offerData.username
          this.userObserv.updateSharedData(this.username);
          this.userObserv.updateLoginRequest(response);
          console.log('Anzeige erfolgreich hinzugefügt:', response, this.username);
          this.closeDialog();
        },
        (error) => {
          console.error('Fehler beim Hinzufügen der Anzeige:', error);
        }
      );
    }
  }
}
