package com.sipc.intelligentfarmbackend.pojo.dto;

import com.sipc.intelligentfarmbackend.pojo.domain.FieldStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FieldDiagram {
    List<Double> nitrogen;
    List<Double> potassium;
    List<Double> moisture;
    List<Double> phosphorus;
    List<Double> temperature;
    List<Double> lightIntensity;
    List<Double> ph;
    List<Double> co2;
    public FieldDiagram(){
        this.nitrogen = new ArrayList<>(12);
        this.potassium = new ArrayList<>(12);
        this.moisture = new ArrayList<>(12);
        this.phosphorus = new ArrayList<>(12);
        this.temperature = new ArrayList<>(12);
        this.ph = new ArrayList<>(12);
        this.co2 = new ArrayList<>(12);
        this.lightIntensity = new ArrayList<>(12);
    }
    public void convertToFieldStatus(FieldStatus fieldStatus){
        this.nitrogen.add(fieldStatus.getNitrogen());
        this.potassium.add(fieldStatus.getPotassium());
        this.moisture.add(fieldStatus.getMoisture());
        this.phosphorus.add(fieldStatus.getPhosphorus());
        this.temperature.add(fieldStatus.getTemperature());
        this.ph.add(fieldStatus.getPh());
        this.co2.add(fieldStatus.getCo2());
        this.lightIntensity.add(fieldStatus.getLightIntensity());
    }

}
