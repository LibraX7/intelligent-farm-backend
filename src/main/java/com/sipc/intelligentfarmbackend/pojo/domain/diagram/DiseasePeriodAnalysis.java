package com.sipc.intelligentfarmbackend.pojo.domain.diagram;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 病虫害分析
 */
@Data
@AllArgsConstructor
public class DiseasePeriodAnalysis {
    /**
     *  病虫害名称
     */
    private String name;
    /**
     *  数据统计（从下标0-11为0-12月）
     */
    private List<Integer> data;
}
