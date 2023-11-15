import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductapiService } from '../productapi.service';
import { AnzeigeDto } from 'src/models/anzeige.models';

@Component({
  selector: 'app-createproduct',
  templateUrl: './createproduct.component.html',
  styleUrls: ['./createproduct.component.scss']
})
export class CreateproductComponent {
  account: boolean = false;
  productform: FormGroup;

  constructor(
    private dialog: MatDialog,
    private formBuilder: FormBuilder,
    private prodser: ProductapiService
  ) {
    this.productform = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      image: ['', Validators.required],
      price: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
    });
  }

  openDialogLogin() {
    this.dialog.open(LoginDialogComponent);
  }

  openDialogRegister() {
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
