package com.sipc.intelligentfarmbackend.service;

import com.sipc.intelligentfarmbackend.pojo.domain.*;
import com.sipc.intelligentfarmbackend.pojo.dto.OperationDto;
import com.sipc.intelligentfarmbackend.pojo.model.para.CreatFieldPara;
import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;
import com.sipc.intelligentfarmbackend.pojo.model.res.FieldRes;

import java.util.List;

public interface FieldService {
    void addField(CreatFieldPara creatFieldPara);
    void addOperation(OperationDto operationDto);
    void addDevice(Sensor sensor);
    void deleteDevice(Integer id);
    List<Field> getFieldList();
    FieldRes getField(Integer id);
    void alterDevice(Integer id);
    List<FieldStatus> getStatus(Integer id);
    List<FieldNotice> getFieldNotice();
    void alterNoticeStatus(Integer id);
}
