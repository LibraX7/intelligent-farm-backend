package com.sipc.intelligentfarmbackend.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnvironmentAlarmDto {
    private Integer id;
    private Integer fieldId;
    private String fieldName;
    private LocalDateTime alarmTime;
    private String cropName;
    private String alarmProgram;
    private String outlier;
    private String normalStatus;
    private String currentStatus;
}
