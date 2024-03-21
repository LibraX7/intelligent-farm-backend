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


/**
* 
*/
@TableName("retail_commodity")
@Data
public class RetailCommodity implements Serializable {

    /**
    * 商品编号
    */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
    * 商品名称
    */
    @TableField("name")
    private String name;
    /**
    * 商品价格
    */
    @TableField("price")
    private Double price;
    /**
    * 是否上架（0否，1是）
    */
    @TableField("status")
    private Integer status;
    /**
    * 上架时间
    */
    @TableField("time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
    /**
    * 规格
    */
    @TableField("specifications")
    private String specifications;
    /**
    * 产地
    */
    @TableField("location")
    private String location;
    /**
    * 电话
    */
    @TableField("phone")
    private String phone;
    /**
    * 图片
    */
    @TableField("img_url")
    private String imgUrl;
    /**
    * 商品内容
    */
    @TableField("content")
    private String content;
    /**
    * 
    */
    @TableField("user_id")
    private Integer userId;
    /**
     * 商品分类
     */
    @TableField("classification")
    private String classification;

}
