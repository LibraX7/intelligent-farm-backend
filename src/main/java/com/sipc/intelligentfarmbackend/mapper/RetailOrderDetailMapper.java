package com.sipc.intelligentfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.RetailOrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenovo
* @description 针对表【retail_order_detail】的数据库操作Mapper
* @createDate 2024-03-10 09:28:30
* @Entity ./pojo/domain.RetailOrderDetail
*/
@Mapper
public interface RetailOrderDetailMapper extends BaseMapper<RetailOrderDetail> {

}
