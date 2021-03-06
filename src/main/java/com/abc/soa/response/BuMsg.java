package com.abc.soa.response;

import java.util.List;
import java.util.Map;

public class BuMsg {
    private String userId;//用户ID

    private List<String> userIds;//批量用户

    private String type;//消息类型

    private String busiType;//消息业务类型

    private String businessId;//来源ID

    private String webMsg;//站内消息

    private String phoneMsg;//短信消息

    private Map<String, String> dataList;//模板消息内容

    private String templateid;//模板ID

    private String skipUrl;//链接地址

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWebMsg() {
        return webMsg;
    }

    public void setWebMsg(String webMsg) {
        this.webMsg = webMsg;
    }

    public String getPhoneMsg() {
        return phoneMsg;
    }

    public void setPhoneMsg(String phoneMsg) {
        this.phoneMsg = phoneMsg;
    }

    public Map<String, String> getDataList() {
        return dataList;
    }

    public void setDataList(Map<String, String> dataList) {
        this.dataList = dataList;
    }

    public String getTemplateid() {
        return templateid;
    }

    public void setTemplateid(String templateid) {
        this.templateid = templateid;
    }
}
