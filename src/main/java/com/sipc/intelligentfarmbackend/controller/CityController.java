package com.sipc.intelligentfarmbackend.controller;

import com.sipc.intelligentfarmbackend.pojo.domain.CityWeather;
import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;
import com.sipc.intelligentfarmbackend.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;



/**
 * 导入天气城市的代码
 */
@CrossOrigin
@AllArgsConstructor
@RestController
public class CityController {
    private CityService cityService;
    @GetMapping("/get/weather")
    public CommonResult<CityWeather> getWeather(@RequestParam String cityName){
        return CommonResult.success(cityService.getWeather(cityName));
    }
//    导入丫丫天气城市数据测试代码
//    @PostMapping("/weather")
//    public String test(@RequestBody List<CityDto> cityDtos){
//        for (CityDto cityDto : cityDtos) {
//            List<CityDto> cityList = cityDto.getList();
//            for (CityDto cityDto1 : cityList) {
//                if (cityDto1.getList() == null){
//                    City city = new City();
//                    BeanUtils.copyProperties(cityDto1,city);
//                    cityMapper.insert(city);
//                }else {
//                    List<CityDto> cityDtoList = cityDto1.getList();
//                    for (CityDto dto : cityDtoList) {
//                        City city = new City();
//                        BeanUtils.copyProperties(dto,city);
//                         cityMapper.insert(city);
//                    }
//                }
//
//            }
//        }
//        return "ok";
//    }

}