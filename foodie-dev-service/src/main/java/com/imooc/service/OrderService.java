package com.imooc.service;

import com.imooc.pojo.bo.SubmitOrderBO;

public interface OrderService {

    /**
     * 用于创建订单相关信息
     * @param submitOrderBO 创建订单业务对象
     */
    String createOrder(SubmitOrderBO submitOrderBO);
}
