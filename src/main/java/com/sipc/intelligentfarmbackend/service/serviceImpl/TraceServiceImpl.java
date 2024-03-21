package com.sipc.intelligentfarmbackend.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.exception.BaseException;
import com.sipc.intelligentfarmbackend.mapper.NoticeMapper;
import com.sipc.intelligentfarmbackend.mapper.PlantMapper;
import com.sipc.intelligentfarmbackend.mapper.PlantStatusMapper;
import com.sipc.intelligentfarmbackend.mapper.ResourceNoticeMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.*;
import com.sipc.intelligentfarmbackend.pojo.dto.PlantDto;
import com.sipc.intelligentfarmbackend.pojo.dto.UserDto;
import com.sipc.intelligentfarmbackend.pojo.model.para.NoticePara;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.UserService;
import com.sipc.intelligentfarmbackend.service.TraceService;
import com.sipc.intelligentfarmbackend.utils.JwtUtils;
import com.sipc.intelligentfarmbackend.utils.MinioUtil;
import com.sipc.intelligentfarmbackend.utils.TokenThreadLocalUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class TraceServiceImpl implements TraceService {

    private PlantMapper plantMapper;
    private NoticeMapper noticeMapper;
    private UserService userService;
    private ResourceNoticeMapper resourceNoticeMapper;
    private PlantStatusMapper plantStatusMapper;
    private MinioUtil minioUtil;

    @Override
    public void addTrace(Plant plant) {
        User user = JwtUtils.getUserByToken(TokenThreadLocalUtil.getInstance().getToken());
        log.error(plant.toString());
        // 初始化作物状态为种植中
        plant.setStatus(0);
        plant.setUserId(user.getId());
        try {
            plantMapper.insert(plant);
        }catch (Exception e){
            throw new BaseException("新增溯源数据异常");
        }
    }

    @Override
    public PageResult<PlantDto> getPlantList(Integer pageNum, Integer pageSize, String keyWord) {
        User user = JwtUtils.getUserByToken(TokenThreadLocalUtil.getInstance().getToken());
        user = userService.getUserInfoById(user.getId());
        Page<Plant> page = new Page<>(pageNum,pageSize);
        PageResult<PlantDto> pageResult = new PageResult<>();
        Page<Plant> page1;

        if(user.getRoleId() == 6){
            page1 = plantMapper.selectAllPlantPages(page,keyWord);
            page.setTotal(page1.getRecords().size());
            page.setPages(page1.getTotal() / pageSize);
            page = plantMapper.selectAllPlantInfoPage(page,pageSize,(pageNum - 1) * pageSize,keyWord);
        }else {
            page1 = plantMapper.selectAllPlantPage(page,keyWord,user.getId());
            page.setTotal(page1.getRecords().size());
            page.setPages(page1.getTotal() / pageSize);
            page = plantMapper.selectPlantInfoPage(page, pageSize, (pageNum - 1) * pageSize, keyWord, user.getId());
        }
        List<Plant> plantList = page.getRecords();
        List<PlantDto> plantDtoList = new ArrayList<>(plantList.size());
        for (Plant plant : plantList) {
            PlantDto plantDto = new PlantDto();
            BeanUtils.copyProperties(plant,plantDto);
            plantDto.setFarmer(user.getName());
            plantDto.setPhone(user.getPhone());
            plantDtoList.add(plantDto);
        }
        pageResult.setPageSize(page.getSize());
        pageResult.setPageNo(page.getCurrent());
        pageResult.setRecords(plantDtoList);
        pageResult.setPages(page.getPages());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }

    @Override
    public PageResult<DriverNotice> getNoticePage(Integer pageNum, Integer pageSize, String keyWord) {
        User user = JwtUtils.getUserByToken(TokenThreadLocalUtil.getInstance().getToken());
        Page<DriverNotice> page = new Page<>(pageNum,pageSize);
        PageResult<DriverNotice> pageResult = new PageResult<>();
        Page<DriverNotice> page1;
        if(user.getRoleId() == 6){
            page1 = noticeMapper.selectAllPlantPages(page,keyWord);
            page.setTotal(page1.getRecords().size());
            page.setPages(page1.getTotal() / pageSize);
            pageResult.praisePage(noticeMapper.selectAllNoticeInfoPage(page,pageSize,(pageNum - 1) * pageSize,keyWord));
        }else {
            page1 = noticeMapper.selectAllPlantPagesU(page,keyWord,user.getId());
            page.setTotal(page1.getRecords().size());
            page.setPages(page1.getTotal() / pageSize);
            pageResult.praisePage(noticeMapper.selectNoticeInfoPage(page, pageSize, (pageNum - 1) * pageSize, keyWord, user.getId()));
        }
        return pageResult;
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
        User destination = userService.getUserInfoById(noticePara.getDestId());
        ResourceNotice resourceNotice = new ResourceNotice();
        // 封装司机信息
        driverNotice.setDriverId(noticePara.getDriverId());
        driverNotice.setSource(noticer.getName());
        driverNotice.setDestination(destination.getEnterpriseName());
        driverNotice.setNotes(noticePara.getNotes());
        driverNotice.setSourceId(noticePara.getSourceId());
        driverNotice.setDestId(noticePara.getDestId());
        driverNotice.setTime(LocalDateTime.now());
        driverNotice.setTraceId(noticePara.getTraceId());
        driverNotice.setStatus(0);
        // 封装原料商信息
        resourceNotice.setSource(noticer.getEnterpriseName());
        resourceNotice.setFammer(noticer.getName());
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

    @Override
    public List<UserDto> getDriverList() {
        List<UserDto> userDtoList = new LinkedList<>();
        List<User> userList = userService.getSpecialUser(2);
        for (User user : userList) {
            userDtoList.add(new UserDto(user.getId(), user.getName()));
        }
        return userDtoList;
    }

    @Override
    public void addRecords(PlantStatus plantStatus) {
        if(plantStatusMapper.insert(plantStatus) == 0) {
            throw new BaseException("添加失败");
        }
    }

    @Override
    public List<PlantStatus> getRecords(Integer traceId) {
        List<PlantStatus> plantStatuses = plantStatusMapper.selectList(new LambdaQueryWrapper<PlantStatus>().eq(PlantStatus::getPlantId,traceId));
        for (PlantStatus plantStatus : plantStatuses) {
            if (!plantStatus.getImgUrl().equals("")) {
                plantStatus.setImgUrl(minioUtil.downloadFile(plantStatus.getImgUrl()));
            }
        }
        return plantStatuses;
    }
}
