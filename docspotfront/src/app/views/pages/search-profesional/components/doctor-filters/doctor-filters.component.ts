import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { DropdownModule } from 'primeng/dropdown';

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
export class DoctorFiltersComponent {

  public descendantPunctuation:boolean = true;
  public descendantPrice:boolean = true;

  public specialtyOptions:SpecialtyOption[] = [];

  ngOnInit() {
    this.specialtyOptions = [
      { value: 'Cardiología' },
      { value: 'Dermatología' },
      { value: 'Endocrinología' },
      { value: 'Gastroenterología' },
      { value: 'Hematología' },
      { value: 'Neurología' },
      { value: 'Oftalmología' },
      { value: 'Pediatría' },
      { value: 'Psiquiatría' },
      { value: 'Urología' }
    ];
  }

  sortPunctuation() {
    this.descendantPunctuation = !this.descendantPunctuation;
  }

  sortPrice() {
    this.descendantPrice = !this.descendantPrice;
  }
}
