package com.sipc.intelligentfarmbackend.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.ResourceStatus;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenovo
* @description 针对表【resource_status】的数据库操作Mapper
* @createDate 2024-03-08 20:44:08
* @Entity ./pojo/domain.ResourceStatus
*/
@Mapper
public interface ResourceStatusMapper extends BaseMapper<ResourceStatus> {
}
