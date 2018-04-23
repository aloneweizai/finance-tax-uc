package com.abc.bean.userinfo;

import java.util.Date;

/**
 * Created by zlk on 2017-07-20.
 */
public class PointLog {
    private String id;
    private String userId;
    private String ruleId;
    private String name;
    private String code;
    private String type;
    private Integer income;
    private Integer outgo;
    private String usablePoints;
    private Date createTime;

    private String logType;
    private String remark;

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getOutgo() {
        return outgo;
    }

    public void setOutgo(Integer outgo) {
        this.outgo = outgo;
    }

    public String getUsablePoints() {
        return usablePoints;
    }

    public void setUsablePoints(String usablePoints) {
        this.usablePoints = usablePoints;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
