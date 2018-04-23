package com.abc.soa.request.userinfo;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-22
 * @Time:18:10
 */
public class CheckInReq {
    private String userId;
    private String date;

    private String yearMonth;

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
