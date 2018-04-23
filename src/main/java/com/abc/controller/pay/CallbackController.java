package com.abc.controller.pay;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CodeUtil;
import com.abc.common.util.DesCryptUtil;
import com.abc.controller.BaseController;
import com.abc.service.RedisCode;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.CallbackBo;
import com.abc.soa.request.CallbackPayBo;
import com.abc.soa.response.UserBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by stuy on 2017/8/9.
 */
@Controller
public class CallbackController extends BaseController {

    @Autowired
    DesCryptUtil dcu;

    private static final Logger LOGGER = LoggerFactory.getLogger(CallbackController.class);


    /**
     * 支付成功支付宝会调操作
     * @param session
     * @param request
     * @param response
     */
    @RequestMapping(value = "/callback.html")
    public void sessos(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("支付宝成功回调到系统!");
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter
                .hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            try {
                valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            params.put(name, valueStr);
        }
        try {
            // 商户订单号
            String out_trade_no = new String(request.getParameter(
                    "out_trade_no").getBytes("utf-8"), "UTF-8");

            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no")
                    .getBytes("utf-8"), "UTF-8");

            // 交易状态
            String trade_status = new String(request.getParameter(
                    "trade_status").getBytes("utf-8"), "UTF-8");
            log.debug("支付状态:"+trade_status);
            String total_amount = new String(request.getParameter(
                    "total_amount").getBytes("utf-8"), "UTF-8");
            Object gmt_payment=request.getParameter("gmt_payment");
            PrintWriter out = response.getWriter();
            if(gmt_payment!=null&&!"".equals(gmt_payment)){
                String date = new String(request.getParameter("gmt_payment")
                        .getBytes("utf-8"), "UTF-8");
                CallbackBo callbackBo = new CallbackBo();
                callbackBo.setGmt_payment(date);
                callbackBo.setOut_trade_no(out_trade_no);
                callbackBo.setTotal_amount(total_amount);
                callbackBo.setTrade_no(trade_no);
                callbackBo.setTrade_status(trade_status);
                callbackBo.setParams(params);
                BaseResponse baseResponses = SoaConnectionFactory.post(request, ConstantsUri.ALIPAY_VALIDATE, params, BaseResponse.class);
                if ("2000".equals(baseResponses.getCode())) {
                    out.print("success");
                    BaseResponse baseResponse = SoaConnectionFactory.get(request, ConstantsUri.ALIPAY_CALLBACK, callbackBo, BaseResponse.class);
                    if ("2000".equals(baseResponse.getCode())) {
                        CallbackPayBo callbackPayBo=new CallbackPayBo();
                        callbackPayBo.setStatus(trade_status);
                        callbackPayBo.setBool(true);
                        getInfoService.setObject(RedisCode.TIME_2_60_60,RedisCode.ORDER+":"+out_trade_no,callbackPayBo);
                    }
                }else{
                    out.println("fail");
                }
            }else{
                CallbackPayBo callbackPayBo=new CallbackPayBo();
                callbackPayBo.setStatus(trade_status);
                callbackPayBo.setBool(false);
                getInfoService.setObject(RedisCode.TIME_2_60_60,RedisCode.ORDER+":"+out_trade_no,callbackPayBo);
                out.println("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付成功Soa微信回调操作
     * @param session
     * @param request
     * @param response
     */
    @RequestMapping(value = "/weixin_callback.html")
    public void weixin_callback(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Soa微信成功回调到系统!");
        try {
            // 商户订单号
            String jylsh = new String(request.getParameter(
                    "jylsh").getBytes("utf-8"), "UTF-8");
            if(jylsh!=null&&!"".equals(jylsh)){
                CallbackPayBo callbackPayBo=new CallbackPayBo();
                callbackPayBo.setStatus(jylsh);
                callbackPayBo.setBool(true);
                getInfoService.setObject(RedisCode.TIME_2_60_60,RedisCode.ORDER+":"+jylsh,callbackPayBo);
            }else{
                CallbackPayBo callbackPayBo=new CallbackPayBo();
                callbackPayBo.setStatus(jylsh);
                callbackPayBo.setBool(false);
                getInfoService.setObject(RedisCode.TIME_2_60_60,RedisCode.ORDER+":"+jylsh,callbackPayBo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成在线支付页面链接，可保持登录状态
     * @param request
     * @return
     */
    @GetMapping("/toPayServiceCharge")
    public ModelAndView toPayServiceCharge(@RequestParam(value = "nsrsbh", required = false ,defaultValue = "") String nsrsbh, HttpServletRequest request, HttpServletResponse response   ){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);

        String userToken = "";
        String accessToken ="";
        String userId = "";
        if(getInfoService.getString(request, RedisCode.USER_TOKEN)!=null) {
            userToken = getInfoService.getString(request, RedisCode.USER_TOKEN);
//            userId = getInfoService.getString(request, RedisCode.USER_ID);  //不能用这种取法，这种方式取到的userId是编码后的值

            //取userId
            Object obj = getInfoService.getUserBo(request);
            if (obj != null) {
                UserBo userBo = (UserBo) obj;
                userId = userBo.getId();
            }
        }
        ServletContext application = request.getSession().getServletContext();
        if (application.getAttribute("accessToken") != null) {
            accessToken = (String) application.getAttribute("accessToken");
        }

        if(userToken != null && userId!=null && userToken.length()>0 && userId.length()>0 && accessToken!=null ){
            try {
//                String content = "8290c10db7fb751bb8d0bf47e15ea4c1|4a8e0160a0a94bfab6bc2bbc820c1b90|457aafc7cf834b7ea32418277e689c4d";
                String content = accessToken + "|" + userToken + "|" + userId ;
//                log.debug("跳转支付页面content="+content);
                byte[] strTemp = dcu.symmetricEncrypt(SpringCtxHolder.getProperty("SCP_CRYPT").getBytes(), content.getBytes() );
                String encodeStr = CodeUtil.encodeContent("BASE64", strTemp);
//                log.debug("跳转支付页面encodeStr="+encodeStr);

                if (strTemp != null) {
//                    return mav.addObject("toUrl", SpringCtxHolder.getProperty("SCP_URL")+"/abcolp/OrderFWNF.aspx?p="+ URLEncoder.encode(encodeStr,"utf-8"));
                    return mav.addObject("toUrl", SpringCtxHolder.getProperty("SCP_URL")+"/OrderFWNF.aspx?p="+ URLEncoder.encode(encodeStr, "utf-8")+"&nsrsbh="+nsrsbh);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                return mav.addObject("toUrl",SpringCtxHolder.getProperty("SCP_URL"));
            }
        }
        return mav.addObject("toUrl",SpringCtxHolder.getProperty("SCP_URL"));
    }

}
