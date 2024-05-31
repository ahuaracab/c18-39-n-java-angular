import { Component } from '@angular/core';
import { DoctorDetailsCardComponent } from './components/doctor-details-card/doctor-details-card.component';
import { DoctorFiltersComponent } from './components/doctor-filters/doctor-filters.component';
import { FieldsetModule } from "primeng/fieldset";

@Component({
  selector: 'app-search-profesional',
  standalone: true,
  imports: [
    DoctorDetailsCardComponent,
    DoctorFiltersComponent,
    FieldsetModule,
  ],
  templateUrl: './search-profesional.component.html',
  styleUrl: './search-profesional.component.scss'
})
export class SearchProfesionalComponent {

  constructor() {}

  getProfessionals() {

  }

}
