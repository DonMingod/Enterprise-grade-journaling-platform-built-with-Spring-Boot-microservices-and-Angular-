package com.huisa.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class WeatherSimpleResponse {
    private String city;
    private String country;
    private BigDecimal temperature;
    private BigDecimal feelsLike;
    private String description;
    private String icon;
    private Integer humidity;
    private BigDecimal windSpeed;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
