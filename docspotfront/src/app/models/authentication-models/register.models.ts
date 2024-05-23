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

export interface PacientRegister {
  namePatient: string;
  cellphonePatient: string;
  photoPatient: string;
  hasSocialWork: boolean;
  socialWork: string;
  user: UserDTO;
}
