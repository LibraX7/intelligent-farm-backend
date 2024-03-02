package com.sipc.intelligentfarmbackend.service;

import com.sipc.intelligentfarmbackend.pojo.domain.User;
import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;
import com.sipc.intelligentfarmbackend.pojo.model.res.LoginRes;

import java.util.List;

public interface UserService {
    LoginRes login(String phone,String code);
    void register(User user);
    User getUserInfoById(Integer id);
    List<User> getSpecialUser(Integer id);
}
