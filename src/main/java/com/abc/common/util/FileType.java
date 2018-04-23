package com.abc.common.util;

/**
 * Created by xieyanmao on 2017/7/9.
 */
public enum FileType {

    IMAGE("image","图片","/images"),
    CSS("css","样式","/css"),
    JS("js","js脚本","/js"),
    FILE("file","附件","/file"),
    TEMPLATES("template","模板","/template");

    public String tag;

    public String description;

    public String dir;

    FileType(String tag, String description, String dir) {
        this.tag = tag;
        this.description = description;
        this.dir = dir;
    }

}
