package com.sipc.intelligentfarmbackend.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.exception.BaseException;
import com.sipc.intelligentfarmbackend.mapper.*;
import com.sipc.intelligentfarmbackend.pojo.domain.*;
import com.sipc.intelligentfarmbackend.pojo.model.para.NoticePara;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.ProductService;
import com.sipc.intelligentfarmbackend.utils.JwtUtils;
import com.sipc.intelligentfarmbackend.utils.TimeUtil;
import com.sipc.intelligentfarmbackend.utils.TokenThreadLocalUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductNoticeMapper productNoticeMapper;
    private ProductInfoMapper productInfoMapper;
    private ProductMissionMapper productMissionMapper;
    private RetailNoticeMapper retailNoticeMapper;
    private UserMapper userMapper;
    private NoticeMapper noticeMapper;
    private PlantMapper plantMapper;

    @Override
    public PageResult<ProductNotice> getNoticePage(Integer productId, Integer pageNum, Integer pageSize, String keyWord) {
        User user = JwtUtils.getUserByToken(TokenThreadLocalUtil.getInstance().getToken());
        Page<ProductNotice> page = new Page<>(pageNum,pageSize);
        PageResult<ProductNotice> pageResult = new PageResult<>();
        Page<ProductNotice> page1;
        if(user.getRoleId() == 6){
            page1 = productNoticeMapper.selectAllPlantPages(page,keyWord);
            page.setTotal(page1.getRecords().size());
            page.setPages(page1.getTotal() / pageSize);
            pageResult.praisePage(productNoticeMapper.selectAllPlantInfoPage(page,pageSize,(pageNum - 1) * pageSize,keyWord));
        }else {
            page1 = productNoticeMapper.selectAllPlantPageU(page,keyWord,user.getId());
            page.setTotal(page1.getRecords().size());
            page.setPages(page1.getTotal() / pageSize);
            pageResult.praisePage(productNoticeMapper.selectNoticeInfoPage(page, pageSize, (pageNum - 1) * pageSize, keyWord, user.getId()));
        }
        return pageResult;
    }

    @Override
    public void addProductInfo(ProductInfo productInfo) {
        productInfo.setTime(LocalDateTime.now());
        if(productInfoMapper.insert(productInfo) == 0){
            throw new BaseException("检查数据是否完整");
        }
    }
    @Override
    public ProductInfo getInfo(Integer id) {
        return productInfoMapper.selectOne(new LambdaQueryWrapper<ProductInfo>().eq(ProductInfo::getProductId,id));
    }

    @Override
    public void addProductMission(ProductMission productMission) {
        if(productMissionMapper.insert(productMission) == 0){
            throw new BaseException("添加失败");
        }
        ProductNotice productNotice = productNoticeMapper.selectById(productMission.getProductId());
        productNotice.setProductStatus(1);
        if(productNoticeMapper.updateById(productNotice) == 0){
            throw new BaseException("更新商品状态失败/数据未更改");
        }
    }
    @Override
    public ProductMission getMission(Integer id){
        return productMissionMapper.selectOne(new LambdaQueryWrapper<ProductMission>().eq(ProductMission::getProductId,id));
    }

    @Override
    public void outputProduct(Integer id) {
        ProductNotice productNotice = productNoticeMapper.selectById(id);
        if(productNotice == null){
            throw new BaseException("id不存在");
        }
        if(productNotice.getStatus() == 1){
            throw new BaseException("商品已出库");
        }
        productNotice.setStatus(1);
        productNoticeMapper.updateById(productNotice);
    }

    @Override
    public void noticeDriver(NoticePara noticePara) {
        // 查询数据
        User source = userMapper.selectById(noticePara.getSourceId());
        User dest = userMapper.selectById(noticePara.getDestId());
        Plant plant = plantMapper.selectById(noticePara.getTraceId());
        // 封装司机信息
        DriverNotice driverNotice = new DriverNotice();
        BeanUtils.copyProperties(noticePara,driverNotice);
        driverNotice.setTime(LocalDateTime.now());
        driverNotice.setStatus(0);
        driverNotice.setSource(source.getEnterpriseName());
        driverNotice.setDestination(dest.getEnterpriseName());
        if(noticeMapper.insert(driverNotice) == 0) throw new BaseException("司机信息插入失败");
        // 封装零售商信息
        RetailNotice retailNotice = new RetailNotice();
        retailNotice.setStatus(0);
        retailNotice.setName(plant.getName());
        retailNotice.setTime(LocalDateTime.now());
        retailNotice.setTraceId(noticePara.getTraceId());
        retailNotice.setUserId(noticePara.getDestId());
        retailNotice.setSource(source.getEnterpriseName());
        if(retailNoticeMapper.insert(retailNotice) == 0) throw new BaseException("零售商信息插入失败");

    }

    @Override
    public void deleteNotice(List<Integer> ids) {
        for (Integer id : ids) {
            productInfoMapper.delete(new LambdaQueryWrapper<ProductInfo>().eq(ProductInfo::getProductId,id));
            productMissionMapper.delete(new LambdaQueryWrapper<ProductMission>().eq(ProductMission::getProductId,id));
        }
        productNoticeMapper.deleteBatchIds(ids);

    }
}
