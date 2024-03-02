package com.sipc.intelligentfarmbackend.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OperationDto {
    private Integer id;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 操作时间
     */
    private String operateTime;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 备注
     */
    private String notes;

    private Integer fieldId;
}
