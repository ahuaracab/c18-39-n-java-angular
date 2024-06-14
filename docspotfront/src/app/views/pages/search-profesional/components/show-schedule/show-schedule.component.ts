import { CommonModule } from '@angular/common';
import { Component, Input, OnInit, ChangeDetectorRef } from '@angular/core';
import moment from 'moment';
import { Observable, forkJoin, map } from 'rxjs';
import { ResponseShift } from 'src/app/models/shitf-models/shift.model';
import { ShiftService } from 'src/app/services/service-shift/shift.service';
// import Swiper from 'swiper';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { DialogService } from 'src/app/services/component/service-dialog/dialog.service';
import { ReservDto } from 'src/app/models/reservation-models/reservation.mode';
import { ReservationDataDto } from 'src/app/models/reservation-models/reservationPopUpConfirm.model';
import { ReservationService } from 'src/app/services/service-reservation/reservation.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import {
  DialogActionsDataDto,
  DialogDataDto,
} from 'src/app/models/components/common/dialog.model';

/*
{
  "professionalId": 1402,
  "professionalName": "DR. RUIZ MUJICA, PAUL ADISON",
  "cmp": "CMP 066024",
  "basicServiceId": 4,
  "centerId": 1,
  "provissionId": 1028581,
  "appointmentDays": [
      {
          "attentionTypeId": 1,
          "attentionType": "Presencial",
          "days": [
              {
                  "fecha": "2024-06-10",
                  "appointmentHours": [
                      "16:15",
                      "16:30",
                      "16:45",
                      "17:00",
                      "17:15",
                  ]
              },
              {
                  "fecha": "2024-06-15",
                  "appointmentHours": [
                      "08:00",
                      "08:15",
                      "08:30",
                      "08:45",
                      "09:00",
                      "09:15",
                  ]
              }
          ]
      }
  ]
}
*/

interface Turno {
  days: Available[];
}

interface Available {
  fecha: string;
  appointmentHours: DescHour[];
}

interface DescHour {
  id: string;
  hour: string;
  available: boolean;
}

@Component({
  selector: 'app-show-schedule',
  standalone: true,
  imports: [CommonModule, MatProgressSpinnerModule],
  templateUrl: './show-schedule.component.html',
  styleUrl: './show-schedule.component.scss',
})
export class ShowScheduleComponent implements OnInit {
  // obtener el especialista
  @Input() idDoctor: string = '';
  @Input() nameDoctor: string = '';
  @Input() priceDoctor: number = 0;

  private daysLen: number = 7;
  private daysLoad: string[] = [];

  public days: Available[] = [];
  public hoursAvailable: DescHour[] = [];

  public turno: ResponseShift[] = [];

  public daySelect: Available = {} as Available;
  public hourSelect: DescHour = {} as DescHour;

  public loading: boolean = false;

  constructor(
    private cdr: ChangeDetectorRef,
    // servicios consulta horario
    private shiftService: ShiftService, // servicio obtener data usuario para la reserva
    // servicios reservar turno
    private dialogService: DialogService,
    private reservationService: ReservationService
  ) {}

  ngOnInit(): void {
    // settear la cantidad de dias a traer
    this.daysLoad = this.getDays(this.daysLen);

    this.loading = true;
    this.cdr.detectChanges();

    // utilizar servicio para obtener los horarios;
    const requests: Observable<ResponseShift[] | null>[] = this.daysLoad.map(
      (date) =>
        this.shiftService
          .getShitfByProfessionalId(this.idDoctor, date)
          .pipe(map((response) => response.body))
    );
    // .pipe(
    //   mergeMap(results => results)
    forkJoin(requests)
      .pipe(
        map((results) =>
          // Filtrar los valores null y concatenar los arrays de turnos
          results
            .filter((res): res is ResponseShift[] => res !== null)
            .reduce((acc, curr) => acc.concat(curr), [])
        )
      )
      .subscribe({
        next: (result: ResponseShift[]) => {
          // loading false
          if (result && result.length > 1) {
            console.log('results', result);
            const allShifts: ResponseShift[] = result;
            if (allShifts) {
              this.turno = allShifts;
              // this.turno.push(result);
              // ejemplo
              this.turno = this.loadDays(this.turno);
              this.days = this.transformShiftsToDays(this.turno);
              this.daySelect = this.days[0];
              this.hoursAvailable = this.daySelect?.appointmentHours ?? [];
              this.loading = false;
              this.cdr.detectChanges();
            }
          }
        },
        error: (error) => {
          this.loading = false;
          this.cdr.detectChanges();
          console.log(error);
        },
      });
  }

  private getDays(days: number): string[] {
    const result: string[] = [];
    const currentDate = new Date();

    for (let i = 0; i < days; i++) {
      const date = new Date(currentDate);
      date.setDate(currentDate.getDate() + i);
      const formattedDate = date.toISOString().split('T')[0];
      result.push(formattedDate);
    }

    return result;
  }

  private transformShiftsToDays(shifts: ResponseShift[]): Available[] {
    console.log('Inicio Transform: ', shifts);
    // Agrupar los turnos por fecha
    const groupedByDate = shifts.reduce(
      (acc: { [date: string]: ResponseShift[] }, shift) => {
        if (!acc[shift.dateShift]) {
          acc[shift.dateShift] = [];
        }
        acc[shift.dateShift].push(shift);
        return acc;
      },
      {}
    );

    // Convertir el objeto agrupado en el formato deseado
    const transformedDays: Available[] = Object.keys(groupedByDate).map(
      (date) => {
        return {
          fecha: date,
          appointmentHours: groupedByDate[date].map((shift) => ({
            id: shift.idShift,
            hour: shift.hoursTime,
            available: !shift.stateShift, // Aquí puedes aplicar tu lógica para determinar si está deshabilitado
          })),
        };
      }
    );

    console.log('Fin Transform: ', transformedDays);
    return transformedDays;
  }

  private loadDays(turno: ResponseShift[]): ResponseShift[] {
    const daysOrderByAsc = turno.sort((a, b) => {
      const fechaA = new Date(a.dateShift);
      const fechaB = new Date(b.dateShift);
      return fechaA.getTime() - fechaB.getTime();
    });
    return [...daysOrderByAsc];
  }

  public getDayShort(dateComplete: string): string {
    // Parse the date using Moment.js
    const date = moment(dateComplete, 'YYYY-MM-DD');

    // Extract day and month using Moment.js format methods
    const dayDate = date.format('DD'); // Get the day of the month as a string (DD format)
    const monthDate = date.format('MMM'); // Get the month as a short string (MMM format)

    const monthNameSpanish: { [key: string]: string } = {
      Jan: 'Enero',
      Feb: 'Febrero',
      Mar: 'Marzo',
      Apr: 'Abril',
      May: 'Mayo',
      Jun: 'Junio',
      Jul: 'Julio',
      Aug: 'Agosto',
      Sep: 'Septiembre',
      Oct: 'Octubre',
      Nov: 'Noviembre',
      Dec: 'Diciembre',
    };

    const monthDateShort = monthNameSpanish[monthDate].substring(0, 3);

    // Combine and format the output
    return `${dayDate} ${monthDateShort}`;
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
    const abbreviatedDayName = dayNameSpanish[dayName].substring(0, 3);

    return abbreviatedDayName;
  }

  public getHourShort(hour: string): string {
    const timeString = hour;
    const timeParts = timeString.split(':');

    const hours = timeParts[0]; // Get the first part (hours)
    const minutes = timeParts[1]; // Get the second part (minutes)

    const formattedTime = `${hours}:${minutes}`;
    return formattedTime;
  }

  public showHours(day: Available): void {
    this.daySelect = { ...day };
    console.log('dia seleccionado: ', this.daySelect.fecha);
    this.hoursAvailable = this.orderHours([...this.daySelect.appointmentHours]);
  }

  private orderHours(hours: DescHour[]): DescHour[] {
    function compareDescHours(
      descHour1: DescHour,
      descHour2: DescHour
    ): number {
      const [h1, m1, s1] = descHour1.hour.split(':').map(Number);
      const [h2, m2, s2] = descHour2.hour.split(':').map(Number);

      if (h1 !== h2) {
        return h1 - h2;
      }
      if (m1 !== m2) {
        return m1 - m2;
      }
      if (s1 !== s2) {
        return s1 - s2;
      }
      return 0; // Igual si son exactamente iguales
    }

    return hours.sort(compareDescHours);
  }

  public selectHours(hour: DescHour): void {
    this.hourSelect = hour;
    console.log('hora seleccionada: ', this.hourSelect);
    let data: ReservationDataDto = this.loadReservations();
    // obtener id del paciente
    /* llamar a modal setteando data */
    this.dialogService.openReservationConfirm(data).subscribe((result) => {
      if (result) {
        this.dialogService.openLoadingWindow();
        let reservDto: ReservDto = this.loadReservationPost();
        this.reservationService.postReservation(reservDto).subscribe({
          next: (res: HttpResponse<any>) => {
            this.dialogService.closeDialog();
            this.dialogService.openSuccessDialog(this.loadSuccessResponse());
          },
          error: (error: HttpErrorResponse) => {
            this.dialogService.closeDialog();
            this.dialogService.openAlertDialog(this.loadErrorResponse());
          },
        });
      }
      return;
    });
  }

  private loadErrorResponse(): DialogDataDto {
    let dataError: DialogDataDto = {
      tittle: 'Reservación fallida!',
      content: 'No se realizó la reservación',
      actions: [
        {
          name: 'Volver',
          returnValue: true,
        },
      ],
    };
    return dataError;
  }

  private loadSuccessResponse(): DialogDataDto {
    let dataSuccess: DialogDataDto = {
      tittle: 'Reservacion Realizada!',
      content: 'Se registró la reservación correctamente',
      actions: [
        {
          name: 'Ok',
          returnValue: true,
        },
      ],
    };
    return dataSuccess;
  }

  private loadReservations(): ReservationDataDto {
    const reservationDto: ReservationDataDto = {
      tittle: 'Confimacion de Reserva',
      nameProfessional: this.nameDoctor,
      namePatient: localStorage.getItem('namePatient') ?? '',
      fecha: this.daySelect.fecha,
      hour: this.hourSelect.hour,
      priceProfessional: this.priceDoctor,
      actions: [
        {
          name: 'Aceptar',
          returnValue: true,
          style: 'btn_standard',
        },
        {
          name: 'Cancelar',
          returnValue: false,
          style: 'btn_cancel',
        },
      ],
    };
    return reservationDto;
  }

  private loadReservationPost(): ReservDto {
    let reserv: ReservDto = {
      queryIntent: '',
      shift: {
        idShift: this.hourSelect.id,
      },
      patient: {
        idPatient: localStorage.getItem('idPatient') ?? '',
      },
    };
    return reserv;
  }
}
