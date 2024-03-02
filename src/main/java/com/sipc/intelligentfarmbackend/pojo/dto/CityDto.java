package com.sipc.intelligentfarmbackend.pojo.dto;

import com.sipc.intelligentfarmbackend.pojo.domain.City;
import lombok.Data;

import java.util.List;
@Data
public class CityDto {
    private String city_id;
    private String name;
    private String en;
    private List<CityDto> list;
}
