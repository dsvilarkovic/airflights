import { Vehicle } from './vehicle';
import { rentacar } from "./rentacar";

export class Branch {
    id: Number;
    address: String;
    city: String;
    rentacar: rentacar;
}