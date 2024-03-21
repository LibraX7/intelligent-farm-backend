package com.sipc.intelligentfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.pojo.domain.ResourceNotice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceNoticeMapper extends BaseMapper<ResourceNotice> {
    Page<ResourceNotice> selectNoticeInfoPage(Page<ResourceNotice> page, Integer pageSize, int offset, String keyWord, Integer resourceId);
    IPage<ResourceNotice> selectAllPlantInfoPage(Page<ResourceNotice> page, Integer pageSize, int offset, String keyWord);
    Page<ResourceNotice> selectAllPlantPages(Page<ResourceNotice> page, String keyWord);
    Page<ResourceNotice> selectAllPlantPageU(Page<ResourceNotice> page, String keyWord, Integer resourceId);
}
