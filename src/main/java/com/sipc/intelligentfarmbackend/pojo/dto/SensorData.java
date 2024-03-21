package com.sipc.intelligentfarmbackend.pojo.dto;

import lombok.Data;

@Data
public class SensorData {
    private String MessageType;
    /**
     * 氮
     */
    private Double nitrogen;
    /**
     * 钾
     */
    private Double potassium;
    /**
     * 土壤湿度
     */
    private Double moisture;
    /**
     * 磷
     */

    private Double phosphorus;
    /**
     * 温度
     */
    private Double temperature;
    /**
     * ph值
     */
    private Double ph;
}
