package com.sipc.intelligentfarmbackend.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.exception.BaseException;
import com.sipc.intelligentfarmbackend.mapper.RetailCommodityMapper;
import com.sipc.intelligentfarmbackend.mapper.RetailNoticeMapper;
import com.sipc.intelligentfarmbackend.mapper.RetailOrderDetailMapper;
import com.sipc.intelligentfarmbackend.mapper.RetailOrderMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.*;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.RetailService;
import com.sipc.intelligentfarmbackend.utils.JwtUtils;
import com.sipc.intelligentfarmbackend.utils.MinioUtil;
import com.sipc.intelligentfarmbackend.utils.TokenThreadLocalUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RetailServiceImpl implements RetailService {
    private MinioUtil minioUtil;
    private RetailOrderMapper retailOrderMapper;
    private RetailNoticeMapper retailNoticeMapper;
    private RetailCommodityMapper retailCommodityMapper;
    private RetailOrderDetailMapper retailOrderDetailMapper;
    @Override
    public PageResult<RetailNotice> getNoticePage(Integer pageNum, Integer pageSize, String keyWord) {
        User user = JwtUtils.getUserByToken(TokenThreadLocalUtil.getInstance().getToken());
        Page<RetailNotice> page = new Page<>(pageNum,pageSize);
        PageResult<RetailNotice> pageResult = new PageResult<>();
        Page<RetailNotice> page1;
        if(user.getRoleId() == 6){
            page1 = retailNoticeMapper.selectAllPlantPages(page,keyWord);
            page.setTotal(page1.getRecords().size());
            page.setPages(page1.getTotal() / pageSize);
            pageResult.praisePage(retailNoticeMapper.selectAllPlantInfoPage(page,pageSize,(pageNum - 1) * pageSize,keyWord));
        }else {
            page1 = retailNoticeMapper.selectAllPlantPageU(page,keyWord,user.getId());
            page.setPages(page1.getTotal() / pageSize);
            page.setTotal(page1.getRecords().size());
            pageResult.praisePage(retailNoticeMapper.getNoticePage(page, pageSize, (pageNum - 1) * pageSize, keyWord, user.getId()));
        }
        return pageResult;
    }

    @Override
    public void signFor(Integer retailId) {
        RetailNotice retailNotice = retailNoticeMapper.selectById(retailId);
        retailNotice.setStatus(1);
        if(retailNoticeMapper.updateById(retailNotice) == 0){
            throw new BaseException("更新状态失败");
        }
    }

    @Override
    public void deleteNotice(List<Integer> ids) {
        if(retailNoticeMapper.deleteBatchIds(ids) == 0){
            throw new BaseException("删除失败");
        }
    }
    @Override
    public PageResult<RetailCommodity> getCommodityPage(Integer pageNum, Integer pageSize, String keyWord) {
        User user = JwtUtils.getUserByToken(TokenThreadLocalUtil.getInstance().getToken());
        Page<RetailCommodity> page = new Page<>(pageNum,pageSize);
        PageResult<RetailCommodity> pageResult = new PageResult<>();
        Page<RetailCommodity> page1;
        if(user.getRoleId() == 6){
            page1 = retailCommodityMapper.selectAllPlantPages(page,keyWord);
            page.setTotal(page1.getRecords().size());
            page.setPages(page1.getTotal() / pageSize);
            pageResult.praisePage(retailCommodityMapper.selectAllPlantInfoPage(page,pageSize,(pageNum - 1) * pageSize,keyWord));
        }else {
            page1 = retailCommodityMapper.selectAllPlantPageU(page,keyWord,user.getId());
            page.setPages(page1.getTotal() / pageSize);
            page.setTotal(page1.getRecords().size());
            pageResult.praisePage(retailCommodityMapper.getCommodity(page, pageSize, (pageNum - 1) * pageSize, keyWord, user.getId()));
        }
        return pageResult;
    }

    @Override
    public void addCommodity(RetailCommodity retailCommodity) {
        if(retailCommodityMapper.insert(retailCommodity) == 0){
            throw new BaseException("商品添加失败");
        }
    }

    @Override
    public void updateCommodity(RetailCommodity retailCommodity) {
        RetailCommodity retailCommodity1 = retailCommodityMapper.selectById(retailCommodity.getId());
        minioUtil.removeObject(retailCommodity1.getImgUrl());
        if(retailCommodityMapper.insert(retailCommodity) == 0){
            throw new BaseException("更新商品失败");
        }
    }

    @Override
    public void deleteCommodity(List<Integer> ids) {
        List<RetailCommodity> list = retailCommodityMapper.selectBatchIds(ids);
        for (RetailCommodity retailCommodity : list) {
            minioUtil.removeObject(retailCommodity.getImgUrl());
        }
        if(retailCommodityMapper.deleteBatchIds(ids) == 0) throw new BaseException("删除商品失败");
    }

    @Override
    public PageResult<RetailOrder> getOrderPages(Integer pageNum, Integer pageSize, String keyWord) {
        User user = JwtUtils.getUserByToken(TokenThreadLocalUtil.getInstance().getToken());
        Page<RetailOrder> page = new Page<>(pageNum,pageSize);
        PageResult<RetailOrder> pageResult = new PageResult<>();
        Page<RetailOrder> page1;
        if(user.getRoleId() == 6){
            page1 = retailOrderMapper.selectAllPlantPages(page,keyWord);
            page.setTotal(page1.getRecords().size());
            page.setPages(page1.getTotal() / pageSize);
            pageResult.praisePage(retailOrderMapper.selectAllPlantInfoPage(page,pageSize,(pageNum - 1) * pageSize,keyWord));
        }else {
            page1 = retailOrderMapper.selectAllPlantPageU(page,keyWord,user.getId());
            page.setPages(page1.getTotal() / pageSize);
            page.setTotal(page1.getRecords().size());
            pageResult.praisePage(retailOrderMapper.selectOrderPage(page, pageSize, (pageNum - 1) * pageSize, keyWord, user.getId()));
        }
        return pageResult;
    }

    @Override
    public RetailOrderDetail getOrderDetails(Integer orderId) {
        return retailOrderDetailMapper.selectById(orderId);
    }

    @Override
    public void updateAddress(Integer id, String address) {
        RetailOrderDetail retailOrderDetail = retailOrderDetailMapper.selectById(id);
        retailOrderDetail.setAddress(address);
        if(retailOrderDetailMapper.updateById(retailOrderDetail) == 0) throw new BaseException("更新地址失败");
    }

    @Override
    public void sendGoods(Integer id) {
        RetailOrder retailOrder = retailOrderMapper.selectById(id);
        retailOrder.setGoodStatus(1);
        if(retailOrderMapper.updateById(retailOrder) == 0) throw new BaseException("更新发货状态失败");
    }

    @Override
    public void deleteOrder(List<Integer> ids) {
        if(retailOrderMapper.deleteBatchIds(ids) == 0) throw new BaseException("删除订单失败");
    }
}
