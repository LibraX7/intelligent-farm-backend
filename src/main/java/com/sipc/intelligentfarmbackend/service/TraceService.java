package com.sipc.intelligentfarmbackend.service;

import com.sipc.intelligentfarmbackend.pojo.domain.DriverNotice;
import com.sipc.intelligentfarmbackend.pojo.domain.Plant;
import com.sipc.intelligentfarmbackend.pojo.domain.PlantStatus;
import com.sipc.intelligentfarmbackend.pojo.dto.PlantDto;
import com.sipc.intelligentfarmbackend.pojo.dto.UserDto;
import com.sipc.intelligentfarmbackend.pojo.model.para.NoticePara;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;

import java.util.List;

public interface TraceService {
    PageResult<PlantDto> getPlantList(Integer pageNum, Integer pageSize, String keyWord );
    PageResult<DriverNotice> getNoticePage( Integer pageNum, Integer pageSize, String keyWord);
    void addNoticeToDriver(NoticePara notice);
    void deleteTrace(List<Integer> ids);
    void addTrace(Plant plant);
    void beginTransport(Integer id);
    void endTransport(Integer id);
    void deleteTransport(List<Integer> ids);
    List<UserDto> getDriverList();
    void addRecords(PlantStatus plantStatus);
    List<PlantStatus> getRecords(Integer traceId);
}
