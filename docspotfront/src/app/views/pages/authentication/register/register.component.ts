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
import { MatCheckboxModule } from '@angular/material/checkbox';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatChipsModule } from '@angular/material/chips';
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
    MatCheckboxModule,
    MatIconModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatSelectModule,
    MatOptionModule,
    MatChipsModule
  ],
  providers: [],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent implements OnInit {
  specDefault: string[] = [
    'Pediatría',
    'Cardiología',
    'Dermatología',
    'Oftalmología',
    'Oncología',
    'Neurología',
    'Ginecología',
    'Urología',
    'Otorrinolaringología',
    'Endocrinología',
  ];

  public selectedRol = Rol;

  public rol: string = '';
  public register!: FormGroup;
  public showPassword: boolean = false;
  public loading: boolean = false;
  public errorMessage: string = '';

  public showForm: string = '';
  public showTouched: boolean = false;

  public hasWorkAdded: boolean = false;
  public specAvailable: string[] = [];
  public specSelect: string[] = [];

  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.getEspecialidades();
    this.initializedForm();
  }

  private getEspecialidades(): void {
    // llamar a la api en busca de especialidades
    // cargar los objetos
    // fallo cargar de forma manual
    this.specAvailable = [
      'Pediatría',
      'Cardiología',
      'Dermatología',
      'Oftalmología',
      'Oncología',
      'Neurología',
      'Ginecología',
      'Urología',
      'Otorrinolaringología',
      'Endocrinología',
    ];
  }

  private initializedForm(): void {
    this.register = this.fb.group({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
      rol: new FormControl('', Validators.required),
      name: new FormControl('', Validators.required),
    });
  }

  public selectPacient(): void {
    this.showTouched = true;
    this.toggleExtraControls(this.selectedRol.patient);
    this.showForm = this.selectedRol.patient;
    this.register.get('rol')?.patchValue(this.selectedRol.patient);
  }

  public selectProfessional(): void {
    this.showTouched = true;
    this.toggleExtraControls(this.selectedRol.professional);
    this.showForm = this.selectedRol.professional;
    this.register.get('rol')?.patchValue(this.selectedRol.professional);
  }

  /* intercambio de formulario por button */
  public toggleExtraControls(role: string): void {
    // this.initializedForm();

    if (role == this.selectedRol.professional) {
      this.addControlsProfessional(this.register);
      this.removeControlsPatient(this.register);
    } else if (role == this.selectedRol.patient) {
      this.addControlsPatient(this.register);
      this.removeControlsProfessional(this.register);
      this.listenCheckBox(this.register);
    }
  }

  /* Escuchar cambios en el checkbox */
  private listenCheckBox(formG: FormGroup): void {
    formG.get('hasWork')?.valueChanges.subscribe((hasSocialWork) => {
      if (hasSocialWork) {
        formG.get('socialWork')?.enable();
      } else {
        formG.get('socialWork')?.disable();
        formG.get('socialWork')?.setValue('');
        formG.get('socialWork')?.markAsUntouched();
      }
    });
  }

  /* Cambio de formulario Profesional */
  private addControlsProfessional(formG: FormGroup): void {
    formG.addControl('mp', new FormControl('', Validators.required));
    formG.addControl('especialidad', new FormControl(this.specSelect, Validators.required));
  }

  private removeControlsPatient(formG: FormGroup): void {
    this.hasWorkAdded = false;
    formG.removeControl('cellphone');
    formG.removeControl('photo');
    formG.removeControl('hasWork');
    formG.removeControl('socialWork');
  }
  /* FIN - Cambio de formulario Profesional */


  /* Cambio de formulario Paciente */
  private addControlsPatient(formG: FormGroup): void {
    formG.addControl('cellphone', new FormControl('', Validators.required));
    formG.addControl('photo', new FormControl('', Validators.required));
    formG.addControl(
      'hasWork',
      new FormControl({ value: false, disabled: false }, Validators.required)
    );
    formG.addControl(
      'socialWork',
      new FormControl({ value: '', disabled: true }, Validators.required)
    );
    this.hasWorkAdded = true;
  }

  private removeControlsProfessional(formG: FormGroup): void {
    formG.removeControl('mp');
    formG.removeControl('especialidad');
  }
  /* FIN - Cambio de formulario Paciente */


  public addSpecialty(especialidad: string): void {
    if (especialidad && !this.specSelect.includes(especialidad)) {
      this.specSelect.push(especialidad);
      this.DeleteSpecialtyAvailable(especialidad);
    }
  }

  public DeleteSpecialty(especialidad: string): void {
    const index = this.specSelect.indexOf(especialidad);
    if (index !== -1) {
      this.specSelect.splice(index, 1);
      this.addSpecialtyAvailable(especialidad);
    }
  }

  public DeleteSpecialtyAvailable(especialidad: string): void {
    const index = this.specAvailable.indexOf(especialidad);
    if (index !== -1) {
      this.specAvailable.splice(index, 1);
    }
  }

  public addSpecialtyAvailable(especialidad: string): void {
    this.specAvailable.push(especialidad);
  }
  


  public send(): void {
    console.log('Form:', this.register.value);
    if (this.showForm == this.selectedRol.patient) {
      // pasar data a un objeto para crear paciente
      // llamar a la API
      // controlar respuesta
      //  -- exitoso -> volver a login
      //  -- falla -> mostrar modal
    } else if (this.showForm == this.selectedRol.professional) {
      // pasar data a un objeto para crear professional
      // llamar a la API
      // controlar respuesta
      //  -- exitoso -> volver a login
      //  -- falla -> mostrar modal
    }

    console.log('Enviar formulario');
  }
}
