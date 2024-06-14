import { CommonModule } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { ReservationDataDto } from 'src/app/models/reservation-models/reservationPopUpConfirm.model';
import moment from 'moment';

@Component({
  selector: 'app-reservation-confirm',
  standalone: true,
  imports: [
    CommonModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule
  ],
  templateUrl: './reservation-confirm.component.html',
  styleUrl: './reservation-confirm.component.scss'
})
export class ReservationConfirmComponent {
  constructor(
  public dialogRef: MatDialogRef<ReservationConfirmComponent>,
  @Inject(MAT_DIALOG_DATA) public data: ReservationDataDto
  ){}

  public getDateLong(dateComplete: string): string {
    // Parse the date using Moment.js
    const date = moment(dateComplete, 'YYYY-MM-DD');

    // Extract day and month using Moment.js format methods
    const year = date.format("YYYY");
    const dayDate = date.format('DD'); // Get the day of the month as a string (DD format)
    const monthDate = date.format('MMM'); // Get the month as a short string (MMM format)

    const monthNameSpanish:{ [key: string]: string } = {
      Jan: "Enero",
      Feb: "Febrero",
      Mar: "Marzo",
      Apr: "Abril",
      May: "Mayo",
      Jun: "Junio",
      Jul: "Julio",
      Aug: "Agosto",
      Sep: "Septiembre",
      Oct: "Octubre",
      Nov: "Noviembre",
      Dec: "Diciembre"
    };

    const monthDateShort = monthNameSpanish[monthDate];
    const dayName = this.getDayName(dateComplete);

    // Combine and format the output
    return `${dayName} ${dayDate}, de ${monthDateShort} del ${year}`;
  }


  public getDayName(dateComplete: string): string {
    // Parse the date using Moment.js (assuming it's already loaded)
    const date = moment(dateComplete, 'YYYY-MM-DD');

    // date.locale('es'); no cambia a español

    const dayNameSpanish: { [key: string]: string } = {
      Monday: 'Lunes',
      Tuesday: 'Martes',
      Wednesday: 'Miércoles',
      Thursday: 'Jueves',
      Friday: 'Viernes',
      Saturday: 'Sábado',
      Sunday: 'Domingo',
    };
    // Use locale for Spanish day names
    const dayName = date.format('dddd'); // Get day name in Spanish (long format)

    // Get the first three letters
    const abbreviatedDayName = dayNameSpanish[dayName];

    return abbreviatedDayName;
  }
}
