package com.sipc.intelligentfarmbackend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.pojo.domain.Plant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    Page<Plant> selectPlantInfoPage(Page<Plant> page,String keyWord);
}
