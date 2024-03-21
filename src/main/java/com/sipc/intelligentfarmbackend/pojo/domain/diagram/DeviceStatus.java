package com.sipc.intelligentfarmbackend.pojo.domain.diagram;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 设备状态
 */

@Data
@AllArgsConstructor
public class DeviceStatus {
    /**
     *  数量
     */
    private Integer number;
    /**
     *  状态（0关闭，1开启）
     */
    private Integer status;
}
