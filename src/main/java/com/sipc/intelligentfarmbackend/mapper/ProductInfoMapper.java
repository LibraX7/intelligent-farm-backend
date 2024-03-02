package com.sipc.intelligentfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.ProductInfo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenovo
* @description 针对表【product_info】的数据库操作Mapper
* @createDate 2024-02-28 22:23:28
* @Entity ./pojo/domain.ProductInfo
*/
@Mapper
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {

}
