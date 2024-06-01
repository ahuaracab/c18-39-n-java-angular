import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { userLogin } from 'src/app/models/authentication-models/login.models';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { AuthV1Service } from 'src/app/services/service-authentication/auth-v1.service';
import {
  HttpClientModule,
  HttpErrorResponse,
  HttpResponse,
} from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatProgressSpinnerModule,
    HttpClientModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  providers: [
    AuthV1Service
  ]
})
export class LoginComponent implements OnInit {
  public loginForm!: FormGroup;
  public showPassword: boolean = false;
  public loading: boolean = false;
  public errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthV1Service
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  // Método para alternar el valor de showPassword
  public togglePasswordVisibility(): void {
    console.log(this.showPassword);
    this.showPassword = !this.showPassword;
  }

  public navRegister(): void {
    event?.preventDefault();
    this.router.navigate(['/auth/register']);
  }

  public login(): void {
    console.log('Ruta absoluta:', this.router.url);
    if (this.loginForm.invalid) {
      this.errorMessage = 'Por favor, complete el formulario correctamente.';
      return;
    } else {
      this.errorMessage = '';
    }

    const userData: userLogin = this.loginForm.value;

    console.log(`email: ${userData.email}\npassword: ${userData.password}`);

    // animacion de carga en button
    this.loading = true;

    // enviar a servicio
    this.authService.login(userData).subscribe({
      next: (response: HttpResponse<any>) => {
        this.loading = false;
        // guardar data del usuario en localStorage
        // navegar a la vista segun tipo de rol
      },
      error: (error: HttpErrorResponse) => {
        this.loading = false;
        console.log("error:",error);
        // dependiendo del error
        this.errorMessage = 'correo o contraseña incorrecta';
      },
    });
  }
}
