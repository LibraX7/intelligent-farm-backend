package com.sipc.intelligentfarmbackend.pojo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;


@Data
public class PlantDto implements Serializable {
    /**
     * 作物编号
     */
    private Integer id;

    /**
     * 作物名称
     */
    private String name;

    /**
     * 种植年度
     */
    private String time;

    /**
     * 作物地址
     */
    private String address;

    /**
     * 种植方式
     */
    private String plantWay;

    /**
     * 肥料名称
     */
    private String fertilizer;

    /**
     * 作物类型
     */
    private String cropType;

    /**
     * 施肥周期
     */
    private String fertilizationCycle;
    /**
     * 育苗周期
     */
    private String seedlingCycle;
    /**
     * 除草周期
     */
    private String weedingCycle;
    /**
     * 灌溉周期
     */
    private String irrigationCycle;
    /**
     * 是否套袋（1是0否）
     */
    private Integer bagging;
    /**
     * 作物状态
     */
    private Integer status;
    /**
     * 登记时间
     */
    private LocalDateTime createTime;
    private String notes;
    private Integer userId;
    private String farmer;
    private String phone;
}
