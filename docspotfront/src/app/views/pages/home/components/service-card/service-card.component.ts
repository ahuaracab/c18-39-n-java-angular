import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

import { AvatarModule } from 'primeng/avatar'
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-service-card',
  standalone: true,
  imports: [
    AvatarModule,
    CardModule,
    CommonModule,
  ],
  templateUrl: './service-card.component.html',
  styleUrl: './service-card.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ServiceCardComponent {

  public services = [
    {
      title: 'Consulta Médica Online',
      description: 'Habla con médicos calificados desde la comodidad de tu hogar. Realiza tus consultas sin salir de casa.',
    },
    {
      title: 'Historial Clínico Digital',
      description: 'Accede a tu historial médico, recetas y resultados de laboratorio en un solo lugar.'
    },
    {
      title: 'Reserva Fácil y Rápida',
      description: 'Encuentra especialistas, agenda citas y recibe confirmaciones al instante.'
    }
  ]

}
