import { Hotel } from 'src/app/hotel';
import { Room } from './room';

//mock za rezervaciju hotela
export class RoomResMock {
    id: number;
    room: Room;
    user_id:number;
    reservationDate:string;
    startDate:string;
    endDate:string;
    price:number;
    hotel: Hotel;
    active: boolean;//sluzi za logicko brisanje
    rateRoom: boolean; //da li je ocenjeno vozilo
    rateHotel: boolean; //da li je ocenjen rentacar
}
