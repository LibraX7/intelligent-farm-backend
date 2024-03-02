package com.sipc.intelligentfarmbackend.pojo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 天气对应规则，前缀num为天气或风向的编号，可到官网自行查看http://www.yytianqi.com/api.html
 */
@Data
public class Weather implements Serializable {
    /**
     * 白天天气
     */
    private String tq1;
    /**
     * 夜晚天气
     */
    private String tq2;
    private String numtq1;
    private String numtq2;
    /**
     * 白天气温
     */
    private String qw1;
    /**
     * 夜晚气温
     */
    private String qw2;
    /**
     * 白天风力
     */
    private String fl1;
    private String numfl1;
    /**
     * 夜晚风力
     */
    private String fl2;
    private String numfl2;
    /**
     * 白天风向
     */
    private String fx1;
    private String numfx1;
    /**
     * 夜晚风向
     */
    private String fx2;
    private String numfx2;
    /**
     * 预报日期
     */

    private String date;
}
