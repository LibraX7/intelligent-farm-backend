package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author gang.yang
 * @since 2024-02-02
 */
@Data
@TableName("alarm_message")
public class AlarmMessage implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     * 通知内容
     */
    @TableField("content")
    private String content;

    /**
     *  地块id
     */
    @TableField("field_id")
    private Integer fieldId;

}
