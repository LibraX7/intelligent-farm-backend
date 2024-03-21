package com.sipc.intelligentfarmbackend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.pojo.domain.Plant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gang.yang
 * @since 2024-02-02
 */
@Mapper
public interface PlantMapper extends BaseMapper<Plant> {
    Page<Plant> selectPlantInfoPage(Page<Plant> page,Integer pageSize,Integer offset,String keyWord,Integer userId);
    Page<Plant> selectAllPlantInfoPage(Page<Plant> page, Integer pageSize,Integer offset, String keyWord);
    Page<Plant> selectAllPlantPage(Page<Plant> page, String keyWord, Integer userId);
    Page<Plant> selectAllPlantPages(Page<Plant> page, String keyWord);
}
