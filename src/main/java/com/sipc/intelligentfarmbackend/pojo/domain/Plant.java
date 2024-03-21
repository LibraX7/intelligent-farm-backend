package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author gang.yang
 * @since 2024-02-02
 */
@Data
@TableName("plant")
public class Plant implements Serializable {
    /**
     * 作物编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 作物名称
     */
    @TableField("name")
    private String name;

    /**
     * 种植年度
     */
    @TableField("time")
    private String time;

    /**
     * 作物地址
     */
    @TableField("address")
    private String address;

    /**
     * 种植方式
     */
    @TableField("plant_way")
    private String plantWay;

    /**
     * 肥料名称
     */
    @TableField("fertilizer")
    private String fertilizer;

    /**
     * 作物类型
     */
    @TableField("crop_type")
    private String cropType;

    /**
     * 施肥周期
     */
    @TableField("fertilization_cycle")
    private String fertilizationCycle;

    /**
     * 育苗周期
     */
    @TableField("seedling_cycle")
    private String seedlingCycle;

    /**
     * 除草周期
     */
    @TableField("weeding_cycle")
    private String weedingCycle;

    /**
     * 灌溉周期
     */
    @TableField("irrigation_cycle")
    private String irrigationCycle;

    /**
     * 是否套袋（1是0否）
     */
    @TableField("bagging")
    private Integer bagging;

    /**
     * 作物状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 登记时间
     */
    @TableField("create_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @TableField("notes")
    private String notes;
    @TableField("user_id")
    private Integer userId;
}
