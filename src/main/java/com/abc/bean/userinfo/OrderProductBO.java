package com.abc.bean.userinfo;

import java.util.Date;

/**
 * Created by zlk on 2017-07-24.
 */
public class OrderProductBO {
    private String orderNo;
    private String productId;
    private Double goodsPrice;
    private Integer num;
    private Double dealPrice;
    private String name;
    private String categoryId;
    private String category;
    private Double weight;
    private Date lastUpdate;
    private Date createTime;

    private ProductBO productBO;
    private GoodsBO goodsBO;

    private String goodsId;

    private String specInfo;
    private String goodsType;
    private String browseUrl;

    private String tradingChannels;

    /**是否可退货，0，可退，1，不可退**/
    private String isReturn;

    /**是否可换货，0：可换，1：不可换**/
    private String isExchange;

    private String imageUrl;

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getSpecInfo() {
        return specInfo;
    }

    public void setSpecInfo(String specInfo) {
        this.specInfo = specInfo;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getBrowseUrl() {
        return browseUrl;
    }

    public void setBrowseUrl(String browseUrl) {
        this.browseUrl = browseUrl;
    }

    public String getTradingChannels() {
        return tradingChannels;
    }

    public void setTradingChannels(String tradingChannels) {
        this.tradingChannels = tradingChannels;
    }

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

    public GoodsBO getGoodsBO() {
        return goodsBO;
    }

    public void setGoodsBO(GoodsBO goodsBO) {
        this.goodsBO = goodsBO;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public ProductBO getProductBO() {
        return productBO;
    }

    public void setProductBO(ProductBO productBO) {
        this.productBO = productBO;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
