package com.sipc.intelligentfarmbackend.pojo.model.res;

import lombok.Data;

@Data
public class NoticeResult {
    /**
     * 货物id
     */
    private Integer id;

    /**
     * 农户
     */
    private String noticer;

    /**
     * 原料厂商
     */
    private String rawMaterialSupplier;

    /**
     * 备注
     */
    private String notes;

    /**
     * 运输状态：0未运输，1运输中，2运输完毕
     */
    private Integer status;
}
