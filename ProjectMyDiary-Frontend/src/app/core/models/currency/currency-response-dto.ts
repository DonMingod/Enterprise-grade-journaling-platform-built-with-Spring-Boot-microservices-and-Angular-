export interface CurrencyResponseDto {
    result: number;
    base_code: string;
    target_code: string;
    conversion_rate: number;
    conversion_result: number;
    conversion_rates: Record<string, number>;
}
