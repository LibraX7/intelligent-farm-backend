package com.sipc.intelligentfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sipc.intelligentfarmbackend.pojo.domain.DriverNotice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper extends BaseMapper<DriverNotice> {
    Page<DriverNotice> selectNoticeInfoPage(Page<DriverNotice> page, String keyWord, Integer driverId);
}
