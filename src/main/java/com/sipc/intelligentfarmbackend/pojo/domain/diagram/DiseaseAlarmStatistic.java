package com.sipc.intelligentfarmbackend.pojo.domain.diagram;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 病虫害报警统计
 */
@Data
@AllArgsConstructor
public class DiseaseAlarmStatistic {
    /**
     * 名称
     */
    private String name;
    /**
     * 数据
     */
    private Integer number;
}
