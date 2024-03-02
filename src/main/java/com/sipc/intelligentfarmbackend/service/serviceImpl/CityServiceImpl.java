package com.sipc.intelligentfarmbackend.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sipc.intelligentfarmbackend.mapper.CityMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.City;
import com.sipc.intelligentfarmbackend.service.CityService;
import com.sipc.intelligentfarmbackend.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
