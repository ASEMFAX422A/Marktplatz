import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductapiService } from '../productapi.service';
import { AnzeigeDto } from 'src/models/anzeige.models';
import { UserapiService } from '../userapi.service';
import { UserDto } from 'src/models/login.modules';

@Component({
  selector: 'app-createproduct',
  templateUrl: './createproduct.component.html',
  styleUrls: ['./createproduct.component.scss']
})
export class CreateproductComponent {
  productform: FormGroup;
  anmeldungboolean: boolean = false;
  username: string ="";
  name: string = "";
  email: string = "";
  password: string = "";
  profilePic: string = "";

  constructor(private dialog: MatDialog,private formBuilder: FormBuilder,private prodser: ProductapiService, private userObserver: UserapiService) {
    this.productform = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      image: ['', Validators.required],
      price: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
    });
  }

  ngOnInit() {
    this.userObserver.loginRequest$.subscribe((status) => {
      this.anmeldungboolean = status;
    });

    this.userObserver.loginRequest$.subscribe((status) => {
      this.anmeldungboolean = status;
    });

    this.userObserver.username$.subscribe((username) => {
      this.username = username;
    });

    this.userObserver.name$.subscribe((name) => {
      this.name = name;
    });

    this.userObserver.email$.subscribe((email) => {
      this.email = email;
    });

    this.userObserver.password$.subscribe((password) => {
      this.password = password;
    });

    this.userObserver.profilePic$.subscribe((profilePic) => {
      this.profilePic = profilePic;
    });
  }


  openDialogLogin() {
    this.dialog.closeAll();
    this.dialog.open(LoginDialogComponent);
  }

  openDialogRegister() {
    this.dialog.closeAll();
    this.dialog.open(RegisterDialogComponent);
  }

  closeDialog() {
    this.dialog.closeAll();
  }

  onSubmit() {
    if (this.productform.valid) {
      const offerData: AnzeigeDto = this.productform.value;
      this.prodser.addAnzeige(offerData).subscribe(
        (response) => {
          console.log('Anzeige erfolgreich hinzugefügt:', response);
          this.productform.reset();
        },
        (error) => {
          console.error('Fehler beim Hinzufügen der Anzeige:', error);
        }
      );
    }
  }
}
