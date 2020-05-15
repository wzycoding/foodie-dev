package com.imooc.service.impl.center;

import com.github.pagehelper.PageInfo;
import com.imooc.utils.PagedGridResult;

import java.util.List;

/**
 * 基础service
 */
public class BaseService {
    /**
     * 设置分页参数
     * @param list 源数据list
     * @param page 第几页
     * @return 分页结果
     */
    public PagedGridResult setterPagedGrid(List<?> list, int page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        // 总页数
        grid.setTotal(pageList.getPages());
        // 总记录数
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
