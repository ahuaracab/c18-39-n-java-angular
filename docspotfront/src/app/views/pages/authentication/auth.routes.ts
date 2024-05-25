import { LoginComponent } from "./login/login.component";
import { Routes } from '@angular/router';

export const AUTH_ROUTES: Routes = [
    {
        path: 'login',
        component: LoginComponent,
    }
]