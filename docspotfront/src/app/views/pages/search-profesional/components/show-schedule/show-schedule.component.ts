import { CommonModule } from '@angular/common';
import { Component, Input, OnInit, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import moment from 'moment';
import { Observable, forkJoin, map, mergeMap } from 'rxjs';
import { ResponseShift } from 'src/app/models/shitf-models/shift.model';
import { ShiftService } from 'src/app/services/service-shift/shift.service';
// import Swiper from 'swiper';

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
  id:string,
  hour:string,
  available:boolean
}

@Component({
  selector: 'app-show-schedule',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './show-schedule.component.html',
  styleUrl: './show-schedule.component.scss'
})
export class ShowScheduleComponent implements OnInit{
  // obtener el especialista
  @Input() idDoctor:string = '3da859d0-7c5c-451a-a4df-8e73cadb0a48';

  private daysLen:number = 7;
  private daysLoad:string[] = [];

  public days:Available[] = [];
  public hoursAvailable:DescHour[] = [];

  public turno:ResponseShift[] = [];

  public daySelect:Available = {} as Available;
  public hourSelect:DescHour = {} as DescHour;

  constructor(
    private cdr: ChangeDetectorRef,
    // servicios consulta horario
    private shiftService:ShiftService,
    // servicio obtener data usuario para la reserva
    // servicios reservar turno
  ){}

  private getDays(days:number):string[] {
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

  // ngAfterViewInit() {
  //   const swiper = new Swiper('.swiper-container', {
  //     // Swiper options here (optional)
  //     navigation: {
  //       nextEl: '.swiper-button-next',
  //       prevEl: '.swiper-button-prev',
  //     },
  //     direction: 'horizontal',
  //     spaceBetween: 20,
  //     slidesPerView: 4,
  //   });
  // }

  ngOnInit(): void {

    // settear la cantidad de dias a traer
    this.daysLoad = this.getDays(this.daysLen);

    // loading  true

    // utilizar servicio para obtener los horarios;
    const requests:Observable<ResponseShift[] | null>[]= this.daysLoad.map(date => 
      this.shiftService.getShitfByProfessionalId(this.idDoctor, date)
        .pipe(map(response => response.body))
    );
    // .pipe(
    //   mergeMap(results => results)
    forkJoin(requests).pipe(
      map(results => 
        // Filtrar los valores null y concatenar los arrays de turnos
        results.filter((res): res is ResponseShift[] => res !== null).reduce((acc, curr) => acc.concat(curr), [])
      ))
    .subscribe({
      next: (result:(ResponseShift[])) => {
        // loading false
        if(result && result.length > 1) {
          console.log("results",result);
          const allShifts: ResponseShift[] = result;
          if (allShifts) {
            this.turno = allShifts;
            // this.turno.push(result);
            // ejemplo
            this.turno = this.loadDays(this.turno);
            this.days = this.transformShiftsToDays(this.turno);
            this.daySelect = this.days[0];
            this.hoursAvailable = this.daySelect?.appointmentHours ?? [];
            this.cdr.detectChanges();
          }
        }
      },
      error: (error) => {
        // loading false
        console.log(error);
      } 
    }
      
    );

    
  }

  private transformShiftsToDays(shifts: ResponseShift[]): Available[] {
    console.log('Inicio Transform: ',shifts);
    // Agrupar los turnos por fecha
    const groupedByDate = shifts.reduce((acc: { [date: string]: ResponseShift[] }, shift) => {
      if (!acc[shift.dateShift]) {
        acc[shift.dateShift] = [];
      }
      acc[shift.dateShift].push(shift);
      return acc;
    }, {});

    // Convertir el objeto agrupado en el formato deseado
    const transformedDays: Available[] = Object.keys(groupedByDate).map(date => {
      return {
        fecha: date,
        appointmentHours: groupedByDate[date].map(shift => ({
          id: shift.idShift,
          hour: shift.hoursTime,
          available: !shift.stateShift // Aquí puedes aplicar tu lógica para determinar si está deshabilitado
        }))
      };
    });

    console.log('Fin Transform: ',transformedDays);
    return transformedDays;
  }

  private loadDays(turno:ResponseShift[]):ResponseShift[]{
    const daysOrderByAsc = turno.sort((a, b) => {
      const fechaA = new Date(a.dateShift);
      const fechaB = new Date(b.dateShift);
      return fechaA.getTime() - fechaB.getTime();
    }); 
    return [...daysOrderByAsc];
  }

  // public getDayShort(dateComplete:string):string {
  //   const date = new Date(dateComplete);
  //   const monthDate = date.toLocaleDateString('es-Es', {month:'short'});
  //   const dayDate = date.getDate().toString().padStart(2, '0');
  //   return `${monthDate} - ${dayDate}`;
  // }

  public getDayShort(dateComplete: string): string {
    // Parse the date using Moment.js
    const date = moment(dateComplete, 'YYYY-MM-DD');
  
    // Extract day and month using Moment.js format methods
    const dayDate = date.format('DD'); // Get the day of the month as a string (DD format)
    const monthDate = date.format('MMM'); // Get the month as a short string (MMM format)
  
    // Combine and format the output
    return `${monthDate} - ${dayDate}`;
  }
  

  // public getDayName(dateComplete:string):string {
  //   const date = new Date(dateComplete);
  //   return date.toLocaleDateString('es-ES', { weekday: 'long' });
  // }

  public getDayName(dateComplete: string): string {
    // Parse the date using Moment.js
    const date = moment(dateComplete, 'YYYY-MM-DD');
  
    // Extract the day name using Moment.js format method
    const dayName = date.format('dddd'); // Get the day name as a long string (dddd format)
  
    return dayName;
  }

  public showHours(day:Available):void {
    this.daySelect = {...day};
    console.log("dia seleccionado: ",this.daySelect.fecha);
    this.hoursAvailable = this.orderHours([...this.daySelect.appointmentHours]);
  }

  private orderHours(hours:DescHour[]):DescHour[] {
    function compareDescHours(descHour1: DescHour, descHour2: DescHour): number {
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

  public selectHours(hour:DescHour):void {
    this.hourSelect = hour;
    console.log("hora seleccionada: ",this.hourSelect);
  }

}
