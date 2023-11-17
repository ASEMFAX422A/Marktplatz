import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { UserapiService } from '../userapi.service';
import { UserDto } from 'src/models/login.modules';


@Component({
  selector: 'app-register-dialog',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.scss']
})
export class RegisterDialogComponent {
  isPasswordVisiblePassword: boolean = false;
  isPasswordVisiblePasswordcnfm: boolean = false;
  isSubmitCheckTrue: boolean = false;
  isAllrequired: boolean = false;
  registerForm: FormGroup;



  constructor(private matDialog:MatDialog, private formBuilder: FormBuilder, private prodser: UserapiService) {
    this.registerForm = this.formBuilder.group({
      fullName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    });
  }
  closeDialog(){
    this.matDialog.closeAll()
  }

  togglePasswordVisibilityPassword(): void {
    this.isPasswordVisiblePassword = !this.isPasswordVisiblePassword;
  }
  togglePasswordVisibilityPasswordCnfm(): void {
    this.isPasswordVisiblePasswordcnfm = !this.isPasswordVisiblePasswordcnfm;
  }

  submitcheck():void {
    this.isAllrequired = true;
  }

  openDialogLogin() {
    this.matDialog.closeAll()
    this.matDialog.open(LoginDialogComponent)
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const offerData: UserDto = this.registerForm.value;
      this.prodser.addUser(offerData).subscribe(
        (response) => {
          console.log('Anzeige erfolgreich hinzugefügt:', response);
          this.registerForm.reset();
        },
        (error) => {
          console.error('Fehler beim Hinzufügen der Anzeige:', error);
        }
      );
    }
  }

}
