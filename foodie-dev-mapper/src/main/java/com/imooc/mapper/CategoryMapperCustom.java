package com.imooc.mapper;

import com.imooc.vo.CategoryVO;

import java.util.List;

public interface CategoryMapperCustom {

    List<CategoryVO> getSubCatList(Integer rootCatId);

}