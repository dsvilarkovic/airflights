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
    price:Number;
}