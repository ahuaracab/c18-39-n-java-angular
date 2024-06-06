import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-home-main-section',
  standalone: true,
  imports: [
    ButtonModule,
    CommonModule,
  ],
  templateUrl: './home-main-section.component.html',
  styleUrl: './home-main-section.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class HomeMainSectionComponent { }
