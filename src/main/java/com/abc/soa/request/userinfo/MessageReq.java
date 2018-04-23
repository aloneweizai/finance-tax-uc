package com.abc.soa.request.userinfo;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-15
 * @Time:19:56
 */
public class MessageReq {
    //1.系统消息;2.帮邦消息
    private String type;
    private Integer page;
    private Integer size;

    private String busiType;

    //0:删除；1:未读；2:已读
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
