import { Vehicle } from "./Vehicle";

export class VehicleReservation {
    id: number;
    vehicle: Vehicle;
    user_id:number;
    reservationdate:string;
    pickupdate:string;
    dropoffdate:string;
    pickuplocation:String;
    dropofflocation:String;
    price:Number;
}