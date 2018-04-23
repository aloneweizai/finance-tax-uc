package com.abc.bean.userinfo;

import java.util.Date;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-03
 * @Time:18:28
 */
public class DeliveryMethodBO {
    private String id;
    private String name;
    private String type;
    private String sort;
    private String status;
    private String description;

    private Date createTime;
    private Date lastUpdate;
    private Double firstWeight;
    private Double firstWeightFee;
    private Double addedWeight;
    private Double addedWeightFee;
    private String isInsured;
    private String rate;
    private Double minInsuredFee;
    private String areaFeeType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Double getFirstWeight() {
        return firstWeight;
    }

    public void setFirstWeight(Double firstWeight) {
        this.firstWeight = firstWeight;
    }

    public Double getFirstWeightFee() {
        return firstWeightFee;
    }

    public void setFirstWeightFee(Double firstWeightFee) {
        this.firstWeightFee = firstWeightFee;
    }

    public Double getAddedWeight() {
        return addedWeight;
    }

    public void setAddedWeight(Double addedWeight) {
        this.addedWeight = addedWeight;
    }

    public Double getAddedWeightFee() {
        return addedWeightFee;
    }

    public void setAddedWeightFee(Double addedWeightFee) {
        this.addedWeightFee = addedWeightFee;
    }

    public String getIsInsured() {
        return isInsured;
    }

    public void setIsInsured(String isInsured) {
        this.isInsured = isInsured;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Double getMinInsuredFee() {
        return minInsuredFee;
    }

    public void setMinInsuredFee(Double minInsuredFee) {
        this.minInsuredFee = minInsuredFee;
    }

    public String getAreaFeeType() {
        return areaFeeType;
    }

    public void setAreaFeeType(String areaFeeType) {
        this.areaFeeType = areaFeeType;
    }
}
