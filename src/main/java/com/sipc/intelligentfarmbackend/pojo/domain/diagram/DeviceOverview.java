package com.sipc.intelligentfarmbackend.pojo.domain.diagram;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  设备概况
 */
@Data
@AllArgsConstructor
public class DeviceOverview {
    private String deviceName;
    private Integer status;
    private String fieldName;
}
