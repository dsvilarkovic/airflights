export class Airplane{
    id: number;
    fullName : string;
    airline_id : number;
    segmentConfig : {
        id: number;
        airplaneId: number;
        segmentNum : number;
        seats : Seat[];

    }

}