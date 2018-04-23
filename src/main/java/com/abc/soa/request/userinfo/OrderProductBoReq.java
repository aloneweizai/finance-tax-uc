package com.abc.soa.request.userinfo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by andy on 2017/10/25.
 */
public class OrderProductBoReq {
    private String productId;
    @NotEmpty
    private Integer num;
    @NotEmpty
    private Double dealPrice;
    @NotEmpty
    private Double goodsPrice;
    @NotEmpty
    private String name;
    @NotEmpty
    private String goodsId;
    @NotEmpty
    private String browseUrl;//商品浏览地址
    @NotEmpty
    private String specInfo;
    @NotEmpty
    private String tradingChannels;

    private String categoryId;
    private String category;
    private Double weight;

    //图片地址
    private String imageUrl;

    //可否退货：0可以；1不可以
    private String isReturn;
    //可否换货：0可以，1不可以
    private String isExchange;

    public String getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    public String getIsExchange() {
        return isExchange;
    }

    public void setIsExchange(String isExchange) {
        this.isExchange = isExchange;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(Double dealPrice) {
        this.dealPrice = dealPrice;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getBrowseUrl() {
        return browseUrl;
    }

    public void setBrowseUrl(String browseUrl) {
        this.browseUrl = browseUrl;
    }

    public String getSpecInfo() {
        return specInfo;
    }

    public void setSpecInfo(String specInfo) {
        this.specInfo = specInfo;
    }

    public String getTradingChannels() {
        return tradingChannels;
    }

    public void setTradingChannels(String tradingChannels) {
        this.tradingChannels = tradingChannels;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
