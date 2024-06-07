import { Routes } from '@angular/router';
import { LayoutV1Component } from './layout-v1.component';

export const LAYOUT_V1_ROUTES: Routes = [
    {
        path: 'v1',
        component: LayoutV1Component,
        loadChildren: () =>
            import('src/app/views/pages/patient-home/patient-home.routes')
              .then((m) => m.PATIENT_HOME_ROUTES),
    }
]