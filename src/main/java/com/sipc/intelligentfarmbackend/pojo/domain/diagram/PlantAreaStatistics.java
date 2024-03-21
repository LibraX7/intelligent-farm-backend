package com.sipc.intelligentfarmbackend.pojo.domain.diagram;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 作物面积统计
 */

@Data
@AllArgsConstructor
public class PlantAreaStatistics {
    /**
     *  作物名称
     */
    private String name;
    /**
     *  产量
     */
    private Long value;
}
