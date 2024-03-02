package com.sipc.intelligentfarmbackend.service;

import com.sipc.intelligentfarmbackend.pojo.domain.ProductInfo;
import com.sipc.intelligentfarmbackend.pojo.domain.ProductMission;
import com.sipc.intelligentfarmbackend.pojo.domain.ProductNotice;
import com.sipc.intelligentfarmbackend.pojo.domain.ResourceNotice;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;

public interface ProductService {
    PageResult<ProductNotice> getNoticePage(Integer productId, Integer pageNum, Integer pageSize, String keyWord);
    ProductInfo getInfo(Integer id);
    ProductMission getMission(Integer id);
    void addProductInfo(ProductInfo productInfo);
    void addProductMission(ProductMission productMission);
    void outputProduct(Integer id);

}
