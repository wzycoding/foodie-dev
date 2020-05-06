package com.imooc.pojo.vo;

import java.util.List;

/**
 * 商品分类VO类，由于分类不会太多，id为int，并且后期分库分表不会考虑商品分类
 */
public class CategoryVO {

    private Integer id;

    private String name;

    private Integer type;

    private Integer fatherId;

    /**
     * 三级分类VOList
     */
    private List<SubCategoryVO> subCatList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public List<SubCategoryVO> getSubCatList() {
        return subCatList;
    }

    public void setSubCatList(List<SubCategoryVO> subCatList) {
        this.subCatList = subCatList;
    }
}
