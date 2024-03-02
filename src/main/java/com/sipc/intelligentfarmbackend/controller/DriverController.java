package com.sipc.intelligentfarmbackend.controller;

import com.sipc.intelligentfarmbackend.pojo.domain.DriverNotice;
import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.TraceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/driver")
@AllArgsConstructor
public class DriverController {
    private TraceService traceService;
    @GetMapping("/page/notice")
    public CommonResult<PageResult<DriverNotice>> getNoticePages(@RequestParam("driverId") Integer driverId,
                                                                 @RequestParam("pageNum") Integer pageNum,
                                                                 @RequestParam("pageSize") Integer pageSize,
                                                                 @RequestParam("keyWord") String keyWord){
        return CommonResult.success(traceService.getNoticePage(driverId, pageNum, pageSize, keyWord));
    }

    @PutMapping("/begin")
    public CommonResult<String> beginTransport(@RequestParam Integer id){
        traceService.beginTransport(id);
        return CommonResult.success("设置成功");
    }
    @PutMapping("/end")
    public CommonResult<String> endTransport(@RequestParam Integer id){
        traceService.endTransport(id);
        return CommonResult.success("设置成功");
    }
    @PostMapping("/delete")
    public CommonResult<String> deleteTrace(@RequestBody List<Integer> ids){
        traceService.deleteTransport(ids);
        return CommonResult.success();
    }

}
