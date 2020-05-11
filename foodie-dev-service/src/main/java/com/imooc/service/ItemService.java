package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.ShopCartVO;
import com.imooc.utils.PagedGridResult;

import java.util.List;

public interface ItemService {

    /**
     * 根据商品id查询详情
     * @param itemId 商品id
     * @return 商品详情
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     * @param itemId 商品id
     * @return 图片列表
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品id查询规格信息
     * @param itemId 商品id
     * @return 规格信息列表
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询商品属性
     * @param itemId 商品id
     * @return 商品属性
     */
    ItemsParam queryItemParam(String itemId);

    /**
     * 根据商品id查询商品的评价等级数量
     * @param itemId 商品id
     */
    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 根据商品id查询商品评价
     * @param itemId 商品id
     * @param level 评价等级
     * @param page 第几页
     * @param pageSize 每页多少条
     * @return 商品评价列表
     */
    PagedGridResult queryPagedComments(String itemId, Integer level, int page, int pageSize);

    /**
     * 搜索商品列表
     * @param keywords 搜索关键字
     * @param sort 排序规则
     * @param page 第几页
     * @param pageSize 每页多少条
     * @return 商品列表
     */
    PagedGridResult searchItems(String keywords, String sort, int page, int pageSize);

    /**
     * 搜索商品列表 通过分类id
     * @param catId 搜索关键字
     * @param sort 排序规则
     * @param page 第几页
     * @param pageSize 每页多少条
     * @return 商品列表
     */
    PagedGridResult searchItems(Integer catId, String sort, int page, int pageSize);

    /**
     * 根据规格ids查询最新的购物车中的商品数据（用于刷新渲染购物车中的商品数据）
     * @param specIds 商品规格ids
     * @return 购物车商品列表
     */
    List<ShopCartVO> queryItemsBySpecIds(String specIds);


    /**
     * 根据商品规格id获取商品规格的具体信息
     * @param specId
     * @return
     */
    ItemsSpec queryItemSpecById(String specId);


    /**
     * 根据商品id获得商品图片的主图URL
     * @param itemId
     * @return
     */
    String queryItemMainImgById(String itemId);


    /**
     * 减少库存
     * @param specId
     * @param buyCounts
     */
    void decreaseItemSpecStock(String specId, int buyCounts);


}
