import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';



@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatIconModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {

  public loginForm!: FormGroup;
  public showPassword: boolean = false;
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

    // redireccion

    // error message
  }
}
