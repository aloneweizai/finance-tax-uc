package com.abc.controller;

import com.abc.application.SpringCtxHolder;
import com.abc.bean.userinfo.*;
import com.abc.bean.userinfo.BusinessMessage;
import com.abc.bean.userinfo.OrderBO;
import com.abc.cache.RedisCacheService;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.*;
import com.abc.service.GetInfoService;
import com.abc.service.RedisCode;
import com.abc.service.ResponseCookie;
import com.abc.service.RsaV2Service;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.*;
import com.abc.soa.request.LoginBo;
import com.abc.soa.request.SmsBo;
import com.abc.soa.request.userinfo.MessageReq;
import com.abc.soa.request.userinfo.OrderListReq;
import com.abc.soa.response.*;
import com.abc.soa.response.userinfo.*;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

/**
 * Created by stuy on 2017/7/17.
 */
@Controller
public class LoginController extends BaseController {


    @Autowired
    private RestTemplate restTemplate;

    //活动类型

    private String topone_category = "fl1";

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录页面跳转
     * @param request
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request) {
        String recallurl = request.getParameter("recallurl");
        ModelAndView mav= new ModelAndView("login");
        if(StringUtils.isNotEmpty(recallurl)){
            mav.addObject("recallurl",recallurl);
        }
        mav.addObject("yhxyUrl",SpringCtxHolder.getProperty("YHXY_URL"));
        return mav;
    }

    @GetMapping("/")
    public ModelAndView moren_login(HttpServletRequest request) {
        ModelAndView mav= new ModelAndView("redirect:login");
        return mav;
    }

    @GetMapping("/404.html")
    public ModelAndView error404(HttpServletRequest request) {
        ModelAndView mav= new ModelAndView("404");
        String ucdomain=SpringCtxHolder.getProperty("ucdomain");
        String snsdomain=SpringCtxHolder.getProperty("snsdomain");
        String cswdomain=SpringCtxHolder.getProperty("cswdomain");
        mav.addObject("ucurl",ucdomain);
        mav.addObject("snsurl",snsdomain);
        mav.addObject("cswurl",cswdomain);
        return mav;
    }


    @GetMapping("/500.html")
    public ModelAndView error500(HttpServletRequest request) {
        ModelAndView mav= new ModelAndView("500");
        String ucdomain=SpringCtxHolder.getProperty("ucdomain");
        String snsdomain=SpringCtxHolder.getProperty("snsdomain");
        String cswdomain=SpringCtxHolder.getProperty("cswdomain");
        mav.addObject("ucurl",ucdomain);
        mav.addObject("snsurl",snsdomain);
        mav.addObject("cswurl",cswdomain);
        return mav;
    }

    /**
     * 找回密码
     * @return
     */
    @GetMapping("/forgotpassword.html")
    public ModelAndView forgotpassword() {
        ModelAndView mav=new ModelAndView("forgotpassword");
        return mav;
    }


    /**
     * 个人中心iframe默认页面
     * @param session
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/user_index.html")
    public ModelAndView user_index( HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        try {
            long time=System.currentTimeMillis();
            logger.info("启动时间："+time);
            ModelAndView mav= new ModelAndView("user_index");
            Object obj= getInfoService.getUserBo(request);
            if(obj!=null){
                UserBo loginUserBo = (UserBo) obj;

                //积分、经验值、任务完成数
                EarnedBoResp taskBo = SoaConnectionFactory.getRestful(request, ConstantsUri.TASK_INDEX_FINISH, null, EarnedBoResp.class, loginUserBo.getId());
                mav.addObject("taskBo", taskBo.getData());

                //活动中心
                EventResp eventResp = SoaConnectionFactory.getRestful(request, ConstantsUri.UC_ACTIVITY, null, EventResp.class,topone_category);
                mav.addObject("event", eventResp.getData().getEvent());

            }else{
                mav.addObject("user",new UserBo());
            }
            long time1=System.currentTimeMillis();
            logger.info("启动时间："+(time1-time));
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }


    /**
     * 获取未读消息
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/message_wd.html")
    public ModelAndView message_xt(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("index/message");
        try {
            MessageReq messageReq = new MessageReq();
            //messageReq.setType("1");
            messageReq.setPage(1);
            messageReq.setSize(3);
            messageReq.setStatus("1");//未读消息
            messageReq.setBusiType(null);
            BusinessMessageResp messageResp = SoaConnectionFactory.get(request, ConstantsUri.UC_MESSAGES, messageReq,BusinessMessageResp.class);
            List<BusinessMessage> sysTaskList = messageResp.getDataList();
            mav.addObject("businessMessages", sysTaskList);
            DictRes msgTypes = RedisCacheService.getRedisDictRes(request, getInfoService, "msgType");
            mav.addObject("msgTypes", msgTypes.getDataList());
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
    }

    /**
     * 获取订单列表
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/order_index.html")
    public ModelAndView order_index(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("index/order_index");
        try {
            UserBo loginUserBo = getInfoService.getUserBo(request);
            String key="orderStatus";
            DictRes dictRes = RedisCacheService.getRedisDictRes(request,getInfoService,key);

            //查询并添加订单状态数据字典
            mav.addObject("orderStatus", dictRes.getDataList());
            OrderListReq orderListReq =  new OrderListReq();
            orderListReq.setUserId(loginUserBo.getId());
            orderListReq.setName(loginUserBo.getUsername());
            orderListReq.setPage(1);
            orderListReq.setSize(3);//限制显示为5条
            OrderListResp orders = SoaConnectionFactory.get(request, ConstantsUri.ORDER_ALL, orderListReq, OrderListResp.class);
            List<OrderBO> orderList = JSON.parseArray(orders.getDataList(), OrderBO.class);
            mav.addObject("orders", orderList);
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
    }


    /**
     * 获取发票列表
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/invoice_index.html")
    public ModelAndView invoice_index(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("index/invoice_index");
        try {
            UserBo loginUserBo = getInfoService.getUserBo(request);
            String key = "fqsqstatus";
            DictRes dictRes = RedisCacheService.getRedisDictRes(request, getInfoService, key);
            mav.addObject("fqsqstatus",dictRes.getDataList());
            HistoryReq historyBo=new HistoryReq();
            historyBo.setStatus("5");
            historyBo.setUserId(loginUserBo.getId());
            InvoiceRes invoiceRes= SoaConnectionFactory.get(request, ConstantsUri.USER_HISTORY_INVOICE,historyBo, InvoiceRes.class);
            mav.addObject("invoicelist",invoiceRes.getDataList());
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
    }


    /**
     * 新消息角标
     * @param request
     * @return
     */
    @GetMapping("/message_count_index.html")
    public ModelAndView messageCount(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("index/message_count_index");
        try {
            //消息接口--->新消息个数
            MessageReq messageReq = new MessageReq();
            //messageReq.setType("1");
            messageReq.setPage(0);
            messageReq.setSize(0);
            messageReq.setBusiType(null);
            messageReq.setStatus("1");
            BusinessMessageResp messageResp = SoaConnectionFactory.get(request, ConstantsUri.UC_MESSAGES, messageReq,BusinessMessageResp.class);
            mav.addObject("sysTotal", messageResp.getTotal());
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
    }



    /**
     * 登录
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/login.html")
    public ModelAndView login(
            @RequestBody LoginBo loginbo,
                              HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if("phone".equals(loginbo.getType())){
                if(loginbo.getPhone()!=null&&!"".equals(loginbo.getPhone())&&loginbo.getCode()!=null&&!"".equals(loginbo.getCode())){
                    SmsBo smsBo=new SmsBo();
                    smsBo.setCode(loginbo.getCode());
                    smsBo.setPhone(loginbo.getPhone());
                    smsBo.setType(SmsType.SJDL);
                    LoginUserBo userBo=SoaConnectionFactory.post(request, ConstantsUri.PHONE_LOGIN,smsBo, LoginUserBo.class);
                    if("2000".equals(userBo.getCode())){
                        UserBo user = userBo.getData().getUser();
                        logger.info("用户：【" + loginbo.getUsername() + "】 登陆成功");
                        setCookie(response,userBo,request);
                        mav.addObject("data", userBo);
                    }else{
                        mav.addObject("data", userBo);
                    }
                }
            }else if("username".equals(loginbo.getType())){
                if(loginbo.getUsername()!=null&&!"".equals(loginbo.getUsername())&&loginbo.getPassword()!=null&&!"".equals(loginbo.getPassword())){
                    RSAPublicKey publickey =null;
                    UserSlatBo salt=SoaConnectionFactory.getRestful(request,ConstantsUri.UC_USER_LOGIN,null,UserSlatBo.class,loginbo.getUsername());
                    if(salt.getData()!=null&&isNull(salt.getData().getSalt())){
                        publickey=RedisCacheService.getRedisPublicKey(request,getInfoService);
                        if(publickey==null){
                            BaseResponse brs=new BaseResponse("1000","获取公钥失败!");
                            mav.addObject("data",brs);
                            return mav;
                        }
                    }else{
                        BaseResponse brs=new BaseResponse("1000","用户名或密码错误!");
                        mav.addObject("data",brs);
                        return mav;
                    }
                    LoginReq loginReq=new LoginReq();
                    loginReq.setUsernameOrPhone(loginbo.getUsername());

                    /**
                     * MD5加盐一起加密
                     */
                    MD5 md5 = new MD5(loginbo.getPassword() + salt.getData().getSalt());
                    BASE64Encoder base64Encoder=new BASE64Encoder();
                    String md5str = md5.compute();
                    logger.debug(md5str);

                    /**
                     * MD5加密密文转成RSA加密
                     */
                    String rsastr = RSA.encryptString(publickey, md5str);
                    //byte[] rsastr = RsaV2Service.rsaV2Encryption(md5str, publickey);

                    /**
                     * RSA密文转成base64
                     */
                    String base64str = base64Encoder.encodeBuffer(rsastr.getBytes());
                    logger.debug(base64str);
                    loginReq.setPassword(base64str);
                    LoginUserBo userBo=SoaConnectionFactory.post(request, ConstantsUri.USER_LOGIN_V2,loginReq, LoginUserBo.class);
                    if("2000".equals(userBo.getCode())){
                        logger.info("用户：【" + loginbo.getUsername() + "】 登陆成功");
                        setCookie(response,userBo,request);
                        mav.addObject("data", userBo);
                    }else{
                        mav.addObject("data", userBo);
                    }
                }
            }
            if(StringUtils.isNotEmpty(loginbo.getRecallurl())) {
                BASE64Decoder base64Decoder=new BASE64Decoder();
                try {
                    String recallurl = new String(base64Decoder.decodeBuffer(loginbo.getRecallurl()), "UTF-8");
                    mav.addObject("redirect",recallurl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }







    /**
     * 登录
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/login_v2.html")
    public ModelAndView login_v2(
            @RequestBody LoginBo loginbo,
            HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if("phone".equals(loginbo.getType())){
                if(loginbo.getPhone()!=null&&!"".equals(loginbo.getPhone())&&loginbo.getCode()!=null&&!"".equals(loginbo.getCode())){
                    SmsBo smsBo=new SmsBo();
                    smsBo.setCode(loginbo.getCode());
                    smsBo.setPhone(loginbo.getPhone());
                    smsBo.setType(SmsType.SJDL);
                    LoginUserBo userBo=SoaConnectionFactory.post(request, ConstantsUri.PHONE_LOGIN,smsBo, LoginUserBo.class);
                    if("2000".equals(userBo.getCode())){
                        logger.info("用户：【" + loginbo.getUsername() + "】 登陆成功");
                        setCookie(response,userBo,request);
                        mav.addObject("data", userBo);
                    }else{
                        mav.addObject("data", userBo);
                    }
                }
            }else if("username".equals(loginbo.getType())){
                if(loginbo.getUsername()!=null&&!"".equals(loginbo.getUsername())&&loginbo.getPassword()!=null&&!"".equals(loginbo.getPassword())){

                    RSAPrivateKey p = RedisCacheService.getRedisPrivateKey(request, getInfoService);

                    RSAPublicKey publickey =RedisCacheService.getRedisV2PublicKey(request,getInfoService);
                    if(publickey==null){
                        BaseResponse brs=new BaseResponse("1000","获取公钥失败!");
                        mav.addObject("data",brs);
                        return mav;
                    }
                    LoginReq loginReq=new LoginReq();
                    loginReq.setUsernameOrPhone(loginbo.getUsername());


                    BASE64Encoder base64Encoder=new BASE64Encoder();
                    String md5str = loginbo.getPassword();
                    /**
                     * MD5加密密文转成RSA加密
                     */
                    byte[] rsastr = RsaV2Service.rsaV2Encryption(md5str, publickey);

                    /**
                     * RSA密文转成base64
                     */
                    String base64str = base64Encoder.encodeBuffer(rsastr);
                    logger.debug(base64str);
                    loginReq.setPassword(base64str);
                    LoginUserBo userBo=SoaConnectionFactory.post(request, ConstantsUri.USER_LOGIN_V2,loginReq, LoginUserBo.class);
                    if("2000".equals(userBo.getCode())){
                        logger.info("用户：【" + loginbo.getUsername() + "】 登陆成功");
                        setCookie(response,userBo,request);
                        mav.addObject("data", userBo);
                    }else{
                        mav.addObject("data", userBo);
                    }
                }
            }
            if(StringUtils.isNotEmpty(loginbo.getRecallurl())) {
                BASE64Decoder base64Decoder=new BASE64Decoder();
                try {
                    String recallurl = new String(base64Decoder.decodeBuffer(loginbo.getRecallurl()), "UTF-8");
                    mav.addObject("redirect",recallurl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }

    /**
     * 网页版首页
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/index.html")
    public ModelAndView index(@RequestParam(value = "url", required = false) String url, HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            long time=System.currentTimeMillis();
            logger.info("启动时间:"+time);
            ModelAndView mav= new ModelAndView("index");
            Object obj= getInfoService.getUserBo(request);
            if(obj!=null){
                UserBo loginUserBo = (UserBo) obj;
               // RedisCacheService.getRedisTodo(request,getInfoService,loginUserBo.getId());
                mav.addObject("user",loginUserBo);

                UserExpResp userExpResp = SoaConnectionFactory.getRestful(request, ConstantsUri.UEXP_ID, null, UserExpResp.class,loginUserBo.getId());
                model.addAttribute("userExp",userExpResp.getData());


                if(isNull(url)){
                    BASE64Decoder base64Decoder=new BASE64Decoder();
                    try {
                        url=new String (base64Decoder.decodeBuffer(url),"UTF-8");
                        mav.addObject("url",url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    mav.addObject("url","user_index.html");
                }
            }else{
                mav.addObject("user",new UserBo());
            }
            logger.info("结束时间:"+(System.currentTimeMillis()-time));
        return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 获取用户基本信息
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/index_userinfo.html")
    public ModelAndView index_userinfo(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        String requestHeader = request.getHeader("user-agent");
        ModelAndView mav = new ModelAndView("index/index_user");
        if(isMobileDevice(requestHeader)){
            mav = new ModelAndView("index/index_user_m");
        }

        try {
            Object obj= getInfoService.getUserBo(request);
            UserBo loginUserBo = (UserBo) obj;
            mav.addObject("user",loginUserBo);
            UserTzxxRes userTzxxRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_TZXX,null, UserTzxxRes.class,loginUserBo.getId());
            mav.addObject("usertzxx",userTzxxRes.getData());
            LevelReq levelReq=new LevelReq();
            if(loginUserBo.getVipLevel()!=null&&!"".equals(loginUserBo.getVipLevel())){
                levelReq.setName(loginUserBo.getVipLevel());
            }else{
                levelReq.setName("VIP0");
            }
            levelReq.setStatus(true);
            //model.addAttribute("path", SpringCtxHolder.getProperty("imagedomain"));
            int count=0;
            if(isNull(loginUserBo.getPhone())){
                count+=25;
            }
            if(userTzxxRes.getData()!=null){
                if(isNull(userTzxxRes.getData().getWeixin())){
                    count+=25;
                }
                if("2".equals(userTzxxRes.getData().getValidStatus())){
                    count+=25;
                }
            }
            mav.addObject("aqcount",count);
            VipLeveRes baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_VIP,null,VipLeveRes.class,levelReq.getName());
            mav.addObject("vip",baseResponse.getData());

            //会员有效期
            mav.addObject("vipExpireDate", calcDate(loginUserBo.getVipExpireDate()));

            //我的积分、经验值
            UserPointsResp userPointsResp = SoaConnectionFactory.getRestful(request, ConstantsUri.POINTS, null, UserPointsResp.class,loginUserBo.getId());
            model.addAttribute("points",userPointsResp.getData());
            UserExpResp userExpResp = SoaConnectionFactory.getRestful(request, ConstantsUri.UEXP_ID, null, UserExpResp.class,loginUserBo.getId());
            model.addAttribute("userExp",userExpResp.getData());

            //累计签到
            CheckTotalResp totalResp = SoaConnectionFactory.get(request, ConstantsUri.UC_CHECK_TOTAL, null, CheckTotalResp.class);
            model.addAttribute("checks",totalResp.getData());
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
    }


    /**
     * 财税客户端首页地址
     * @param url
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/external_index.html")
    public ModelAndView external_index( @RequestParam(value = "url", required = false) String url,
                                        HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            ModelAndView mav= new ModelAndView("external_index");
            Object obj=getInfoService.getUserBo(request);
            if(obj!=null){
                UserBo loginUserBo = (UserBo) obj;
                //RedisCacheService.getRedisTodo(request,getInfoService,loginUserBo.getId());
                mav.addObject("user",loginUserBo);
                //我的经验值
                UserExpResp userExpResp = SoaConnectionFactory.getRestful(request, ConstantsUri.UEXP_ID, null, UserExpResp.class,loginUserBo.getId());
                model.addAttribute("userExp",userExpResp.getData());
            }else{
                mav.addObject("user",new UserBo());
            }
            if(isNull(url)){
                BASE64Decoder base64Decoder=new BASE64Decoder();
                try {
                    url=new String (base64Decoder.decodeBuffer(url),"UTF-8");
                    mav.addObject("url",url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                mav.addObject("url","user_index.html");
            }
            return mav;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }
    /**
     * 设置cookie
     * @param response
     * @param userBo 用户对象
     */
    private void setCookie( HttpServletResponse response,LoginUserBo userBo,HttpServletRequest request){
        String str=System.currentTimeMillis()+getFixLenthString(6);
        Map<String,String>  map=new HashMap<String,String>();
        //StringBuffer sb=new StringBuffer();
        map.put(RedisCode.USER_TOKEN,userBo.getData().getToken());
        map.put(RedisCode.ONLY,str);
        MD5 md5=new MD5(userBo.getData().getUser().getId());
        map.put(RedisCode.USER_ID,md5.compute());
        String json=JSON.toJSONString(map);
        String key="";
        try {
            key=URLEncoder.encode(SymmetricEncoder.setMiwen(json),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        getInfoService.removeKeys(request,userBo.getData().getUser().getId());
        getInfoService.setString(request,map,RedisCode.USER_TOKEN,userBo.getData().getToken());
        getInfoService.setString(request,map,RedisCode.ONLY,str);
        getInfoService.setString(request,map,RedisCode.USER_ID,md5.compute());
        getInfoService.setObject(request,map,RedisCode.CURRENT_USER,userBo.getData().getUser());

        //带域名和路径的cookie
        ResponseCookie.setCookie(response,"userPicturePath",userBo.getData().getUser().getUserPicturePath(),"abc12366.com","/");
        String nickname="";
        try {
            nickname=URLEncoder.encode(userBo.getData().getUser().getNickname(),"UTF-8");
            ResponseCookie.setCookie(response,"nickname",nickname,"abc12366.com","/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseCookie.setCookie(response,"key",key,"abc12366.com","/");
        ResponseCookie.setCookie(response,"vipLevel",userBo.getData().getUser().getVipLevel(),"abc12366.com","/");

        String vipLevelName="";
        try {
            vipLevelName=URLEncoder.encode(userBo.getData().getUser().getVipLevelName(),"UTF-8");
            ResponseCookie.setCookie(response,"vipLevelName",vipLevelName,"abc12366.com","/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String level="";
        if(userBo.getData().getUser().getLevel()!=null){
            level=userBo.getData().getUser().getLevel();
        }
        ResponseCookie.setCookie(response,"level",level,"abc12366.com","/");

        String levelName="";
        try {
            if(userBo.getData().getUser().getLevelName()!=null){
                levelName=URLEncoder.encode(userBo.getData().getUser().getLevelName(),"UTF-8");
            }
            ResponseCookie.setCookie(response,"levelName",levelName,"abc12366.com","/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //不要路径和域名的cookie
//        ResponseCookie.setCookie(response,"userPicturePath",userBo.getData().getUser().getUserPicturePath());
//        ResponseCookie.setCookie(response,"nickname",nickname);
//        ResponseCookie.setCookie(response,"key",key);
//        ResponseCookie.setCookie(response,"vipLevel",userBo.getData().getUser().getVipLevel());
//        ResponseCookie.setCookie(response,"vipLevelName",vipLevelName);
//        ResponseCookie.setCookie(response,"level",userBo.getData().getUser().getLevel());
//        ResponseCookie.setCookie(response,"levelName",levelName);

        //要路径不要域名的cookie
        ResponseCookie.setCookie(response,"userPicturePath",userBo.getData().getUser().getUserPicturePath(),"/");
        ResponseCookie.setCookie(response,"nickname",nickname,"/");
        ResponseCookie.setCookie(response,"key",key,"/");
        ResponseCookie.setCookie(response,"vipLevel",userBo.getData().getUser().getVipLevel(),"/");
        ResponseCookie.setCookie(response,"vipLevelName",vipLevelName,"/");
        ResponseCookie.setCookie(response,"level",userBo.getData().getUser().getLevel(),"/");
        ResponseCookie.setCookie(response,"levelName",levelName,"/");

    }

    /**
     * 产生固定长度随机数
     * @param strLength
     * @return
     */
    private static String getFixLenthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }


    /**
     * 注册
     * @param username
     * @param code
     * @param password
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/zhuce.html")
    public ModelAndView zhuce(@RequestParam(value = "username") String username,
                              @RequestParam(value = "code") String code,
                              @RequestParam(value = "password") String password,
                              @RequestParam(value = "tpcode") String tpcode,
                              HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(isNull(username)&&isNull(code)&&isNull(password)&&isNull(tpcode)){
                RSAPublicKey publickey = RedisCacheService.getRedisPublicKey(request, getInfoService);
                if(publickey==null){
                    BaseResponse brs=new BaseResponse("1000","获取公钥失败!");
                    mav.addObject("data",brs);
                    return mav;
                }
                BaseResponse brcode = isCodeTime(tpcode, session);
                if("2000".equals(brcode.getCode())){
                    String codes=RedisCode.CODE+":"+session.getId();
                    getInfoService.remove(codes);
                    RegisterReq registerReq=new RegisterReq();
                    registerReq.setPhone(username);
                    String rsapassword=RSA.encryptString(publickey,password);
                    BASE64Encoder base64Encoder=new BASE64Encoder();
                    registerReq.setPassword(base64Encoder.encodeBuffer(rsapassword.getBytes()));
                    registerReq.setVerifyingCode(code);
                    registerReq.setStatus(true);
                    registerReq.setVerifyingType(SmsType.YHZC);

                    String ip=getIpAddr(request);
                    logger.debug(ip);
                    //ip="113.247.252.116";
                    HttpHeaders httpHeaders=new HttpHeaders();
                    HttpEntity<String> requestEntity = new HttpEntity<String>(null, httpHeaders);
                    String url="http://ip.taobao.com/service/getIpInfo.php?ip="+ip;
                    ResponseEntity<String> taskDataListBo = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
                    String json = taskDataListBo.getBody();
                    TaoBaoIpDataBo taoBaoIpDataBo=JSON.parseObject(json,TaoBaoIpDataBo.class);
                    if("0".equals(taoBaoIpDataBo.getCode())&&"CN".equals(taoBaoIpDataBo.getData().getCountry_id())){
                        registerReq.setProvince(taoBaoIpDataBo.getData().getRegion_id());
                        registerReq.setCity(taoBaoIpDataBo.getData().getCity_id());
                    }
                    RegisterBo registerBo=SoaConnectionFactory.post(request, ConstantsUri.USER_REGISTER,registerReq, RegisterBo.class);
                    if("2000".equals(registerBo.getCode())){
                        logger.info("用户：【" + username + "】 注册成功");
                        BaseResponse br=new BaseResponse("2000","注册成功");
                        mav.addObject("result", br);
                        return mav;
                    }else{
                        BaseResponse br=new BaseResponse("500",registerBo.getMessage());
                        mav.addObject("result", br);
                    }
                }else {
                    mav.addObject("result", brcode);
                }
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }

    /**
     * 注册
     * @param username
     * @param code
     * @param password
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/zhuce_v2.html")
    public ModelAndView zhuce_v2(@RequestParam(value = "username") String username,
                              @RequestParam(value = "code") String code,
                              @RequestParam(value = "password") String password,
                              @RequestParam(value = "tpcode") String tpcode,
                              HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(isNull(username)&&isNull(code)&&isNull(password)&&isNull(tpcode)){
                RSAPublicKey publickey = RedisCacheService.getRedisV2PublicKey(request, getInfoService);
                if(publickey==null){
                    BaseResponse brs=new BaseResponse("1000","获取公钥失败!");
                    mav.addObject("data",brs);
                    return mav;
                }
                BaseResponse brcode = isCodeTime(tpcode, session);
                if("2000".equals(brcode.getCode())){
                    String codes=RedisCode.CODE+":"+session.getId();
                    getInfoService.remove(codes);
                    RegisterReq registerReq=new RegisterReq();
                    registerReq.setPhone(username);
                    //String rsapassword=RSA.encryptString(publickey,password);
                    byte[] bs = RsaV2Service.rsaV2Encryption(password, publickey);
                    BASE64Encoder base64Encoder=new BASE64Encoder();
                    registerReq.setPassword(base64Encoder.encodeBuffer(bs));
                    registerReq.setVerifyingCode(code);
                    registerReq.setStatus(true);
                    registerReq.setVerifyingType(SmsType.YHZC);

                    String ip=getIpAddr(request);
                    logger.debug(ip);
                    //ip="113.247.252.116";
                    HttpHeaders httpHeaders=new HttpHeaders();
                    HttpEntity<String> requestEntity = new HttpEntity<String>(null, httpHeaders);
                    String url="http://ip.taobao.com/service/getIpInfo.php?ip="+ip;
                    ResponseEntity<String> taskDataListBo = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
                    String json = taskDataListBo.getBody();
                    TaoBaoIpDataBo taoBaoIpDataBo=JSON.parseObject(json,TaoBaoIpDataBo.class);
                    if("0".equals(taoBaoIpDataBo.getCode())&&"CN".equals(taoBaoIpDataBo.getData().getCountry_id())){
                        registerReq.setProvince(taoBaoIpDataBo.getData().getRegion_id());
                        registerReq.setCity(taoBaoIpDataBo.getData().getCity_id());
                    }
                    RegisterBo registerBo=SoaConnectionFactory.post(request, ConstantsUri.USER_REGISTER_V2,registerReq, RegisterBo.class);
                    if("2000".equals(registerBo.getCode())){
                        logger.info("用户：【" + username + "】 注册成功");
                        BaseResponse br=new BaseResponse("2000","注册成功");
                        mav.addObject("result", br);
                        return mav;
                    }else{
                        BaseResponse br=new BaseResponse("500",registerBo.getMessage());
                        mav.addObject("result", br);
                    }
                }else {
                    mav.addObject("result", brcode);
                }
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }


    public static final String getIpAddr(final HttpServletRequest request)
            throws Exception {
        if (request == null) {
            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
        }
        String ipString = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ipString.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ipString = str;
                break;
            }
        }

        return ipString;
    }






    /**
     * 验证手机号码是不是存在
     * @param phone
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/forgot.html")
    public ModelAndView forgot(@RequestParam(value = "phone") String phone,
                               @RequestParam(value = "code") String code,
                               HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(phone!=null&&!"".equals(phone)&&code!=null&&!"".equals(code)){
                BaseResponse brcode = isCodeTime(code, session);
                if("2000".equals(brcode.getCode())){
                    String codes=RedisCode.CODE+":"+session.getId();
                    getInfoService.remove(codes);
                    BaseResponse baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_PHONE,null, BaseResponse.class,phone);
                    mav.addObject("result", baseResponse);
                }else{
                    mav.addObject("result", brcode);
                }
            }else{
                if(phone==null||"".equals(phone)){
                    BaseResponse br=new BaseResponse("600","手机号码不能为空");
                    mav.addObject("result", br);
                }else{
                    BaseResponse br=new BaseResponse("700","手机号码不能为空");
                    mav.addObject("result", br);
                }
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }

    /**
     * 验证短信验证码是否正确
     * @param phone
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/smsforgot.html")
    public ModelAndView smsforgot(@RequestParam(value = "phone") String phone,
                                  @RequestParam(value = "code") String code,
                                  HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(phone!=null&&!"".equals(phone)&&code!=null&&!"".equals(code)){
                SmsBo smsBo=new SmsBo();
                smsBo.setCode(code);
                smsBo.setPhone(phone);
                smsBo.setType(SmsType.WJMM);
                VerifyPhoneBo baseResponse=SoaConnectionFactory.post(request,ConstantsUri.SMS_VERIFYPHONE,smsBo,VerifyPhoneBo.class);
                if("2000".equals(baseResponse.getCode())){
                    getInfoService.setString(24*60*60,session.getId()+":"+RedisCode.USER_TOKEN,baseResponse.getData());
                    //验证码是否正确
                    mav.addObject("result", baseResponse);
                }else{
                    //验证码是否正确
                    mav.addObject("result", baseResponse);
                }

            }else{
                if(phone==null||"".equals(phone)){
                    BaseResponse br=new BaseResponse("600","手机号码不能为空");
                    mav.addObject("result", br);
                }else{
                    BaseResponse br=new BaseResponse("700","手机号码不能为空");
                    mav.addObject("result", br);
                }
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }


    /**
     * 修改密码
     * @param phone
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/saveforgot.html")
    public ModelAndView saveforgot(@RequestParam(value = "phone") String phone,
                                   @RequestParam(value = "password") String password,
                                   HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(phone!=null&&!"".equals(phone)&&password!=null&&!"".equals(password)){
                RSAPublicKey publickey = RedisCacheService.getRedisPublicKey(request, getInfoService);
                if(publickey==null){
                    BaseResponse brs=new BaseResponse("1000","获取公钥失败!");
                    mav.addObject("data",brs);
                    return mav;
                }
                String rsapassword=RSA.encryptString(publickey,password);
                BASE64Encoder base64Encoder=new BASE64Encoder();
                String user_token=session.getId()+":"+RedisCode.USER_TOKEN;
                ResetPasswordBO resetPasswordBO=new ResetPasswordBO();
                resetPasswordBO.setPassword(base64Encoder.encodeBuffer(rsapassword.getBytes()));
                resetPasswordBO.setPhone(phone);
                resetPasswordBO.setToken(getInfoService.getString(user_token));
                BaseResponse baseResponse=SoaConnectionFactory.post(request,ConstantsUri.UC_RESET_PASSWORD,resetPasswordBO,BaseResponse.class);
                if("2000".equals(baseResponse.getCode())){
                    getInfoService.remove(user_token);
                }
                mav.addObject("result", baseResponse);
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }

    /**
     * 修改密码
     * @param phone
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/saveforgot_v2.html")
    public ModelAndView saveforgot_v2(@RequestParam(value = "phone") String phone,
                                   @RequestParam(value = "password") String password,
                                   HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(phone!=null&&!"".equals(phone)&&password!=null&&!"".equals(password)){
                RSAPublicKey publickey = RedisCacheService.getRedisV2PublicKey(request, getInfoService);
                if(publickey==null){
                    BaseResponse brs=new BaseResponse("1000","获取公钥失败!");
                    mav.addObject("data",brs);
                    return mav;
                }
//                String rsapassword=RSA.encryptString(publickey,password);
                byte[] rsapassword = RsaV2Service.rsaV2Encryption(password, publickey);
                BASE64Encoder base64Encoder=new BASE64Encoder();
                String user_token=session.getId()+":"+RedisCode.USER_TOKEN;
                ResetPasswordBO resetPasswordBO=new ResetPasswordBO();
                resetPasswordBO.setPassword(base64Encoder.encodeBuffer(rsapassword));
                resetPasswordBO.setPhone(phone);
                resetPasswordBO.setToken(getInfoService.getString(user_token));
                BaseResponse baseResponse=SoaConnectionFactory.post(request,ConstantsUri.UC_RESET_PASSWORD_V2,resetPasswordBO,BaseResponse.class);
                if("2000".equals(baseResponse.getCode())){
                    getInfoService.remove(user_token);
                }
                mav.addObject("result", baseResponse);
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }

    /**
     * 登出
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout.html")
    public ModelAndView logout(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            ModelAndView mav= new ModelAndView("login");
            UserBo obj=(UserBo)getInfoService.getUserBo(request);
            String userToken=getInfoService.getString(request,RedisCode.USER_TOKEN);
            String cookieName="key,userPicturePath,nickname,vipLevel,vipLevelName,level,levelName";
            Cookie[] cookies = request.getCookies();
            for (Cookie coo : cookies) {
                if(cookieName.indexOf(coo.getName())>-1){
                           // coo.setDomain("abc12366.com");
                            //coo.setDomain("118.118.116.132");
                            //coo.setPath("/");
                            coo.setMaxAge(-1); //清空cookie
                            response.addCookie(coo);
                    }
            }

            BaseResponse base=SoaConnectionFactory.delete(request,ConstantsUri.USER_LOGOUT,null,BaseResponse.class,userToken);
            if("2000".equals(base.getCode())){
                getInfoService.removeKeys(request,obj.getId());
            }
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 登出
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/logout.html")
    public ModelAndView logoutkhd(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo obj=(UserBo)getInfoService.getUserBo(request);
            String userToken=getInfoService.getString(request,RedisCode.USER_TOKEN);
            String cookieName="key,userPicturePath,nickname,vipLevel,vipLevelName,level,levelName";
            Cookie[] cookies = request.getCookies();
            for (Cookie coo : cookies) {
                if(cookieName.indexOf(coo.getName())>-1){
                     coo.setDomain("abc12366.com");
                    //coo.setDomain("118.118.116.132");
                    coo.setPath("/");
                    coo.setMaxAge(0); //清空cookie
                    response.addCookie(coo);
                }
            }

            BaseResponse base=SoaConnectionFactory.delete(request,ConstantsUri.USER_LOGOUT,null,BaseResponse.class,userToken);
            if("2000".equals(base.getCode())){
                getInfoService.removeKeys(request,obj.getId());
                BaseResponse br=new BaseResponse("2000","登出成功");
                mav.addObject("code",br.getCode());
                mav.addObject("message",br.getMessage());
            }
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            return mav;
        }
    }


    /**
     * 登出
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/logout_client.html")
    public ModelAndView logout_client(HttpSession session,@RequestParam(value = "userToken") String userToken, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            Object objs=getInfoService.getUserBo(request);

            if(objs!=null){
                UserBo obj=(UserBo)objs;
                userToken=getInfoService.getString(request,RedisCode.USER_TOKEN);
                String cookieName="key,userPicturePath,nickname,vipLevel,vipLevelName,level,levelName";
                Cookie[] cookies = request.getCookies();
                for (Cookie coo : cookies) {
                    if(cookieName.indexOf(coo.getName())>-1){
                         coo.setDomain("abc12366.com");
                        //coo.setDomain("118.118.116.132");
                        coo.setPath("/");
                        coo.setMaxAge(0); //清空cookie
                        response.addCookie(coo);
                    }
                }

                BaseResponse base=SoaConnectionFactory.delete(request,ConstantsUri.USER_LOGOUT,null,BaseResponse.class,userToken);
                if(base.getCode()=="2000"){
                    getInfoService.removeKeys(request,obj.getId());
                }
                BaseResponse br=new BaseResponse("2000","登出成功");
                mav.addObject("code",br.getCode());
                mav.addObject("message",br.getMessage());
            }else{
                SoaConnectionFactory.delete(request,ConstantsUri.USER_LOGOUT,null,BaseResponse.class,userToken);
                BaseResponse br=new BaseResponse("2000","登出成功");
                mav.addObject("code",br.getCode());
                mav.addObject("message",br.getMessage());
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }

    /**
     * 登出
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout_session.html")
    public ModelAndView logout_session(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo obj=(UserBo)getInfoService.getUserBo(request);
            String userToken=getInfoService.getString(request,RedisCode.USER_TOKEN);
            BaseResponse base=SoaConnectionFactory.delete(request,ConstantsUri.USER_LOGOUT,null,BaseResponse.class,userToken);
            if(base.getCode()=="2000"){
                getInfoService.removeKeys(request,obj.getId());
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }





    /**
     * 验证手机号码是不是存在
     * @param session
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/tp_forgot.html")
    public ModelAndView forgot(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "tpcode", required = false) String tpcode,
            HttpSession session, Model model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(!(code!=null&&!"".equals(code))){
                code=tpcode;
            }
            BASE64Decoder base64Decoder=new BASE64Decoder();
            code=new String(base64Decoder.decodeBuffer(code));
            BaseResponse brcode = isCodeTime(code, session);
            if("2000".equals(brcode.getCode())){
                mav.addObject("ok", "正确");
            }else{
                mav.addObject("error",brcode.getMessage());
            }
        } catch (IOException e) {
            logger.debug("异常:"+e.getMessage());
            mav.addObject("soacode","7777");
            mav.addObject("message","操作失败,请稍后再试...");
            return mav;
        }
        return mav;
    }


    /**
     * 验证手机号码是不是存在
     * @param phone
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/phone_forgot.html")
    public ModelAndView phone_forgot(
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "username", required = false) String username,
            HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            //String phone=request.getParameter("phone");
            if(!(phone!=null&&!"".equals(phone))){
                phone=username;
            }
            BASE64Decoder base64Decoder=new BASE64Decoder();
            phone=new String(base64Decoder.decodeBuffer(phone));
            PhoneForgotBo baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_PHONE,null, PhoneForgotBo.class,phone);
            PrintWriter out = null;
            if("2000".equals(baseResponse.getCode())){
                mav.addObject("error","手机号码已经存在");
            }else{
                mav.addObject("ok", "正确");
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }


    /**
     * 验证手机号码是不是存在
     * @param phone
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/phone_forgot_login_phone.html")
    public ModelAndView phone_forgot_login(
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "username", required = false) String username,
            HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            //String phone=request.getParameter("phone");
            if(!(phone!=null&&!"".equals(phone))){
                phone=username;
            }
            BASE64Decoder base64Decoder=new BASE64Decoder();
            phone=new String(base64Decoder.decodeBuffer(phone));
            PhoneForgotBo baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_PHONE,null, PhoneForgotBo.class,phone);
            PrintWriter out = null;
            if("2000".equals(baseResponse.getCode())){
                mav.addObject("ok","正确");
            }else{
                mav.addObject("error", "手机号码不存在");
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }


    /**
     * 登录验证手机号是否存在
     * @param phone
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/phone_forgot_login.html")
    public ModelAndView phone_forgot_login(
            @RequestParam(value = "phone", required = false) String phone,
            HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            BASE64Decoder base64Decoder=new BASE64Decoder();
            phone=new String(base64Decoder.decodeBuffer(phone));
            BaseResponse baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_PHONE,null, BaseResponse.class,phone);
            PrintWriter out = null;
            if("2000".equals(baseResponse.getCode())){
                mav.addObject("ok", "正确");
            }else{
                mav.addObject("error","手机号码不存在");
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }

    /**
     * 验证手机是否存在（手机存在）
     * @param phone
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/forgot_phone_new.html")
    public ModelAndView forgot_phone(
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "username", required = false) String username,
            HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(!(phone!=null&&!"".equals(phone))){
                phone=username;
            }
            BaseResponse baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_PHONE,null, BaseResponse.class,phone);
            if("2000".equals(baseResponse.getCode())){
                mav.addObject("ok", "正确");
            }else{
                mav.addObject("error","手机号码不存在");
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }

    /**
     * 验证手机是否存在（反向手机存在）
     * @param phone
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/forgot_phone_new_fangxiang.html")
    public ModelAndView forgot_phone_new_fangxiang(
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "username", required = false) String username,
            HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(!(phone!=null&&!"".equals(phone))){
                phone=username;
            }
            BASE64Decoder base64Decoder=new BASE64Decoder();
            phone=new String(base64Decoder.decodeBuffer(phone));
            BaseResponse baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_PHONE,null, BaseResponse.class,phone);
            if("2000".equals(baseResponse.getCode())){
                mav.addObject("error","手机号码已存在");
            }else{
                mav.addObject("ok", "正确");
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }




    /**
     * 财税客户端跳转
     * @param session
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/clientjump.html")
    public ModelAndView clientjump( @RequestParam(value = "data", required = false) String data,
            HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        if(isNull(data)){
            try {
                RSAPrivateKey privatekey = RedisCacheService.getRedisPrivateKey(request,getInfoService);
                logger.info("请求数据:"+data);
                String []test=data.split(" ");
                String json="";
                for(String str : test){
                    json+=RSA.decryptString(privatekey,str);
                }
                logger.info("财税客户端JSON数据:"+json);
                ClientUrlBo clientUrlBo = JSON.parseObject(json, ClientUrlBo.class);
                if("csclient".equals(clientUrlBo.getOnly())){
                    if(clientUrlBo.getToken()!=null&&!"".equals(clientUrlBo.getToken())){
                        try {
                            UserClientBo baseResponse=RedisCacheService.getRedisUserClient(request,getInfoService,clientUrlBo.getAccessToken(),clientUrlBo.getToken());
                            if("2000".equals(baseResponse.getCode())){
                                if(clientUrlBo.getUrl()!=null&&!"".equals(clientUrlBo.getUrl())){
                                    UserBo userbo = baseResponse.getData();
                                    String userid=userbo.getId();
                                    Set<byte[]> set = getInfoService.getKeys(userid);
                                    if(set!=null&&set.size()>0){
                                        Map<String,String> maps=new HashMap<String,String>();
                                        for(byte [] b :set){
                                            String key=new String(b,"UTF-8");
                                            if(key.indexOf(RedisCode.ONLY)!=-1){
                                                maps.put(RedisCode.ONLY,getInfoService.getString(key));
                                            }
                                            if(key.indexOf(RedisCode.USER_ID)!=-1){
                                                maps.put(RedisCode.USER_ID,getInfoService.getString(key));
                                            }
                                            if(key.indexOf(RedisCode.USER_TOKEN)!=-1){
                                                maps.put(RedisCode.USER_TOKEN,getInfoService.getString(key));
                                            }
                                            if(maps.size()==3){
                                                break;
                                            }
                                        }
                                        setCookieTs(request,response,maps,userbo);
                                    }else{
                                        Map<String,String> map=new HashMap<String,String>();
                                        String only=System.currentTimeMillis()+getFixLenthString(6);
                                        map.put(RedisCode.ONLY,only);
                                        MD5 md5 = new MD5(userbo.getId());
                                        map.put(RedisCode.USER_ID,md5.compute());
                                        map.put(RedisCode.USER_TOKEN,clientUrlBo.getToken());
                                        setCookieTs(request,response,map,userbo);
                                    }
                                    ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                                    mav.addObject("code","2000");
                                   String strs= new BASE64Encoder().encode(clientUrlBo.getUrl().getBytes());
                                    mav.addObject("url",strs);
                                    mav.addObject("messgage","处理成功");
                                    return mav;
                                }else{
                                    ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                                    mav.addObject("code","1000");
                                    mav.addObject("messgage","缺少地址必要参数");
                                    return mav;
                                }
                            }else{
                                ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                                mav.addObject("code","1000");
                                mav.addObject("messgage","获取用户信息失败");
                                return mav;
                            }
                        } catch (Exception e) {
                            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                            logger.debug("异常:"+e.getMessage());
                            if ( e instanceof SoaException){
                                mav.addObject("soacode","8888");
                                mav.addObject("message","服务器繁忙,请稍后再试...");
                            }else{
                                mav.addObject("soacode","7777");
                                mav.addObject("message","操作失败,请稍后再试...");
                            }
                            return mav;
                        }
                    }else{
                        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                        mav.addObject("code","1000");
                        mav.addObject("messgage","缺少User-Token必要参数");
                        return mav;
                    }
                }else{
                    ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                    mav.addObject("code","1000");
                    mav.addObject("messgage","缺少终端必要参数");
                    return mav;
                }
            } catch (SoaException e) {
                ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                logger.debug("异常:"+e.getMessage());
                if ( e instanceof SoaException){
                    mav.addObject("soacode","8888");
                    mav.addObject("message","服务器繁忙,请稍后再试...");
                }else{
                    mav.addObject("soacode","7777");
                    mav.addObject("message","操作失败,请稍后再试...");
                }
                return mav;
            }
        }else{
            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
            mav.addObject("code","1000");
            mav.addObject("messgage","缺少Data参数");
            return mav;
        }
    }


    /**
     * 财税客户端跳转
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/clientjump_v2.html")
    public ModelAndView clientjump_v2( @RequestParam(value = "data", required = false) String data, HttpServletRequest request, HttpServletResponse response) {
        if(isNull(data)){
            try {
                RSAPrivateKey privatekey = RedisCacheService.getRedisV2PrivateKey(request,getInfoService);
                logger.info("请求数据:"+data);
                byte[] bs = RsaV2Service.rsaV2Decrypt(data, privatekey);
                String json=new String(bs,"UTF-8");
                logger.info("财税客户端JSON数据:"+json);
                ClientUrlBo clientUrlBo = JSON.parseObject(json, ClientUrlBo.class);
                if("csclient".equals(clientUrlBo.getOnly())){
                    if(clientUrlBo.getToken()!=null&&!"".equals(clientUrlBo.getToken())){
                        try {
                            UserClientBo baseResponse=RedisCacheService.getRedisUserClient(request,getInfoService,clientUrlBo.getAccessToken(),clientUrlBo.getToken());
                            if("2000".equals(baseResponse.getCode())){
                                if(clientUrlBo.getUrl()!=null&&!"".equals(clientUrlBo.getUrl())){
                                    UserBo userbo = baseResponse.getData();
                                    String userid=userbo.getId();
                                    Set<byte[]> set = getInfoService.getKeys(userid);
                                    if(set!=null&&set.size()>0){
                                        Map<String,String> maps=new HashMap<String,String>();
                                        for(byte [] b :set){
                                            String key=new String(b,"UTF-8");
                                            if(key.indexOf(RedisCode.ONLY)!=-1){
                                                maps.put(RedisCode.ONLY,getInfoService.getString(key));
                                            }
                                            if(key.indexOf(RedisCode.USER_ID)!=-1){
                                                maps.put(RedisCode.USER_ID,getInfoService.getString(key));
                                            }
                                            if(key.indexOf(RedisCode.USER_TOKEN)!=-1){
                                                maps.put(RedisCode.USER_TOKEN,getInfoService.getString(key));
                                            }
                                            if(maps.size()==3){
                                                break;
                                            }
                                        }
                                        setCookieTs(request,response,maps,userbo);
                                    }else{
                                        Map<String,String> map=new HashMap<String,String>();
                                        String only=System.currentTimeMillis()+getFixLenthString(6);
                                        map.put(RedisCode.ONLY,only);
                                        MD5 md5 = new MD5(userbo.getId());
                                        map.put(RedisCode.USER_ID,md5.compute());
                                        map.put(RedisCode.USER_TOKEN,clientUrlBo.getToken());
                                        setCookieTs(request,response,map,userbo);
                                    }
                                    ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                                    mav.addObject("code","2000");
                                    String strs= new BASE64Encoder().encode(clientUrlBo.getUrl().getBytes());
                                    mav.addObject("url",strs);
                                    mav.addObject("messgage","处理成功");
                                    return mav;
                                }else{
                                    ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                                    mav.addObject("code","1000");
                                    mav.addObject("messgage","缺少地址必要参数");
                                    return mav;
                                }
                            }else{
                                ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                                mav.addObject("code","1000");
                                mav.addObject("messgage","获取用户信息失败");
                                return mav;
                            }
                        } catch (Exception e) {
                            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                            logger.debug("异常:"+e.getMessage());
                            if ( e instanceof SoaException){
                                mav.addObject("soacode","8888");
                                mav.addObject("message","服务器繁忙,请稍后再试...");
                            }else{
                                mav.addObject("soacode","7777");
                                mav.addObject("message","操作失败,请稍后再试...");
                            }
                            return mav;
                        }
                    }else{
                        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                        mav.addObject("code","1000");
                        mav.addObject("messgage","缺少User-Token必要参数");
                        return mav;
                    }
                }else{
                    ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                    mav.addObject("code","1000");
                    mav.addObject("messgage","缺少终端必要参数");
                    return mav;
                }
            } catch (Exception e) {
                ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                logger.debug("异常:"+e.getMessage());
                if ( e instanceof SoaException){
                    mav.addObject("soacode","8888");
                    mav.addObject("message","服务器繁忙,请稍后再试...");
                }else{
                    mav.addObject("soacode","7777");
                    mav.addObject("message","操作失败,请稍后再试...");
                }
                return mav;
            }
        }else{
            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
            mav.addObject("code","1000");
            mav.addObject("messgage","缺少Data参数");
            return mav;
        }
    }


    /**
     * 财税客户端存入Cookie
     * @param response
     * @param maps
     * @param userBo
     */
    private void setCookieTs(HttpServletRequest request, HttpServletResponse response,Map<String,String> maps,UserBo userBo){
        String str=System.currentTimeMillis()+getFixLenthString(6);
        Map<String,String> map=new HashMap<String,String>();
        map.put(RedisCode.ONLY,maps.get(RedisCode.ONLY));
        map.put(RedisCode.USER_ID,maps.get(RedisCode.USER_ID));
        map.put(RedisCode.USER_TOKEN,maps.get(RedisCode.USER_TOKEN));
        String json=JSON.toJSONString(map);
        String key="";
        try {
            key= URLEncoder.encode(SymmetricEncoder.setMiwen(json),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MD5 md5=new MD5(userBo.getId());
        getInfoService.setClientString(request,map, RedisCode.USER_TOKEN,maps.get(RedisCode.USER_TOKEN));
        getInfoService.setClientString(request,map, RedisCode.ONLY,maps.get(RedisCode.ONLY));
        getInfoService.setClientString(request,map, RedisCode.USER_ID,md5.compute());
        getInfoService.setClientObject(request,map, RedisCode.CURRENT_USER,userBo);

        //带域名和路径的cookie
        ResponseCookie.setCookie(response,"userPicturePath",userBo.getUserPicturePath(),"abc12366.com","/");
        String nickname="";
        try {
            nickname=URLEncoder.encode(userBo.getNickname(),"UTF-8");
            ResponseCookie.setCookie(response,"nickname",nickname,"abc12366.com","/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseCookie.setCookie(response,"key",key,"abc12366.com","/");
        ResponseCookie.setCookie(response,"vipLevel",userBo.getVipLevel(),"abc12366.com","/");

        String vipLevelName="";
        try {
            vipLevelName=URLEncoder.encode(userBo.getVipLevelName(),"UTF-8");
            ResponseCookie.setCookie(response,"vipLevelName",vipLevelName,"abc12366.com","/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseCookie.setCookie(response,"level",userBo.getLevel(),"abc12366.com","/");

        String levelName="";
        try {
            levelName=URLEncoder.encode(userBo.getLevelName(),"UTF-8");
            ResponseCookie.setCookie(response,"levelName",levelName,"abc12366.com","/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ResponseCookie.setCookie(response,"client","abcClient","abc12366.com","/");

        //不要路径和域名的cookie
        ResponseCookie.setCookie(response,"userPicturePath",userBo.getUserPicturePath());
        ResponseCookie.setCookie(response,"nickname",nickname);
        ResponseCookie.setCookie(response,"key",key);
        ResponseCookie.setCookie(response,"vipLevel",userBo.getVipLevel());
        ResponseCookie.setCookie(response,"vipLevelName",vipLevelName);
        ResponseCookie.setCookie(response,"level",userBo.getLevel());
        ResponseCookie.setCookie(response,"levelName",levelName);
        ResponseCookie.setCookie(response,"client","abcClient");

        //要路径不要域名的cookie
        ResponseCookie.setCookie(response,"userPicturePath",userBo.getUserPicturePath(),"/");
        ResponseCookie.setCookie(response,"nickname",nickname,"/");
        ResponseCookie.setCookie(response,"key",key,"/");
        ResponseCookie.setCookie(response,"vipLevel",userBo.getVipLevel(),"/");
        ResponseCookie.setCookie(response,"vipLevelName",vipLevelName,"/");
        ResponseCookie.setCookie(response,"level",userBo.getLevel(),"/");
        ResponseCookie.setCookie(response,"levelName",levelName,"/");
        ResponseCookie.setCookie(response,"client","abcClient","/");

    }



    /**
     * 检测是否超时
     * @param request
     * @return
     */
    @RequestMapping("/overtime.html")
    public ModelAndView overtime(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        Object obj=getInfoService.getUserBo(request);
        if(obj!=null){
            BaseResponse br=new BaseResponse("2000","");
            mav.addObject("data",br);
        }else{
            BaseResponse br=new BaseResponse("1000","");
            mav.addObject("data",br);
        }
        return mav;
    }

    /**
     * 登出
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout_index.html")
    public String logout_index(HttpServletRequest request, HttpServletResponse response,Model model) {
        try {
//            ModelAndView mav= new ModelAndView("redirect:http://www.baidu.com");
            UserBo obj=(UserBo)getInfoService.getUserBo(request);
            String userToken=getInfoService.getString(request,RedisCode.USER_TOKEN);
            String cookieName="key,userPicturePath,nickname";
            Cookie[] cookies = request.getCookies();
            for (Cookie coo : cookies) {
                if(cookieName.indexOf(coo.getName())>-1){
                    // coo.setDomain("abc12366.com");
                    //coo.setDomain("118.118.116.132");
                    //coo.setPath("/");
                    coo.setMaxAge(-1); //清空cookie
                    response.addCookie(coo);
                }
            }

            BaseResponse base=SoaConnectionFactory.delete(request,ConstantsUri.USER_LOGOUT,null,BaseResponse.class,userToken);
            if("2000".equals(base.getCode())){
                getInfoService.removeKeys(request,obj.getId());
            }
            return "redirect:"+ SpringCtxHolder.getProperty("cswdomain");
//            return "soaerror";
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            //ModelAndView mav= new ModelAndView("soaerror");
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }


    @GetMapping("/extension.html")
    public ModelAndView extension(@RequestParam(value = "p", required = false) String p,HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav= new ModelAndView("client");
        String ucdomain= SpringCtxHolder.getProperty("ucdomain");
        if(isNull(p)){
            try {
                byte[] b = Base64.getDecoder().decode(p.getBytes());
                String ps=new String(b);
                String[] strs = ps.split("\\|");
                String no=strs[0];
                String url=strs[1];
                if(isNull(no)){
                    ResponseCookie.setCookie(response,"no",no,"/");
                    ResponseCookie.setCookie(response,"no",no,"abc12366.com","/");
                }
                if(isNull(url)){
                    if(isNull(url) && url.indexOf(ucdomain)>-1){
                        UserBo userbo = getInfoService.getUserBo(request);
                        if(userbo!=null){
                            url=url.replace("http://","");
                            url=url.replace("https://","");
                            mav.addObject("url",url);
                        }else{
                            url=url.replace("http://","");
                            url=url.replace("https://","");
                            byte[] bs = Base64.getEncoder().encode(url.getBytes());
                            String u=new String(bs);
                            mav.addObject("url",ucdomain+"/login?recallurl="+u);
                        }
                    }else{
                        mav.addObject("url",url);
                    }
                }else{
                    mav.addObject("url",ucdomain+"/login");
                }
            } catch (Exception e) {
                logger.info("推广地址参数:"+p);
                mav.addObject("url",ucdomain+"/login");
            }
        }else{
            mav.addObject("url",ucdomain+"/login");
        }
        return  mav;
    }


    public static boolean  isMobileDevice(String requestHeader){
        /**
         * android : 所有android设备
         * mac os : iphone ipad
         * windows phone:Nokia等windows系统的手机
         */
        String[] deviceArray = new String[]{"android","mac os","windows phone"};
        if(requestHeader == null)
            return false;
        requestHeader = requestHeader.toLowerCase();
        for(int i=0;i<deviceArray.length;i++){
            if(requestHeader.indexOf(deviceArray[i])>0){
                return true;
            }
        }
        return false;
    }
}

