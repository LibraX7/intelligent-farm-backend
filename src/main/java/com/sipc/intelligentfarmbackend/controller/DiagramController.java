package com.sipc.intelligentfarmbackend.controller;

import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;
import com.sipc.intelligentfarmbackend.pojo.model.res.DiagramRes;
import com.sipc.intelligentfarmbackend.service.DiagramService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class DiagramController {
    private DiagramService diagramService;
    @GetMapping("/get/diagram")
    public CommonResult<DiagramRes> getDiagramData(){
        return CommonResult.success(diagramService.getData());
    }
}
