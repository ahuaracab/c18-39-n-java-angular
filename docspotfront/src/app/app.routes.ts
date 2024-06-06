import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'auth/login' },
  {
    path: 'auth',
    //   canActivate: [BeforeUserLoggedGuard],
    loadChildren: () =>
      import('./views/pages/authentication/auth.routes').then(
        (m) => m.AUTH_ROUTES
      ),
  },
  {
    path: 'doctors',
    loadChildren: () =>
      import(
        './views/pages/search-profesional/search-professional.routes'
      ).then((c) => c.SEARCH_PROFESSIONAL_ROUTES),
  },
  {
    path: 'home',
    loadChildren: () => import('./views/pages/home/home.routes').then((c) => c.HOME_ROUTES),
  },
  {
    path: 'page-not-found',
    loadComponent: () => import('./views/common/page-not-found/page-not-found.component').then(c => c.PageNotFoundComponent)
  },
  // ,
  // {
  //   path: "",
  //   canActivate: [AfterUserLoggedGuard],
  //   loadChildren: () =>
  //     import('./views/layouts/layouts.module')
  //       .then((m) => m.LayoutsModule),
  // },
  { path: '**', pathMatch: 'full', redirectTo: '/page-not-found' },
];
