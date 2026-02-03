export interface WeatherResponseDto {
    name: string;
    weather: Array<{
        id: number;
        main: string;
        description: string;
        icon: string;
    }>;
    main: {
        temp: number;
        feels_like: number;
        temp_min: number;
        temp_max: number;
        pressure: number;
        humidity: number;
        sea_level?: number;
        grnd_level?: number;

    };
    wind?:{
        speed: number;
        deg: number;
        gust?: number;
    };
    dt?: number;
    sys?: {
        country: string;
        sunrise?: number;
        sunset?: number;
    }
}
