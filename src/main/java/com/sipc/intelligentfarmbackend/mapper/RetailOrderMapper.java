package com.sipc.intelligentfarmbackend.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.pojo.domain.RetailOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RetailOrderMapper extends BaseMapper<RetailOrder> {
    Page<RetailOrder> selectOrderPage(Page<RetailOrder> page, Integer pageSize,Integer offset, String keyWord, Integer userId);

    Page<RetailOrder> selectAllPlantPageU(Page<RetailOrder> page, String keyWord, Integer userId);

    IPage<RetailOrder> selectAllPlantInfoPage(Page<RetailOrder> page, Integer pageSize,Integer offset, String keyWord);

    Page<RetailOrder> selectAllPlantPages(Page<RetailOrder> page, String keyWord);
}
