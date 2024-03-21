package com.sipc.intelligentfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.pojo.domain.DriverNotice;
import com.sipc.intelligentfarmbackend.pojo.domain.Plant;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper extends BaseMapper<DriverNotice> {
    Page<DriverNotice> selectNoticeInfoPage(Page<DriverNotice> page,  Integer pageSize, Integer offset,String keyWord, Integer driverId);
    IPage<DriverNotice> selectAllNoticeInfoPage(Page<DriverNotice> page, Integer pageSize, Integer offset, String keyWord);
    Page<DriverNotice> selectAllPlantPages(Page<DriverNotice> page, String keyWord);
    Page<DriverNotice> selectAllPlantPagesU(Page<DriverNotice> page, String keyWord,Integer driverId);
}
