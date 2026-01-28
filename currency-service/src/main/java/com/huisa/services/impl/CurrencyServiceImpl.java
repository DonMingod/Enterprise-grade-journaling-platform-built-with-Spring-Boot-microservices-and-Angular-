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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final ExchangeRateClient exchangeRateClient;

    @Override
    @CircuitBreaker(name = "currencyApi", fallbackMethod = "getExchangeRatesFallback")
    @Retry(name = "currencyApi")
    public ExchangeRateResponse getExchangeRates(String baseCurrency) {
        log.info("Consultando tasas de cambio para: {}", baseCurrency);
        return exchangeRateClient.getExchangeRates(baseCurrency.toUpperCase());
    }

    @Override
    public ConversionResponse convertCurrency(ConversionRequest request) {
        log.info("Convirtiendo {} {} a {}",
                request.getAmount(), request.getFrom(), request.getTo());

        ExchangeRateResponse rates = getExchangeRates(request.getFrom());

        BigDecimal rate = Optional.ofNullable(rates.getRates().get(request.getTo().toUpperCase()))
                .orElseThrow(() -> new RuntimeException("Moneda no soportada: " + request.getTo()));

        BigDecimal convertedAmount = request.getAmount()
                .multiply(rate)
                .setScale(2, RoundingMode.HALF_UP);

        return ConversionResponse.builder()
                .from(request.getFrom().toUpperCase())
                .to(request.getTo().toUpperCase())
                .amount(request.getAmount())
                .convertedAmount(convertedAmount)
                .rate(rate)
                .date(rates.getDate())
                .build();
    }

    @Override
    public BigDecimal getRate(String from, String to) {
        log.info("Obteniendo tasa de {} a {}", from, to);
        ExchangeRateResponse rates = getExchangeRates(from);

        return Optional.ofNullable(rates.getRates().get(to.toUpperCase()))
                .orElseThrow(() -> new RuntimeException("Moneda no soportada: " + to));
    }

    public ExchangeRateResponse getExchangeRatesFallback(String baseCurrency, Throwable throwable) {
        log.error("Error al consultar API externa: {}", throwable.getMessage());
        log.warn("Usando tasas de respaldo");

        Map<String, BigDecimal> fallbackRates = new HashMap<>();
        fallbackRates.put("USD", BigDecimal.ONE);
        fallbackRates.put("EUR", new BigDecimal("0.92"));
        fallbackRates.put("GBP", new BigDecimal("0.79"));
        fallbackRates.put("PEN", new BigDecimal("3.75"));
        fallbackRates.put("MXN", new BigDecimal("17.05"));
        fallbackRates.put("ARS", new BigDecimal("880.50"));
        fallbackRates.put("BRL", new BigDecimal("5.05"));
        fallbackRates.put("CLP", new BigDecimal("800.00"));
        fallbackRates.put("COP", new BigDecimal("3900.00"));

        return ExchangeRateResponse.builder()
                .base(baseCurrency.toUpperCase())
                .date(LocalDate.now())
                .rates(fallbackRates)
                .build();
    }
}
