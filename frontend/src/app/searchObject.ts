import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { Condition } from './condition';

export class SearchObject {
    startD: number;
    startM: number;
    startY: number;
    endD: number;
    endM: number;
    endY: number;
    location: string;
    name: string;
    persons: number;
    pf: number;
    pt: number;
    conditions: Array<Condition>;
    days: number;
}