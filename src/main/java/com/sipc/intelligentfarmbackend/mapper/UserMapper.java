package com.sipc.intelligentfarmbackend.mapper;

import com.sipc.intelligentfarmbackend.pojo.domain.User;
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
public interface UserMapper extends BaseMapper<User> {
    List<User> getSpecialUser(Integer id);
}
