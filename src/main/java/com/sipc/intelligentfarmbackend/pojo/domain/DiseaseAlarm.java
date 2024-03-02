package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@TableName("disease_alarm")
public class DiseaseAlarm {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("field_id")
    private Integer fieldId;

    @TableField("alarm_time")
    private LocalDateTime alarmTime;

    @TableField("status")
    private String status;

    @TableField("disease")
    private String disease;

    @TableField("img_url")
    private String imgUrl;

    @TableField("operation")
    private String operation;
}
