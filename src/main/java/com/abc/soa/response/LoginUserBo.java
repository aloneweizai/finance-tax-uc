package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/7/19.
 */
public class LoginUserBo extends BaseResponse {

    private static final long serialVersionUID = 8844409891125883570L;


    public LoginBo getData() {
        return data;
    }

    public void setData(LoginBo data) {
        this.data = data;
    }


    private LoginBo data;
}
