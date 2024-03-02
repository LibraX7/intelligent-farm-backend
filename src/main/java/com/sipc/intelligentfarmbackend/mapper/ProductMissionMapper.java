package com.sipc.intelligentfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.ProductMission;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenovo
* @description 针对表【product_mission】的数据库操作Mapper
* @createDate 2024-02-28 22:51:21
* @Entity ./pojo/domain.ProductMission
*/
@Mapper
public interface ProductMissionMapper extends BaseMapper<ProductMission> {


}
