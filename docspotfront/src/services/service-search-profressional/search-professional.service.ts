import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SearchProfessionalService {

  constructor(
    private _http: HttpClient
  ) { }

  getAllProfessionals() {
    // ! Insertar el endpoint
  }
}
