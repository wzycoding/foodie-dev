package com.imooc.service.center;


import com.imooc.utils.PagedGridResult;

public interface MyOrdersService {
    /**
     * 查询用户订单
     * @param userId 用户id
     * @param orderStatus 订单状态
     * @param page 第几页
     * @param pageSize 每页多少条记录
     * @return
     */
    PagedGridResult queryMyOrders(String userId,
                                  Integer orderStatus,
                                  Integer page,
                                  Integer pageSize);
}
