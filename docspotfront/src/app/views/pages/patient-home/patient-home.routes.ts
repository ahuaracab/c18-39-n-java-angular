import { Routes } from '@angular/router';
import { PatientHomeComponent } from './patient-home.component';

export const PATIENT_HOME_ROUTES: Routes = [
    {
        path: 'patient',
        component: PatientHomeComponent,
    }
]