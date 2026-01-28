package com.huisa.controller;


import com.huisa.exception.CountryNotFoundException;
import com.huisa.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;


    @GetMapping
    public ResponseEntity<?> getAllCountries() {
        log.info("GET /countries - Obteniendo todos los países");
        return ResponseEntity.ok(countryService.getAllCountries());
    }
    @GetMapping("/code/{code}")
    public ResponseEntity<?> getCountryByCode(@PathVariable String code) {
        log.info("GET /countries/code/{}", code);
        return ResponseEntity.ok(countryService.getCountryByCode(code));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCountryByName(@RequestParam String name) {
        log.info("GET /countries/search?name={}", name);
        return ResponseEntity.ok(countryService.searchCountryByName(name));
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<?> getCountriesByRegion(@PathVariable String region) {
        log.info("GET /countries/region/{}", region);
        return ResponseEntity.ok(countryService.getCountriesByRegion(region));
    }


    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<?> handleCountryNotFound(CountryNotFoundException ex) {
        log.error("Country not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "error", "NOT_FOUND",
                        "message", ex.getMessage()
                ));
    }

}
