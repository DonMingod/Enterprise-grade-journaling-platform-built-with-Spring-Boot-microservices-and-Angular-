package com.huisa.service;

import com.huisa.dto.Country;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountries();

    Country getCountryByCode(String code);

    List<Country> searchCountryByName(String name);

    List<Country> getCountriesByRegion(String region);

}
