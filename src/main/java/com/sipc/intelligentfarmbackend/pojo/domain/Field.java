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
@TableName("field")
public class Field implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 地块名称
     */
    @TableField("name")
    private String name;

    /**
     * 地块位置
     */
    @TableField("location")
    private String location;

    /**
     * 地块面积
     */
    @TableField("area")
    private Long area;

    /**
     * 播种时间
     */
    @TableField("plant_time")
    private LocalDateTime plantTime;

    /**
     * 作物名称
     */
    @TableField("crop_name")
    private String cropName;
    @TableField("user_id")
    private Integer userId;
}
