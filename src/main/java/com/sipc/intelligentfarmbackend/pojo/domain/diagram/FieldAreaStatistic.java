package com.sipc.intelligentfarmbackend.pojo.domain.diagram;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  地块种植面积统计
 */
@Data
@AllArgsConstructor
public class FieldAreaStatistic {
    /**
     *  地块名称
     */
    private String place;
    /**
     *  种植作物
     */
    private String plant;
    /**
     *  种植面积
     */
    private Long number;
}
