package com.huisa.configclient;


import com.huisa.dto.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "exchange-rate-api",
        url = "${currency.api.base-url}",
        fallback = ExchangeRateClientFallback.class
)
public interface ExchangeRateClient {

    @GetMapping("/{baseCurrency}")
    ExchangeRateResponse getExchangeRates(@PathVariable String baseCurrency);
}