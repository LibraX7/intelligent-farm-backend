package com.sipc.intelligentfarmbackend.controller;

import com.sipc.intelligentfarmbackend.pojo.domain.ProductInfo;
import com.sipc.intelligentfarmbackend.pojo.domain.ProductMission;
import com.sipc.intelligentfarmbackend.pojo.domain.ProductNotice;
import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    @GetMapping("/product/page")
    public CommonResult<PageResult<ProductNotice>> getPage(@RequestParam("pageNum") Integer pageNum,
                                                           @RequestParam("pageSize") Integer pageSize,
                                                           @RequestParam("keyWord") String keyWord,
                                                           @RequestParam("id") Integer id){
        return CommonResult.success(productService.getNoticePage(id,pageNum,pageSize,keyWord));
    }
    @PostMapping("/product/add/info")
    public CommonResult<String> addInfo(@RequestBody ProductInfo productInfo){
        productService.addProductInfo(productInfo);
        return CommonResult.success();
    }
    @GetMapping("/product/get/info")
    public CommonResult<ProductInfo> getInfo(@RequestParam("id") Integer id){
        return CommonResult.success(productService.getInfo(id));
    }
    @PostMapping("/product/add/mission")
    public CommonResult<String> addMission(@RequestBody ProductMission productMission){
        productService.addProductMission(productMission);
        return CommonResult.success();
    }
    @GetMapping("/product/get/mission")
    public CommonResult<ProductMission> getMission(@RequestParam("id") Integer id){
        return CommonResult.success(productService.getMission(id));
    }
    @PutMapping("/product/output")
    public CommonResult<String> outputProduct(@RequestParam("id") Integer id){
        productService.outputProduct(id);
        return CommonResult.success();
    }

}
