export class WeatherDTO {
    id?: string;
 
    constructor(data?: Partial<WeatherDTO>) {
        Object.assign(this, data);
    }
}