package com.sipc.intelligentfarmbackend.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.exception.BaseException;
import com.sipc.intelligentfarmbackend.mapper.ResourceNoticeMapper;
import com.sipc.intelligentfarmbackend.pojo.domain.DriverNotice;
import com.sipc.intelligentfarmbackend.pojo.domain.ResourceNotice;
import com.sipc.intelligentfarmbackend.pojo.model.res.PageResult;
import com.sipc.intelligentfarmbackend.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private ResourceNoticeMapper resourceNoticeMapper;
    @Override
    public PageResult<ResourceNotice> getNoticePage(Integer resourceId, Integer pageNum, Integer pageSize, String keyWord) {
        Page<ResourceNotice> page = new Page<>(pageNum,pageSize);
        PageResult<ResourceNotice> pageResult = new PageResult<>();
        return  pageResult.praisePage(resourceNoticeMapper.selectNoticeInfoPage(page,keyWord,resourceId));
    }

    @Override
    public void deleteInfo(List<Integer> ids) {
        if(resourceNoticeMapper.deleteBatchIds(ids) == 0){
            throw new BaseException("删除失败，检查id");
        }
    }

    @Override
    public void addNoticeToDriver(ResourceNotice resourceNotice) {
        DriverNotice driverNotice = new DriverNotice();

    }
}
