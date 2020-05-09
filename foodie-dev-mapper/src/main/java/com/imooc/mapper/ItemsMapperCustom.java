package com.imooc.mapper;


import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.SearchItemsVO;
import com.imooc.pojo.vo.ShopCartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom{

    List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);

    /**
     * k是默认， c是根据销量排序， p根据价格排序
     * @param map
     * @return
     */
    List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> map);

    List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> map);

    List<ShopCartVO> queryItemsBySpecIds(@Param("paramsList") List<String> specIds);

}