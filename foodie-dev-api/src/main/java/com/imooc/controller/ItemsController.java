package com.imooc.controller;

import com.imooc.pojo.*;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.pojo.vo.ShopCartVO;
import com.imooc.service.ItemService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品controller
 */
@Api(value = "商品", tags = {"商品信息展示的相关接口"})
@RestController
@RequestMapping("items")
public class ItemsController extends BaseController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/info/{itemId}")
    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    public IMOOCJSONResult info(
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

    @GetMapping("/commentLevel")
    @ApiOperation(value = "查询商品评价等级", notes = "查询商品评价等级", httpMethod = "GET")
    public IMOOCJSONResult commentLevel(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @RequestParam String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg("");
        }
        CommentLevelCountsVO countsVO = itemService.queryCommentCounts(itemId);
        return IMOOCJSONResult.ok(countsVO);
    }


    @GetMapping("/comments")
    @ApiOperation(value = "查询商品评论", notes = "查询商品评论", httpMethod = "GET")
    public IMOOCJSONResult comments(@ApiParam(name = "itemId", value = "商品id", required = true)
                                        @RequestParam String itemId,
                                        @ApiParam(name = "level", value = "评价等级", required = false)
                                        @RequestParam Integer level,
                                        @ApiParam(name = "page", value = "查询下一页的地址页", required = false)
                                        @RequestParam Integer page,
                                        @ApiParam(name = "pageSize", value = "分页的每一页显示的记录数", required = false)
                                        @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg("");
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        PagedGridResult grid = itemService.queryPagedComments(itemId, level, page, pageSize);
        return IMOOCJSONResult.ok(grid);
    }

    @GetMapping("/search")
    @ApiOperation(value = "搜索商品列表", notes = "搜索商品列表", httpMethod = "GET")
    public IMOOCJSONResult search(@ApiParam(name = "keywords", value = "关键字", required = true)
                                    @RequestParam String keywords,
                                    @ApiParam(name = "sort", value = "排序规则", required = false)
                                    @RequestParam String sort,
                                    @ApiParam(name = "page", value = "查询下一页的地址页", required = false)
                                    @RequestParam Integer page,
                                    @ApiParam(name = "pageSize", value = "分页的每一页显示的记录数", required = false)
                                    @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(keywords)) {
            return IMOOCJSONResult.errorMsg("");
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        PagedGridResult grid = itemService.searchItems(keywords, sort, page, pageSize);
        return IMOOCJSONResult.ok(grid);
    }

    @GetMapping("/catItems")
    @ApiOperation(value = "通过分类id搜索商品列表", notes = "通过分类id搜索商品列表", httpMethod = "GET")
    public IMOOCJSONResult catItems(@ApiParam(name = "catId", value = "三级分类id", required = true)
                                  @RequestParam Integer catId,
                                  @ApiParam(name = "sort", value = "排序规则", required = false)
                                  @RequestParam String sort,
                                  @ApiParam(name = "page", value = "查询下一页的地址页", required = false)
                                  @RequestParam Integer page,
                                  @ApiParam(name = "pageSize", value = "分页的每一页显示的记录数", required = false)
                                  @RequestParam Integer pageSize) {
        if (catId == null) {
            return IMOOCJSONResult.errorMsg("");
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        PagedGridResult grid = itemService.searchItems(catId, sort, page, pageSize);
        return IMOOCJSONResult.ok(grid);
    }

    // 用于用户长时间未登录网站，刷新购物车中的数据（主要是商品价格），类似于京东、淘宝
    @GetMapping("/refresh")
    @ApiOperation(value = "根据商品规格ids查找最新的商品数据", notes = "根据商品规格ids查找最新的商品数据", httpMethod = "GET")
    public IMOOCJSONResult catItems(@ApiParam(name = "itemSpecIds", value = "拼接的规格ids", required = true)
                                    @RequestParam String itemSpecIds) {
        if (StringUtils.isBlank(itemSpecIds)) {
            return IMOOCJSONResult.ok();
        }

        List<ShopCartVO> list = itemService.queryItemsBySpecIds(itemSpecIds);
        return IMOOCJSONResult.ok(list);
    }

}
