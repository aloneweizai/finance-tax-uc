package com.abc.soa.request;

public class LotteryActivityprizeRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String activityId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}
