package com.sipc.intelligentfarmbackend.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sipc.intelligentfarmbackend.exception.BaseException;
import com.sipc.intelligentfarmbackend.mapper.UserMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.User;
import com.sipc.intelligentfarmbackend.pojo.model.res.LoginRes;
import com.sipc.intelligentfarmbackend.service.UserService;
import com.sipc.intelligentfarmbackend.utils.JwtUtils;
import com.sipc.intelligentfarmbackend.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    @Override
    public LoginRes login(String phone,String code) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(StringUtils.isNotEmpty(phone),User::getPhone,phone);
        User user = userMapper.selectOne(lqw);
        if(user == null){
            throw new BaseException("用户不存在");
        }
        if(StringUtils.isEmpty(code) || !code.equals(phone.substring(0,5))){
            throw new BaseException("验证码错误");
        }
        return new LoginRes(user, JwtUtils.sign(user));
    }

    @Override
    public void register(User user) {
        if(userMapper.insert(user) == 0){
            throw new BaseException("创建用户异常，检查参数格式是否正确");
        }
    }

    @Override
    public User getUserInfoById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> getSpecialUser(Integer id) {
        return userMapper.getSpecialUser(id);
    }
}
