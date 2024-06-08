import { Routes } from '@angular/router';
import { LayoutV2Component } from './layout-v2.component';

export const LAYOUT_V2_ROUTES: Routes = [
    {
        path: 'v2',
        component: LayoutV2Component,
        loadChildren: () =>
            import('src/app/views/pages/patient-home/patient-home.routes')
              .then((m) => m.PATIENT_HOME_ROUTES),
    }
]