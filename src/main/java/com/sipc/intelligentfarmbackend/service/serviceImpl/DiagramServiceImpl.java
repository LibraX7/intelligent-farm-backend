package com.sipc.intelligentfarmbackend.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sipc.intelligentfarmbackend.mapper.DiseaseAlarmMapper;
import com.sipc.intelligentfarmbackend.mapper.FieldMapper;
import com.sipc.intelligentfarmbackend.mapper.SensorMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.DiseaseAlarm;
import com.sipc.intelligentfarmbackend.pojo.domain.Field;
import com.sipc.intelligentfarmbackend.pojo.domain.Sensor;
import com.sipc.intelligentfarmbackend.pojo.domain.User;
import com.sipc.intelligentfarmbackend.pojo.domain.diagram.*;
import com.sipc.intelligentfarmbackend.pojo.model.res.DiagramRes;
import com.sipc.intelligentfarmbackend.service.DiagramService;
import com.sipc.intelligentfarmbackend.utils.JwtUtils;
import com.sipc.intelligentfarmbackend.utils.TokenThreadLocalUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DiagramServiceImpl implements DiagramService {
    private FieldMapper fieldMapper;
    private SensorMapper sensorMapper;
    private DiseaseAlarmMapper diseaseAlarmMapper;
    @Override
    public DiagramRes getData() {
        User user = JwtUtils.getUserByToken(TokenThreadLocalUtil.getInstance().getToken());
        List<DeviceOverview> deviceOverviews = new LinkedList<>();
        List<DeviceStatus> deviceStatus = new LinkedList<>();
        List<DiseaseAlarmStatistic> diseaseAlarmStatistic = new LinkedList<>();
        List<DiseasePeriodAnalysis> diseasePeriodAnalysis = new LinkedList<>();
        List<FieldAreaStatistic> fieldAreaStatistic = new LinkedList<>();
        List<HarvestNumber> harvestNumber = new LinkedList<>();
        List<PlantAreaStatistics> plantAreaStatistics = new LinkedList<>();
        // 查询地块相关数据
        List<Field> fieldList = fieldMapper.selectList(new LambdaQueryWrapper<Field>().eq(Field::getUserId,user.getId()));
        List<Integer> fieldIdList = fieldList.stream()
                                             .map(Field::getId)
                                             .toList();
        // 封装数据
        // 地块作物统计
        for (Field field : fieldList) {
            fieldAreaStatistic.add(new FieldAreaStatistic(field.getName(),field.getCropName(),field.getArea()));
        }
        // 作物面积统计
        Map<String,List<Field>> plantMap = fieldList.stream().collect(Collectors.groupingBy(Field::getCropName));
        for(Map.Entry<String,List<Field>> entry :plantMap.entrySet()){
            plantAreaStatistics.add(new PlantAreaStatistics(entry.getKey(),entry.getValue()
                                                                                .stream()
                                                                                .mapToLong(Field::getArea)
                                                                                .sum()));
        }
        // 总收成量
        Map<Integer, List<Field>> fieldByYear = fieldList.stream()
                .collect(Collectors.groupingBy(field -> field.getPlantTime().getYear()));
        int thisYearValue = LocalDate.now().getYear();
        List<Field> thisYear = fieldByYear.getOrDefault(thisYearValue, Collections.emptyList());
        List<Field> lastYear = fieldByYear.getOrDefault(thisYearValue - 1, Collections.emptyList());
        List<Field> lastTwoYear = fieldByYear.getOrDefault(thisYearValue - 2, Collections.emptyList());
        long thisYearTotal = thisYear.stream().mapToLong(Field::getArea).sum();
        long lastYearTotal = lastYear.stream().mapToLong(Field::getArea).sum();
        long lastTwoYearTotal = lastTwoYear.stream().mapToLong(Field::getArea).sum();
        harvestNumber.add(new HarvestNumber(( lastYearTotal == 0 ? 100L : (100L * thisYearValue / lastYearTotal)),thisYearTotal,0));
        harvestNumber.add(new HarvestNumber(lastTwoYearTotal == 0 ? 100L : (100L * lastYearTotal / lastTwoYearTotal),lastYearTotal,1));
        // 查询传感器数据
        List<Sensor> sensorList = sensorMapper.selectList(new LambdaQueryWrapper<Sensor>().in(Sensor::getFieldId,fieldIdList));
        // 封装传感器数据
        Map<Integer, String> fieldMap = fieldList.stream()
                .collect(Collectors.toMap(Field::getId, Field::getName));
        Map<Integer,List<Sensor>> integerListMap = sensorList.stream().collect(Collectors.groupingBy(Sensor::getStatus));
        for (Map.Entry<Integer,List<Sensor>> entry :integerListMap.entrySet()){
            deviceStatus.add(new DeviceStatus(entry.getValue().size(),entry.getKey()));
        }
        for (Sensor sensor : sensorList) {
            deviceOverviews.add(new DeviceOverview(sensor.getName(),sensor.getStatus(),fieldMap.get(sensor.getFieldId())));
        }

        // 查询病虫害数据
        List<DiseaseAlarm> diseaseAlarmStatisticList = diseaseAlarmMapper.selectList(new LambdaQueryWrapper<DiseaseAlarm>().in(DiseaseAlarm::getFieldId,fieldIdList));
        // 封装病虫害报警统计
        Map<String,List<DiseaseAlarm>> diseaseMap = diseaseAlarmStatisticList.stream().collect(Collectors.groupingBy(DiseaseAlarm::getDisease));
        for (Map.Entry<String,List<DiseaseAlarm>> entry:diseaseMap.entrySet()){
            diseaseAlarmStatistic.add(new DiseaseAlarmStatistic(entry.getKey(),entry.getValue().size()));
        }
        // 封装病虫害时段分析
        Map<Integer,List<DiseaseAlarm>> diseaseByMonth = diseaseAlarmStatisticList.stream()
                .collect(Collectors.groupingBy(diseaseAlarm -> diseaseAlarm.getAlarmTime().getMonthValue()));
        Map<Integer,List<DiseaseAlarm>> treeByMonth = new TreeMap<>(diseaseByMonth);
        List<Integer> whiteDisease = new ArrayList<>(13);
        List<Integer> pointDisease = new ArrayList<>(13);
        for(Map.Entry<Integer,List<DiseaseAlarm>> entry : treeByMonth.entrySet()){
            List<DiseaseAlarm> whiteDiseaseAlarmList = entry.getValue().stream().filter(diseaseAlarm -> "白粉病".equals(diseaseAlarm.getDisease())).toList();
            List<DiseaseAlarm> pointDiseaseAlarmList = entry.getValue().stream().filter(diseaseAlarm -> "斑点病".equals(diseaseAlarm.getDisease())).toList();
            whiteDisease.add(whiteDiseaseAlarmList.size());
            pointDisease.add(pointDiseaseAlarmList.size());
        }
        diseasePeriodAnalysis.add(new DiseasePeriodAnalysis("白粉病",whiteDisease));
        diseasePeriodAnalysis.add(new DiseasePeriodAnalysis("斑点病",pointDisease));
        return new DiagramRes(deviceOverviews,deviceStatus,diseaseAlarmStatistic,diseasePeriodAnalysis,fieldAreaStatistic,harvestNumber,plantAreaStatistics);
    }
}
