package com.huisa.service;

import com.huisa.dto.WeatherResponse;
import com.huisa.dto.WeatherSimpleResponse;

public interface WeatherService {

    WeatherResponse getCurrentWeatherByCity(String city);

    WeatherResponse getCurrentWeatherByCoordinates(Double latitude, Double longitude);

    WeatherSimpleResponse getSimpleWeatherByCity(String city);
}
