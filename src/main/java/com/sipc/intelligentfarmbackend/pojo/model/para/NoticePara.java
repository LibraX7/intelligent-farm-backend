package com.sipc.intelligentfarmbackend.pojo.model.para;

import lombok.Data;

@Data
public class NoticePara {
    /**
     * 溯源id
     */
    private Integer traceId;
    /**
     * 农户Id
     */
    private Integer sourceId;
    /**
     * 司机Id
     */
    private Integer driverId;
    /**
     * 原料厂商Id
     */
    private Integer destId;
    /**
     * 备注
     */
    private String notes;
}
