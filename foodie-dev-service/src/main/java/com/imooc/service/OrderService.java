package com.imooc.service;

import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.vo.OrderVO;

public interface OrderService {

    /**
     * 用于创建订单相关信息
     * @param submitOrderBO 创建订单业务对象
     */
    OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 修改订单状态
     * @param orderId 订单id
     * @param orderStatus 订单状态
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 查询订单状态
     * @param orderId 订单id
     * @return 订单状态信息
     */
    OrderStatus queryOrderStatusInfo(String orderId);

}
