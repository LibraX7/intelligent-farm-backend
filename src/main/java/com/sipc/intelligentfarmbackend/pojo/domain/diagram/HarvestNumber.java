package com.sipc.intelligentfarmbackend.pojo.domain.diagram;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 总收成量
 */
@Data
@AllArgsConstructor
public class HarvestNumber {
    /**
     * 增长率
     */
    private Long number;
    /**
     *  面积
     */
    private Long place;
    /**
     *  时间类型（0今年，1去年）
     */
    private Integer timeType;
}
