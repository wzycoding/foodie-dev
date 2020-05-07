package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;

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
}
