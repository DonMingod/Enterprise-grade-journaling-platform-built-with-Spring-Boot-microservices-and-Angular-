package com.huisa.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country{

    @JsonProperty("name")
    private Name name;

    @JsonProperty("cca2")
    private String cca2;

    @JsonProperty("cca3")
    private String cca3;

    @JsonProperty("capital")
    private List<String> capital;

    @JsonProperty("region")
    private String region;

    @JsonProperty("subregion")
    private String subregion;

    @JsonProperty("population")
    private Long population;

    @JsonProperty("languages")
    private Map<String, String> languages;

    @JsonProperty("currencies")
    private Map<String, Currency> currencies;

    @JsonProperty("flags")
    private Flags flags;

    @JsonProperty("timezones")
    private List<String> timezones;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Name {
        @JsonProperty("common")
        private String common;

        @JsonProperty("official")
        private String official;

        @JsonProperty("nativeName")
        private Map<String, NativeName> nativeName;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NativeName {
        @JsonProperty("official")
        private String official;

        @JsonProperty("common")
        private String common;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Currency {
        @JsonProperty("name")
        private String name;

        @JsonProperty("symbol")
        private String symbol;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Flags {
        @JsonProperty("png")
        private String png;

        @JsonProperty("svg")
        private String svg;

        @JsonProperty("alt")
        private String alt;
    }
}
