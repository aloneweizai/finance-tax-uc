package com.abc.controller;

import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.CouponActivityDataBo;
import com.abc.soa.response.CouponActivityDataListBO;
import com.abc.soa.response.CouponDataBo;
import com.abc.soa.response.UserBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by stuy on 2018/1/31.
 */
@Controller
@RequestMapping(value = "/coupon")
public class CouponController extends BaseController {

    /**
     * 优惠券活动列表页
     * @return
     */
    @GetMapping("/activities/list.html")
    public ModelAndView couponlist(HttpServletRequest request) {
        ModelAndView mav= new ModelAndView("coupon/couponlist");
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            CouponActivityDataListBO br = SoaConnectionFactory.get(request, ConstantsUri.COUPON_ACTIVITIES, null, CouponActivityDataListBO.class);
            mav.addObject("data",br);
            mav.addObject("user",userbo);
            Calendar calendar= Calendar.getInstance();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
            String t=dateFormat.format(calendar.getTime());
            Date date = dateFormat.parse(t);
            mav.addObject("time",date);
        } catch (SoaException e) {
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
        }catch (Exception e){
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
        }

        return mav;
    }


    /**
     * 优惠券活动列表页
     * @return
     */
    @GetMapping("/details/{id}/{hdid}")
    public ModelAndView coupon(HttpServletRequest request,@PathVariable(value = "id") String id,@PathVariable(value = "hdid") String hdid) {
        ModelAndView mav= new ModelAndView("coupon/coupon");
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            CouponActivityDataBo couponActivityDataBo=SoaConnectionFactory.get(request,ConstantsUri.COUPON_ACTIVITIES_ID,null,CouponActivityDataBo.class,hdid);
            long time=couponActivityDataBo.getData().getActivityEndTime().getTime()-System.currentTimeMillis()+24*60*60*1000-1000;
            if(time>0){
                mav.addObject("time",time/(24*3600*1000)+1);
            }else{
                mav.addObject("time",-1);
            }
            CouponDataBo couponDataBo=SoaConnectionFactory.get(request,ConstantsUri.COUPON_ID,null,CouponDataBo.class,hdid);
            mav.addObject("data",couponDataBo.getData());
            mav.addObject("hdid",hdid);
            mav.addObject("image",couponActivityDataBo.getData().getImageUrl());
            mav.addObject("sysl",couponActivityDataBo.getData().getCouponNum()-couponDataBo.getData().getCollectNum());
            mav.addObject("user",userbo);
            mav.addObject("endtime",couponActivityDataBo.getData().getActivityEndTime());
            String categoryids=couponDataBo.getData().getCategoryIds();
            StringBuffer sb=new StringBuffer();
            int count=0;
            if(categoryids.indexOf("ALL")>-1){
                count=3;
            }else{
                if(categoryids.indexOf("CSKT")>-1){
                    sb.append("财税课堂");
                    count++;
                }
                if(categoryids.indexOf("JFCZ")>-1){
                    if(count!=0){
                        sb.append("、");
                    }
                    sb.append("积分充值");
                    count++;
                }
                if(categoryids.indexOf("HYCZ")>-1){
                    if(count!=0){
                        sb.append("、");
                    }
                    sb.append("会员购买");
                    count++;
                }
            }
            if(count==3){
                mav.addObject("lx","全品类");
            }else{
                mav.addObject("lx",sb.toString());
            }

        } catch (SoaException e) {
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
        }
        return mav;
    }



    /**
     * 优惠券活动列表页
     * @return
     */
    @PostMapping("/receive/{id}")
    public ModelAndView receive(HttpServletRequest request,@PathVariable(value = "id") String id) {
        ModelAndView mav= new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            BaseResponse br = SoaConnectionFactory.get(request, ConstantsUri.USER_COUPON, null, BaseResponse.class, userbo.getId(), id);
            mav.addObject("data",br);
        } catch (SoaException e) {
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
        }
        return mav;
    }

}
