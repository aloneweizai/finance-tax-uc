package com.abc.bean.userinfo;

import java.util.Date;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-14
 * @Time:20:28
 */
public class VipPrivilegeLevel {
    /**id*/
    private String id;
    /**'等级id'*/
    private String levelId;
    /**'权益id'*/
    private String privilegeId;
    /**'val1'*/
    private String val1;
    /**'val2'*/
    private String val2;
    /**'val3'*/
    private String val3;
    /**'val4'*/
    private String val4;
    /**''描述''*/
    private String description;

    /**''数据状态''*/
    private boolean status;
    /**修改时间*/
    private Date lastUpdate;
    /**创建时间*/
    private Date createTime;

    //添加字段
    private String levelName;
    private String privilegeName;

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getVal1() {
        return val1;
    }

    public void setVal1(String val1) {
        this.val1 = val1;
    }

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }

    public String getVal3() {
        return val3;
    }

    public void setVal3(String val3) {
        this.val3 = val3;
    }

    public String getVal4() {
        return val4;
    }

    public void setVal4(String val4) {
        this.val4 = val4;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
