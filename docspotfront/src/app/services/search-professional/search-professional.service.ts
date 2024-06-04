import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Specialty } from 'src/app/models/authentication-models/register.models';

@Injectable({
  providedIn: 'root'
})
export class SearchProfessionalService {

  constructor(private _http: HttpClient) {}

  getSpecialties():Observable<Specialty[]> {
    return this._http.get<Specialty[]>(`https://docspotback.onrender.com/api/specialty`)
  }
}
