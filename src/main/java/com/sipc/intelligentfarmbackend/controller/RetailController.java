package com.sipc.intelligentfarmbackend.controller;

import com.sipc.intelligentfarmbackend.pojo.domain.RetailCommodity;
import com.sipc.intelligentfarmbackend.pojo.domain.RetailNotice;
import com.sipc.intelligentfarmbackend.pojo.domain.RetailOrder;
import com.sipc.intelligentfarmbackend.pojo.domain.RetailOrderDetail;
import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.RetailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class RetailController {
    private RetailService retailService;

    /**
     *  原料管理
     */
    @GetMapping("/get/page/notice/")
    public CommonResult<PageResult<RetailNotice>> getPage(@RequestParam("pageNum") Integer pageNum,
                                                          @RequestParam("pageSize") Integer pageSize,
                                                          @RequestParam("keyWord") String keyWord){
        return CommonResult.success(retailService.getNoticePage(pageNum,pageSize,keyWord));
    }
    @GetMapping("/sign/for/retail")
    public CommonResult<String> signFor(@RequestParam("retailId") Integer retailId){
        retailService.signFor(retailId);
        return CommonResult.success();
    }
    @PostMapping("/delete/retail/notice")
    public CommonResult<String> deleteId(@RequestBody List<Integer> ids){
        retailService.deleteNotice(ids);
        return CommonResult.success();
    }
    /**
     *  商品管理
     */
    @GetMapping("/get/page/retail/commodity")
    public CommonResult<PageResult<RetailCommodity>> getPageCommodity(@RequestParam("pageNum") Integer pageNum,
                                                                         @RequestParam("pageSize") Integer pageSize,
                                                                         @RequestParam("keyWord") String keyWord){

        return CommonResult.success(retailService.getCommodityPage(pageNum,pageSize,keyWord));
    }
    @PostMapping("/add/retail/commodity")
    public CommonResult<String> addCommodity(@RequestBody RetailCommodity retailCommodity){
        retailService.addCommodity(retailCommodity);
        return CommonResult.success();
    }
    @PutMapping("/update/retail/commodity")
    public CommonResult<String> updateCommodity(@RequestBody RetailCommodity retailCommodity){
        retailService.updateCommodity(retailCommodity);
        return CommonResult.success();
    }
    @PostMapping("/delete/retail/commodity")
    public CommonResult<String> deleteCommodity(@RequestBody List<Integer> ids){
        retailService.deleteCommodity(ids);
        return CommonResult.success();
    }
    /**
     *  订单管理
     */
    @GetMapping("/get/retail/order/page")
    public CommonResult<PageResult<RetailOrder>> getOrderPage(@RequestParam("pageNum") Integer pageNum,
                                                              @RequestParam("pageSize") Integer pageSize,
                                                              @RequestParam("keyWord") String keyWord){
       return CommonResult.success(retailService.getOrderPages(pageNum,pageSize,keyWord));
    }
    @GetMapping("/get/retail/order/detail")
    public CommonResult<RetailOrderDetail>  getOrderDetail(@RequestParam("orderId") Integer orderId){
        return CommonResult.success(retailService.getOrderDetails(orderId));
    }
    @PutMapping("/retail/sent/goods")
    public CommonResult<String> sentGoods(@RequestParam("orderId") Integer orderId){
        retailService.sendGoods(orderId);
        return CommonResult.success();
    }
    @PutMapping("/update/retail/order")
    public CommonResult<String> updateAddress(@RequestParam("orderDetailId") Integer orderDetailId,
                                              @RequestParam("address") String address){
        retailService.updateAddress(orderDetailId,address);
        return CommonResult.success();
    }
    @PostMapping("/delete/retail/order")
    public CommonResult<String> deleteOrder(@RequestBody List<Integer> ids){
        retailService.deleteOrder(ids);
        return CommonResult.success();
    }

}
