package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("environment_alarm")
public class EnvironmentAlarm {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("field_id")
    private Integer fieldId;

    @TableField("alarm_time")
    private LocalDateTime alarmTime;

    @TableField("alarm_program")
    private String alarmProgram;

    @TableField("outlier")
    private String outlier;

    @TableField("normal")
    private String normal;

    @TableField("currentStatus")
    private String currentStatus;
}
