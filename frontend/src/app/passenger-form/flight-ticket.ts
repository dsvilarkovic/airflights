import { UserToUpdate } from '../user-profile/user-to-update';

export class FlightTicket{
    id : number;
    abstractUserDTO : UserToUpdate;
    flightId : number;
    departureDestination : number;
    arrivalDestination : number;
    departureDatetime : string;
    arrivalDatetime : string;
    travelTime : number;
    travelDistance : number;
    seatDTO : Seat;
    isFastReservation : boolean;
    isAccepted : boolean;
    flightGrade : number ;
    airlineGrade : number;
    flightClassPrice : number;
    airlineClassType : number;
    priceReduction : number;
}