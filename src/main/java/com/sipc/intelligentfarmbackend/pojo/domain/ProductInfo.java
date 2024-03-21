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


@TableName ("product_info")
@Data
public class ProductInfo implements Serializable {

    /**
     *
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    @TableField("name")
    private String name;
    /**
     * 配料
     */
    @TableField("material")
    private String material;
    /**
     * 负责人
     */
    @TableField("person")
    private String person;
    /**
     * 电话
     */
    @TableField("phone")
    private String phone;
    /**
     * 生产车间
     */
    @TableField("workshop")
    private String workshop;
    /**
     * 生产工时
     */
    @TableField("work_time")
    private String workTime;
    /**
     * 存储方式
     */
    @TableField("storage_method")
    private String storageMethod;
    /**
     * 净含量
     */
    @TableField("net_content")
    private String netContent;
    /**
     * 食用推荐
     */
    @TableField("recommend")
    private String recommend;
    /**
     * 备注
     */
    @TableField("note")
    private String note;
    /**
     * 生产id
     */
    @TableField("product_id")
    private Integer productId;

    @TableField("time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
}