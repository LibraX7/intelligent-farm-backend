package com.sipc.intelligentfarmbackend.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.pojo.domain.RetailCommodity;
import org.apache.ibatis.annotations.Mapper;


/**
* @author lenovo
*/
@Mapper
public interface RetailCommodityMapper extends BaseMapper<RetailCommodity> {
    Page<RetailCommodity> getCommodity(Page<RetailCommodity> page, Integer pageSize, Integer offset, String keyWord, Integer userId);
    Page<RetailCommodity> selectAllPlantPages(Page<RetailCommodity> page, String keyWord);
    IPage<RetailCommodity> selectAllPlantInfoPage(Page<RetailCommodity> page, Integer pageSize, Integer offset, String keyWord);
    Page<RetailCommodity> selectAllPlantPageU(Page<RetailCommodity> page, String keyWord, Integer userId);
}
