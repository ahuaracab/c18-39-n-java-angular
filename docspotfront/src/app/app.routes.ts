import { Routes } from '@angular/router';

export const routes: Routes = [
    {path: '', pathMatch: 'full', redirectTo: 'auth'},
    {
      path: "auth",
    //   canActivate: [BeforeUserLoggedGuard],
      loadChildren: () =>
        import('./views/pages/authentication/auth.routes')
          .then((m) => m.AUTH_ROUTES),
    },
    // ,
    // {
    //   path: "",
    //   canActivate: [AfterUserLoggedGuard],
    //   loadChildren: () =>
    //     import('./views/layouts/layouts.module')
    //       .then((m) => m.LayoutsModule),
    // },
    {path: '**', pathMatch: 'full', redirectTo: 'auth'},
];
