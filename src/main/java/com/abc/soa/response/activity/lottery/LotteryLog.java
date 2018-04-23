package com.abc.soa.response.activity.lottery;

/**
 * @Author liuQi
 * @Date 2017/9/9 15:02
 */
public class LotteryLog {

    /** key */
    private String id;

    private String username;

    private String lotteryName;

    private String userPicturePath;

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

}
