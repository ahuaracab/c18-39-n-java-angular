import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'app-specialty-card',
  standalone: true,
  imports: [
    CommonModule,
  ],
  templateUrl: './specialty-card.component.html',
  styleUrl: './specialty-card.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SpecialtyCardComponent {

  public specialties = [
    {
      title: 'Cardiología'
    },
    {
      title: 'Pediatría'
    },
    {
      title: 'Dermatología',
    },
    {
      title: 'Neurología'
    }
  ]
}
