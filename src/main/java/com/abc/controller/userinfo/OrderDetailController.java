package com.abc.controller.userinfo;

import com.abc.bean.userinfo.OrderBO;
import com.abc.cache.RedisCacheService;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.userinfo.CouponUserListReq;
import com.abc.soa.request.userinfo.OrderLogReq;
import com.abc.soa.response.DictRes;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.UserTzxxRes;
import com.abc.soa.response.userinfo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author:zlk
 * @Description:订单详情
 * @Date:2017-08-30
 * @Time:18:01
 */
@Controller
@RequestMapping(value = "/orderDetail")
public class OrderDetailController extends BaseController{

    private final static Logger logger = LoggerFactory.getLogger(OrderDetailController.class);

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ModelAndView orderDetail(@PathVariable(value = "id")String id,HttpServletRequest request,HttpSession session){
        try {
            ModelAndView mav = new ModelAndView("userinfo/order_detail");
            UserBo userBo = getInfoService.getUserBo(request);
            OrderDetailResp orderDetailResp = SoaConnectionFactory.getRestful(request, ConstantsUri.ORDER_DETAIL, null, OrderDetailResp.class, id);
            OrderBO orderBO = orderDetailResp.getData();
            if(orderBO !=null) {
                mav.addObject("orderNo",orderBO.getOrderNo());
                mav.addObject("orderStatus",orderBO.getOrderStatus());
                mav.addObject("orderBO",orderBO);
            }

            OrderLogReq orderLogReq = new OrderLogReq();
            orderLogReq.setOrderNo(id);
            orderLogReq.setLogType("0");//订单日志
            orderLogReq.setExchangeId(null);
            OrderLogResp orderLogResp = SoaConnectionFactory.get(request, ConstantsUri.ORDER_LOG, orderLogReq, OrderLogResp.class);
            mav.addObject("orderlogs",orderLogResp.getDataList());
            if(userBo!=null) {
                UserTzxxRes userTzxxRes = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_TZXX, null, UserTzxxRes.class, userBo.getId());
                mav.addObject("usertzxx", userTzxxRes.getData());
            }
            mav.addObject("userBo",userBo);

            if(orderBO!=null && orderBO.getExpressCompId()!=null){ //查询物流
                ExpressCompResp expressComp=SoaConnectionFactory.getRestful(request,ConstantsUri.UC_EXPRESS,null,ExpressCompResp.class,orderBO.getExpressCompId());
                mav.addObject("expressComp",expressComp.getData());
            }

            CouponUserListReq couponUserListReq = new CouponUserListReq();
            couponUserListReq.setOrderNo(id);
            couponUserListReq.setPage(0);
            couponUserListReq.setSize(0);
            CouponUserListResp couponUserListResp = SoaConnectionFactory.get(request, ConstantsUri.COUPON_USED_LIST, couponUserListReq, CouponUserListResp.class);
            mav.addObject("coupons",couponUserListResp.getDataList());

            DictRes invoicename = RedisCacheService.getRedisDictRes(request,getInfoService,"invoicename");
            DictRes invoicecontent = RedisCacheService.getRedisDictRes(request,getInfoService,"invoicecontent");
            DictRes invoicetype = RedisCacheService.getRedisDictRes(request,getInfoService,"invoicetype");
            DictRes fqsqstatus = RedisCacheService.getRedisDictRes(request,getInfoService,"fqsqstatus");
            DictRes operTypes = RedisCacheService.getRedisDictRes(request,getInfoService,"operType");

            //查询并添加订单状态数据字典
            mav.addObject("invoicenames", invoicename.getDataList());
            mav.addObject("invoicecontents", invoicecontent.getDataList());
            mav.addObject("invoicetypes", invoicetype.getDataList());
            mav.addObject("fqsqstatus", fqsqstatus.getDataList());
            mav.addObject("operTypes", operTypes.getDataList());
            //mav.addObject("path", SpringCtxHolder.getProperty("imagedomain"));
            return mav;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

}
