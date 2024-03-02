package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("operation")
public class Operation implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 操作人
     */
    @TableField("operator")
    private String operator;

    /**
     * 联系电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 操作时间
     */
    @TableField("operate_time")
    private LocalDateTime operateTime;

    /**
     * 操作内容
     */
    @TableField("content")
    private String content;

    /**
     * 备注
     */
    @TableField("notes")
    private String notes;

    @TableField("field_id")
    private Integer fieldId;
}
