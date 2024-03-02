package com.sipc.intelligentfarmbackend.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.exception.BaseException;
import com.sipc.intelligentfarmbackend.mapper.NoticeMapper;
import com.sipc.intelligentfarmbackend.mapper.PlantMapper;
import com.sipc.intelligentfarmbackend.mapper.ResourceNoticeMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.DriverNotice;
import com.sipc.intelligentfarmbackend.pojo.domain.Plant;
import com.sipc.intelligentfarmbackend.pojo.domain.ResourceNotice;
import com.sipc.intelligentfarmbackend.pojo.domain.User;
import com.sipc.intelligentfarmbackend.pojo.model.para.NoticePara;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.UserService;
import com.sipc.intelligentfarmbackend.service.TraceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class TraceServiceImpl implements TraceService {

    private PlantMapper plantMapper;
    private NoticeMapper noticeMapper;
    private UserService userService;
    private ResourceNoticeMapper resourceNoticeMapper;

    @Override
    public void addTrace(Plant plant) {
        // 初始化作物状态为种植中
        plant.setStatus(0);
        try {
            plantMapper.insert(plant);
        }catch (Exception e){
            throw new BaseException("新增溯源数据异常");
        }
    }

    @Override
    public PageResult<Plant> getPlantList(Integer pageNum, Integer pageSize, String keyWord) {
        Page<Plant> page = new Page<>(pageNum,pageSize);
        PageResult<Plant> pageResult = new PageResult<>();
        return  pageResult.praisePage(plantMapper.selectPlantInfoPage(page,keyWord));

    }

    @Override
    public PageResult<DriverNotice> getNoticePage(Integer driverId, Integer pageNum, Integer pageSize, String keyWord) {
        Page<DriverNotice> page = new Page<>(pageNum,pageSize);
        PageResult<DriverNotice> pageResult = new PageResult<>();
        return  pageResult.praisePage(noticeMapper.selectNoticeInfoPage(page,keyWord,driverId));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteTrace(List<Integer> ids) {
        if(plantMapper.deleteBatchIds(ids) == 0){
            throw new BaseException("删除源数据异常");
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addNoticeToDriver(NoticePara noticePara) {
        // 处理信息
        DriverNotice driverNotice = new DriverNotice();
        User noticer = userService.getUserInfoById(noticePara.getSourceId());
        User destination = userService.getUserInfoById(noticePara.getResourceId());
        ResourceNotice resourceNotice = new ResourceNotice();
        // 封装司机信息
        driverNotice.setDriverId(noticePara.getDriverId());
        driverNotice.setSource(noticer.getName());
        driverNotice.setDestination(destination.getEnterpriseName());
        driverNotice.setNotes(noticePara.getNotes());
        driverNotice.setSourceId(noticePara.getSourceId());
        driverNotice.setDestId(noticePara.getResourceId());
        driverNotice.setTraceId(noticePara.getTraceId());
        driverNotice.setStatus(0);
        // 封装原料商信息
        resourceNotice.setSource(noticer.getEnterpriseName());
        resourceNotice.setFarmer(noticer.getName());
        resourceNotice.setStatus(0);
        resourceNotice.setTime(LocalDateTime.now());
        resourceNotice.setResourceId(resourceNotice.getResourceId());
        resourceNotice.setTraceId(driverNotice.getTraceId());
        // 插入数据
        if(noticeMapper.insert(driverNotice) == 0 || resourceNoticeMapper.insert(resourceNotice) == 0){
            throw new BaseException("通知失败，请检查参数是否完整");
        }
    }

    @Override
    public void beginTransport(Integer id) {
        DriverNotice driverNotice = noticeMapper.selectById(id);
        if(driverNotice == null){
            throw new BaseException("货单不存在");
        }
        driverNotice.setStatus(1);
        if(noticeMapper.updateById(driverNotice) == 0){
            throw new BaseException("更新状态失败");
        }
    }

    @Override
    public void endTransport(Integer id) {
        DriverNotice driverNotice = noticeMapper.selectById(id);
        if(driverNotice == null){
            throw new BaseException("货单不存在");
        }
        driverNotice.setStatus(2);
        if(noticeMapper.updateById(driverNotice) == 0){
            throw new BaseException("更新状态失败");
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteTransport(List<Integer> ids) {
        if(noticeMapper.deleteBatchIds(ids) == 0){
            throw new BaseException("删除数据失败");
        }
    }
}
