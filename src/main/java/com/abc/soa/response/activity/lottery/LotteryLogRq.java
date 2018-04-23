package com.abc.soa.response.activity.lottery;

import com.abc.soa.request.BaseRq;

public class LotteryLogRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer isluck;
private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userName;
    private String activityName;
    private String startTime;
    private String endTime;
    public Integer getIsluck() {
        return isluck;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setIsluck(Integer isluck) {
        this.isluck = isluck;
    }
}
