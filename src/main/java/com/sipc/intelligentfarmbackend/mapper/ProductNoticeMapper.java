package com.sipc.intelligentfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.pojo.domain.ProductNotice;
import com.sipc.intelligentfarmbackend.pojo.domain.ResourceNotice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductNoticeMapper extends BaseMapper<ProductNotice> {
    Page<ProductNotice> selectNoticeInfoPage(Page<ProductNotice> page, String keyWord, Integer resourceId);
}
