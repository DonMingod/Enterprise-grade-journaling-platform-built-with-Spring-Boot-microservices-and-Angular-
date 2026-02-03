export interface CountryResponseDto {
    name:{
        common: string;
        official: string;
        nativeName?: Record<string, {official: string; common: string;}>;
    };
    cca2: string;
    cca3: string;
    region: string;
    subregion?: string;
    population: number;
    capital?: string[];
    lenguages?: Record<string, string>;
    currencies?: Record<string, {name: string; symbol: string;}>;
    flags:{svg: string; png: string; alt?: string;};
    timezones: string[];

}
