interface RoleDTO {
  nameRole: string;
  descriptionRole: string;
}

interface UserDTO {
  email: string;
  password: string;
  active: boolean;
  roles: Array<RoleDTO>
  patients: Array<any>
}

interface PacientRegister {
  namePatient: string;
  cellphonePatient: string;
  photoPatient: string;
  hasSocialWork: boolean;
  socialWork: string;
  user: UserDTO;
}
