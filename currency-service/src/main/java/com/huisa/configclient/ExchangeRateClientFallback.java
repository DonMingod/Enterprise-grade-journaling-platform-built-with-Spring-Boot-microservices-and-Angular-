package com.huisa.configclient;

import com.huisa.dto.ExchangeRateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ExchangeRateClientFallback  implements  ExchangeRateClient{

    @Override
    public ExchangeRateResponse getExchangeRates(String baseCurrency) {

        Map<String, BigDecimal> defaultRates = new HashMap<>();
        defaultRates.put("USD", BigDecimal.ONE);
        defaultRates.put("EUR", new BigDecimal("0.92"));
        defaultRates.put("GBP", new BigDecimal("0.79"));
        defaultRates.put("PEN", new BigDecimal("3.75"));
        defaultRates.put("MXN", new BigDecimal("17.05"));
        defaultRates.put("ARS", new BigDecimal("880.50"));
        defaultRates.put("CLP", new BigDecimal("800.00"));
        defaultRates.put("COP", new BigDecimal("3900.00"));

        return ExchangeRateResponse.builder()
                .base(baseCurrency)
                .date(LocalDate.now())
                .rates(defaultRates)
                .build();
    }
}
