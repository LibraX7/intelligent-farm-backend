package com.sipc.intelligentfarmbackend.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.exception.BaseException;
import com.sipc.intelligentfarmbackend.mapper.*;
import com.sipc.intelligentfarmbackend.pojo.domain.*;
import com.sipc.intelligentfarmbackend.pojo.model.para.NoticePara;
import com.sipc.intelligentfarmbackend.pojo.model.para.ResourceNoticePara;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.ResourceService;
import com.sipc.intelligentfarmbackend.utils.JwtUtils;
import com.sipc.intelligentfarmbackend.utils.TokenThreadLocalUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private ResourceNoticeMapper resourceNoticeMapper;
    private ResourceStatusMapper resourceStatusMapper;
    private ProductNoticeMapper productNoticeMapper;
    private PlantMapper plantMapper;
    private NoticeMapper noticeMapper;
    private UserMapper userMapper;
    @Override
    public PageResult<ResourceNotice> getNoticePage(Integer resourceId, Integer pageNum, Integer pageSize, String keyWord) {
        User user = JwtUtils.getUserByToken(TokenThreadLocalUtil.getInstance().getToken());
        Page<ResourceNotice> page = new Page<>(pageNum,pageSize);
        PageResult<ResourceNotice> pageResult = new PageResult<>();
        Page<ResourceNotice> page1;
        if(user.getRoleId() == 6){
            page1 = resourceNoticeMapper.selectAllPlantPages(page,keyWord);
            page.setPages(page1.getTotal() / pageSize);
            page.setTotal(page1.getRecords().size());
            pageResult.praisePage(resourceNoticeMapper.selectAllPlantInfoPage(page,pageSize,(pageNum - 1) * pageSize,keyWord));
        }else {
            page1 = resourceNoticeMapper.selectAllPlantPageU(page,keyWord,user.getId());
            page.setTotal(page1.getRecords().size());
            page.setPages(page1.getTotal() / pageSize);
            pageResult.praisePage(resourceNoticeMapper.selectNoticeInfoPage(page, pageSize, (pageNum - 1) * pageSize, keyWord, user.getId()));
        }
        return pageResult;
    }

    @Override
    public void deleteInfo(List<Integer> ids) {
        if(resourceNoticeMapper.deleteBatchIds(ids) == 0){
            throw new BaseException("删除失败，检查id");
        }
    }

    @Override
    public void addNoticeToDriver(NoticePara noticePara) {
        // 查询数据
        User source = userMapper.selectById(noticePara.getSourceId());
        User dest = userMapper.selectById(noticePara.getDestId());
        Plant plant = plantMapper.selectById(noticePara.getTraceId());
        // 封装司机信息
        DriverNotice driverNotice = new DriverNotice();
        BeanUtils.copyProperties(noticePara,driverNotice);
        driverNotice.setStatus(0);
        driverNotice.setTime(LocalDateTime.now());
        driverNotice.setSource(source.getEnterpriseName());
        driverNotice.setDestination(dest.getEnterpriseName());
        if(noticeMapper.insert(driverNotice) == 0) throw new BaseException("司机信息插入失败");
        // 产品信息包装
        ProductNotice productNotice = new ProductNotice();
        productNotice.setResourceId(source.getId());
        productNotice.setProductId(dest.getId());
        productNotice.setStatus(0);
        productNotice.setName(plant.getName());
        productNotice.setProductStatus(0);
        productNotice.setResourceFactory(source.getEnterpriseName());
        productNotice.setResourcePerson(source.getName());
        productNotice.setResourcePerson(source.getEnterpriseName() + source.getName());
        productNotice.setTraceId(noticePara.getTraceId());
        if(productNoticeMapper.insert(productNotice) == 0) throw new BaseException("产品信息插入失败");
    }

    @Override
    public void outRepository(ResourceNoticePara resourceNoticePara) {
        log.info(resourceNoticePara.toString());
        ResourceStatus resourceStatus = new ResourceStatus();
        ResourceNotice resourceNotice = resourceNoticeMapper.selectById(resourceNoticePara.getResourceId());
        if(resourceNotice.getStatus() == 1){
            throw new BaseException("产品已出库");
        }
        resourceNotice.setStatus(1);
        if(resourceNoticeMapper.updateById(resourceNotice) == 0) throw new BaseException("更新状态失败");
        BeanUtils.copyProperties(resourceNoticePara,resourceStatus);
        resourceStatus.setResourceId(resourceNoticePara.getResourceId());
        if(resourceStatusMapper.insert(resourceStatus) == 0) throw new BaseException("填写记录失败");
    }
}
