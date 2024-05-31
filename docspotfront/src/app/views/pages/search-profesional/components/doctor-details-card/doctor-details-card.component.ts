import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, ViewChild } from '@angular/core';

import { CardModule } from 'primeng/card';
import { Rating, RatingModule } from 'primeng/rating';


import { Doctor } from '../../../../../models/search-professional-models/searchProfessional.model';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-doctor-details-card',
  standalone: true,
  imports: [
    CommonModule,
    CardModule,
    RatingModule,
    ReactiveFormsModule
  ],
  templateUrl: './doctor-details-card.component.html',
  styleUrl: './doctor-details-card.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DoctorDetailsCardComponent {

  @ViewChild('rating') rating!: Rating;

  public doctorDetailsForm: FormGroup = this._fb.group({});

  public doctors:Doctor[] = [
    {
      src: 'assets/images/doctor-2.jpg',
      alt: 'Foto del doctor',
      doctorName: 'Alfonso Valladares',
      medicalSpecialty: 'Cirujano',
      price: '1000.00',
      rating: 1,
    },
    {
      src: 'assets/images/doctor-1.jpg',
      alt: 'Foto del doctor',
      doctorName: 'Sara Figueroa',
      medicalSpecialty: 'Medicina general',
      price: '5000.00',
      rating: 2,
    },
    {
      src: 'assets/images/doctor-1.jpg',
      alt: 'Foto del doctor',
      doctorName: 'María González',
      medicalSpecialty: 'Cardióloga',
      price: '1200.00',
      rating: 3,
    },
    {
      src: 'assets/images/doctor-2.jpg',
      alt: 'Foto del doctor',
      doctorName: 'Carlos Umaña',
      medicalSpecialty: 'Dermatólogo',
      price: '900.00',
      rating: 4,
    },
    {
      src: 'assets/images/doctor-1.jpg',
      alt: 'Foto del doctor',
      doctorName: 'Laura Fernández',
      medicalSpecialty: 'Pediatra',
      price: '1100.00',
      rating: 5,
    },
    {
      src: 'assets/images/doctor-2.jpg',
      alt: 'Foto del doctor',
      doctorName: 'Javier Ruiz',
      medicalSpecialty: 'Oncólogo',
      price: '1300.00',
      rating: 1,
    },
    {
      src: 'assets/images/doctor-1.jpg',
      alt: 'Foto del doctor',
      doctorName: 'Ana Martínez',
      medicalSpecialty: 'Ginecóloga',
      price: '950.00',
      rating: 2,
    },
    {
      src: 'assets/images/doctor-2.jpg',
      alt: 'Foto del doctor',
      doctorName: 'Pedro Sánchez',
      medicalSpecialty: 'Neurólogo',
      price: '1400.00',
      rating: 3,
    },
    {
      src: 'assets/images/doctor-1.jpg',
      alt: 'Foto del doctor',
      doctorName: 'Lucía Ramírez',
      medicalSpecialty: 'Psiquiatra',
      price: '1000.00',
      rating: 4,
    },
    {
      src: 'assets/images/doctor-2.jpg',
      alt: 'Foto del doctor',
      doctorName: 'Fernando Torres',
      medicalSpecialty: 'Ortopedista',
      price: '1050.00',
      rating: 5,
    },
    {
      src: 'assets/images/doctor-1.jpg',
      alt: 'Foto del doctor',
      doctorName: 'Elena García',
      medicalSpecialty: 'Endocrinóloga',
      price: '1150.00',
      rating: 1,
    },
    {
      src: 'assets/images/doctor-2.jpg',
      alt: 'Foto del doctor',
      doctorName: 'Roberto López',
      medicalSpecialty: 'Gastroenterólogo',
      price: '1250.00',
      rating: 2,
    },
  ];

  constructor(
    private _fb: FormBuilder
  ) {}

  ngOnInit():void {
    this.initForm();
  }

  initForm() {
    //TODO: Hacer que el valor del rating cambie dependiendo del valor que trae en el objeto
    this.doctorDetailsForm = this._fb.group({
      rating: [0, Validators.required ]
    })
  }


}
