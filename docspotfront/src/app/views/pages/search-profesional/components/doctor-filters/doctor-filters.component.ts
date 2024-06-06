import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { DropdownModule } from 'primeng/dropdown';
import { SearchProfessionalService } from '../../../../../services/search-professional/search-professional.service';
import { Specialty } from '../../../../../models/search-professional-models/searchProfessional.model';

@Component({
  selector: 'app-doctor-filters',
  standalone: true,
  imports: [
    ButtonModule,
    CommonModule,
    CardModule,
    DropdownModule,
    ReactiveFormsModule,
  ],
  templateUrl: './doctor-filters.component.html',
  styleUrl: './doctor-filters.component.scss',
})
export class DoctorFiltersComponent implements OnInit {

  public doctorFiltersForm: FormGroup = this._fb.group({});

  public specialties: Specialty[] = [];

  public descendantPunctuation:boolean = true;
  public descendantPrice:boolean = true;

  constructor(
    private _fb:                        FormBuilder,
    private _searchProfessionalService: SearchProfessionalService
  ) {}

  ngOnInit():void {
    this.getSpecialties();
    this.initForm();
  }

  initForm():void {
    this.doctorFiltersForm = this._fb.group({
      idSpecialty: [ '', [Validators.required] ]
    })
  }

  getSpecialties():void {
    this._searchProfessionalService.getSpecialties().subscribe({
      next: (res) => this.specialties = res
    })
  }

  searchSpecialty():void {

    const id = this.doctorFiltersForm.controls['idSpecialty'].value;

    this._searchProfessionalService.getSpecialtyById(id).subscribe({
      next: (res) => console.log(res)
    })
  }

  sortPunctuation():void {
    this.descendantPunctuation = !this.descendantPunctuation;
  }

  sortPrice():void {
    this.descendantPrice = !this.descendantPrice;
  }
}
