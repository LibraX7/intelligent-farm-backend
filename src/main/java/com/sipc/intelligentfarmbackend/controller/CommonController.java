package com.sipc.intelligentfarmbackend.controller;

import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@AllArgsConstructor
public class CommonController {
    private CommonService commonService;
    @PostMapping("/common/upload")
    public CommonResult<String> uploadFile(@RequestParam(name = "image") MultipartFile image){
        return CommonResult.success(commonService.uploadPicture(image));
    }
}
