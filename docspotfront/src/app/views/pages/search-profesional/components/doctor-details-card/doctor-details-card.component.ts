import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';

import { CardModule } from 'primeng/card';
import { RatingModule } from 'primeng/rating';

import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Professional } from 'src/app/models/search-professional-models/searchProfessional.model';
import { SearchProfessionalService } from 'src/app/services/search-professional/search-professional.service';
@Component({
  selector: 'app-doctor-details-card',
  standalone: true,
  imports: [
    CommonModule,
    CardModule,
    RatingModule,
    ReactiveFormsModule,
  ],
  templateUrl: './doctor-details-card.component.html',
  styleUrl: './doctor-details-card.component.scss',
})
export class DoctorDetailsCardComponent implements OnInit {

  public doctorDetailsForm: FormGroup = this._fb.group({});
  public professionals: Professional[] = [];

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
