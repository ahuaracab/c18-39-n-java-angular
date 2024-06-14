export interface ReservDto {
    queryIntent:string,
    shift: {
        idShift:string
    },
    patient: {
        idPatient:string
    }
}