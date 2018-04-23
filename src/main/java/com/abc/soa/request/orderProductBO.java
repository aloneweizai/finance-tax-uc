package com.abc.soa.request;

/**
 * Created by stuy on 2017/10/26.
 */
public class orderProductBO {
    /**
     * 非必填
     */
    private String productId;
    /**
     * 购买数量
     * 必填
     */
    private int num;
    /**
     * 成交价
     * 必填
     */
    private double dealPrice;
    /**
     * 商品价格
     * 必填
     */
    private double goodsPrice;

    /**
     * 商品名称
     * 必填
     */
    private String name;
    /**
     * 分类编号
     * 非必填
     */
    private String categoryId;
    /**
     * 分类名称
     * 非必填
     */
    private String category;
    /**
     * 非必填
     */
    private String weight;
    /**
     * 商品编号
     * 必填
     */
    private String goodsId;
    /**
     * 商品浏览url
     * 必填
     */
    private String browseUrl;
    /**
     * 规格
     * 必填
     */
    private String specInfo;
    /**
     * 交易渠道
     * 必填
     */
    private String tradingChannels;

    //图片地址
    private String imageUrl;

    //可否退货：0可以；1不可以
    private String isReturn = "1";
    //可否换货：0可以，1不可以
    private String isExchange = "1";

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(double dealPrice) {
        this.dealPrice = dealPrice;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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
}
