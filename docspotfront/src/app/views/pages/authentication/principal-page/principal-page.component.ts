import { Component } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-principal-page',
  standalone: true,
  imports: [
    CommonModule,
    LoginComponent,
    RegisterComponent
  ],
  templateUrl: './principal-page.component.html',
  styleUrl: './principal-page.component.scss'
})
export class PrincipalPageComponent {
  isLogin = true; // Estado inicial

  toggleForm() {
    this.isLogin = !this.isLogin;
  }
}
