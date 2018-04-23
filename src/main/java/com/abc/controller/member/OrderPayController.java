package com.abc.controller.member;

import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.service.RedisCode;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.CallbackBo;
import com.abc.soa.request.CallbackPayBo;
import com.abc.soa.request.RefundDataRes;
import com.abc.soa.response.UserClientBo;
import com.abc.soa.response.WeixinQueryBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by stuy on 2017/8/7.
 */
@Controller
@RequestMapping(value = "/memberOrder/orderpay")
public class OrderPayController extends BaseController{

    //protected Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * 读取订单是否支付完成
     * @param orderid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderstatus/{orderid}")
    public ModelAndView orderid(@PathVariable(value = "orderid") String orderid, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        /**
         * 从redis里面取出订单状态
         */
        Object obj=getInfoService.getObject(RedisCode.ORDER+":"+orderid,CallbackPayBo.class);

        if(obj!=null){
            CallbackPayBo str =(CallbackPayBo)obj;
            if(str.getBool()){
                BaseResponse br=new BaseResponse("2000","");
                mav.addObject("data",br);
                ///订单状态已经改成成功，从redis里面移除
                getInfoService.remove(RedisCode.ORDER+":"+orderid);
                Object object =getInfoService.getString(request, RedisCode.USER_TOKEN);
                if(object!=null){
                    try {
                        String usertoken=object.toString();
                        UserClientBo baseResponse=SoaConnectionFactory.getRestful(request, ConstantsUri.GETUSER_TOKEN,null,UserClientBo.class,usertoken);
                        if("2000".equals(baseResponse.getCode())){
                            getInfoService.setUserBo(request,baseResponse.getData());
                        }
                    } catch (SoaException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                BaseResponse br=new BaseResponse("6666","");
                mav.addObject("data",br);
            }
        }else{
            BaseResponse br=new BaseResponse("1000","");
            mav.addObject("data",br);
        }
        return mav;
    }


    /**
     * 调取支付宝查询接口
     * @param orderid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/{orderid}", method = RequestMethod.POST)
    public ModelAndView postorder(@PathVariable(value = "orderid") String orderid, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            Map map=new HashMap();
            map.put("out_trade_no",orderid);
            RefundDataRes refundDataRes=SoaConnectionFactory.get(request, ConstantsUri.ALIPAY_QUERY,map,RefundDataRes.class);
            if("2000".equals(refundDataRes.getCode())){
                if("TRADE_SUCCESS".equals(refundDataRes.getData().getTrade_status())){
                    CallbackBo callbackBo = new CallbackBo();
                    callbackBo.setGmt_payment(refundDataRes.getData().getSend_pay_date());
                    callbackBo.setOut_trade_no(refundDataRes.getData().getOut_trade_no());
                    callbackBo.setTotal_amount(refundDataRes.getData().getTotal_amount());
                    callbackBo.setTrade_no(refundDataRes.getData().getTrade_no());
                    callbackBo.setTrade_status(refundDataRes.getData().getTrade_status());
//                    callbackBo.setParams(params);
                    BaseResponse baseResponse = SoaConnectionFactory.get(request, ConstantsUri.ALIPAY_CALLBACK, callbackBo, BaseResponse.class);
                    if ("2000".equals(baseResponse.getCode())) {
                        BaseResponse br=new BaseResponse("2000","");
                        mav.addObject("data",br);
                    }else{
                        BaseResponse br=new BaseResponse("1000","");
                        mav.addObject("data",br);
                    }
                }else{
                    BaseResponse br=new BaseResponse("1000","");
                    mav.addObject("data",br);
                }
            }else{
                BaseResponse br=new BaseResponse("1000","");
                mav.addObject("data",br);
            }
        } catch (SoaException e) {
            log.debug("异常:"+e.getMessage());
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
     * 调取微信查询接口
     * @param orderid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/weixin/{orderid}", method = RequestMethod.POST)
    public ModelAndView weixin(@PathVariable(value = "orderid") String orderid, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            Map map=new HashMap();
            map.put("out_trade_no",orderid);
            WeixinQueryBo br=SoaConnectionFactory.get(request,ConstantsUri.WEIXIN_QUERY,map,WeixinQueryBo.class);
            if("SUCCESS".equals(br.getData().getTrade_state())){
                BaseResponse brs=new BaseResponse("2000","");
                mav.addObject("data",brs);
            }else{
                BaseResponse brs=new BaseResponse("1000","");
                mav.addObject("data",brs);
            }
            mav.addObject("data",br.getData().getTrade_state());
        } catch (SoaException e) {
            log.debug("异常:"+e.getMessage());
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
     * 支付成功支付宝跳转到成功页面
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/success.html", method = RequestMethod.GET)
    public String sessos(HttpSession session, HttpServletRequest request) {
        log.info("支付返回页面成功!");
        return "member/success";
    }



    /**
     * 支付成功支付宝跳转到成功页面
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/ewmpaysuccess.html", method = RequestMethod.GET)
    public ModelAndView ewmpaysuccess(@RequestParam(value = "orderid", required = false) String orderid, HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("member/ewmpaysuccess");
        mav.addObject("orderid",orderid);
        log.info("支付返回页面成功!");
        return mav;
    }

}
