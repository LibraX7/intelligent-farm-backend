package com.sipc.intelligentfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.pojo.domain.RetailNotice;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenovo
*/
@Mapper
public interface RetailNoticeMapper extends BaseMapper<RetailNotice> {
    Page<RetailNotice> getNoticePage(Page<RetailNotice> page, Integer pageSize, Integer offset, String keyWord, Integer retailId);

    Page<RetailNotice> selectAllPlantPages(Page<RetailNotice> page, String keyWord);

    IPage<RetailNotice> selectAllPlantInfoPage(Page<RetailNotice> page, Integer pageSize, Integer offset, String keyWord);

    Page<RetailNotice> selectAllPlantPageU(Page<RetailNotice> page, String keyWord, Integer retailId);
}
