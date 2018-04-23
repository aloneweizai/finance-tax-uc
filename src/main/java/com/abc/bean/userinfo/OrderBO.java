package com.abc.bean.userinfo;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.AddressBo;
import com.abc.soa.response.InvoiceBo;

import java.util.Date;
import java.util.List;

/**
 * Created by zlk on 2017-07-18.
 */
public class OrderBO extends BaseResponse{
    private String orderNo;
    private String userId;
    private String orderStatus;
    private String deliveryMethod;
    private String payMethod;
    private String nowVipLevel;
    private Date createTime;
    private Date lastUpdate;
    private String username;
    //是否需要寄送:1,是；2否
    private Integer isShipping;
    //是否免运费:1,是；2否
    private Integer isFreeShipping;
    private Double deliveryFee;
    private Double totalPrice;
    private String expressNo;
    private String remark;
    private Integer giftPoints;
    private String tradeMethod;
    private String cancelId;
    private String goodsId;
    private String expressCompId;

    private ExpressComp expressComp;
    private String status;
    private String goodsType;
    private String nowVipLevelName;
    /**
     * 是否已开发票，true：是，false：否
     **/
    private Boolean isInvoice;

    private User user;

    private List<OrderProductBO> orderProductBOList;
    private List<DictBO> dictBOList;
    private TradeBO tradeBO;
    private Date startTime;
    private Date endTime;

    private List<OrderLogBO> orderLogBOList;

    private InvoiceBo invoiceBO;

    private DeliveryMethodBO deliveryMethodBO;

    private String recommendUser;

    //下单人
    private String consignee;
    //下单人号码
    private String contactNumber;
    //下单人地址
    private String shippingAddress;

    //交易记录
    private List<TradeBO>tradeBOList;

    //订单优惠赠送
    private List<OrderGiftBO> orderGiftBOList;

    public List<OrderGiftBO> getOrderGiftBOList() {
        return orderGiftBOList;
    }

    public void setOrderGiftBOList(List<OrderGiftBO> orderGiftBOList) {
        this.orderGiftBOList = orderGiftBOList;
    }

    public List<TradeBO> getTradeBOList() {
        return tradeBOList;
    }

    public void setTradeBOList(List<TradeBO> tradeBOList) {
        this.tradeBOList = tradeBOList;
    }

    public String getNowVipLevelName() {
        return nowVipLevelName;
    }

    public void setNowVipLevelName(String nowVipLevelName) {
        this.nowVipLevelName = nowVipLevelName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public ExpressComp getExpressComp() {
        return expressComp;
    }

    public void setExpressComp(ExpressComp expressComp) {
        this.expressComp = expressComp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpressCompId() {
        return expressCompId;
    }

    public void setExpressCompId(String expressCompId) {
        this.expressCompId = expressCompId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getRecommendUser() {
        return recommendUser;
    }

    public void setRecommendUser(String recommendUser) {
        this.recommendUser = recommendUser;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getNowVipLevel() {
        return nowVipLevel;
    }

    public void setNowVipLevel(String nowVipLevel) {
        this.nowVipLevel = nowVipLevel;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIsShipping() {
        return isShipping;
    }

    public void setIsShipping(Integer isShipping) {
        this.isShipping = isShipping;
    }

    public Integer getIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(Integer isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }


    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGiftPoints() {
        return giftPoints;
    }

    public void setGiftPoints(Integer giftPoints) {
        this.giftPoints = giftPoints;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public String getCancelId() {
        return cancelId;
    }

    public void setCancelId(String cancelId) {
        this.cancelId = cancelId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Boolean getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(Boolean isInvoice) {
        this.isInvoice = isInvoice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderProductBO> getOrderProductBOList() {
        return orderProductBOList;
    }

    public void setOrderProductBOList(List<OrderProductBO> orderProductBOList) {
        this.orderProductBOList = orderProductBOList;
    }

    public List<DictBO> getDictBOList() {
        return dictBOList;
    }

    public void setDictBOList(List<DictBO> dictBOList) {
        this.dictBOList = dictBOList;
    }

    public TradeBO getTradeBO() {
        return tradeBO;
    }

    public void setTradeBO(TradeBO tradeBO) {
        this.tradeBO = tradeBO;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<OrderLogBO> getOrderLogBOList() {
        return orderLogBOList;
    }

    public void setOrderLogBOList(List<OrderLogBO> orderLogBOList) {
        this.orderLogBOList = orderLogBOList;
    }

    public InvoiceBo getInvoiceBO() {
        return invoiceBO;
    }

    public void setInvoiceBO(InvoiceBo invoiceBO) {
        this.invoiceBO = invoiceBO;
    }

    public DeliveryMethodBO getDeliveryMethodBO() {
        return deliveryMethodBO;
    }

    public void setDeliveryMethodBO(DeliveryMethodBO deliveryMethodBO) {
        this.deliveryMethodBO = deliveryMethodBO;
    }
}
