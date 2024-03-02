package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product_notice")
public class ProductNotice {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("resource_factory")
    private String resourceFactory;
    @TableField("resource_person")
    private String resourcePerson;
    @TableField("name")
    private String name;
    @TableField("product_status")
    private Integer productStatus;
    @TableField("status")
    private Integer status;
    @TableField("product_id")
    private Integer productId;
    @TableField("resource_id")
    private Integer resourceId;
}
