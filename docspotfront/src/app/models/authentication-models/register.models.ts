export interface RoleDTO {
  nameRole: string;
  descriptionRole: string;
}

export interface UserDTO {
  email: string;
  password: string;
  active: boolean;
  roles: Array<RoleDTO>
  patients: Array<any>
}

export interface Pacient {
  namePatient: string;
  cellphonePatient: string;
  photoPatient: string;
  hasSocialWork: boolean;
  socialWork: string;
  user: UserDTO;
}

export interface PacientRegister {
  rol: string;
  email: string;
  password: string;
  namePatient: string;
  cellphonePatient: string;
  photoPatient: string;
  hasSocialWork: boolean;
  socialWork: string;
}

export interface ProfessionalRegister {
  rol: string;
  email: string;
  password: string;
  nameProfessional: string;
  mp: string;
  specialties: Specialty[];
}

export interface Specialty {
  idSpecialty: string;
  nameSpecialty: string;
  descriptionSpecialty: string;
}
