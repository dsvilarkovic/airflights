import { rentacar } from "./rentacar";

export class Vehicle {
    id: Number;
    name: String;
    brand: String;
    model:String;  
    year: Number;
    seats: Number;
    price: Number;
    rating: Number;
    type: String;
    rentacar: rentacar;
}