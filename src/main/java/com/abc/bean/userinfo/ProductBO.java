package com.abc.bean.userinfo;

import java.util.Date;
import java.util.List;

/**
 * @Author:zlk
 * @Description:商品实体
 * @Date:2017-08-07
 * @Time:15:10
 */
public class ProductBO {
    private String id;
    private String goodsId;
    private String dictId;
    private Integer stock;
    private Double marketPrice;
    private Double sellingPrice;
    private Double costPrice;
    private Double finalPrice;
    private Integer discount;
    private String uvipLevel;
    private Double weight;
    private List<DictBO> dictList;
    private List<UvipPrice>  uvipPriceList;
    private String goodsName;
    private Integer startRepo;
    private Integer endRepo;
    private Date createTime;
    private Date lastUpdate;

    //1.实物;2.虚拟;3.服务;4.会员;5充值;6学堂服务
    private String goodsType;

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsName() {
        return goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public Integer getStartRepo() {
        return startRepo;
    }
    public void setStartRepo(Integer startRepo) {
        this.startRepo = startRepo;
    }
    public Integer getEndRepo() {
        return endRepo;
    }
    public void setEndRepo(Integer endRepo) {
        this.endRepo = endRepo;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public String getDictId() {
        return dictId;
    }
    public void setDictId(String dictId) {
        this.dictId = dictId;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public Double getMarketPrice() {
        return marketPrice;
    }
    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }
    public Double getSellingPrice() {
        return sellingPrice;
    }
    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public Double getCostPrice() {
        return costPrice;
    }
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }
    public Double getFinalPrice() {
        return finalPrice;
    }
    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }
    public Integer getDiscount() {
        return discount;
    }
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
    public String getUvipLevel() {
        return uvipLevel;
    }
    public void setUvipLevel(String uvipLevel) {
        this.uvipLevel = uvipLevel;
    }
    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    public List<DictBO> getDictList() {
        return dictList;
    }
    public void setDictList(List<DictBO> dictList) {
        this.dictList = dictList;
    }
    public List<UvipPrice> getUvipPriceList() {
        return uvipPriceList;
    }
    public void setUvipPriceList(List<UvipPrice> uvipPriceList) {
        this.uvipPriceList = uvipPriceList;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

}
