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
@TableName("sensor")
public class Sensor implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备名称
     */
    @TableField("name")
    private String name;

    /**
     * 设备状态（1开启，0关闭）
     */
    @TableField("status")
    private Integer status;

    @TableField("field_id")
    private Integer fieldId;
}
