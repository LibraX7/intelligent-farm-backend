package com.sipc.intelligentfarmbackend.mapper;

import com.sipc.intelligentfarmbackend.pojo.domain.AlarmMessage;
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
public interface FieldNoticeRelationMapper extends BaseMapper<AlarmMessage> {

}
