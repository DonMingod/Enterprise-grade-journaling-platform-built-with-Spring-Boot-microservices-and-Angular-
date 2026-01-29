package com.huisa.client;


import com.huisa.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherApiClient {

    private  final RestTemplate restTemplate;

    @Value("${weather.api.base-url}")
    private String baseUrl;

    @Value("${weather.api.api-key}")
    private String apiKey;

    @Value("${weather.api.default-units:metric}")
    private String defaultUnits;

    @Value("${weather.api.default-lang:es}")
    private String defaultLang;

    public WeatherResponse getCurrentWeatherByCity(String city) {
        try {
            log.info("Obteniendo clima para ciudad: {}", city);

            String url = UriComponentsBuilder.fromUriString(baseUrl + "/weather")
                    .queryParam("q", city)
                    .queryParam("appid", apiKey)
                    .queryParam("units", defaultUnits)
                    .queryParam("lang", defaultLang)
                    .toUriString();

            return restTemplate.getForObject(url, WeatherResponse.class);
        } catch (Exception e) {
            log.error("Error al obtener clima para {}: {}", city, e.getMessage());
            return getDefaultWeatherResponse(city);
        }
    }

    public WeatherResponse getCurrentWeatherByCoordinates(Double lat, Double lon) {
        try {
            log.info("Obteniendo clima para coordenadas: {}, {}", lat, lon);

            String url = UriComponentsBuilder.fromUriString(baseUrl + "/weather")
                    .queryParam("lat", lat)
                    .queryParam("lon", lon)
                    .queryParam("appid", apiKey)
                    .queryParam("units", defaultUnits)
                    .queryParam("lang", defaultLang)
                    .toUriString();

            return restTemplate.getForObject(url, WeatherResponse.class);
        } catch (Exception e) {
            log.error("Error al obtener clima para coordenadas {}, {}: {}", lat, lon, e.getMessage());
            return getDefaultWeatherResponse("Desconocido");
        }
    }

    private WeatherResponse getDefaultWeatherResponse(String city) {
        log.warn("Retornando datos por defecto para: {}", city);

        Weather weather = new Weather();
        weather.setId(800);
        weather.setMain("Clear");
        weather.setDescription("cielo despejado");
        weather.setIcon("01d");

        Main main = new Main();
        main.setTemp(new BigDecimal("20.0"));
        main.setFeelsLike(new BigDecimal("19.5"));
        main.setTempMin(new BigDecimal("18.0"));
        main.setTempMax(new BigDecimal("22.0"));
        main.setHumidity(60);
        main.setPressure(1013);

        Wind wind = new Wind();
        wind.setSpeed(new BigDecimal("3.5"));
        wind.setDeg(180);

        Clouds clouds = new Clouds();
        clouds.setAll(0);

        Coord coord = new Coord();
        coord.setLat(new BigDecimal("0.0"));
        coord.setLon(new BigDecimal("0.0"));

        Sys sys = new Sys();
        sys.setCountry("XX");

        WeatherResponse response = new WeatherResponse();
        response.setName(city);
        response.setWeather(List.of(weather));
        response.setMain(main);
        response.setWind(wind);
        response.setClouds(clouds);
        response.setCoord(coord);
        response.setSys(sys);
        response.setCod(200);

        return response;
    }

}
