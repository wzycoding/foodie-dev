package com.imooc.controller;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayMethod;
import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.vo.MerchantOrdersVO;
import com.imooc.pojo.vo.OrderVO;
import com.imooc.service.OrderService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "订单相关", tags = { "订单相关的api接口" })
@RestController
@RequestMapping("orders")
public class OrdersController extends BaseController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/create")
    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    public IMOOCJSONResult create(
            @RequestBody SubmitOrderBO submitOrderBO,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (submitOrderBO.getPayMethod() != PayMethod.WEIXIN.type && submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type) {
            return IMOOCJSONResult.errorMsg("支付方式不支持");
        }
//        System.out.println(submitOrderBO);

        // 1.创建订单
        OrderVO orderVO = orderService.createOrder(submitOrderBO);
        String orderId = orderVO.getOrderId();


        // 2.创建订单以后，移除购物车中已结算（已提交）的商品
        /**
         * 1001
         * 2002 ->用户购买
         * 3003 ->用户购买
         * 4004
         */
        // TODO 整合redis之后，完善购物车中的已结算商品清除，并且同步到前端的cookie
//        CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "", true);

        // 3.向支付中心发送当前订单，用于保存支付中心的订单数据
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(payReturnUrl);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("imoocUserId", "imooc");
        httpHeaders.add("password", "imooc");

        HttpEntity<MerchantOrdersVO> entity = new HttpEntity<>(merchantOrdersVO, httpHeaders);

        // 为了方便测试购买，所有的支付金额都改为1分钱
        merchantOrdersVO.setAmount(1);

        // TODO 这里一定会失败
        // 远程调用支付中心
//        ResponseEntity<IMOOCJSONResult> responseEntity = restTemplate.postForEntity(paymentUrl, entity, IMOOCJSONResult.class);
//        IMOOCJSONResult result = responseEntity.getBody();

//        if (result.getStatus() == 200) {
//            return IMOOCJSONResult.errorMsg("支付中心订单创建失败，请联系管理员");
//        }
        return IMOOCJSONResult.ok(orderId);
    }

    /**
     * 支付成功后的微信回调地址
     * @param merchantOrderId 商户订单id
     * @return HTTP状态码
     */
    @PostMapping("notifyMerchantOrderPaid")
    @ApiOperation(value = "通知更改商户订单状态", notes = "通知更改商户订单状态", httpMethod = "POST")
    public int notifyMerchantOrderPaid(String merchantOrderId) {
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }

    @PostMapping("getPaidOrderInfo")
    @ApiOperation(value = "查询用户订单状态", notes = "查询用户订单状态", httpMethod = "POST")
    public IMOOCJSONResult getPaidOrderInfo(String orderId) {
        OrderStatus orderStatus = orderService.queryOrderStatusInfo(orderId);
        return IMOOCJSONResult.ok(orderStatus);
    }

}
