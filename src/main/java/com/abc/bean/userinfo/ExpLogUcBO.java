package com.abc.bean.userinfo;

import java.util.Date;

/**
 * Created by zlk on 2017-08-02.
 */
public class ExpLogUcBO {
    private String id;
    private String channel;
    private Integer exp;
    private Integer sums;
    private Date createTime;
    private String remark;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public int getSums() {
        return sums;
    }

    public void setSums(Integer sums) {
        this.sums = sums;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
