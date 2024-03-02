package com.sipc.intelligentfarmbackend.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.FieldStatusAlarm;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenovo
* @description 针对表【field_status_alarm】的数据库操作Mapper
* @createDate 2024-03-02 17:32:36
* @Entity ./pojo/domain.FieldStatusAlarm
*/
@Mapper
public interface FieldStatusAlarmMapper extends BaseMapper<FieldStatusAlarm> {

}
