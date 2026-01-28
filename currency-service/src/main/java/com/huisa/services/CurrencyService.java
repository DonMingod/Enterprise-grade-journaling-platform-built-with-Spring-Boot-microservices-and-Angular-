package com.huisa.services;

import com.huisa.dto.ConversionRequest;
import com.huisa.dto.ConversionResponse;
import com.huisa.dto.ExchangeRateResponse;

import java.math.BigDecimal;

public interface CurrencyService {
    ExchangeRateResponse getExchangeRates(String baseCurrency);

    ConversionResponse convertCurrency(ConversionRequest conversionRequest);

    BigDecimal getRate (String from, String to);


}
