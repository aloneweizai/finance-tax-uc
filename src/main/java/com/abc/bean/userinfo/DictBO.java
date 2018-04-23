package com.abc.bean.userinfo;

import java.util.Date;

/**
 * @Author:zlk
 * @Description:字典
 * @Date:2017-08-07
 * @Time:18:15
 */
public class DictBO {
    private String id;//PK
    private String dictId; //字典ID
    private String dictName; //字典名称
    private String fieldKey; //键名
    private String fieldValue; //键值
    private Boolean status; //状态
    private String createUser; //创建用户
    private Date createTime; //创建时间
    private String lastUser; //修改用户
    private Date lastUpdate; //上次修改时间

    public DictBO() {
    }

    public DictBO(String id, String dictId, String dictName, String fieldKey, String fieldValue, Boolean status, Date createTime, String createUser, Date lastUpdate, String lastUser) {
        this.id = id;
        this.dictId = dictId;
        this.dictName = dictName;
        this.fieldKey = fieldKey;
        this.fieldValue = fieldValue;
        this.status = status;
        this.createTime = createTime;
        this.createUser = createUser;
        this.lastUpdate = lastUpdate;
        this.lastUser = lastUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUser() {
        return lastUser;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    @Override
    public String toString() {
        return "Dict{" +
                "id='" + id + '\'' +
                ", dictId='" + dictId + '\'' +
                ", dictName='" + dictName + '\'' +
                ", fieldKey='" + fieldKey + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUser='" + lastUser + '\'' +
                '}';
    }
}
