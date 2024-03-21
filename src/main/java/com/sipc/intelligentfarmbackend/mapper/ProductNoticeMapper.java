package com.sipc.intelligentfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.pojo.domain.ProductNotice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductNoticeMapper extends BaseMapper<ProductNotice> {
    Page<ProductNotice> selectNoticeInfoPage(Page<ProductNotice> page, Integer pageSize, Integer offset, String keyWord, Integer productId);
    Page<ProductNotice> selectAllPlantPages(Page<ProductNotice> page, String keyWord);
    IPage<ProductNotice> selectAllPlantInfoPage(Page<ProductNotice> page, Integer pageSize, Integer offset, String keyWord);
    Page<ProductNotice> selectAllPlantPageU(Page<ProductNotice> page, String keyWord, Integer productId);
}
