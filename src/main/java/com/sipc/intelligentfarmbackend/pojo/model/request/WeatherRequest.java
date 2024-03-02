package com.sipc.intelligentfarmbackend.pojo.model.request;

import com.sipc.intelligentfarmbackend.pojo.domain.CityWeather;
import lombok.Data;

@Data
public class WeatherRequest {
    private String code;
    private String msg;
    private String counts;
    private CityWeather data;
}
