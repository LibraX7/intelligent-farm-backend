package com.sipc.intelligentfarmbackend.controller;

import com.sipc.intelligentfarmbackend.pojo.domain.ResourceNotice;
import com.sipc.intelligentfarmbackend.pojo.dto.UserDto;
import com.sipc.intelligentfarmbackend.pojo.model.para.NoticePara;
import com.sipc.intelligentfarmbackend.pojo.model.para.ResourceNoticePara;
import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController()
@AllArgsConstructor
public class ResourceController {
    private ResourceService resourceService;
    @GetMapping("/resource/page")
    public CommonResult<PageResult<ResourceNotice>> getResourceNotice(@RequestParam("pageNum") Integer pageNum,
                                                                      @RequestParam("pageSize") Integer pageSize,
                                                                      @RequestParam("keyWord") String keyWord,
                                                                      @RequestParam("id") Integer id){

        return CommonResult.success(resourceService.getNoticePage(id,pageNum,pageSize,keyWord));
    }

    @PostMapping("/resource/delete")
    public CommonResult<String> deleteNotice(@RequestBody List<Integer> ids){
        resourceService.deleteInfo(ids);
        return CommonResult.success();
    }
    @PostMapping("/resource/out")
    public CommonResult<String> outRepository(@RequestBody ResourceNoticePara resourceNoticePara){
        resourceService.outRepository(resourceNoticePara);
        return CommonResult.success();
    }
    @PostMapping("/resource/notice/driver")
    public CommonResult<String> addNoticeToDriver(@RequestBody NoticePara noticePara){
        resourceService.addNoticeToDriver(noticePara);
        return CommonResult.success();
    }

}
