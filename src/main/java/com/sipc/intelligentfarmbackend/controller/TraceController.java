package com.sipc.intelligentfarmbackend.controller;

import com.sipc.intelligentfarmbackend.pojo.domain.Plant;
import com.sipc.intelligentfarmbackend.pojo.domain.PlantStatus;
import com.sipc.intelligentfarmbackend.pojo.dto.PlantDto;
import com.sipc.intelligentfarmbackend.pojo.model.para.NoticePara;
import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.TraceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 溯源业务
 */
@CrossOrigin
@RestController
@AllArgsConstructor
public class TraceController {
    private TraceService traceService;
    @PostMapping("/add/trace")
    public CommonResult<String> addTrace(@RequestBody Plant plant){
        traceService.addTrace(plant);
        return CommonResult.success("添加成功");
    }
    @GetMapping("/get/page/trace")
    public CommonResult<PageResult<PlantDto>> getTraceList(@RequestParam("pageNum") Integer pageNum,
                                                           @RequestParam("pageSize") Integer pageSize,
                                                           @RequestParam("keyWord") String keyWord){
        return CommonResult.success(traceService.getPlantList(pageNum,pageSize,keyWord));
    }
    @PostMapping("/delete/trace")
    public CommonResult<String> deleteTrace(@RequestBody List<Integer> ids){
        traceService.deleteTrace(ids);
        return CommonResult.success("删除成功");
    }

    @PostMapping("/add/notice/trace")
    public CommonResult<String> noticeDriver(@RequestBody NoticePara notice){
        traceService.addNoticeToDriver(notice);
        return CommonResult.success("添加成功");
    }
    @PostMapping("/add/records/trace")
    public CommonResult<String> addRecords(@RequestBody PlantStatus plantStatus){
        traceService.addRecords(plantStatus);
        return CommonResult.success();
    }
    @GetMapping("/get/records/trace")
    public CommonResult<List<PlantStatus>> getRecords(@RequestParam Integer traceId){
        return CommonResult.success(traceService.getRecords(traceId));
    }
}
