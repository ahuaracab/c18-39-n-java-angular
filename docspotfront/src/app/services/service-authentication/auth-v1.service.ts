import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { userLogin } from 'src/app/models/authentication-models/login.models';
import { ApiRoutes } from 'src/app/utils/config/api/api-routes';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthV1Service {
  private properties;
  private appUrl:string;

  constructor(
    private http: HttpClient,
  ) {
    this.properties = environment;
    this.appUrl = this.properties.url_api_render;
  }

  public login(user: userLogin): Observable<HttpResponse<any>> {
    const ctrl: string = ApiRoutes.login;
    return this.http.post<any>(`http://localhost:8000/api/login`, user,
      {observe: 'response'}
    );
  }
}
