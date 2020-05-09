package com.imooc.controller;

import com.imooc.pojo.bo.ShopCartBO;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "购物车接口Controller", tags = {"购物车接口相关的api"})
@RestController
@RequestMapping("shopcart")
public class ShopCartController {

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @RequestMapping("/add")
    public IMOOCJSONResult add(@RequestParam String userId,
                               @RequestBody ShopCartBO shopcartBo,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        if (StringUtils.isBlank(userId)) {
            return  IMOOCJSONResult.errorMsg("");
        }

        System.out.println(shopcartBo);
        // TODO 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "删除购物车中的商品", notes = "删除购物车中的商品", httpMethod = "POST")
    @RequestMapping("/del")
    public IMOOCJSONResult del(@RequestParam String userId,
                               @RequestBody String itemSpecId,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)) {
            return  IMOOCJSONResult.errorMsg("");
        }

        // TODO 用户删除在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步删除后端购物车中的商品
        return IMOOCJSONResult.ok();
    }
}
