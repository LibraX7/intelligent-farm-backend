package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

import java.math.BigDecimal;


/**
* 
* @TableName field_status_alarm
*/
@TableName("field_status_alarm")
@Data
public class FieldStatusAlarm implements Serializable {

    /**
    * 
    */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
    * 
    */
    @TableField("field_id")
    private Integer fieldId;
    @TableField("status")
    private String status;
    /**
    * 
    */
    @TableField("max")
    private Double max;
    /**
    * 
    */
    @TableField("min")
    private Double min;


}
