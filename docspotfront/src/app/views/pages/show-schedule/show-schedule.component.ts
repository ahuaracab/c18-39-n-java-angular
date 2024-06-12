import { CommonModule } from '@angular/common';
import { Component, Input, OnInit, AfterViewInit } from '@angular/core';
import moment from 'moment';
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
  appointmentHours: string[];
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
  @Input() idDoctor:string = '';

  public days:Available[] = [];
  public hoursAvailable:string[] = [];

  public turno:Turno = {} as Turno;

  public daySelect:Available = {} as Available;
  public hourSelect:string = '';

  constructor(
    // servicios consulta horario
    // servicio obtener data usuario para la reserva
    // servicios reservar turno
  ){}

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
    // utilizar servicio para obtener los horarios;
    // cargar los datos a las variables;
    // ejemplo
    this.turno = {
      days: [
        {
          fecha:'2024-06-06',
          appointmentHours: [
            "08:15",
            "08:30",
            "08:45",
            "09:00",
          ]
        },
        {
          fecha:'2024-06-07',
          appointmentHours: [
            "08:15",
            "08:35",
            "09:15",
            "09:45",
            "10:30",
            "10:45",
            "11:20",
          ]
        },
        {
          fecha:'2024-06-08',
          appointmentHours: [
            "08:15",
            "08:30",
            "10:00",
            "10:15",
            "10:30",
            "10:45",
            "11:00",
            "11:30",
          ]
        },
        {
          fecha:'2024-06-09',
          appointmentHours: [
            "10:15",
            "10:30",
            "10:45",
            "11:00",
            "12:15",
            "12:45",
          ]
        },
        {
          fecha:'2024-06-10',
          appointmentHours: [
            "12:15",
            "12:45",
            "13:30",
            "15:00",
          ]
        }
      ]
    }
    this.days = this.loadDays(this.turno);
    this.daySelect = this.days[0];
    this.hoursAvailable = this.daySelect.appointmentHours;
  }

  private loadDays(turno:Turno):Available[]{
    const daysOrderByAsc = turno.days.sort((a, b) => {
      const fechaA = new Date(a.fecha);
      const fechaB = new Date(b.fecha);
      return fechaA.getTime() - fechaB.getTime();
    }); 
    return daysOrderByAsc;
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

  private orderHours(hours:string[]):string[] {
    const hoursOrderByAsc = hours.sort((a, b) => {
      const horaA = parseFloat(a.replace(':', '.'));
      const horaB = parseFloat(b.replace(':', '.'));
      return horaA - horaB;
    });
    return hoursOrderByAsc;
  }

  public selectHours(hour:string):void {
    this.hourSelect = hour;
    console.log("hora seleccionada: ",this.hourSelect);
  }

}
