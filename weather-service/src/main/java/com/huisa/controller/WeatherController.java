package com.huisa.controller;


import com.huisa.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {

    private  final WeatherService weatherService;

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentWeather(@RequestParam String city) {
        return ResponseEntity.ok(weatherService.getCurrentWeatherByCity(city));
    }

    @GetMapping("/current/coordinates")
    public ResponseEntity<?> getCurrentWeatherByCoordinates(
            @RequestParam Double lat,
            @RequestParam Double lon) {
        return ResponseEntity.ok(weatherService.getCurrentWeatherByCoordinates(lat, lon));
    }

    @GetMapping("/simple")
    public ResponseEntity<?> getSimpleWeather(@RequestParam String city) {
        return ResponseEntity.ok(weatherService.getSimpleWeatherByCity(city));
    }

}
