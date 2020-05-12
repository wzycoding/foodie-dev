package com.imooc.controller;


import org.springframework.stereotype.Controller;

/**
 * 测试Controller
 */
@Controller
public class BaseController {
    public static final Integer COMMENT_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    public static final String FOODIE_SHOPCART = "shopcart";

    // 微信支付成功 -> 支付中心 -> 天天吃货平台
    // 回调通知的url
    public String payReturnUrl = "http://localhost:8088/orders/notifyMerchantOrderPaid";

    // 支付中心的调用地址
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";		// produce

}
