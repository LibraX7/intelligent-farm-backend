package com.sipc.intelligentfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.FieldStatus;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenovo
* @description 针对表【field_status】的数据库操作Mapper
* @createDate 2024-03-02 17:32:36
* @Entity ./pojo/domain.FieldStatus
*/
@Mapper
public interface FieldStatusMapper extends BaseMapper<FieldStatus> {

}
