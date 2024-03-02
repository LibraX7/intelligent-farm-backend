package com.sipc.intelligentfarmbackend.controller;

import com.sipc.intelligentfarmbackend.mapper.CityMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.City;
import com.sipc.intelligentfarmbackend.pojo.dto.CityDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 导入天气城市的代码
 */
@AllArgsConstructor
@RestController
public class CityController {
    private CityMapper cityMapper;
    @PostMapping("/weather")
    public String test(@RequestBody List<CityDto> cityDtos){
        for (CityDto cityDto : cityDtos) {
            List<CityDto> cityList = cityDto.getList();
            for (CityDto cityDto1 : cityList) {
                if (cityDto1.getList() == null){
                    City city = new City();
                    BeanUtils.copyProperties(cityDto1,city);
                    cityMapper.insert(city);
                }else {
                    List<CityDto> cityDtoList = cityDto1.getList();
                    for (CityDto dto : cityDtoList) {
                        City city = new City();
                        BeanUtils.copyProperties(dto,city);
                         cityMapper.insert(city);
                    }
                }

            }
        }
        return "ok";
    }
}