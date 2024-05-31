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
    {
    path: 'doctors',
    loadChildren: () => import('./views/pages/search-profesional/search-professional.routes').then((c) => c.SEARCH_PROFESSIONAL_ROUTES)
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
