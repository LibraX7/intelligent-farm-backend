package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("resource_status")
public class ResourceStatus {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("role_id")
    private Integer roleId;
    @TableField("check_result")
    private String checkResult;
    @TableField("img_url")
    private String imgUrl;
    @TableField("notes")
    private String notes;
    @TableField("resource_id")
    private Integer resourceId;
}
