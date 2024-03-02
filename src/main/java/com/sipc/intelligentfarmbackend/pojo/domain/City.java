package com.sipc.intelligentfarmbackend.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("city_form")
public class City {
    @TableField("city_id")
    private String city_id;
    @TableField("name")
    private String name;
    @TableField("en")
    private String en;
}
