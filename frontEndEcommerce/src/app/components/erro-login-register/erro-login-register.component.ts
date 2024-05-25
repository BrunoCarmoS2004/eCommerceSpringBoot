import { Component } from '@angular/core';
import { MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
@Component({
  selector: 'app-erro-login-register',
  standalone: true,
  imports: [MatButtonModule, MatDialogTitle, MatDialogContent, MatDialogActions, MatDialogClose, MatButtonModule],
  templateUrl: './erro-login-register.component.html',
  styleUrl: './erro-login-register.component.scss'
})
export class ErroLoginRegisterComponent {
  constructor(public dialogRef: MatDialogRef<ErroLoginRegisterComponent>,){}
  fechar(){
    this.dialogRef.close()
  }
}
