import { Branch } from './branch';
export class rentacar {
    id: number;
    name: string;
    address: string;
    city: string;
    description: string;
    rating: number;
    branches : Array<Branch>;
}