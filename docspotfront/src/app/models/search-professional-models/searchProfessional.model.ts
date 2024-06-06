
export interface Professional {
  idProfessional:   string;
  nameProfessional: string;
  reputation:       number;
  valueQuery:       number;
  mp:               string;
  specialties:      Specialty[];
}

export interface Specialty {
  idSpecialty: string;
  nameSpecialty: string;
  descriptionSpecialty: string;
}
