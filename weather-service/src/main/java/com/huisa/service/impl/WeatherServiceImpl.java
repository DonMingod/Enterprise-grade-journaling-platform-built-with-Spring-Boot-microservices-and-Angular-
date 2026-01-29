package com.huisa.service.impl;

import com.huisa.client.WeatherApiClient;
import com.huisa.dto.WeatherResponse;
import com.huisa.dto.WeatherSimpleResponse;
import com.huisa.service.WeatherService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherApiClient weatherApiClient;

    @Override
    @CircuitBreaker(name = "weatherApi", fallbackMethod = "getCurrentWeatherByCityFallback")
    @Retry(name = "weatherApi")
    public WeatherResponse getCurrentWeatherByCity(String city) {
        log.info("Obteniendo clima actual para ciudad: {}", city);
        return weatherApiClient.getCurrentWeatherByCity(city);
    }

    @Override
    @CircuitBreaker(name = "weatherApi", fallbackMethod = "getCurrentWeatherByCoordinatesFallback")
    @Retry(name = "weatherApi")
    public WeatherResponse getCurrentWeatherByCoordinates(Double latitude, Double longitude) {
        log.info("Obteniendo clima actual para coordenadas: {}, {}", latitude, longitude);
        return weatherApiClient.getCurrentWeatherByCoordinates(latitude, longitude);
    }

    @Override
    public WeatherSimpleResponse getSimpleWeatherByCity(String city) {
        log.info("Obteniendo clima simplificado para: {}", city);
        WeatherResponse fullWeather = getCurrentWeatherByCity(city);

        if (fullWeather == null) {
            log.error("No se pudo obtener datos del clima para: {}", city);
            return null;
        }

        return convertToSimpleResponse(fullWeather);
    }

    private WeatherSimpleResponse convertToSimpleResponse(WeatherResponse weather) {
        try {
            if (weather == null) {
                log.error("WeatherResponse es null");
                return null;
            }

            String description = "N/A";
            String icon = "01d";
            if (weather.getWeather() != null && !weather.getWeather().isEmpty()) {
                description = weather.getWeather().get(0).getDescription();
                icon = weather.getWeather().get(0).getIcon();
            }

            return WeatherSimpleResponse.builder()
                    .city(weather.getName() != null ? weather.getName() : "Unknown")
                    .country(weather.getSys() != null ? weather.getSys().getCountry() : "XX")
                    .temperature(weather.getMain() != null ? weather.getMain().getTemp() : null)
                    .feelsLike(weather.getMain() != null ? weather.getMain().getFeelsLike() : null)
                    .description(description)
                    .icon(icon)
                    .humidity(weather.getMain() != null ? weather.getMain().getHumidity() : null)
                    .windSpeed(weather.getWind() != null ? weather.getWind().getSpeed() : null)
                    .latitude(weather.getCoord() != null ? weather.getCoord().getLat() : null)
                    .longitude(weather.getCoord() != null ? weather.getCoord().getLon() : null)
                    .build();
        } catch (Exception e) {
            log.error("Error al convertir WeatherResponse a Simple: {}", e.getMessage(), e);
            return null;
        }
    }

    // Métodos fallback
    private WeatherResponse getCurrentWeatherByCityFallback(String city, Exception e) {
        log.error("Fallback activado para ciudad {}: {}", city, e.getMessage());
        return weatherApiClient.getCurrentWeatherByCity(city);
    }

    private WeatherResponse getCurrentWeatherByCoordinatesFallback(Double lat, Double lon, Exception e) {
        log.error("Fallback activado para coordenadas {}, {}: {}", lat, lon, e.getMessage());
        return weatherApiClient.getCurrentWeatherByCoordinates(lat, lon);
    }
}
