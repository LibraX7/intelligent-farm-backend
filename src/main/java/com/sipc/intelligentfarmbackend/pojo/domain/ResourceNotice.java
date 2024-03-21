
package com.sipc.intelligentfarmbackend.pojo.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;

/**
* 
* @TableName resource_notice
*/
@Data
@TableName("resource_notice")
public class ResourceNotice implements Serializable {

    /**
    * 货物id
    */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
    * 农户
    */
    @TableField("fammer")
    private String fammer;
    /**
    * 来源
    */
    @TableField("source")
    private String source;
    /**
    * 时间
    */
    @TableField("time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
    /**
    * 状态
    */
    @TableField("status")
    private Integer status;
    /**
    * 
    */
    @TableField("resource_id")
    private Integer resourceId;
    /**
    * 溯源id
    */
    @TableField("trace_id")
    private Integer traceId;

}
