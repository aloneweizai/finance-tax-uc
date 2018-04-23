package com.abc.soa.request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuy on 2017/8/11.
 */
public class UncheckUrls {
    private List<String> urls;

    public List<String> getUrls(String path) {
        addUrl(path);
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }


    private void addUrl(String path){
        urls=new ArrayList<String>();
        if("/".equals(path)){
            path="";
        }
        urls.add(path+"/login");
        urls.add(path+"/login.html");
        urls.add(path+"/zhuce.html");
        urls.add(path+"/sms.html");
        urls.add(path+"/validatecode.html");
        urls.add(path+"/forgot.html");
        urls.add(path+"/logout.html");
        urls.add(path+"/phone_forgot.html");
        urls.add(path+"/external_index.html");
        urls.add(path+"/tp_forgot.html");
        urls.add(path+"/forgotpassword.html");
        urls.add(path+"/callback.html");
        urls.add(path+"/orderpay/success.html");
        urls.add(path+"/smsforgot.html");
        urls.add(path+"/saveforgot.html");
        urls.add(path+"/phone_forgot_login.html");
        urls.add(path+"/forgot_phone_new_fangxiang.html");
        urls.add(path+"/rsa.html");
        urls.add(path+"/clientjump.html");
        urls.add(path+"/logout_session.html");
        urls.add(path+"/overtime.html");
        urls.add(path+"/client_currency.html");
        urls.add(path+"/logout_client.html");
        urls.add(path+"/error");
        urls.add(path+"/client_token.html");
        urls.add(path+"/500.html");
        urls.add(path+"/404.html");
        urls.add(path+"/weixin/callback.html");
        urls.add(path+"/ip.html");
    }
}
