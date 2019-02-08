import { Airline } from './airline';

export class Flight {
    id : number;
    departureDateTime : string;    
    arrivalDatetime: string;
    legCount: number;
    airline: Airline;
    travelTime: number;
    travelDistance: number;
    flightDiscount: number;
    airlineId: number;
    flightLegsDTO: [];
      flightClassPricesMap: { BUSINESS: 23, FIRST: 434 }
}