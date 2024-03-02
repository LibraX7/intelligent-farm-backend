package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("resource_notice")
public class ResourceNotice {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("farmer")
    private String farmer;
    @TableField("source")
    private String source;
    @TableField("time")
    private LocalDateTime time;
    @TableField("status")
    private Integer status;
    @TableField("resource_id")
    private Integer resourceId;
    @TableField("trace_id")
    private Integer traceId;
}
