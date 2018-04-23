package com.abc.soa.request.userinfo;

import java.io.Serializable;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-14
 * @Time:20:34
 */
public class VipPrivilegeLevelReq implements Serializable{
    private String level;
    private Boolean status;
    private Integer page;
    private Integer size;

    private String levelname;

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
