package com.imooc.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.OrdersMapperCustom;
import com.imooc.pojo.vo.MyOrdersVO;
import com.imooc.service.center.MyOrdersService;
import com.imooc.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyOrderServiceImpl implements MyOrdersService {

    @Autowired
    private OrdersMapperCustom ordersMapperCustom;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedGridResult queryMyOrders(String userId,
                                         Integer orderStatus,
                                         Integer page,
                                         Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        if (orderStatus != null) {
            map.put("orderStatus", orderStatus);
        }
        PageHelper.startPage(page, pageSize);
        List<MyOrdersVO> list = ordersMapperCustom.queryMyOrders(map);

        return setterPagedGrid(list, page);
    }

    /**
     * 设置分页参数
     * @param list 源数据list
     * @param page 第几页
     * @return 分页结果
     */
    private PagedGridResult setterPagedGrid(List<?> list, int page) {
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
