package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@TableName("plant_status")
@Data
public class PlantStatus implements Serializable {

    /**
    * 
    */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
    * 
    */
    @TableField("plant_id")
    private Integer plantId;
    /**
    * 
    */
    @TableField("grow_status")
    private String growStatus;
    /**
    * 
    */
    @TableField("light_status")
    private String lightStatus;
    /**
    * 
    */
    @TableField("time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
    /**
    * 
    */
    @TableField("temperature")
    private String temperature;
    /**
    * 
    */
    @TableField("water_status")
    private String waterStatus;

    @TableField("notes")
    private String notes;
    /**
    * 
    */
    @TableField("img_url")
    private String imgUrl;

    /**
    * 
    */
}
