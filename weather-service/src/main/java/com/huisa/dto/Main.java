package com.huisa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class Main {

    private BigDecimal temp;

    @JsonProperty("feels_like")
    private BigDecimal feelsLike;

    @JsonProperty("temp_min")
    private BigDecimal tempMin;

    @JsonProperty("temp_max")
    private BigDecimal tempMax;

    private Integer pressure;

    private Integer humidity;
}