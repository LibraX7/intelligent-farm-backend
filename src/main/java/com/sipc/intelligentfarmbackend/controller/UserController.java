package com.sipc.intelligentfarmbackend.controller;

import com.sipc.intelligentfarmbackend.aop.Pass;
import com.sipc.intelligentfarmbackend.pojo.domain.User;
import com.sipc.intelligentfarmbackend.pojo.model.para.LoginPara;
import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;
import com.sipc.intelligentfarmbackend.pojo.model.res.LoginRes;
import com.sipc.intelligentfarmbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public CommonResult<String> register(@RequestBody User user){
        return new CommonResult<>();
    }
    @PostMapping("/login")
    @Pass
    public CommonResult<LoginRes> login(@RequestBody LoginPara loginPara){
        return CommonResult.success(userService.login(loginPara.getPhone(),loginPara.getCode()));
    }
    @GetMapping("/sendCode")
    @Pass
    public CommonResult<String> sendCode(@RequestParam String phone){
        return CommonResult.success("测试阶段验证码:" + phone.substring(0,5));
    }
    @GetMapping("/get/user/list")
    public CommonResult<List<User>> getSpecialUser(@RequestParam Integer roleId){
        return CommonResult.success(userService.getSpecialUser(roleId));
    }
}
