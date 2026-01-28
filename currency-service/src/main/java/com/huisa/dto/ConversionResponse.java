package com.huisa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversionResponse {

    private String from;
    private String to;
    private BigDecimal amount;
    private BigDecimal convertedAmount;
    private BigDecimal rate;
    private LocalDate date;

}
