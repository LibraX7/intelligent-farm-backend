package com.sipc.intelligentfarmbackend.pojo.model.para;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatFieldPara {
    private Integer id;

    /**
     * 地块名称
     */
    private String name;

    /**
     * 地块位置
     */
    private String location;

    /**
     * 地块面积
     */
    private Long area;

    /**
     * 播种时间
     */
    private String plantTime;

    /**
     * 作物名称
     */
    private String cropName;
}
