package com.sipc.intelligentfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.pojo.domain.DriverNotice;
import com.sipc.intelligentfarmbackend.pojo.domain.ResourceNotice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceNoticeMapper extends BaseMapper<ResourceNotice> {
    Page<ResourceNotice> selectNoticeInfoPage(Page<ResourceNotice> page, String keyWord, Integer resourceId);

}
