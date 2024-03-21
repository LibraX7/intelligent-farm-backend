package com.sipc.intelligentfarmbackend.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sipc.intelligentfarmbackend.exception.BaseException;
import com.sipc.intelligentfarmbackend.mapper.CityMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.City;
import com.sipc.intelligentfarmbackend.pojo.domain.CityWeather;
import com.sipc.intelligentfarmbackend.pojo.model.request.WeatherRequest;
import com.sipc.intelligentfarmbackend.service.CityService;
import com.sipc.intelligentfarmbackend.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    private CityMapper cityMapper;

    @Override
    public Boolean containCity(String cityName) {
        LambdaQueryWrapper<City> lbq = new LambdaQueryWrapper<>();
        lbq.eq(StringUtils.isNotEmpty(cityName),City::getName,cityName);
        return cityMapper.selectOne(lbq) != null;
    }

    @Override
    public CityWeather getWeather(String city) {
            City city1 = cityMapper.selectOne(new LambdaQueryWrapper<City>()
                .eq(City::getName,city));
            if(city1 == null){
                throw new BaseException("请指定合法的城市名称");
            }
            String apiUrl = "https://api.yytianqi.com/forecast7d?city=" + city1.getCity_id()+ "&key=1wdpff40ltc8nj8b";
            WeatherRequest entity = new WeatherRequest();
            try {
                // 创建HttpClient
                HttpClient httpClient = HttpClient.newHttpClient();

                // 创建GET请求
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(apiUrl))
                        .build();
                // 发送GET请求并接收响应
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                // 检查响应状态码
                if (response.statusCode() == 200) {
                    // 使用Jackson库将JSON数据映射到实体类
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.registerModule(new JavaTimeModule());
                    entity = objectMapper.readValue(response.body(), WeatherRequest.class);
                } else {
                    throw new BaseException("获取天气失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return entity.getData();
    }
}
