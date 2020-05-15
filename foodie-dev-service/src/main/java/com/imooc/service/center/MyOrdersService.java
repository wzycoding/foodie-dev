package com.imooc.service.center;


import com.imooc.pojo.Orders;
import com.imooc.pojo.vo.center.OrderStatusCountsVO;
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

    /**
     * 更新订单状态：订单状态 --> 商家发货
     * @param orderId
     */
    void updateDeliverOrderStatus(String orderId);

    Orders queryMyOrder(String userId, String orderId);

    /**
     * 更新订单状态确认收货
     * @param orderId
     * @return
     */
    boolean updateReceiveOrderStatus(String orderId);

    /**
     *
     * @param userId
     * @param orderId
     * @return
     */
    boolean deleteOrder(String userId, String orderId);


    /**
     * 查询用户订单数
     * @param userId
     */
    OrderStatusCountsVO getOrderStatusCounts(String userId);

    /**
     * 获取订单动向
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult getOrdersTrend(String userId, Integer page, Integer pageSize);
}
