package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author gang.yang
 * @since 2024-02-02
 */
@Data
@TableName("user")
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("role_id")
    private Integer roleId;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("enterprise_name")
    private String enterpriseName;

    @TableField("enterprise_concat_information")
    private String enterpriseConcatInformation;

    @TableField("img_url")
    private String imgUrl;
}
