package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("driver_notice")
public class DriverNotice {

    /**
     * 货物id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 农户Id
     */
    @TableField("source")
    private String source;

    /**
     * 原料厂商Id
     */
    @TableField("destination")
    private String destination;

    /**
     * 司机id
     *
     */
    @TableField("driver_id")
    private Integer driverId;

    /**
     * 备注
     */
    @TableField("notes")
    private String notes;

    /**
     * 运输状态：0未运输，1运输中，2运输完毕
     */
    @TableField("status")
    private Integer status;

    @TableField("source_id")
    private Integer sourceId;

    @TableField("dest_id")
    private Integer destId;

    @TableField("trace_id")
    private Integer traceId;
}
