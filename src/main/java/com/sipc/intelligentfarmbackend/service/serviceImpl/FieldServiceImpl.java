package com.sipc.intelligentfarmbackend.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sipc.intelligentfarmbackend.exception.BaseException;
import com.sipc.intelligentfarmbackend.mapper.*;
import com.sipc.intelligentfarmbackend.pojo.domain.*;
import com.sipc.intelligentfarmbackend.pojo.dto.FieldDiagram;
import com.sipc.intelligentfarmbackend.pojo.dto.OperationDto;
import com.sipc.intelligentfarmbackend.pojo.model.para.CreatFieldPara;
import com.sipc.intelligentfarmbackend.pojo.model.res.FieldRes;
import com.sipc.intelligentfarmbackend.service.FieldService;
import com.sipc.intelligentfarmbackend.utils.JwtUtils;
import com.sipc.intelligentfarmbackend.utils.TimeUtil;
import com.sipc.intelligentfarmbackend.utils.TokenThreadLocalUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FieldServiceImpl implements FieldService {
    private FieldMapper fieldMapper;
    private OperationMapper operationMapper;
    private SensorMapper sensorMapper;
    private FieldStatusMapper fieldStatusMapper;
    private FieldNoticeMapper fieldNoticeMapper;
    @Override
    public void addField(CreatFieldPara creatFieldPara) {
        Field field = new Field();
        BeanUtils.copyProperties(creatFieldPara,field);
        User user = JwtUtils.getUserByToken(TokenThreadLocalUtil.getInstance().getToken());
        field.setUserId(user.getId());
        field.setPlantTime(TimeUtil.getTimeByStringS(creatFieldPara.getPlantTime()));
        if(fieldMapper.addField(field) == 0){
            throw new BaseException("增加地块异常");
        }
    }
    @Override
    public void addOperation(OperationDto operationDto) {
        Operation operation = new Operation();
        BeanUtils.copyProperties(operationDto,operation);
        operation.setOperateTime(TimeUtil.getTimeByString(operationDto.getOperateTime()));
        if(operationMapper.insert(operation) == 0) {
            throw new BaseException("增加操作异常");
        }
    }
    @Override
    public void addDevice(Sensor sensor) {
        sensor.setStatus(0);
        if(sensorMapper.insert(sensor) == 0){
            throw new BaseException("添加失败");
        }
    }

    @Override
    public void deleteDevice(Integer id) {
        if(sensorMapper.deleteById(id) == 0){
            throw new BaseException("删除失败");
        }
    }

    @Override
    public List<Field> getFieldList() {
        User user = JwtUtils.getUserByToken(TokenThreadLocalUtil.getInstance().getToken());
        return fieldMapper.selectList(new LambdaQueryWrapper<Field>().eq(Field::getUserId,user.getId()));
    }

    @Override
    public void alterDevice(Integer id) {
        Sensor sensor = sensorMapper.selectById(id);
        if(sensor == null) throw new BaseException("设备不存在");
        if (sensor.getStatus() == 0) sensor.setStatus(1);else sensor.setStatus(0);
        if(sensorMapper.updateById(sensor) == 0) throw new BaseException("更新状态失败");
    }

    @Override
    public FieldRes getField(Integer id) {
        FieldRes fieldRes = new FieldRes();
        // 查询数据
        Field field = fieldMapper.selectById(id);
        List<Operation> operationList = operationMapper.selectList(new LambdaQueryWrapper<Operation>()
                .eq(Operation::getFieldId,id));
        List<Sensor> sensorList = sensorMapper.selectList(new LambdaQueryWrapper<Sensor>()
                .eq(Sensor::getFieldId,id));
        FieldStatus fieldStatus = fieldStatusMapper.selectOne(new LambdaQueryWrapper<FieldStatus>()
                .eq(FieldStatus::getFieldId,id)
                .orderByDesc(FieldStatus::getUpdateTime)
                .last("limit 1"));
        List<FieldStatus> fieldStatusList = fieldStatusMapper.selectList(new LambdaQueryWrapper<FieldStatus>()
                .eq(FieldStatus::getFieldId,id)
                .orderByAsc(FieldStatus::getTimeType));
        FieldDiagram fieldDiagram = new FieldDiagram();
        for (FieldStatus status : fieldStatusList) {
            fieldDiagram.convertToFieldStatus(status);
        }
        // 封装数据
        BeanUtils.copyProperties(field,fieldRes);
        fieldRes.setOperationList(operationList);
        fieldRes.setFieldStatuses(fieldStatus);
        fieldRes.setSensorList(sensorList);
        fieldRes.setPlantTime(TimeUtil.getTimeByDate(field.getPlantTime()));
        fieldRes.setFieldDiagram(fieldDiagram);

        return fieldRes;
    }

    @Override
    public List<FieldStatus> getStatus(Integer id) {
        return fieldStatusMapper.selectList(new LambdaQueryWrapper<FieldStatus>()
                .eq(FieldStatus::getFieldId,id)
                .orderByAsc(FieldStatus::getTimeType));
    }

    @Override
    public List<FieldNotice> getFieldNotice() {
        User user = JwtUtils.getUserByToken(TokenThreadLocalUtil.getInstance().getToken());
        return fieldNoticeMapper.selectList(new LambdaQueryWrapper<FieldNotice>().eq(FieldNotice::getUserId,user.getId()));
    }

    @Override
    public void alterNoticeStatus(Integer id) {
        FieldNotice fieldNotice = fieldNoticeMapper.selectById(id);
        fieldNotice.setStatus(1);
        if(fieldNoticeMapper.updateById(fieldNotice) == 0){
            throw new BaseException("更新状态失败");
        }
    }
}
