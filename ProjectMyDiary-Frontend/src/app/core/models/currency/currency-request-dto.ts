import { from } from "rxjs";

export interface CurrencyRequestDto {
    from: string;
    to: string;
    amount: number;
}
