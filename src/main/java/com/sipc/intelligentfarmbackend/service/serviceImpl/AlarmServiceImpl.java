package com.sipc.intelligentfarmbackend.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.common.UnitConverse;
import com.sipc.intelligentfarmbackend.exception.BaseException;
import com.sipc.intelligentfarmbackend.mapper.DiseaseAlarmMapper;
import com.sipc.intelligentfarmbackend.mapper.EnvironmentAlarmMapper;
import com.sipc.intelligentfarmbackend.mapper.FieldStatusAlarmMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.*;
import com.sipc.intelligentfarmbackend.pojo.dto.EnvironmentAlarmDto;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.AlarmService;
import com.sipc.intelligentfarmbackend.utils.JwtUtils;
import com.sipc.intelligentfarmbackend.utils.MinioUtil;
import com.sipc.intelligentfarmbackend.utils.StringUtils;
import com.sipc.intelligentfarmbackend.utils.TokenThreadLocalUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AlarmServiceImpl implements AlarmService {
    private DiseaseAlarmMapper diseaseAlarmMapper;
    private EnvironmentAlarmMapper environmentAlarmMapper;
    private FieldStatusAlarmMapper fieldStatusAlarmMapper;
    private MinioUtil minioUtil;

    @Override
    public PageResult<DiseaseAlarm> selectPage(Integer pageNum, Integer pageSize, String keyWord) {
        // 查询分页结果
        Page<DiseaseAlarm> page = new Page<>(pageNum,pageSize);
        PageResult<DiseaseAlarm> pageResult = new PageResult<>();
        Page<DiseaseAlarm> page1;
        User user = JwtUtils.getUserByToken(TokenThreadLocalUtil.getInstance().getToken());
        if(user.getRoleId() == 6){
            page1 = diseaseAlarmMapper.selectAllPages(page,keyWord);
            page.setTotal(page1.getRecords().size());
            page.setPages(page1.getTotal() / pageSize);
            page = diseaseAlarmMapper.selectAllPage(page,pageSize,(pageNum - 1) * pageSize,keyWord);
        }else {
            page1 = diseaseAlarmMapper.selectAllPagesU(page,keyWord,user.getId());
            page.setTotal(page1.getRecords().size());
            page.setPages(page1.getTotal() / pageSize);
            page = diseaseAlarmMapper.selectPage(page, pageSize, (pageNum - 1) * pageSize, keyWord, user.getId());
        }
        // 处理图片数据
        List<DiseaseAlarm> diseaseAlarmList = page.getRecords();
        for (DiseaseAlarm diseaseAlarm:diseaseAlarmList){
            if(StringUtils.isNotEmpty(diseaseAlarm.getImageUrl())){
                diseaseAlarm.setImageUrl(minioUtil.downloadFile(diseaseAlarm.getImageUrl()));
            }
        }
        page.setRecords(diseaseAlarmList);
        return pageResult.praisePage(page);
    }
    @Override
    public void solveProblem(Integer id) {
        DiseaseAlarm diseaseAlarm = diseaseAlarmMapper.selectById(id);
        if(diseaseAlarm == null || diseaseAlarm.getStatus() == 1){
            throw new BaseException("状态异常");
        }
        diseaseAlarm.setStatus(1);
        if(diseaseAlarmMapper.updateById(diseaseAlarm) == 0){
            throw new BaseException("更新失败");
        }
    }
    @Override
    public void deleteDisease(List<Integer> ids) {
        List<DiseaseAlarm> diseaseAlarmList = diseaseAlarmMapper.selectBatchIds(ids);
        for (DiseaseAlarm diseaseAlarm:diseaseAlarmList){
            try {minioUtil.removeObject(diseaseAlarm.getImageUrl());}
            catch (Exception e){throw new BaseException("删除警报id：" + diseaseAlarm.getId() + "图片name为" + diseaseAlarm.getImageUrl() + "失败");}
        }
        if(diseaseAlarmMapper.deleteBatchIds(ids) == 0)
            throw new BaseException("删除病虫害警报信息失败");
    }
    @Override
    public PageResult<EnvironmentAlarmDto> selectEnvironmentPage(Integer pageNum, Integer pageSize, String keyWord) {
        Page<EnvironmentAlarm> page = new Page<>(pageNum,pageSize);
        PageResult<EnvironmentAlarm> pageResult = new PageResult<>();
        Page<EnvironmentAlarm> page1;
        User user = JwtUtils.getUserByToken(TokenThreadLocalUtil.getInstance().getToken());
        if(user.getRoleId() == 6){
            page1 = environmentAlarmMapper.selectAllPages(page,keyWord);
            page.setTotal(page1.getRecords().size());
            page.setPages(page1.getTotal() / pageSize);
            pageResult.praisePage(environmentAlarmMapper.selectAllPageEnvironment(page,pageSize,(pageNum - 1) * pageSize,keyWord));
        }else {
            page1 = environmentAlarmMapper.selectAllPagesU(page,keyWord,user.getId());
            page.setTotal(page1.getRecords().size());
            page.setPages(page1.getTotal() / pageSize);
            pageResult.praisePage(environmentAlarmMapper.selectPageEnvironment(page, pageSize, (pageNum - 1) * pageSize, keyWord, user.getId()));
        }
        List<EnvironmentAlarm> environmentAlarmList = pageResult.getRecords();
        List<EnvironmentAlarmDto> environmentAlarmDtoList = new ArrayList<>(environmentAlarmList.size() + 1);
        for (EnvironmentAlarm environmentAlarm : environmentAlarmList) {
            EnvironmentAlarmDto environmentAlarmDto = new EnvironmentAlarmDto();
            BeanUtils.copyProperties(environmentAlarm,environmentAlarmDto);
            String unit = UnitConverse.UNIT_MAP.get(environmentAlarm.getStatusAlarmId());// 获取相应单位
            environmentAlarmDto.setNormalStatus(environmentAlarm.getNormalMin() + unit + "-" + environmentAlarm.getNormalMax() + unit);
            environmentAlarmDtoList.add(environmentAlarmDto);
        }
        PageResult<EnvironmentAlarmDto> pageResult1 = new PageResult<>();
        pageResult1.setRecords(environmentAlarmDtoList);
        pageResult1.setPageNo(page.getCurrent());
        pageResult1.setPages(page.getPages());
        pageResult1.setPageSize(page.getSize());
        pageResult1.setTotal(page.getTotal());

        return pageResult1;
    }
    @Override
    public void deleteEnvironment(List<Integer> ids) {
        if(environmentAlarmMapper.deleteBatchIds(ids) == 0)
            throw new BaseException("删除环境警告失败");
    }
    @Override
    public void updateAlarm(List<FieldStatusAlarm> fieldStatusAlarm) {
       for(FieldStatusAlarm fieldStatusAlarm1:fieldStatusAlarm){
           if(fieldStatusAlarmMapper.updateById(fieldStatusAlarm1) == 0){
               throw new BaseException(fieldStatusAlarm1.getStatus() + "范围设置失败");
           }
       }
    }
}
