package com.abc.bean.userinfo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-12
 * @Time:17:10
 */
public class VipPrivilege implements Serializable{
    private String id;
    private String name;//特权名称
    private String icon;//图标
    private String description;//描述
    private Boolean status;//数据状态

    private Date lastUpdate;
    private Date createTime;
    //特权代码
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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