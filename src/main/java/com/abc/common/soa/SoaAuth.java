package com.abc.common.soa;

import com.abc.application.SpringCtxHolder;
import com.abc.common.exception.SoaInterfaceException;
import com.abc.common.soa.response.AuthAccessTokenResponse;
import com.abc.common.util.BaseObject;
import com.abc.common.util.DateUtil;
import com.abc.common.util.MD5;
import com.abc.soa.ConstantsUri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by relic5 on 6/12/16.
 */
public class SoaAuth {

    private static Logger log = LoggerFactory.getLogger(SoaAuth.class);

    public static void saveAccessTokenInSession(HttpServletRequest request, ConstantsUri url) throws SoaInterfaceException{
        ServletContext application = request.getSession().getServletContext();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("name", SpringCtxHolder.getProperty("abc.soa-app-id"));
        String password=SpringCtxHolder.getProperty("abc.soa-secret");
        MD5 md5=new MD5(password);
        params.put("password",md5.compute());
        AuthAccessTokenResponse resp = null;

        SoaConnection<AuthAccessTokenResponse> res = new SoaConnection<AuthAccessTokenResponse>(request, url, params, AuthAccessTokenResponse.class, HttpMethod.POST);
        try {
            res.sendRequest();
            resp = res.parseObject();
        } catch (SoaInterfaceException e) {
            log.error(e.getMessage());
            //e.printStackTrace();
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        if(resp== null || !resp.isSuccess()){
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.MINUTE,-5);
            application.setAttribute("accessToken", "");
            application.setAttribute("expiresTime", sdf.format(cal.getTime()));
        }else{
            int longtime=0;
            Calendar cal = Calendar.getInstance();
            try{
                longtime=Integer.parseInt(resp.getExpires_in())/60;
            }catch (Exception e){
                log.error(e.getMessage());
                e.printStackTrace();
            }
            try{
                cal.setTime(sdf.parse(DateUtil.getTimeByMinute(longtime)));
            }catch (Exception e){
                log.error(e.getMessage());
                e.printStackTrace();
                cal.setTime(new Date());
            }finally {
                cal.add(Calendar.MINUTE, -5);
            }
            application.setAttribute("accessToken", resp.getToken());
            application.setAttribute("expiresTime", DateUtil.getTimeByMinute(longtime));
        }
    }

}
