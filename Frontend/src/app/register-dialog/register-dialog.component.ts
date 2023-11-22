import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { UserapiService } from '../userapi.service';
import { UserDto } from 'src/models/login.modules';
import { ToastrService } from 'ngx-toastr';


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

  constructor(private matDialog:MatDialog, private formBuilder: FormBuilder, private prodser: UserapiService, private toastr: ToastrService) {
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      profilePic: [''],
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
          this.toastr.success("Register was successful","", {positionClass: 'toast-top-center',})
          console.log('Anzeige erfolgreich hinzugefügt:', response);
          this.registerForm.reset();
          this.closeDialog();
          this.openDialogLogin();
        },
        (error) => {
          this.toastr.error("Register failed","", {positionClass: 'toast-top-center',})
          console.error('Fehler beim Hinzufügen der Anzeige:', error);
        }
      );
    }
  }

}
