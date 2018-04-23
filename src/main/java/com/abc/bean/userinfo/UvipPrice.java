package com.abc.bean.userinfo;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-07
 * @Time:18:24
 */
public class UvipPrice {
    private String id;
    private String productId;
    private String vipLevel;
    private Double discount;
    private Double tradePrice;
    private Double giftPoints;

    public String getVipLevel() {
        return vipLevel;
    }
    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }
    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    public Double getTradePrice() {
        return tradePrice;
    }
    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }
    public Double getGiftPoints() {
        return giftPoints;
    }
    public void setGiftPoints(Double giftPoints) {
        this.giftPoints = giftPoints;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }

}
