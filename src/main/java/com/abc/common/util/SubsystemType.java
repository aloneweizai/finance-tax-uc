package com.abc.common.util;

/**
 * Created by xieyanmao on 2017/7/9.
 */
public enum SubsystemType {

    CSBB("csbb","财税帮邦","/csbb"),
    CSUC("csuc","财税用户中心","/csuc"),
    CSW("csw","财税网","/csw"),
    CSZJKHD("cszjkhd","财税专家客户端","/cszjkhd");

    public String tag;

    public String describe;

    public String subDir;

    private SubsystemType(String tag, String describe, String subDir) {
        this.tag = tag;
        this.describe = describe;
        this.subDir = subDir;
    }

}
