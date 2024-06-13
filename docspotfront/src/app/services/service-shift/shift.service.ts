import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiRoutes } from 'src/app/utils/config/api/api-routes';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ShiftService {
  private properties;
  private appUrl:string;

  constructor(
    private http: HttpClient,
  ) { 
    this.properties = environment;
    this.appUrl = this.properties.url_api;
  }

  public getShitfByProfessionalId(id:string): Observable<HttpResponse<any>> {
    const ctrl: string = `${ApiRoutes.shifts}/${id}`;
    return this.http.get<any>(`${this.appUrl}${ctrl}`,
      {observe: 'response'}
    );
  }

}
