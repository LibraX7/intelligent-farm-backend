package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
* 
* @TableName field_status
*/
@TableName("field_status")
@Data
public class FieldStatus implements Serializable {

    /**
     *
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 氮
     */
    @TableField("field_id")
    private Integer fieldId;
    @TableField("nitrogen")
    private Double nitrogen;
    /**
     * 钾
     */
    @TableField("potassium")
    private Double potassium;
    /**
     * 土壤湿度
     */
    @TableField("moisture")
    private Double moisture;
    /**
     * 磷
     */

    @TableField("phosphorus")
    private Double phosphorus;
    /**
     * 温度
     */
    @TableField("temperature")
    private Double temperature;
    /**
     * ph值
     */
    @TableField("ph")
    private Double ph;
    /**
     *
     */
    @TableField("co2")
    private Double co2;
    /**
     *
     */
    @TableField("time_type")
    private Object timeType;
    /**
     * 光照强度
     */
    @TableField("light_intensity")
    private Double lightIntensity;
    @TableField("update_time")
    private LocalDateTime updateTime;
}
