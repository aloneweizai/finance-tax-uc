package com.abc.controller.member;

import com.abc.application.SpringCtxHolder;
import com.abc.bean.userinfo.*;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.DizhiIDBo;
import com.abc.soa.request.OrderPayBO;
import com.abc.soa.request.VipLevelBO;
import com.abc.soa.request.VipLevelDataBO;
import com.abc.soa.request.userinfo.OrderBoReq;
import com.abc.soa.request.userinfo.OrderProductBoReq;
import com.abc.soa.response.AddressBo;
import com.abc.soa.response.AddressRes;
import com.abc.soa.response.DizhiNameBo;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.userinfo.OrderDetailResp;
import com.abc.soa.response.userinfo.UvipPriceResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zlk
 * @Description:积分支付
 * @Date:2017-09-08
 * @Time:15:40
 */
@Controller
@RequestMapping(value = "/pointPay")
public class PointPayController extends BaseController{
    private final static Logger logger = LoggerFactory.getLogger(PointPayController.class);

    /**
     * 会员积分购买
     * @param goodsid
     * @param request
     * @return
     */
    @RequestMapping(value = "/{goodsid}",method = RequestMethod.POST)
    public ModelAndView vipOrder(@PathVariable(value = "goodsid")String goodsid,HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try{
            UserBo obj = getInfoService.getUserBo(request);
            if(isNull(goodsid)){
                OrderBoReq orderBoReq = new OrderBoReq();
                OrderProductBoReq orderProductBoReq = new OrderProductBoReq();
                VipLevelDataBO vipLevelDataBO=SoaConnectionFactory.get(request,ConstantsUri.MEMBER_VIP_LEVEL_ID,null,VipLevelDataBO.class,goodsid);
                VipLevelBO vipLevelBO = vipLevelDataBO.getData();
                orderBoReq.setUserId(obj.getId());
                orderBoReq.setUsername(obj.getUsername());
                //会员不需寄送
                orderBoReq.setIsShipping(2);
                orderBoReq.setIsFreeShipping(2);
                orderBoReq.setTotalPrice(Double.valueOf(vipLevelBO.getPointsPrice()));
                orderBoReq.setTradeMethod("POINTS");
                orderBoReq.setNowVipLevel(obj.getVipLevel());
                orderBoReq.setGiftPoints(0);
                orderProductBoReq.setName(vipLevelBO.getLevel());
                orderProductBoReq.setNum(1);
                orderProductBoReq.setImageUrl(vipLevelBO.getImgUrl());
                //会员不可退换
                orderProductBoReq.setIsReturn("1");
                orderProductBoReq.setIsExchange("1");
                orderProductBoReq.setGoodsId(vipLevelBO.getId());
                orderProductBoReq.setGoodsPrice(Double.valueOf(vipLevelBO.getPointsPrice()));
                orderProductBoReq.setDealPrice(Double.valueOf(vipLevelBO.getPointsPrice()));//实付金额

                orderProductBoReq.setBrowseUrl( SpringCtxHolder.getProperty("ucdomain"));
                orderProductBoReq.setSpecInfo(vipLevelBO.getLevelCode());
                orderProductBoReq.setTradingChannels("HYCZ");
                List<OrderProductBoReq> dataList = new ArrayList<>();
                dataList.add(orderProductBoReq);
                orderBoReq.setOrderProductBOList(dataList);

                OrderDetailResp br=SoaConnectionFactory.post(request,ConstantsUri.MEMBER_ORDER_PLACE,orderBoReq,OrderDetailResp.class,obj.getId());
                mav.addObject("data",br);

            }else{
                BaseResponse br=new BaseResponse("1000","缺少商品编号,下单失败!");
                mav.addObject("data",br);
            }
        }catch (Exception e){
            logger.debug("异常:" + e.getMessage());
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
     * 积分订单支付页面
     * @param orderNo
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/{orderNo}",method = RequestMethod.GET)
    public ModelAndView pointPayPage(@PathVariable(value = "orderNo")String orderNo,HttpServletRequest request,HttpSession session){
        ModelAndView mav = new ModelAndView("member/point_payment");
        UserBo userBo = getInfoService.getUserBo(request);
        try {
            OrderDetailResp orderDetailResp = SoaConnectionFactory.get(request, ConstantsUri.ORDER_DETAIL, null, OrderDetailResp.class, orderNo);
            OrderBO orderBO = orderDetailResp.getData();
            List<OrderProductBO> orderProductBOList =  orderBO.getOrderProductBOList();
            mav.addObject("order",orderBO);
            String tradeNo = null;
            if(orderBO.getTradeBO()!=null) {
                tradeNo = orderBO.getTradeBO().getTradeNo();
            }else{
                List<TradeBO>tradeBOList = orderBO.getTradeBOList();
                if(tradeBOList != null && tradeBOList.size() >0){
                    tradeNo =tradeBOList.get(0).getTradeNo();
                }
            }
            mav.addObject("tradeNo", tradeNo);
            if(orderProductBOList!=null && orderProductBOList.size()>0){
                GoodsBO goodsBO = orderProductBOList.get(0).getGoodsBO();
                mav.addObject("goodsBO",goodsBO);
            }
            mav.addObject("user",userBo);
        }catch (Exception e){
            logger.debug("异常:" + e.getMessage());
            mav= new ModelAndView("soaerror");
            return mav;
        }
        return mav;
    }

    /**
     * 积分支付
     * @param realGoodBO
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/toPay",method = RequestMethod.POST)
    public ModelAndView pay(@RequestBody RealGoodBO realGoodBO,HttpServletRequest request,HttpSession session){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        UserBo userBo = getInfoService.getUserBo(request);
        try {
            OrderPayBO orderPayBO = new OrderPayBO();
            orderPayBO.setTradeNo(realGoodBO.getTradeNo());
            orderPayBO.setUserId(userBo.getId());
            orderPayBO.setIsPay(2);
            orderPayBO.setPayMethod("POINTS");
            orderPayBO.setTradeMethod("POINTS");
            BaseResponse br=SoaConnectionFactory.post(request,ConstantsUri.ORDER_PAY_POINT,orderPayBO,BaseResponse.class);
            mav.addObject("data",br);
        } catch (Exception e) {
            logger.debug("异常:" + e.getMessage());
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
