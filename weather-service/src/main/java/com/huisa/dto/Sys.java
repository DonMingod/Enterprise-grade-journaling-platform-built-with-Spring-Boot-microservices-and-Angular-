package com.huisa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sys {

    private String country;
    private Long sunrise;
    private Long sunset;
}
