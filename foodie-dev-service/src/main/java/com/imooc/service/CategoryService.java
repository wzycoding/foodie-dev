package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;

import java.util.List;

/**
 * 商品分类Service
 */
public interface CategoryService {

    /**
     * 查询所有一级分类
     * @return
     */
    List<Category> queryAllRootLevelCat();


    /**
     * 获取二级和三级分类
     * @param rootCatId 一级分类id
     * @return
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的六个最新的商品数据
     * @param rootCatId 一级分类id
     * @return
     */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
