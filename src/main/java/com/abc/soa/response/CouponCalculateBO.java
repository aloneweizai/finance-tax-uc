package com.abc.soa.response;

/**
 * Created by stuy on 2018/1/22.
 */
public class CouponCalculateBO {

    /**
     * 优惠劵ID
     */
    private String useCouponId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 商品品目
     */
    private String categoryId;

    /**
     * 使用优惠劵之前的金额
     */
    private Double amount;

    public String getUseCouponId() {
        return useCouponId;
    }

    public void setUseCouponId(String useCouponId) {
        this.useCouponId = useCouponId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
