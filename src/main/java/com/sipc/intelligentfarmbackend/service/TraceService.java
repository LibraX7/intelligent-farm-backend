package com.sipc.intelligentfarmbackend.service;

import com.sipc.intelligentfarmbackend.pojo.domain.DriverNotice;
import com.sipc.intelligentfarmbackend.pojo.domain.Plant;
import com.sipc.intelligentfarmbackend.pojo.model.para.NoticePara;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;

import java.util.List;

public interface TraceService {
    PageResult<Plant> getPlantList(Integer pageNum, Integer pageSize, String keyWord );
    PageResult<DriverNotice> getNoticePage(Integer driverId, Integer pageNum, Integer pageSize, String keyWord);
    void addNoticeToDriver(NoticePara notice);
    void deleteTrace(List<Integer> ids);
    void addTrace(Plant plant);
    void beginTransport(Integer id);
    void endTransport(Integer id);
    void deleteTransport(List<Integer> ids);
}
