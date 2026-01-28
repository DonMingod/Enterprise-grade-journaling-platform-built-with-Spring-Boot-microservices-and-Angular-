package com.huisa.service.impl;

import com.huisa.dto.Country;
import com.huisa.exception.CountryNotFoundException;
import com.huisa.service.CountryService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final RestTemplate restTemplate;


    @Value("${restcountries.api.base-url}")
    private String baseUrl;

    private static final String FIELDS = "?fields=name,cca2,cca3,capital,region,subregion,population,languages,currencies,flags";

    @Override
    public List<Country> getAllCountries() {
        log.info("Consultando todos los países");
        String url = baseUrl + "/all" + FIELDS;
        return fetchCountries(url, "países");
    }

    @Override
    public Country getCountryByCode(String code) {
        log.info("Consultando país con código: {}", code);
        String url = baseUrl + "/alpha/" + code + FIELDS;
        List<Country> countries = fetchCountries(url, "país con código: " + code);
        return countries.stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("País no encontrado: " + code));
    }

    @Override
    public List<Country> searchCountryByName(String name) {
        log.info("Buscando países con nombre: {}", name);
        String url = baseUrl + "/name/" + name + FIELDS;
        return fetchCountries(url, "países con nombre: " + name);
    }

    @Override
    public List<Country> getCountriesByRegion(String region) {
        log.info("Consultando países de la región: {}", region);
        String url = baseUrl + "/region/" + region + FIELDS;
        return fetchCountries(url, "países en región: " + region);
    }

    // Método privado que centraliza toda la lógica de consulta
    private List<Country> fetchCountries(String url, String errorContext) {
        try {
            ResponseEntity<List<Country>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Country>>() {}
            );

            List<Country> countries = response.getBody() != null ? response.getBody() : Collections.emptyList();
            log.info("Se encontraron {} resultados", countries.size());
            return countries;

        } catch (HttpClientErrorException.NotFound e) {
            log.error("No se encontraron {}", errorContext);
            throw new CountryNotFoundException("No se encontraron " + errorContext);
        }
    }
}
