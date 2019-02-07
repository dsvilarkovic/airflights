import { Hotel } from "./hotel";

export class Room {
    id: number;
    number: number;
    beds: number;
    rooms: number;
    hotel: Hotel;
    floor: number;
    balcony: boolean;
    price: number;
    promo: boolean; 
    discount: number;
    ratingsSum: number;
    ratingsCount: number;
    tempRate: number;
}