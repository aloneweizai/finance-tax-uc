package com.abc.controller.userinfo;

import com.abc.cache.RedisCacheService;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.userinfo.CouponsReq;
import com.abc.soa.response.DictRes;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.userinfo.CouponsResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by andy on 2018/1/15.
 * 优惠券
 */
@Controller
@RequestMapping(value = "/coupons")
public class CouponsController extends BaseController{
    private final static Logger logger = LoggerFactory.getLogger(CouponsController.class);

    /**
     * 优惠券列表
     * @param request
     * @param status  状态：0-删除；1-草稿；2-启用；3-停用
     * @return
     */
    @GetMapping(value = "/list.html")
    public ModelAndView list(HttpServletRequest request,
                             @RequestParam(value = "index", required = false,defaultValue = "1") String index,
                             @RequestParam(value = "size",required = false,defaultValue = "6")Integer size,
                             @RequestParam(value = "status",defaultValue = "1",required = true) String status)
    {
        ModelAndView mav = new ModelAndView("userinfo/coupons");
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            CouponsReq couponsReq = new CouponsReq();
            couponsReq.setStatus(status);
            couponsReq.setCategoryIds(null);
            if("1".equals(status)) {
                couponsReq.setIsDate("2");
            }else if("5".equals(status)){
                couponsReq.setIsDate("1");
            }else{
                couponsReq.setIsDate("0");
            }
            couponsReq.setPage(Integer.valueOf(index));
            couponsReq.setSize(size);

            CouponsResp couponsResp= SoaConnectionFactory.get(request, ConstantsUri.COUPONS_LIST, couponsReq, CouponsResp.class, userBo.getId());
            mav.addObject("coupons",couponsResp.getDataList());


            DictRes couponTypeRes = RedisCacheService.getRedisDictRes(request, getInfoService, "couponType");
            //查询并添加订单状态数据字典
            mav.addObject("couponTypes", couponTypeRes.getDataList());
            if(couponsResp.getTotal()>0){
                mav.addObject("page", index);
                mav.addObject("count", couponsResp.getTotal());
            }else{
                mav.addObject("page", 0);
                mav.addObject("count", 0);
            }
            mav.addObject("status", status);
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
     * 优惠券删除
     * @param couponId
     * @return
     */
    @GetMapping(value = "/deleteCoupon")
    public ModelAndView delete(HttpServletRequest request,@RequestParam(value = "couponId",required = true)String couponId){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            BaseResponse baseResponse= SoaConnectionFactory.delete(request, ConstantsUri.COUPONS_DELETE, null, BaseResponse.class, userBo.getId(), couponId);
            mav.addObject("data",baseResponse);
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
}
