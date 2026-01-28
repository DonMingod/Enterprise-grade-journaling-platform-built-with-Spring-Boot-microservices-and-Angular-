package com.huisa.services.impl;

import com.huisa.configclient.ExchangeRateClient;
import com.huisa.dto.ConversionRequest;
import com.huisa.dto.ConversionResponse;
import com.huisa.dto.ExchangeRateResponse;
import com.huisa.services.CurrencyService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private  final ExchangeRateClient exchangeRateClient;

    @Override
    @Cacheable(value = "exchangeRates", key = "#baseCurrency")
    @CircuitBreaker(name = "currencyApi", fallbackMethod = "getExchangeRatesFallback")
    @Retry(name = "currencyApi")
    public ExchangeRateResponse getExchangeRates(String baseCurrency) {
        return exchangeRateClient.getExchangeRate(baseCurrency.toUpperCase());
    }

    @Override
    public ConversionResponse convertCurrency(ConversionRequest conversionRequest) {
        log.info("Convirtiendo {} {} a {}",
                conversionRequest.getAmount()
                , conversionRequest.getFrom()
                , conversionRequest.getTo());
        ExchangeRateResponse rates = getExchangeRates(conversionRequest.getFrom());
        BigDecimal rate = rates.getRates().get(conversionRequest.getTo().toUpperCase());
        BigDecimal convertedAmount = conversionRequest.getAmount().multiply(rate)
                .setScale(2, RoundingMode.HALF_UP);
        return ConversionResponse.builder()
                .from(conversionRequest.getFrom().toUpperCase())
                .to(conversionRequest.getTo().toUpperCase())
                .amount(conversionRequest.getAmount())
                .convertedAmount(convertedAmount)
                .rate(rate)
                .date(rates.getDate())
                .build();
    }
    @Override
    public BigDecimal getRate(String from, String to) {
        log.info("Obteniendo tasa de cambio de {} a {}", from, to);
        ExchangeRateResponse rates = getExchangeRates(from);
        BigDecimal rate = rates.getRates().get(to.toUpperCase());
        if (rate == null) {
            throw new RuntimeException("Moneda no soportada: " + to);
        }
        return rate;
    }
    public ExchangeRateResponse getExchangeRatesFallback(String baseCurrency, Throwable throwable) {
        log.error("Error al obtener las tasas de cambio para la moneda base {}: {}",
                baseCurrency, throwable.getMessage());
        throw new RuntimeException("Servicio de tasas de cambio no disponible en este momento.");
    }
}
