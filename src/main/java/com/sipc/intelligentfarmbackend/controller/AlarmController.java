package com.sipc.intelligentfarmbackend.controller;

import com.sipc.intelligentfarmbackend.pojo.domain.DiseaseAlarm;
import com.sipc.intelligentfarmbackend.pojo.domain.EnvironmentAlarm;
import com.sipc.intelligentfarmbackend.pojo.domain.FieldStatusAlarm;
import com.sipc.intelligentfarmbackend.pojo.dto.EnvironmentAlarmDto;
import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.AlarmService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController()
@AllArgsConstructor
public class AlarmController {
    private AlarmService alarmService;
    @GetMapping("/get/page/disease/alarm")
    public CommonResult<PageResult<DiseaseAlarm>> getPageDisease(@RequestParam("pageNum") Integer pageNum,
                                                   @RequestParam("pageSize") Integer pageSize,
                                                   @RequestParam("keyWord") String keyWord){
        return CommonResult.success(alarmService.selectPage(pageNum,pageSize,keyWord));
    }
    @GetMapping("/change/status")
    public CommonResult<String> solveTheProblem(@RequestParam Integer id){
        alarmService.solveProblem(id);
        return CommonResult.success();
    }
    @PostMapping("/delete/disease")
    public CommonResult<String> deleteTheDisease(@RequestBody List<Integer> ids ){
        alarmService.deleteDisease(ids);
        return CommonResult.success();
    }

    @GetMapping("/get/page/environment/alarm")
    public CommonResult<PageResult<EnvironmentAlarmDto>> getPageEnvironment(@RequestParam("pageNum") Integer pageNum,
                                                                            @RequestParam("pageSize") Integer pageSize,
                                                                            @RequestParam("keyWord") String keyWord){
        return CommonResult.success(alarmService.selectEnvironmentPage(pageNum,pageSize,keyWord));
    }

    @PostMapping("/delete/environment/alarm")
    public CommonResult<String> deleteEnvironmentAlarm(@RequestBody List<Integer> ids){
        alarmService.deleteEnvironment(ids);
        return CommonResult.success();
    }

    @PostMapping("/set/alarm/scope")
    public CommonResult<String> setAlarmScope(@RequestBody List<FieldStatusAlarm> fieldStatusAlarm){
        alarmService.updateAlarm(fieldStatusAlarm);
        return CommonResult.success();
    }
}
