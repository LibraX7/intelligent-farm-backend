package com.sipc.intelligentfarmbackend.pojo.model.res;

import com.sipc.intelligentfarmbackend.pojo.domain.FieldStatus;
import com.sipc.intelligentfarmbackend.pojo.domain.Operation;
import com.sipc.intelligentfarmbackend.pojo.domain.Sensor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FieldRes {
    private String name;
    private String location;
    private Long area;
    private String plantTime;
    /**
     * 操作日志
     */
    private List<Operation> operationList;
    /**
     * 设备列表
     */
    private List<Sensor> sensorList;
    /**
     *  土壤实时状况
     */
    private FieldStatus fieldStatuses;

}
