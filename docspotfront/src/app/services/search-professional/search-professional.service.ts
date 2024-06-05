import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Specialty } from 'src/app/models/authentication-models/register.models';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SearchProfessionalService {

  constructor(private _http: HttpClient) {}

  getSpecialties():Observable<Specialty[]> {
    return this._http.get<Specialty[]>(`${environment.url_api}/api/specialty`)
  }

  getSpecialtyById(id: string) {
    return this._http.get(`${environment.url_api}/api/specialty/${id}`)
  }
}
