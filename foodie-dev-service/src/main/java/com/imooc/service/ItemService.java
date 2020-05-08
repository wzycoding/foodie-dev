package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemCommentVO;
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
     * @return 商品评价列表
     */
    PagedGridResult queryPagedComments(String itemId, Integer level, int page, int pageSize);
}
