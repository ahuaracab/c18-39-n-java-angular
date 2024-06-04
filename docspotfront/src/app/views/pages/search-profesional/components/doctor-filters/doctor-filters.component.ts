import { SearchProfessionalService } from '../../../../../services/search-professional/search-professional.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { DropdownModule } from 'primeng/dropdown';
import { Specialty } from '../../../../../models/authentication-models/register.models';

interface SpecialtyOption {
  value: string;
}

@Component({
  selector: 'app-doctor-filters',
  standalone: true,
  imports: [
    ButtonModule,
    CommonModule,
    CardModule,
    DropdownModule,
  ],
  templateUrl: './doctor-filters.component.html',
  styleUrl: './doctor-filters.component.scss',
})
export class DoctorFiltersComponent implements OnInit {

  public descendantPunctuation:boolean = true;
  public descendantPrice:boolean = true;

  public specialtyData: Specialty[] = [];

  constructor(
    private _searchProfessionalService: SearchProfessionalService
  ) {}

  ngOnInit():void {
    this.getSpecialties();
  }

  getSpecialties() {
    this._searchProfessionalService.getSpecialties().subscribe({
      next: (res) => this.specialtyData = res
    })
  }

  sortPunctuation():void {
    this.descendantPunctuation = !this.descendantPunctuation;
  }

  sortPrice():void {
    this.descendantPrice = !this.descendantPrice;
  }
}
