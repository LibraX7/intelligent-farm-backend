package com.sipc.intelligentfarmbackend.service;

import com.sipc.intelligentfarmbackend.pojo.domain.CityWeather;

public interface CityService {
    Boolean containCity(String cityName);
    CityWeather getWeather(String city);
}
