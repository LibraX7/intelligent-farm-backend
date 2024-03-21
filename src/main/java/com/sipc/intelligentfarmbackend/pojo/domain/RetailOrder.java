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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
* 
* @TableName retail_order
*/
@TableName("retail_order")
@Data
public class RetailOrder implements Serializable {

    /**
    * 
    */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
    * 价格
    */
    @TableField("price")
    private Double price;
    /**
    * 是否付款(0-未付款，1-已付款)
    */
    @TableField("payment_status")
    private Integer paymentStatus;
    /**
    * 是否付款(0-未发货，1-已发货)
    */
    @TableField("good_status")
    private Integer goodStatus;
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
    @TableField("user_id")
    private Integer userId;

}
