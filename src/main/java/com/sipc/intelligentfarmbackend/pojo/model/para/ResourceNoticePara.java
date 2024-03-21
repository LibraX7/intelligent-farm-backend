package com.sipc.intelligentfarmbackend.pojo.model.para;

import lombok.Data;

@Data
public class ResourceNoticePara {
    private Integer resourceId;
    private String checkRes;
    private String notes;
    private String imgUrl;
}
