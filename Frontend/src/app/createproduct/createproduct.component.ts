import { Component, ViewChild, ElementRef } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductapiService } from '../productapi.service';
import { AnzeigeDto } from 'src/models/anzeige.models';
import { UserapiService } from '../userapi.service';
import { UserDto } from 'src/models/login.modules';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-createproduct',
  templateUrl: './createproduct.component.html',
  styleUrls: ['./createproduct.component.scss'],
})
export class CreateproductComponent {
  @ViewChild('fileInput') fileInput: ElementRef | undefined;
  productform: FormGroup;
  anmeldungboolean: boolean = false;
  username: string = '';
  name: string = '';
  email: string = '';
  password: string = '';
  profilePic: string = '';
  url: any = 'assets/img/products/Product.png';

  constructor(
    private dialog: MatDialog,
    private formBuilder: FormBuilder,
    private prodser: ProductapiService,
    private userObserver: UserapiService,
    private toastr: ToastrService
  ) {
    this.productform = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      image: ['', Validators.required],
      preis: ['',[Validators.required, Validators.pattern('^[0-9]+(.[0-9]{1,2})?$')],],
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

  base64EncodeImage(file: File): Promise<string> {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onloadend = () => {
        const base64String = (reader.result as string).split(',')[1];
        resolve(base64String);
      };
      reader.onerror = (error) => {
        reject(error);
      };
    });
  }

  async onSubmit() {
    if (!this.anmeldungboolean && !this.productform.valid) {
      this.toastr.error("You are not logged in and your input is invalid","", {positionClass: 'toast-top-center',});
    }
    if (!this.anmeldungboolean && this.productform.valid) {
      this.toastr.error("You are not logged in","", {positionClass: 'toast-top-center',});
    }
    if (this.anmeldungboolean && !this.productform.valid ) {
      this.toastr.error("Your input is invalid","", {positionClass: 'toast-top-center',});
    }

    if (this.productform.valid && this.anmeldungboolean) {
      const offerData: AnzeigeDto = this.productform.value;

      try {
        offerData.image = await this.base64EncodeImage(
          this.productform.get('image')!.value
        );
        console.log(offerData);
        this.prodser.addAnzeige(offerData).subscribe(
          async (response) => {

            this.toastr.success
            console.log('Anzeige erfolgreich hinzugefügt:', response);
            this.toastr.success("Create Product was successful","", {positionClass: 'toast-top-center',})
            this.closeDialog();
          },
          (error) => {
            console.error('Fehler beim Hinzufügen der Anzeige:', error);
            this.toastr.error("Create Product failed","", {positionClass: 'toast-top-center',})
          }
        );
        await this.sleep(5000);
        this.prodser.getAnzeigeByName(offerData.name).subscribe(
          (response) => {
            console.log('Anzeige nach Namen abgerufen:', response);
            response.image = 'data:image/jpg;base64,' + response.image;
            this.url =  response.image;
          },
          (error) => {
            console.error('Fehler beim Abrufen der Anzeige nach Namen:', error);
          }
        );
        this.productform.reset();
      } catch (error) {
        console.error('Fehler beim Base64-Kodieren des Bildes:', error);
      }
    }
  }

  // Sleep-Funktion
  sleep(ms: number): Promise<void> {
    return new Promise<void>((resolve) => setTimeout(resolve, ms));
  }

  openFileSelection() {
      const fileInput = document.createElement('input');
      fileInput.type = 'file';
      fileInput.addEventListener('change', (event) => {
        const inputElement = event.target as HTMLInputElement;
        const files = inputElement.files;

        if (files && files.length > 0) {
          const selectedFile = files[0];
          console.log('Selected file:', selectedFile);
        }
      });
      fileInput.click();
    }


  onFileChange(event: any) {
    const file: any = (event.target as HTMLInputElement).files![0];
    this.url = URL.createObjectURL(file);
    this.productform.get('image')!.setValue(file);
  }

}
