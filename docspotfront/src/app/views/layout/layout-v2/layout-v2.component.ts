import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { RouterOutlet, RouterLink } from '@angular/router';
import { HeaderV1Component } from '../components/header-v1/header-v1.component';
import { MenuV1Component } from '../components/menu-v1/menu-v1.component';

@Component({
  selector: 'app-layout-v2',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    RouterLink,
    MatIconModule,
    MatSidenavModule,
    HeaderV1Component,
    MenuV1Component,
  ],
  templateUrl: './layout-v2.component.html',
  styleUrl: './layout-v2.component.scss',
})
export class LayoutV2Component {}
