import { Branch } from './branch';
export class rentacar {
    id: number;
    name: string;
    address: string;
    city: string;
    description: string;
    branches : Array<Branch>;
    lastRating: number;
    ratingsCount: number;
    ratingsSum: number;
}