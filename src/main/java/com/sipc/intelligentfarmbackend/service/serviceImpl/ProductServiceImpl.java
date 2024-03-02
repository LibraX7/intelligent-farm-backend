package com.sipc.intelligentfarmbackend.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.exception.BaseException;
import com.sipc.intelligentfarmbackend.mapper.ProductInfoMapper;
import com.sipc.intelligentfarmbackend.mapper.ProductMissionMapper;
import com.sipc.intelligentfarmbackend.mapper.ProductNoticeMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.ProductInfo;
import com.sipc.intelligentfarmbackend.pojo.domain.ProductMission;
import com.sipc.intelligentfarmbackend.pojo.domain.ProductNotice;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductNoticeMapper productNoticeMapper;
    private ProductInfoMapper productInfoMapper;
    private ProductMissionMapper productMissionMapper;

    @Override
    public PageResult<ProductNotice> getNoticePage(Integer productId, Integer pageNum, Integer pageSize, String keyWord) {
        Page<ProductNotice> page = new Page<>(pageNum,pageSize);
        PageResult<ProductNotice> pageResult = new PageResult<>();
        return  pageResult.praisePage(productNoticeMapper.selectNoticeInfoPage(page,keyWord,productId));
    }

    @Override
    public void addProductInfo(ProductInfo productInfo) {
        if(productInfoMapper.insert(productInfo) == 0){
            throw new BaseException("检查数据是否完整");
        }
    }
    @Override
    public ProductInfo getInfo(Integer id) {
        return productInfoMapper.selectById(id);
    }

    @Override
    public void addProductMission(ProductMission productMission) {
        if(productMissionMapper.insert(productMission) == 0){
            throw new BaseException("添加失败");
        }
    }
    @Override
    public ProductMission getMission(Integer id){
        return productMissionMapper.selectById(id);
    }

    @Override
    public void outputProduct(Integer id) {
        ProductNotice productNotice = productNoticeMapper.selectById(id);
        if(productNotice == null){
            throw new BaseException("id不存在");
        }
        productNotice.setStatus(0);
        productNoticeMapper.updateById(productNotice);
    }
}
