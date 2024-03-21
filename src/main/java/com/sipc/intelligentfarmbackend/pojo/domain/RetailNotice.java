package com.sipc.intelligentfarmbackend.pojo.domain;


import java.io.Serializable;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
* 
*/
@Data
@TableName("retail_notice")
public class RetailNotice implements Serializable {

    /**
    * 
    */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
    * 
    */
    @TableField("name")
    private String name;
    /**
    * 
    */
    @TableField("source")
    private String source;
    /**
    * 
    */
    @TableField("time")
    private LocalDateTime time;
    /**
    * 
    */
    @TableField("status")
    private Integer status;
    /**
    * 
    */
    @TableField("user_id")
    private Integer userId;
    /**
    * 
    */
    @TableField("trace_id")
    private Integer traceId;


}
