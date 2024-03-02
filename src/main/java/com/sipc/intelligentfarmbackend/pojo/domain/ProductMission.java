package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@TableName("product_mission")
@Data
public class ProductMission implements Serializable {
    /**
     *
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 产品id
     */
    @TableField("product_id")
    private Integer productId;
    /**
     * 任务名称
     */
    @TableField("mission_name")
    private String missionName;
    /**
     * 任务详情
     */
    @TableField("mission_detail")
    private String missionDetail;
}