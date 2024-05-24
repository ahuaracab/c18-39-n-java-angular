import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import {
  FormControl,
  Validators,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

enum Rol {
  professional = 'professional',
  patient = 'patient',
}

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatButtonModule,
  ],
  providers: [],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent implements OnInit {
  public selectedRol = Rol;

  public rol: string = '';
  public register!: FormGroup;
  public showPassword: boolean = false;
  public loading: boolean = false;
  public errorMessage: string = '';

  public showForm: string = '';
  public showTouched: boolean = false;

  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.initializedForm();
  }

  private initializedForm(): void {
    this.register = this.fb.group({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
      rol: new FormControl('', Validators.required),
      name: new FormControl('', Validators.required),
    });

    if (this.showForm == this.selectedRol.professional) {
      this.register.addControl('mp', new FormControl('', Validators.required));
      this.register.addControl(
        'especialidad',
        new FormControl('', Validators.required)
      );
    } else if (this.showForm == this.selectedRol.professional){
      this.register.addControl(
        'cellphone',
        new FormControl('', Validators.required)
      );
      this.register.addControl(
        'photo',
        new FormControl('', Validators.required)
      );
      this.register.addControl(
        'socialWork',
        new FormControl('', Validators.required)
      );
    }
  }

  public selectPacient(): void {
    this.showTouched = true;
    this.toggleExtraControls(this.selectedRol.patient);
    this.showForm = this.selectedRol.patient;
  }

  public selectProfessional(): void {
    this.showTouched = true;
    this.toggleExtraControls(this.selectedRol.professional);
    this.showForm = this.selectedRol.professional;
  }

  public toggleExtraControls(role:string): void {
    // this.initializedForm();

    if (role == this.selectedRol.professional) {
      this.register.addControl('mp', new FormControl('', Validators.required));
      this.register.addControl(
        'especialidad',
        new FormControl('', Validators.required)
      );

      this.register.removeControl('cellphone');
      this.register.removeControl('photo');
      this.register.removeControl('socialWork');

    } else if (role == this.selectedRol.patient) {
      this.register.addControl(
        'cellphone',
        new FormControl('', Validators.required)
      );
      this.register.addControl(
        'photo',
        new FormControl('', Validators.required)
      );
      this.register.addControl(
        'socialWork',
        new FormControl('', Validators.required)
      );

      this.register.removeControl('mp');
      this.register.removeControl('especialidad');
    }
  }

  public send(): void {
    console.log('Enviar formulario');
  }
}
