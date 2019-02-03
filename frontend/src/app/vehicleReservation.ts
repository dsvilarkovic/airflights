import { rentacar } from './rentacar';
import { Vehicle } from "./vehicle";

export class VehicleReservation {
    id: number;
    vehicle: Vehicle;
    user_id:number;
    reservationdate:string;
    pickupdate:string;
    dropoffdate:string;
    pickuplocation:String;
    dropofflocation:String;
    price:number;
    rentacar: rentacar;
    cancel: boolean;//sluzi za logicko brisanje
    rateVehicle: boolean; //da li je ocenjeno vozilo
    rateRentacar: boolean; //da li je ocenjen rentacar
}