package com.abc.bean.userinfo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by andy on 2018/3/13.
 */
public class FollowLecturerBO {

    /**主键**/
    private String id;

    /**用户ID**/
    @NotEmpty
    @Size(min = 6, max = 64)
    private String userId;

    /**被关注讲师ID**/
    @NotEmpty
    @Size(min = 6, max = 64)
    private String lecturerId;

    /**关注状态：0-取消关注，1-已关注**/
    private Boolean status;

    /**创建时间**/
    private java.util.Date createTime;

    /**更新时间**/
    private java.util.Date lastUpdate;

    private CurriculumLecturer curriculumLecturer;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return this.userId;
    }

    public void setLecturerId(String lecturerId){
        this.lecturerId = lecturerId;
    }

    public String getLecturerId(){
        return this.lecturerId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime(){
        return this.createTime;
    }

    public void setLastUpdate(java.util.Date lastUpdate){
        this.lastUpdate = lastUpdate;
    }

    public java.util.Date getLastUpdate(){
        return this.lastUpdate;
    }

    public CurriculumLecturer getCurriculumLecturer() {
        return curriculumLecturer;
    }

    public void setCurriculumLecturer(CurriculumLecturer curriculumLecturer) {
        this.curriculumLecturer = curriculumLecturer;
    }
}
