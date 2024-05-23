import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { userLogin } from 'src/app/models/authentication-models/login.models';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatIconModule, MatProgressSpinnerModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {

  public loginForm!: FormGroup;
  public showPassword: boolean = false;
  public loading: boolean = false;
  public errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
  ){
  }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  // Método para alternar el valor de showPassword
  togglePasswordVisibility(): void {
    console.log(this.showPassword);
    this.showPassword = !this.showPassword;
  }

  login(): void {
    console.log('Ruta absoluta:', this.router.url);
    if (this.loginForm.invalid) {
      this.errorMessage = 'Por favor, complete el formulario correctamente.';
      return;
    } else {
      this.errorMessage = '';
    }

    const userData:userLogin = this.loginForm.value;

    console.log(`email: ${userData.email}\npassword: ${userData.password}`);
    // enviar a servicio

    // animacion de carga en button
    this.loading = true;

    setTimeout(()=>{
      this.loading=false;
    },5000);

    
    // redireccion

    // error message
  }
}
