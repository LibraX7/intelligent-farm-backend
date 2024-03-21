package com.sipc.intelligentfarmbackend.pojo.model.res;

import com.sipc.intelligentfarmbackend.pojo.domain.diagram.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DiagramRes {
    /**
     *  设备概况
     */
    private List<DeviceOverview> deviceOverviews;
    /**
     *  设备状态
     */
    private List<DeviceStatus> deviceStatus;
    /**
     *  病虫害报警统计
     */
    private List<DiseaseAlarmStatistic> diseaseAlarmStatistic;
    /**
     *  病虫害分析
     */
    private List<DiseasePeriodAnalysis> diseasePeriodAnalysis;
    /**
     *  地块种植面积统计
     */
    private List<FieldAreaStatistic> fieldAreaStatistic;
    /**
     *  总收成量
     */
    private List<HarvestNumber> harvestNumber;

    /**
     *  作物面积统计
     */
    private List<PlantAreaStatistics> plantAreaStatistics;
}
