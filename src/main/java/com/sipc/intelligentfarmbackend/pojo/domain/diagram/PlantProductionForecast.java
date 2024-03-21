package com.sipc.intelligentfarmbackend.pojo.domain.diagram;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 作物产量预测
 */
@Data
@AllArgsConstructor
public class PlantProductionForecast {
    /**
     *  作物名称
     */
    private String name;
    /**
     *  产量
     */
    private Integer value;
}
