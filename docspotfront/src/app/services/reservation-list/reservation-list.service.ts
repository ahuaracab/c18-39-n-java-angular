import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from 'src/app/models/reservation-list/reservation-list.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  private properties;
  private appUrl: string;

  constructor(private http: HttpClient) {
    this.properties = environment;
    this.appUrl = this.properties.url_api_render;
  }

  public listReservationsByPatient(
    id: string | null
  ): Observable<Reservation[]> {
    const token = localStorage.getItem('token');
    const ctrl: string = `/api/reservation/patient/${id}`;
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.get<any>(`${this.appUrl}${ctrl}`, {
      headers,
    });
  }

  public listReservationsByProfessional(
    id: string | null
  ): Observable<Reservation[]> {
    const token = localStorage.getItem('token');
    const ctrl: string = `/api/reservation/professional/${id}`;
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.get<any>(`${this.appUrl}${ctrl}`, {
      headers,
    });
  }
}
