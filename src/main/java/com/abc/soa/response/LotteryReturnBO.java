package com.abc.soa.response;

import java.util.Date;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-18
 */


public class LotteryReturnBO {
    /**中奖位置*/
     private Integer index;
     private boolean isLuck;
     private String lotteryName;
     private String lotteryId;


    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public boolean isLuck() {
        return isLuck;
    }

    public void setLuck(boolean luck) {
        isLuck = luck;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public String getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(String lotteryId) {
        this.lotteryId = lotteryId;
    }
}
