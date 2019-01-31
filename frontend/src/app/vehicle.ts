import { Branch } from './branch';
import { rentacar } from "./rentacar";

export class Vehicle {
    id: number;
    name: String;
    brand: String;
    model:String;  
    year: Number;
    seats: Number;
    price: number;
    rating: Number;
    type: String;
    reserved: String;
    rentACarId: Number;
    branchOffice_id: Number;
}