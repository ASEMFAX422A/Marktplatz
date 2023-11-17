import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import { transition } from '@angular/animations';
import { UserDto } from 'src/models/login.modules';
import { UserapiService } from '../userapi.service';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent{
  isPasswordVisiblePassword: boolean = false;
  registerForm: FormGroup;

  constructor(private matDialog:MatDialog, private formBuilder: FormBuilder, private prodser: UserapiService) {
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
      this.prodser.getLogin(offerData).subscribe(
        (response: boolean) => {
          this.prodser.updateLoginRequest(response);
          console.log('Anzeige erfolgreich hinzugefügt:', response);
          this.closeDialog();
        },
        (error) => {
          console.error('Fehler beim Hinzufügen der Anzeige:', error);
        }
      );
    }
  }
}
