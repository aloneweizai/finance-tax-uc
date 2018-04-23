package com.abc.bean.userinfo;

/**
 * Created by zlk on 2017-07-24.
 */
public class UserTaskBO {
    private Integer currentPoints;
    private Integer unReceivePoints;
    private Integer taskRange;
    private Integer finishedTaskCount;

    public Integer getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(Integer currentPoints) {
        this.currentPoints = currentPoints;
    }

    public Integer getUnReceivePoints() {
        return unReceivePoints;
    }

    public void setUnReceivePoints(Integer unReceivePoints) {
        this.unReceivePoints = unReceivePoints;
    }

    public Integer getTaskRange() {
        return taskRange;
    }

    public void setTaskRange(Integer taskRange) {
        this.taskRange = taskRange;
    }

    public Integer getFinishedTaskCount() {
        return finishedTaskCount;
    }

    public void setFinishedTaskCount(Integer finishedTaskCount) {
        this.finishedTaskCount = finishedTaskCount;
    }
}
