package com.huisa.controller;


import com.huisa.dto.ConversionRequest;
import com.huisa.services.CurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/rates/{baseCurrency}")
    public ResponseEntity<?> getExchangeRates(@PathVariable String baseCurrency) {
        return ResponseEntity.ok(currencyService.getExchangeRates(baseCurrency));
    }

    @PostMapping("/convert")
    public ResponseEntity<?> convertCurrency(@Valid @RequestBody ConversionRequest conversionRequest) {
        return ResponseEntity.ok(currencyService.convertCurrency(conversionRequest));
    }

    @GetMapping("/rate")
    public ResponseEntity<?> getRate(@RequestParam String from, @RequestParam String to) {
        return ResponseEntity.ok(currencyService.getRate(from, to));
    }

}
