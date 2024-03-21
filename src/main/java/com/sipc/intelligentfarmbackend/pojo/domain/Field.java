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

/**
 * <p>
 * 
 * </p>
 *
 * @author gang.yang
 * @since 2024-02-02
 */
@Data
@TableName("field")
public class Field implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 地块名称
     */
    @TableField("name")
    private String name;

    /**
     * 地块位置
     */
    @TableField("location")
    private String location;

    /**
     * 地块面积
     */
    @TableField("area")
    private Long area;

    /**
     * 播种时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("plant_time")
    private LocalDateTime plantTime;
    /**
     * 作物名称
     */
    @TableField("crop_name")
    private String cropName;
    @TableField("user_id")
    private Integer userId;
    @TableField("latitude")
    private Double latitude;
    @TableField("longitude")
    private Double longitude;
}
