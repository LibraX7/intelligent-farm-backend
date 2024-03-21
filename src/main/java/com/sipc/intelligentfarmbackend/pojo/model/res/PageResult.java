package com.sipc.intelligentfarmbackend.pojo.model.res;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;
@Data
public class PageResult<T> {
    private Long pageNo;
    private Long pageSize;
    private Long pages;
    private Long total;
    public List<T> records;
    public PageResult<T> praisePage(IPage<T> page) {
        this.records = page.getRecords();
        this.total = page.getTotal();
        this.pageNo = page.getCurrent();
        this.pageSize = page.getSize();
        this.pages = page.getPages();
        return this;
    }
}
