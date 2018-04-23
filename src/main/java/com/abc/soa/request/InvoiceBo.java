package com.abc.soa.request;

import com.abc.bean.userinfo.OrderBO;
import com.abc.soa.response.AddressBo;

import java.io.Serializable;
import java.util.List;


public class InvoiceBo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String userId;
    private String username;
    private String invoiceNo;
    private String invoiceCode;
    private String name;
    private String content;
    private String compName;
    private Double amount;
    private String type;
    private String property;
    private String status;
    private java.util.Date createTime;
    private java.util.Date lastUpdate;
    private String nsrsbh;
    private String nsrmc;
    private String address;
    private String phone;
    private String bank;
    private String addressId;
    /**用户订单号(运单号)**/
    private String userOrderNo;
    private String deliveryMethod;
    private Integer isShipping;
    private Integer isFreeShipping;
    private Double deliveryFee;
    private Integer isInsured;
    private Double InsuredFee;
    private String payMethod;

	public AddressBo getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(AddressBo userAddress) {
		this.userAddress = userAddress;
	}

	/** 收货人**/

    private String consignee;

    private AddressBo userAddress;

    private List<OrderBO> orderBOList;
    private String orderstrNos;

    private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrderstrNos() {
		return orderstrNos;
	}

	public void setOrderstrNos(String orderstrNos) {
		this.orderstrNos = orderstrNos;
	}

	private String[] orderNos;
    private java.util.Date startTime;
    private java.util.Date endTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(java.util.Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getUserOrderNo() {
		return userOrderNo;
	}
	public void setUserOrderNo(String userOrderNo) {
		this.userOrderNo = userOrderNo;
	}
	public String getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
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
	public Integer getIsInsured() {
		return isInsured;
	}
	public void setIsInsured(Integer isInsured) {
		this.isInsured = isInsured;
	}
	public Double getInsuredFee() {
		return InsuredFee;
	}
	public void setInsuredFee(Double insuredFee) {
		InsuredFee = insuredFee;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public List<OrderBO> getOrderBOList() {
		return orderBOList;
	}

	public String[] getOrderNos() {
		return orderNos;
	}

	public void setOrderNos(String[] orderNos) {
		this.orderNos = orderNos;
	}

	public void setOrderBOList(List<OrderBO> orderBOList) {
		this.orderBOList = orderBOList;
	}
	public java.util.Date getStartTime() {
		return startTime;
	}
	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}
	public java.util.Date getEndTime() {
		return endTime;
	}
	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}
    
    
}
