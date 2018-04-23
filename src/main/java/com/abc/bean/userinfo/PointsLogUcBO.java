package com.abc.bean.userinfo;

import java.util.Date;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-03
 * @Time:11:55
 */
public class PointsLogUcBO {
    private String id;
    private String channel;
    private Integer points;
    private Integer sums;
    private Date createTime;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getSums() {
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
