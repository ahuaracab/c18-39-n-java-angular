import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  public rol:string = '';
  public registerFormPatient!: FormGroup;
  public registerFormProfessional!: FormGroup;
  public showPasswordPatient: boolean = false;
  public showPasswordProfessional: boolean = false;
  public loading: boolean = false;
  public errorMessage: string = '';
}
