package com.sipc.intelligentfarmbackend.pojo.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
* 
* @TableName field_notice
*/
@Data
@TableName("field_notice")
public class FieldNotice implements Serializable {

    /**
    * 
    */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
    * 
    */
    @TableField("content")
    private String content;
    /**
    * 
    */
    @TableField("status")
    private Integer status;
    @TableField("user_id")
    private Integer userId;
}
