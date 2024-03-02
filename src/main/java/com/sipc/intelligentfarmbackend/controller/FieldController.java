package com.sipc.intelligentfarmbackend.controller;

import com.sipc.intelligentfarmbackend.pojo.domain.Field;
import com.sipc.intelligentfarmbackend.pojo.domain.FieldStatus;
import com.sipc.intelligentfarmbackend.pojo.domain.Sensor;
import com.sipc.intelligentfarmbackend.pojo.dto.OperationDto;
import com.sipc.intelligentfarmbackend.pojo.model.para.CreatFieldPara;
import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;
import com.sipc.intelligentfarmbackend.pojo.model.res.FieldRes;
import com.sipc.intelligentfarmbackend.service.CityService;
import com.sipc.intelligentfarmbackend.service.FieldService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class FieldController {
    private FieldService fieldService;
    private CityService cityService;
    @PostMapping("/add/field")
    public CommonResult<String> addField(@RequestBody CreatFieldPara field){
        if(cityService.containCity(field.getLocation())){
            return CommonResult.fail("城市信息错误，请输入正确的城市信息");
        }
        fieldService.addField(field);
        return CommonResult.success();
    }
    @PostMapping("/update/field")
    public CommonResult<String> updateField(){
        return CommonResult.success();
    }
    @GetMapping("/getlist/field")
    public CommonResult<List<Field>> getFieldList(){
        return CommonResult.success(fieldService.getFieldList());
      }
    @GetMapping("/get/field")
    public CommonResult<FieldRes> getField(@RequestParam Integer id){
        return CommonResult.success(fieldService.getField(id));
    }
    @PostMapping("/add/operation/field")
    public CommonResult<String> addOperation(@RequestBody OperationDto operation){
        fieldService.addOperation(operation);
        return CommonResult.success();
    }
    @DeleteMapping ("/delete/device/field")
    public CommonResult<String> deleteDevice(@RequestParam Integer id){
        fieldService.deleteDevice(id);
        return CommonResult.success();}
    @PostMapping("/add/device/field")
    public CommonResult<String> addDevice(@RequestBody Sensor sensor){
        fieldService.addDevice(sensor);
        return CommonResult.success();
    }
    @PutMapping("/alter/device/field")
    public CommonResult<String> alterDevice(@RequestParam Integer id){
        fieldService.alterDevice(id);
        return CommonResult.success();
    }
    @GetMapping("/get/status/diagram")
    public CommonResult<List<FieldStatus>> getStatus(){
        return CommonResult.success();
    }

}
