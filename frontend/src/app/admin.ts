import { Hotel } from './hotel';
import { Role } from './role';
import { ComponentFactory } from '@angular/core/src/render3';

export class Admin {
    id: number;
    email: string;
    password: string;
    firstName: string;
    lastName: string;
    address: string;
    phoneNumber: string;
    city : string;
    username: string;
    hotel : Hotel;
    role: Role;
    idCompany: number;
    verify: boolean;
}