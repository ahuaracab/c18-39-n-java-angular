import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';

import { CardModule } from 'primeng/card';
import { RatingModule } from 'primeng/rating';

import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Professional } from 'src/app/models/search-professional-models/searchProfessional.model';
import { SearchProfessionalService } from 'src/app/services/search-professional/search-professional.service';

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
})
export class DoctorDetailsCardComponent implements OnInit {

  public doctorDetailsForm: FormGroup = this._fb.group({});

  public professionals: Professional[] = [];

  // public doctors = [
  //   {
  //     src: 'assets/images/doctor-2.jpg',
  //     alt: 'Foto del doctor',
  //     doctorName: 'Alfonso Valladares',
  //     medicalSpecialty: 'Cirujano',
  //     price: '1000.00',
  //     rating: 1,
  //   },
  //   {
  //     src: 'assets/images/doctor-1.jpg',
  //     alt: 'Foto del doctor',
  //     doctorName: 'Sara Figueroa',
  //     medicalSpecialty: 'Medicina general',
  //     price: '5000.00',
  //     rating: 2,
  //   },
  //   {
  //     src: 'assets/images/doctor-1.jpg',
  //     alt: 'Foto del doctor',
  //     doctorName: 'María González',
  //     medicalSpecialty: 'Cardióloga',
  //     price: '1200.00',
  //     rating: 3,
  //   },
  //   {
  //     src: 'assets/images/doctor-2.jpg',
  //     alt: 'Foto del doctor',
  //     doctorName: 'Carlos Umaña',
  //     medicalSpecialty: 'Dermatólogo',
  //     price: '900.00',
  //     rating: 4,
  //   },
  //   {
  //     src: 'assets/images/doctor-1.jpg',
  //     alt: 'Foto del doctor',
  //     doctorName: 'Laura Fernández',
  //     medicalSpecialty: 'Pediatra',
  //     price: '1100.00',
  //     rating: 5,
  //   },
  //   {
  //     src: 'assets/images/doctor-2.jpg',
  //     alt: 'Foto del doctor',
  //     doctorName: 'Javier Ruiz',
  //     medicalSpecialty: 'Oncólogo',
  //     price: '1300.00',
  //     rating: 1,
  //   },
  //   {
  //     src: 'assets/images/doctor-1.jpg',
  //     alt: 'Foto del doctor',
  //     doctorName: 'Ana Martínez',
  //     medicalSpecialty: 'Ginecóloga',
  //     price: '950.00',
  //     rating: 2,
  //   },
  //   {
  //     src: 'assets/images/doctor-2.jpg',
  //     alt: 'Foto del doctor',
  //     doctorName: 'Pedro Sánchez',
  //     medicalSpecialty: 'Neurólogo',
  //     price: '1400.00',
  //     rating: 3,
  //   },
  //   {
  //     src: 'assets/images/doctor-1.jpg',
  //     alt: 'Foto del doctor',
  //     doctorName: 'Lucía Ramírez',
  //     medicalSpecialty: 'Psiquiatra',
  //     price: '1000.00',
  //     rating: 4,
  //   },
  //   {
  //     src: 'assets/images/doctor-2.jpg',
  //     alt: 'Foto del doctor',
  //     doctorName: 'Fernando Torres',
  //     medicalSpecialty: 'Ortopedista',
  //     price: '1050.00',
  //     rating: 5,
  //   },
  //   {
  //     src: 'assets/images/doctor-1.jpg',
  //     alt: 'Foto del doctor',
  //     doctorName: 'Elena García',
  //     medicalSpecialty: 'Endocrinóloga',
  //     price: '1150.00',
  //     rating: 1,
  //   },
  //   {
  //     src: 'assets/images/doctor-2.jpg',
  //     alt: 'Foto del doctor',
  //     doctorName: 'Roberto López',
  //     medicalSpecialty: 'Gastroenterólogo',
  //     price: '1250.00',
  //     rating: 2,
  //   },
  // ];

  constructor(
    private _fb:                        FormBuilder,
    private _searchProfessionalService: SearchProfessionalService,
  ) {}

  ngOnInit():void {
    this.getProfessionals();
  }

  getProfessionals():void {
    this._searchProfessionalService.getProfessionals().subscribe({
      next: (res) => {
        this.professionals = res;
        //console.log(this.professionals.map(({ reputation }) => reputation));
      }
    })
  }
}
