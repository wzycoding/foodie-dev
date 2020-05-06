package com.imooc.pojo.vo;

public class SimpleItemVO {
//    <id column="itemId" property="itemId"/>
//            <result column="itemName" property="itemName"/>
//            <result column="itemUrl" property="itemUrl"/>
    private String itemId;

    private String itemName;

    private String itemUrl;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
}
