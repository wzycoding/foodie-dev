package com.imooc.controller;

import com.imooc.pojo.*;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.service.ItemService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品controller
 */
@Api(value = "商品", tags = {"商品信息展示的相关接口"})
@RestController
@RequestMapping("items")
public class ItemsController {

    @Autowired
    private ItemService itemService;
    /**
     * 首页轮播图
     */
    @GetMapping("/info/{itemId}")
    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    public IMOOCJSONResult carousel(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg("");
        }

        Items item = itemService.queryItemById(itemId);

        List<ItemsImg> itemImgList = itemService.queryItemImgList(itemId);

        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);

        ItemsParam itemsParam = itemService.queryItemParam(itemId);

        ItemInfoVO vo = new ItemInfoVO();
        vo.setItem(item);
        vo.setItemImgList(itemImgList);
        vo.setItemSpecList(itemsSpecList);
        vo.setItemParams(itemsParam);
        return IMOOCJSONResult.ok(vo);
    }
}
