package com.abc.bean.userinfo;

/**
 * Created by andy on 2017/10/27.
 * 本月获取经验值、积分、完成任务数
 */
public class EarnedBO {
    private String earnedExp;
    private String earnedPoint;
    private String taskRange;
    private String completedTaskCount;

    public String getTaskRange() {
        return taskRange;
    }

    public void setTaskRange(String taskRange) {
        this.taskRange = taskRange;
    }

    public String getEarnedExp() {
        return earnedExp;
    }

    public void setEarnedExp(String earnedExp) {
        this.earnedExp = earnedExp;
    }

    public String getEarnedPoint() {
        return earnedPoint;
    }

    public void setEarnedPoint(String earnedPoint) {
        this.earnedPoint = earnedPoint;
    }

    public String getCompletedTaskCount() {
        return completedTaskCount;
    }

    public void setCompletedTaskCount(String completedTaskCount) {
        this.completedTaskCount = completedTaskCount;
    }
}
