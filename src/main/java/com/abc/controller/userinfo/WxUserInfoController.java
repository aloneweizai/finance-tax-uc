package com.abc.controller.userinfo;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.MatrixToImageWriter;
import com.abc.controller.BaseController;
import com.abc.service.RedisCode;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.*;
import com.abc.soa.request.userinfo.OrderResq;
import com.abc.soa.response.PayRes;
import com.abc.soa.response.UserBo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by stuy on 2017/9/21.
 */
@Controller
@RequestMapping(value = "/weixin")
public class WxUserInfoController extends BaseController {




    /**
     * 生成微信绑定二维码
     * @param session
     * @param request
     * @param response
     */
    @RequestMapping(value = "/weixin.html", method = RequestMethod.GET)
    public ModelAndView weixin_ewm(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            String appid=SpringCtxHolder.getProperty("wx.appid");
            String redirect_uri=SpringCtxHolder.getProperty("wx.redirect_uri");
            BASE64Encoder base64Encoder=new BASE64Encoder();
            UserBo userBo = getInfoService.getUserBo(request);
            StringBuffer sb=new StringBuffer();
            sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?");
            sb.append("appid=").append(appid);
            String idbase64=base64Encoder.encodeBuffer(userBo.getId().getBytes());
            sb.append("&redirect_uri=").append(redirect_uri).append(idbase64.trim());
            //String url2=request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+request.getContextPath();
            String url2= SpringCtxHolder.getProperty("intranet.ip");
            String url=URLEncoder.encode(url2+"/weixin/callback.html","UTF-8");
            sb.append("&response_type=code&scope=snsapi_base&state=").append(url).append("#wechat_redirect");
            int width = 150; // 图像宽度
            int height = 150; // 图像高度
            String imagebase64 = MatrixToImageWriter.qrcodeABC(sb.toString(),width,height);
            mav.addObject("data",imagebase64);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }




    /**
     * 微信绑定回调
     * @param session
     * @param request
     * @param response
     */
    @RequestMapping(value = "/callback.html", method = RequestMethod.POST)
    public ModelAndView callback(@RequestBody WxCallbackBo wxCallbackBo, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            getInfoService.setObject(30000L,wxCallbackBo.getUserid()+"_"+RedisCode.ORDER,wxCallbackBo);
            //redisService.set(wxCallbackBo.getUserid(),wxCallbackBo,30000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }





    /**
     * 微信绑定回调
     * @param session
     * @param request
     * @param response
     */
    @RequestMapping(value = "/wx.html", method = RequestMethod.GET)
    public ModelAndView wx( HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userBo = getInfoService.getUserBo(request);

            Object object = getInfoService.getObject(userBo.getId()+"_"+RedisCode.ORDER,WxCallbackBo.class);
            if(object==null){
                mav.addObject("data","-1");
            }else{
                WxCallbackBo wxCallbackBo=(WxCallbackBo)object;
                if("1".equals(wxCallbackBo.getCode())){
                    mav.addObject("data","1");
                    getInfoService.remove(userBo.getId()+"_"+RedisCode.ORDER);
                    String userToken=getInfoService.getString(request,RedisCode.USER_TOKEN);
                    UserIdBo userID = SoaConnectionFactory.get(request, ConstantsUri.USER_INFO_TOKEN, null, UserIdBo.class, userToken);
                    UserBo user = userID.getData();
                    if(isNull(user.getPhone())){
                        String phones=user.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
                        user.setXinphone(phones);
                    }
                    getInfoService.setUserBo(request,user);
                }else{
                    mav.addObject("data","-1");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }



}
