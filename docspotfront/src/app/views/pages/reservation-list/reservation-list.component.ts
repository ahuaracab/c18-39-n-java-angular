import { ChangeDetectorRef, Component, OnInit, inject } from '@angular/core';
import { DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReservationService } from 'src/app/services/reservation-list/reservation-list.service';
import { Reservation } from 'src/app/models/reservation-list/reservation-list.interface';

@Component({
  selector: 'app-reservation-list',
  standalone: true,
  imports: [DatePipe, RouterModule],
  templateUrl: './reservation-list.component.html',
  styleUrl: './reservation-list.component.scss',
})
export default class ReservationListComponent implements OnInit {
  private reservationService = inject(ReservationService);

  constructor(private cdr: ChangeDetectorRef) {}

  reservations: Reservation[] = [];
  id: string | null = '';
  role: string | null = '';

  ngOnInit(): void {
    this.role = localStorage.getItem('role');
    console.log(this.role);
    if (this.role == 'ROLE_PATIENT') {
      this.listReservationsByPatient();
    } else {
      this.listReservationsByProfessional();
    }
  }

  getId() {
    this.id = localStorage.getItem('id');
    console;
  }

  listReservationsByPatient() {
    this.getId();
    this.reservationService
      .listReservationsByPatient(this.id)
      .subscribe((reservations) => {
        this.reservations = reservations;
        this.cdr.detectChanges();
        console.log(this.reservations);
      });
  }

  listReservationsByProfessional() {
    this.getId();
    this.reservationService
      .listReservationsByProfessional(this.id)
      .subscribe((reservations) => {
        this.reservations = reservations;
        this.cdr.detectChanges();
      });
  }
}
