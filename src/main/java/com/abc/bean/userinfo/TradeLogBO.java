package com.abc.bean.userinfo;


/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-25
 * @Time:11:00
 */
public class TradeLogBO {
        /****/
        private String id;

        /**
         * 订单号
         **/
        private String orderNo;

        /**
         * 交易金额
         **/
        private Double amount;

        /**
         * 交易状态：1.交易中 2.交易成功 3.交易失败 4.取消交易
         **/
        private String tradeStatus;

        private String tradeType;//交易类型 1：支付 2：退款

        /**
         * 交易时间
         **/
        private java.util.Date tradeTime;

        /**
         * 交易方式：WEIXIN、ALIPAY
         **/
        private String payMethod;

        /****/
        private java.util.Date createTime;

        /****/
        private java.util.Date lastUpdate;

        /**
         * 对账状态：1.已对账 2.手工作废 3.手工完成
         **/
        private String compareStatus;

        /**
         * 对账时间
         **/
        private java.util.Date compareTime;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderNo() {
            return this.orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public Double getAmount() {
            return this.amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getTradeStatus() {
            return this.tradeStatus;
        }

        public void setTradeStatus(String tradeStatus) {
            this.tradeStatus = tradeStatus;
        }

        public java.util.Date getTradeTime() {
            return this.tradeTime;
        }

        public void setTradeTime(java.util.Date tradeTime) {
            this.tradeTime = tradeTime;
        }

        public String getPayMethod() {
            return this.payMethod;
        }

        public void setPayMethod(String payMethod) {
            this.payMethod = payMethod;
        }

        public java.util.Date getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(java.util.Date createTime) {
            this.createTime = createTime;
        }

        public java.util.Date getLastUpdate() {
            return this.lastUpdate;
        }

        public void setLastUpdate(java.util.Date lastUpdate) {
            this.lastUpdate = lastUpdate;
        }

        public String getCompareStatus() {
            return this.compareStatus;
        }

        public void setCompareStatus(String compareStatus) {
            this.compareStatus = compareStatus;
        }

        public java.util.Date getCompareTime() {
            return this.compareTime;
        }

        public void setCompareTime(java.util.Date compareTime) {
            this.compareTime = compareTime;
        }

        public String getTradeType() {
            return tradeType;
        }

        public void setTradeType(String tradeType) {
            this.tradeType = tradeType;
        }

}
