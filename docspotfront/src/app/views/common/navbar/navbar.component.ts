import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NavbarComponent {

  // ? Crear interfaz
  public menuItems = [
    { label: 'Inicio', path: '/home' },
    { label: 'Servicios', path: '#' },
    { label: 'Buscar Médicos', path: '/doctors/list' },
  ]
}
