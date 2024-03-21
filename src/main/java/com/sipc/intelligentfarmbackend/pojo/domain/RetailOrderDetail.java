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
* TableName retail_order_detail
*/
@TableName("retail_order_detail")
@Data
public class RetailOrderDetail implements Serializable {

    /**
    * 
    */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
    *  订单编号
    */
    @TableField("order_id")
    private Integer orderId;
    /**
    * 商品id
    */
    @TableField("commodity_id")
    private Integer commodityId;
    /**
    * 数量
    */
    @TableField("count")
    private Integer count;
    /**
    * 用户id
    */
    @TableField("consumer_id")
    private Integer consumerId;
    /**
    * 用户姓名
    */
    @TableField("consumer_name")
    private String consumerName;
    /**
     * 用户电话
     */
    @TableField("consumer_phone")
    private String consumerPhone;
    /**
    * 收货地址
    */
    @TableField("address")
    private String address;
    /**
    * 备注
    */
    @TableField("notes")
    private String notes;
    /**
    * 价格
    */
    @TableField("price")
    private BigDecimal price;
    /**
    * 支付方式
    */
    @TableField("payment")
    private String payment;


}
