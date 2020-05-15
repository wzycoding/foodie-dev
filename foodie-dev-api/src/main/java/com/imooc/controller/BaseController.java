package com.imooc.controller;


import com.imooc.pojo.Orders;
import com.imooc.service.center.MyOrdersService;
import com.imooc.utils.IMOOCJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;

/**
 * 测试Controller
 */
@Controller
public class BaseController {

    @Autowired
    public MyOrdersService myOrdersService;

    public static final Integer COMMON_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    public static final String FOODIE_SHOPCART = "shopcart";

    // 微信支付成功 -> 支付中心 -> 天天吃货平台
    // 回调通知的url
    public String payReturnUrl = "http://localhost:8088/orders/notifyMerchantOrderPaid";

    // 支付中心的调用地址
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";		// produce

    // 用户上次头像的位置, 为了兼容多个系统，使用separator
    public static final String IMAGE_USER_FACE_LOCATION = System.getProperty("user.home") + File.separator +  "workspaces" +
            File.separator +  "images" +
            File.separator +  "foodie" +
            File.separator +  "faces";
//    public static final String IMAGE_USER_FACE_LOCATION = "~/workspaces/images/foodie/faces";

    /**
     * 用于验证用户和订单是否有关联关系，避免菲方用户调用
     * @return
     */
    public IMOOCJSONResult checkUserOrder(String userId, String orderId) {
        Orders order = myOrdersService.queryMyOrder(userId, orderId);
        if (order == null) {
            return IMOOCJSONResult.errorMsg("订单不存在！");
        }
        return IMOOCJSONResult.ok(order);
    }


}
