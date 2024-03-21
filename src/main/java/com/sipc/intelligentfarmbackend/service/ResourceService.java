package com.sipc.intelligentfarmbackend.service;

import com.sipc.intelligentfarmbackend.pojo.domain.ResourceNotice;
import com.sipc.intelligentfarmbackend.pojo.model.para.NoticePara;
import com.sipc.intelligentfarmbackend.pojo.model.para.ResourceNoticePara;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;

import java.util.List;

public interface ResourceService {
    PageResult<ResourceNotice> getNoticePage(Integer resourceId, Integer pageNum, Integer pageSize, String keyWord);
    void deleteInfo(List<Integer> ids);
    void addNoticeToDriver(NoticePara noticePara);
    void outRepository(ResourceNoticePara resourceNoticePara);
}
