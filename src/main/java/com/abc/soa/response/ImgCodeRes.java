package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

import java.util.Base64;

/**
 * Created by stuy on 2017/8/9.
 */
public class ImgCodeRes extends BaseResponse {
    private String base64;

    private String code;

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }
}
