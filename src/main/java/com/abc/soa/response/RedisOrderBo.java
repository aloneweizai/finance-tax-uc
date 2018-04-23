package com.abc.soa.response;

import com.abc.soa.request.CurriculumGiftBo;
import com.abc.soa.request.NewOrderBo;
import com.abc.soa.request.userinfo.OrderBoReq;

import java.util.List;

/**
 * Created by stuy on 2018/1/23.
 */
public class RedisOrderBo {

    //订单对象
    private NewOrderBo newOrderBo;

    //积分支付对象
    private OrderBoReq orderBoReq;

    //订单流水号
    private String lsh;

    //优惠券金额
    private Double yhje;

    //是否提交订单
    private Boolean bool=false;

    //优惠券列表
    private CouponDataListBo couponDataListBo;

    private List<CurriculumGiftBo> curriculumGiftBoList;

    public List<CurriculumGiftBo> getCurriculumGiftBoList() {
        return curriculumGiftBoList;
    }

    public void setCurriculumGiftBoList(List<CurriculumGiftBo> curriculumGiftBoList) {
        this.curriculumGiftBoList = curriculumGiftBoList;
    }

    public Double getYhje() {
        return yhje;
    }

    public void setYhje(Double yhje) {
        this.yhje = yhje;
    }

    public CouponDataListBo getCouponDataListBo() {
        return couponDataListBo;
    }

    public void setCouponDataListBo(CouponDataListBo couponDataListBo) {
        this.couponDataListBo = couponDataListBo;
    }

    public NewOrderBo getNewOrderBo() {
        return newOrderBo;
    }

    public void setNewOrderBo(NewOrderBo newOrderBo) {
        this.newOrderBo = newOrderBo;
    }

    public OrderBoReq getOrderBoReq() {
        return orderBoReq;
    }

    public void setOrderBoReq(OrderBoReq orderBoReq) {
        this.orderBoReq = orderBoReq;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }
}
