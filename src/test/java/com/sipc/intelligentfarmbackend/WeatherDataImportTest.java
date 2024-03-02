//package com.sipc.intelligentfarmbackend;
//
//import com.sipc.intelligentfarmbackend.mapper.CityMapper;
//import com.sipc.intelligentfarmbackend.pojo.domain.City;
//import com.sipc.intelligentfarmbackend.pojo.dto.CityDto;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//@AllArgsConstructor
//@RestController
//public class WeatherDataImportTest {
//    private CityMapper cityMapper;
//    @PostMapping("/weather")
//    public String test(@RequestBody List<CityDto> cityDtos){
//        for (CityDto cityDto : cityDtos) {
//            List<City> cityList = cityDto.getList();
//            for (City city : cityList) {
//                cityMapper.insert(city);
//            }
//        }
//        return "ok";
//    }
//}
