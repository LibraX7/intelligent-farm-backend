package com.sipc.intelligentfarmbackend.service;

import com.sipc.intelligentfarmbackend.pojo.domain.RetailCommodity;
import com.sipc.intelligentfarmbackend.pojo.domain.RetailNotice;
import com.sipc.intelligentfarmbackend.pojo.domain.RetailOrder;
import com.sipc.intelligentfarmbackend.pojo.domain.RetailOrderDetail;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import java.util.List;


public interface RetailService {
    PageResult<RetailNotice> getNoticePage(Integer pageNum, Integer pageSize, String pageNum1);
    void signFor(Integer retailId);
    void deleteNotice(List<Integer> ids);
    PageResult<RetailCommodity>  getCommodityPage(Integer pageNum, Integer pageSize, String keyWord);
    void addCommodity(RetailCommodity retailCommodity);
    void updateCommodity(RetailCommodity retailCommodity);
    void deleteCommodity(List<Integer> ids);
    PageResult<RetailOrder> getOrderPages(Integer pageNum, Integer pageSize, String keyWord);
    RetailOrderDetail getOrderDetails(Integer orderId);
    void updateAddress(Integer id, String address);
    void sendGoods(Integer id);
    void deleteOrder(List<Integer> ids);
}
