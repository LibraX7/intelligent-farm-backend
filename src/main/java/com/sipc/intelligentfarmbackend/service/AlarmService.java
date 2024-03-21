package com.sipc.intelligentfarmbackend.service;

import com.sipc.intelligentfarmbackend.pojo.domain.DiseaseAlarm;
import com.sipc.intelligentfarmbackend.pojo.domain.FieldStatusAlarm;
import com.sipc.intelligentfarmbackend.pojo.dto.EnvironmentAlarmDto;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;

import java.util.List;

public interface AlarmService {
    PageResult<DiseaseAlarm> selectPage(Integer pageNum, Integer pageSize, String keyWord);
    void solveProblem(Integer id);
    void deleteDisease(List<Integer> ids);
    PageResult<EnvironmentAlarmDto> selectEnvironmentPage(Integer pageNum, Integer pageSize, String keyWord);
    void deleteEnvironment(List<Integer> ids);
    void updateAlarm(List<FieldStatusAlarm> fieldStatusAlarm);
}
