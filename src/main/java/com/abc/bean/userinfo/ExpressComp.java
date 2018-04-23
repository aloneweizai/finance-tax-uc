package com.abc.bean.userinfo;

import java.util.Date;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-09-13
 * @Time:11:28
 */
public class ExpressComp {
    private String id;
    private String compCode;
    private String compName;
    private String compUrl;
    private int sort;
    private Date lastUpdate;
    private Date createTime;

    private String templateUrl;

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompUrl() {
        return compUrl;
    }

    public void setCompUrl(String compUrl) {
        this.compUrl = compUrl;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
