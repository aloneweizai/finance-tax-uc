package com.abc.controller.member;

import com.abc.application.SpringCtxHolder;
import com.abc.bean.userinfo.*;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.MD5;
import com.abc.controller.BaseController;
import com.abc.controller.userinfo.UserAddrController;
import com.abc.service.IntegralUtil;
import com.abc.service.RedisCode;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.*;
import com.abc.soa.request.order.OrderTradeDataBo;
import com.abc.soa.request.userinfo.OrderBoReq;
import com.abc.soa.request.userinfo.OrderProductBoReq;
import com.abc.soa.request.userinfo.OrderResq;
import com.abc.soa.response.*;
import com.abc.soa.response.CouponBo;
import com.abc.soa.response.userinfo.GoodsBoResp;
import com.abc.soa.response.userinfo.OrderDetailResp;
import com.abc.soa.response.userinfo.UvipPriceResp;
import com.jpay.ext.kit.IpKit;
import com.jpay.ext.kit.StrKit;
import jdk.nashorn.internal.objects.NativeUint16Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by stuy on 2017/8/3.
 */
@Controller
@RequestMapping(value = "/memberOrder")
public class MemberOrderController extends BaseController{

    private final static Logger logger = LoggerFactory.getLogger(MemberOrderController.class);

    /**
     * 订单提交支付宝跳转支付宝收银台
     * @param orderid
     * @param session
     * @param request
     * @param response
     */
    @RequestMapping(value = "/{orderid}", method = RequestMethod.GET)
    public void sh_default(
            @PathVariable(value = "orderid") String orderid,@RequestParam(value = "orderNo")String orderNo,
            HttpSession session, HttpServletRequest request,HttpServletResponse response) {
        try {
            AliPayReq aliPayReq=new AliPayReq();
            OrderResq baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.ORDER_DETAIL,null,OrderResq.class,orderid);
            AliPagePayContent aliPagePayContent=new AliPagePayContent();
            aliPagePayContent.setSubject("ABC财税专家-"+baseResponse.getData().getOrderProductBOList().get(0).getProductBO().getGoodsName());//标题
            aliPagePayContent.setOut_trade_no(baseResponse.getData().getOrderNo());//订单编号
            aliPagePayContent.setTotal_amount(baseResponse.getData().getTotalPrice()+"");//订单金额
            aliPayReq.setPayContent(aliPagePayContent);
            String url2=request.getScheme()+"://"+ request.getServerName()+request.getContextPath();
            log.info("域名地址:"+url2);
            aliPayReq.setReturn_url(url2+"/orderpay/success.html");
            aliPayReq.setNotify_url(url2+"/callback.html");
            PayRes text=SoaConnectionFactory.post(request,ConstantsUri.ALIPAY_PAY,aliPayReq,PayRes.class);
            UserBo obj = getInfoService.getUserBo(request);
            OrderPayBO orderPayBO=new OrderPayBO();
            orderPayBO.setTradeNo(orderid);
            orderPayBO.setUserId(obj.getId());
            orderPayBO.setIsPay(1);
//            orderPayBO.setPayMethod("ALIPAY");
            SoaConnectionFactory.post(request,ConstantsUri.ORDER_STATUS,orderPayBO,BaseResponse.class);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.write(text.getData());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 会员充值提交订单
//     * @param goodsid
//     * @param session
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/details/{goodsid}", method = RequestMethod.GET)
//    public ModelAndView details(@PathVariable(value = "goodsid") String goodsid,
//                               HttpSession session, HttpServletRequest request) {
//        ModelAndView mav = new ModelAndView("member/ispayment");
//        try {
//            UserBo userbo = getInfoService.getUserBo(request);
//            if(isNull(goodsid)){
//                VipLevelDataBO vipLevelDataBO=SoaConnectionFactory.get(request,ConstantsUri.MEMBER_VIP_LEVEL_ID,null,VipLevelDataBO.class,goodsid);
//                mav.addObject("data",vipLevelDataBO.getData());
//                Map map=new HashMap();
//                map.put("status","1");
//                CouponDataListBo couponDataListBo=SoaConnectionFactory.get(request,ConstantsUri.COUPON_USER_LIST,map,CouponDataListBo.class,userbo.getId());
//                mav.addObject("coupon",couponDataListBo.getDataList());
//                if(couponDataListBo.getDataList()!=null&&couponDataListBo.getDataList().size()>0){
//                    CouponCalculateBO couponCalculateBO=new CouponCalculateBO();
//                    couponCalculateBO.setCouponId(couponDataListBo.getDataList().get(0).getCouponId());
//                    couponCalculateBO.setUserId(userbo.getId());
//                    couponCalculateBO.setCategoryId("HYCZ");
//                    couponCalculateBO.setAmount(vipLevelDataBO.getData().getSalePrice());
//                    OrderAmountBo orderAmountBo=SoaConnectionFactory.post(request,ConstantsUri.COUPON_USER_ORDER,couponCalculateBO,OrderAmountBo.class);
//                    if("2000".equals(orderAmountBo.getCode())){
//                        mav.addObject("sfje",orderAmountBo.getData());
//                        mav.addObject("yhje",vipLevelDataBO.getData().getSalePrice()-orderAmountBo.getData());
//                    }else{
//                        mav.addObject("sfje",vipLevelDataBO.getData().getSalePrice());
//                        mav.addObject("yhje",0.00);
//                    }
//                }else{
//                    mav.addObject("sfje",vipLevelDataBO.getData().getSalePrice());
//                    mav.addObject("yhje",0.00);
//                }
//                hydd(userbo,vipLevelDataBO);
//            }else{
//                BaseResponse br=new BaseResponse("1000","缺少商品编号,下单失败!");
//                mav.addObject("data",br);
//            }
//            mav.addObject("user",userbo);
//        } catch (Exception e) {
//            logger.debug("异常:"+e.getMessage());
//            if ( e instanceof SoaException){
//                mav.addObject("soacode","8888");
//                mav.addObject("message","服务器繁忙,请稍后再试...");
//            }else{
//                mav.addObject("soacode","7777");
//                mav.addObject("message","操作失败,请稍后再试...");
//            }
//            return mav;
//        }
//        return mav;
//    }


    /**
     * 会员充值提交订单
     * @param goodsid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/details/{goodsid}", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable(value = "goodsid") String goodsid,
                               HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("pay/pay");
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            if(isNull(goodsid)){
                VipLevelDataBO vipLevelDataBO=SoaConnectionFactory.get(request,ConstantsUri.MEMBER_VIP_LEVEL_ID,null,VipLevelDataBO.class,goodsid);
                Map map=new HashMap();
                map.put("status","1");
                map.put("categoryIds","HYCZ");
                //map.put("validEndTime",System.currentTimeMillis()+"");
                map.put("isDate","2");
                map.put("page","1");
                map.put("size","100");
                CouponDataListBo couponDataListBo=SoaConnectionFactory.get(request,ConstantsUri.COUPON_USER_LIST,map,CouponDataListBo.class,userbo.getId());
                String no="";
                Cookie[] cookies = request.getCookies();
                for(Cookie cookie : cookies){
                    if("no".equals(cookie.getName())){
                        no=cookie.getValue();
                        break;
                    }
                }
                String lsh=hydd(userbo,vipLevelDataBO,couponDataListBo,0.00,no);
                mav.addObject("lsh",lsh);
            }else{
                BaseResponse br=new BaseResponse("1000","缺少商品编号,下单失败!");
                mav.addObject("data",br);
            }
            mav.addObject("user",userbo);
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
     * 积分充值页面
     * @param goodsid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/jf_details/{goodsid}", method = RequestMethod.GET)
    public ModelAndView jf_details(@PathVariable(value = "goodsid") String goodsid,
                                   @RequestParam(value = "jine") String jine,
                                HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("pay/pay");
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            if(isNull(goodsid)){
                Double jines=0.00;
                try {
                    jines=Double.parseDouble(jine);
                } catch (NumberFormatException e) {
                    BaseResponse br=new BaseResponse("1000","金额错误!");
                    mav.addObject("data",br);
                }
                if(jines<=0){
                    BaseResponse br=new BaseResponse("1000","金额错误!");
                    mav.addObject("data",br);
                }
                mav.addObject("jine",jines);
                mav.addObject("goodsid",goodsid);
                mav.addObject("zsjf",new Double(jines*1000).intValue());
                Map map=new HashMap();
                map.put("status","1");
                map.put("categoryIds","JFCZ");
//                map.put("validEndTime",System.currentTimeMillis()+"");
                map.put("isDate","2");
                map.put("page","1");
                map.put("size","100");
                CouponDataListBo couponDataListBo=SoaConnectionFactory.get(request,ConstantsUri.COUPON_USER_LIST,map,CouponDataListBo.class,userbo.getId());
                String no="";
                Cookie[] cookies = request.getCookies();
                for(Cookie cookie : cookies){
                    if("".equals(cookie.getName())){
                        no=cookie.getValue();
                        break;
                    }
                }
                String lsh=jfcz(jine,goodsid,userbo,couponDataListBo,0.00,no);
                if(lsh==null){
                    BaseResponse br=new BaseResponse("1000","商品错误,下单失败!");
                    mav.addObject("data",br);
                }else{
                    mav.addObject("lsh",lsh);
                }
            }else{
                BaseResponse br=new BaseResponse("1000","缺少商品编号,下单失败!");
                mav.addObject("data",br);
            }
            mav.addObject("user",userbo);
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
     * 会员充值提交订单
     * @param goodsid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/places/{goodsid}", method = RequestMethod.POST)
    public ModelAndView xiadan(@PathVariable(value = "goodsid") String goodsid,
                               @RequestParam(value = "tjr", required = false)String tjr,
                                   HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            if(isNull(goodsid)){
                VipLevelDataBO vipLevelDataBO=SoaConnectionFactory.get(request,ConstantsUri.MEMBER_VIP_LEVEL_ID,null,VipLevelDataBO.class,goodsid);
                if(vipLevelDataBO.getData()!=null){
                    NewOrderBo newOrderBo=new NewOrderBo();
                    newOrderBo.setUserId(userbo.getId());
                    newOrderBo.setTradeMethod("RMB");
                    newOrderBo.setUsername(userbo.getUsername());
                    newOrderBo.setNowVipLevel(userbo.getVipLevel());
                    //总金额或者积分
                    newOrderBo.setTotalPrice(vipLevelDataBO.getData().getSalePrice());
                    newOrderBo.setRecommendUser(tjr);
                    newOrderBo.setGiftPoints(vipLevelDataBO.getData().getSendPoints().intValue());
                    List<orderProductBO> orderProductBOList=new ArrayList<orderProductBO>();
                    orderProductBO orderProductBO_=new orderProductBO();
                    orderProductBO_.setNum(1);
                    //商品名称
                    orderProductBO_.setName(vipLevelDataBO.getData().getLevel());
                    //编号
                    orderProductBO_.setGoodsId(vipLevelDataBO.getData().getId());

                    //成交价格
                    orderProductBO_.setDealPrice(vipLevelDataBO.getData().getSalePrice());
                    //商品原价
                    orderProductBO_.setGoodsPrice(vipLevelDataBO.getData().getMarketPrice());
                    //商品浏览地址
                    orderProductBO_.setBrowseUrl( SpringCtxHolder.getProperty("ucdomain"));
                    //交易平台暂时写死，如果需要改到时候读取数据字典
                    orderProductBO_.setTradingChannels("HYCZ");
                    //没有规格，直接用名称代替
                    orderProductBO_.setSpecInfo(vipLevelDataBO.getData().getLevelCode());
                    //商品图片
                    orderProductBO_.setImageUrl(vipLevelDataBO.getData().getImgUrl());
                    orderProductBOList.add(orderProductBO_);

                    newOrderBo.setOrderProductBOList(orderProductBOList);

                    OrderResq br=SoaConnectionFactory.post(request,ConstantsUri.MEMBER_ORDER_PLACE,newOrderBo,OrderResq.class,userbo.getId());
                    mav.addObject("data",br);
                    if("2000".equals(br.getCode())){
                        mav.addObject("orderId",br.getData().getOrderNo());
                    }
                }else{
                    BaseResponse br=new BaseResponse("1000","商品信息错误!");
                    mav.addObject("data",br);
                }
            }else{
                BaseResponse br=new BaseResponse("1000","缺少商品编号,下单失败!");
                mav.addObject("data",br);
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


    public String hydd(UserBo userBo,VipLevelDataBO vipLevelDataBO, CouponDataListBo couponDataListBo,double yhje,String tjr){

        NewOrderBo newOrderBo=new NewOrderBo();
        newOrderBo.setUserId(userBo.getId());
        newOrderBo.setTradeMethod("RMB");
        newOrderBo.setUsername(userBo.getUsername());
        newOrderBo.setNowVipLevel(userBo.getVipLevel());
        //总金额或者积分
        newOrderBo.setTotalPrice(vipLevelDataBO.getData().getSalePrice());
        //newOrderBo.setRecommendUser(tjr);
        newOrderBo.setGiftPoints(vipLevelDataBO.getData().getSendPoints().intValue());
        newOrderBo.setRecommendUser(tjr);

        List<orderProductBO> orderProductBOList=new ArrayList<orderProductBO>();
        orderProductBO orderProductBO_=new orderProductBO();
        orderProductBO_.setNum(1);
        //商品名称
        orderProductBO_.setName(vipLevelDataBO.getData().getLevel());
        //编号
        orderProductBO_.setGoodsId(vipLevelDataBO.getData().getId());

        //成交价格
        orderProductBO_.setDealPrice(vipLevelDataBO.getData().getSalePrice());
        //商品原价
        orderProductBO_.setGoodsPrice(vipLevelDataBO.getData().getMarketPrice());
        //商品浏览地址
        orderProductBO_.setBrowseUrl( SpringCtxHolder.getProperty("imagedomain")+vipLevelDataBO.getData().getImgUrl());
        //交易平台暂时写死，如果需要改到时候读取数据字典
        orderProductBO_.setTradingChannels("HYCZ");
        //没有规格，直接用名称代替
        orderProductBO_.setSpecInfo(vipLevelDataBO.getData().getLevelCode());
        //商品图片
        orderProductBO_.setImageUrl(vipLevelDataBO.getData().getImgUrl());
        orderProductBOList.add(orderProductBO_);
        couponDataListBo=paixuCounpon(couponDataListBo,vipLevelDataBO.getData().getSalePrice());
        newOrderBo.setOrderProductBOList(orderProductBOList);
        if(Double.parseDouble(vipLevelDataBO.getData().getPointsPrice())>0){
            newOrderBo.setPointsPrice(vipLevelDataBO.getData().getPointsPrice());
        }
        String str=System.currentTimeMillis()+getFixLenthString(6);
        RedisOrderBo redisOrderBo=new RedisOrderBo();
        redisOrderBo.setLsh(str);
        redisOrderBo.setNewOrderBo(newOrderBo);
        redisOrderBo.setCouponDataListBo(couponDataListBo);
        redisOrderBo.setYhje(yhje);
        MD5 md5=new MD5(userBo.getId());
        getInfoService.setObject(RedisCode.TIME_5_60,RedisCode.USER_ORDER_SETTLEMENT+":"+md5.compute()+":"+str,redisOrderBo);
        return str;
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
     *商品下单
     */
    public ModelAndView payHy(HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        /*try {
            UserBo userbo = getInfoService.getUserBo(request);
            OrderBoReq orderBoReq = new OrderBoReq();
            OrderProductBoReq orderProductBoReq = new OrderProductBoReq();
            List<ProductBO> productBOList =  new ArrayList<>();
            orderBoReq.setUserId(userbo.getId());
            orderBoReq.setUsername(userbo.getUsername());
            orderBoReq.setIsShipping(2);
            orderBoReq.setIsFreeShipping(2);
            orderBoReq.setTotalPrice(totalPrice);
            orderBoReq.setTradeMethod("RMB");
            orderBoReq.setNowVipLevel(userbo.getVipLevel());
            orderBoReq.setGiftPoints(goodsBO.getGiftPoints());
            orderProductBoReq.setName(goodsBO.getName());
            orderProductBoReq.setNum(1);
            if(productBOList!= null && productBOList.size() > 0) {
                orderProductBoReq.setGoodsId(productBOList.get(0).getGoodsId());
                orderProductBoReq.setGoodsPrice(productBOList.get(0).getSellingPrice());
                orderProductBoReq.setDealPrice(productBOList.get(0).getSellingPrice());
            }
            orderProductBoReq.setBrowseUrl(goodsBO.getImageUrl());
            orderProductBoReq.setSpecInfo(goodsBO.getName());
            orderProductBoReq.setTradingChannels("2");
            orderProductBoReq.setProductId(guigeid);
            orderProductBoReq.setSpecInfo(goodsBO.getName());
            List<OrderProductBoReq> dataList = new ArrayList<>();
            dataList.add(orderProductBoReq);
            orderBoReq.setOrderProductBOList(dataList);
            OrderResq br=SoaConnectionFactory.post(request,ConstantsUri.MEMBER_ORDER_PLACE,orderBoReq,OrderResq.class,userbo.getId());
            mav.addObject("data",br);
            if(br.getData()!=null){
                mav.addObject("orderId",br.getData().getOrderNo());
            }
        }catch (SoaException e){
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }*/
        return mav;
    }


    /**
     * 读取订单跳转支付页面二维码支付
     * @param orderid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/pay/{orderid}", method = RequestMethod.GET)
    public void guigeid(@PathVariable(value = "orderid") String orderid, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 设置响应的类型格式为图片格式
            response.setContentType("image/jpeg");
            //禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            //ORSER_PAY_SELECT_TRADE
            OrderTradeDataBo orderTradeDataBo=SoaConnectionFactory.get(request,ConstantsUri.ORSER_PAY_SELECT_TRADE,null, OrderTradeDataBo.class,orderid);
            //ALIPAY_PAYCODE
            //OrderResq baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.ORDER_ID,null,OrderResq.class,orderid);
            AliCodePay aliCodePay=new AliCodePay();
            aliCodePay.setSubject("ABC财税专家-"+orderTradeDataBo.getData().getTradeName());//标题
            aliCodePay.setOut_trade_no(orderTradeDataBo.getData().getTradeNo());//订单编号
            aliCodePay.setTotal_amount(orderTradeDataBo.getData().getTradeAmount());//订单金额
            aliCodePay.setQrCodeSize(117);
            //String url2=request.getScheme()+"://"+ request.getServerName()+request.getContextPath();
            String url2="https://"+ request.getServerName()+request.getContextPath();
            log.info("域名地址:"+url2);
            aliCodePay.setReturn_url(url2+"/orderpay/success.html");
            aliCodePay.setNotify_url(url2+"/callback.html");
            PayCodeRes payCodeRes=SoaConnectionFactory.post(request,ConstantsUri.ALIPAY_PAYCODE,aliCodePay,PayCodeRes.class);
            if(payCodeRes.getData()!=null){
                String qccodestr=payCodeRes.getData().getQccodeStr();
                if(isNull(qccodestr)){
                    BASE64Decoder base64Decoder=new BASE64Decoder();
                    ServletOutputStream os=null;
                    try {
                        byte[] b= base64Decoder.decodeBuffer(qccodestr);
                        UserBo obj = getInfoService.getUserBo(request);
                        OrderPayBO orderPayBO=new OrderPayBO();
                        orderPayBO.setTradeNo(orderTradeDataBo.getData().getOrderNo());
                        orderPayBO.setUserId(obj.getId());
                        orderPayBO.setIsPay(1);
                        //orderPayBO.setPayMethod("ALIPAY");
                        BaseResponse br=SoaConnectionFactory.post(request,ConstantsUri.ORDER_STATUS,orderPayBO,BaseResponse.class);
                        if("2000".equals(br.getCode())){
                            os = response.getOutputStream();
                            os.write(b);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            if(os!=null){
                                os.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SoaException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取订单跳转支付页面二维码支付
     * @param orderid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/payewm/{orderid}", method = RequestMethod.GET)
    public ModelAndView payewm(@PathVariable(value = "orderid") String orderid,
                               @RequestParam(value = "tjr", required = false) String tjr,
                               @RequestParam(value = "type_pay", required = false) String type_pay,
                               @RequestParam(value = "wxtype", required = false) String wxtype,HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo obj = getInfoService.getUserBo(request);
            if(isNull(tjr)){
                OrderUpdateBO orderUpdateBO=new OrderUpdateBO();
                orderUpdateBO.setUserId(obj.getId());
                orderUpdateBO.setRecommendUser(tjr);
                orderUpdateBO.setOrderNo(orderid);
                BaseResponse brs=SoaConnectionFactory.post(request,ConstantsUri.ORDER_TJR_UPDATE,orderUpdateBO,BaseResponse.class,obj.getId());
            }
            OrderTradeDataBo orderTradeDataBo=SoaConnectionFactory.get(request,ConstantsUri.ORSER_PAY_SELECT_TRADE,null, OrderTradeDataBo.class,orderid);
            OrderDetailResp orderDetailResp = SoaConnectionFactory.getRestful(request, ConstantsUri.ORDER_WEB_ID, null, OrderDetailResp.class,orderTradeDataBo.getData().getOrderNo());
            if(obj.getId().equals(orderDetailResp.getData().getUserId())){
                //ALIPAY_PAYCODE
                //OrderResq baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.ORDER_ID,null,OrderResq.class,orderid);
                if("zfb".equals(type_pay)){
                    zhiFuBao(request,orderTradeDataBo,mav,obj);
                }else if("wx".equals(type_pay)){
                    weiXin(request,orderTradeDataBo,mav,obj,orderDetailResp,wxtype);
                }else{
                    zhiFuBao(request,orderTradeDataBo,mav,obj);
                }

            }else{
                BaseResponse br=new BaseResponse("0000","订单已失效!");
                mav.addObject("data",br);
                return mav;
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

    public void zhiFuBao(HttpServletRequest request, OrderTradeDataBo orderTradeDataBo,ModelAndView mav,UserBo obj) throws SoaException{
        AliCodePay aliCodePay=new AliCodePay();
        aliCodePay.setSubject("ABC财税专家-"+orderTradeDataBo.getData().getTradeName());//标题
        aliCodePay.setOut_trade_no(orderTradeDataBo.getData().getTradeNo());//订单编号
        aliCodePay.setTotal_amount(orderTradeDataBo.getData().getTradeAmount());//订单金额
        aliCodePay.setQrCodeSize(117);
        // String url2=request.getScheme()+"://"+ request.getServerName()+request.getContextPath();
        String url2="https://"+ request.getServerName()+request.getContextPath();
        log.info("域名地址:"+url2);
        aliCodePay.setReturn_url(url2+"/orderpay/success.html");
        aliCodePay.setNotify_url(url2+"/callback.html");
        PayCodeRes payCodeRes=SoaConnectionFactory.post(request,ConstantsUri.ALIPAY_PAYCODE,aliCodePay,PayCodeRes.class);
        if(payCodeRes.getData()!=null){
            String qccodestr=payCodeRes.getData().getQccodeStr();
            mav.addObject("ewm",qccodestr);
            if(isNull(qccodestr)){
                try {
                    OrderPayBO orderPayBO=new OrderPayBO();
                    orderPayBO.setTradeNo(orderTradeDataBo.getData().getOrderNo());
                    orderPayBO.setUserId(obj.getId());
                    orderPayBO.setIsPay(1);
                    orderPayBO.setPayMethod("ALIPAY");
                    BaseResponse br=SoaConnectionFactory.post(request,ConstantsUri.ORDER_STATUS,orderPayBO,BaseResponse.class);
                    if("2000".equals(br.getCode())){
                        mav.addObject("data",br);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void weiXin(HttpServletRequest request, OrderTradeDataBo orderTradeDataBo,ModelAndView mav,UserBo obj,OrderDetailResp orderDetailResp,String wxtype) throws SoaException{

        WeixinPay weixinPay=new WeixinPay();
        weixinPay.setBody("ABC财税专家-"+orderTradeDataBo.getData().getTradeName());
        Double jine = Double.parseDouble(orderTradeDataBo.getData().getTradeAmount())*100;
        weixinPay.setTotal_fee(jine.intValue());
       // weixinPay.setAttach("http://www.baidu.com");
        weixinPay.setProduct_id(orderDetailResp.getData().getGoodsId());
        weixinPay.setOut_trade_no(orderTradeDataBo.getData().getTradeNo());
        if(wxtype!=null&&!"".equals(wxtype)){
            weixinPay.setTrade_type("MWEB");
        }else{
            weixinPay.setTrade_type("NATIVE");
        }
        String ip = IpKit.getRealIp(request);
        if (StrKit.isBlank(ip)) {
            ip = "127.0.0.1";
        }
        weixinPay.setSpbill_create_ip(ip);
        String ucdomain= SpringCtxHolder.getProperty("ucdomain");
        weixinPay.setAttach(ucdomain+"/weixin_callback.html");
        WeixinBo weixinBo=SoaConnectionFactory.post(request,ConstantsUri.WEIXIN_PALCODE,weixinPay,WeixinBo.class);
        if(weixinBo.getData()!=null){
            String qccodestr=weixinBo.getData().getCode_img();
            mav.addObject("ewm",qccodestr);
            WeiXinSoaBo weixin = weixinBo.getData();
            String url=weixin.getMweb_url();
            url=url+"&redirect_url="+ SpringCtxHolder.getProperty("ucdomain")+"/order_settlement/integral_success/RMB/"+orderTradeDataBo.getData().getTradeNo();
            weixin.setTimeStamp(System.currentTimeMillis());
            mav.addObject("datas",weixin);
            if(isNull(qccodestr)){
                try {
                    OrderPayBO orderPayBO=new OrderPayBO();
                    orderPayBO.setTradeNo(orderTradeDataBo.getData().getOrderNo());
                    orderPayBO.setUserId(obj.getId());
                    orderPayBO.setIsPay(1);
                    orderPayBO.setPayMethod("WEIXIN");
                    BaseResponse br=SoaConnectionFactory.post(request,ConstantsUri.ORDER_STATUS,orderPayBO,BaseResponse.class);
                    if("2000".equals(br.getCode())){
                        mav.addObject("data",br);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }



    /**
     * 读取订单跳转支付页面
     * @param orderid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/{orderid}", method = RequestMethod.POST)
    public ModelAndView orderid(@PathVariable(value = "orderid") String orderid,HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse br=new BaseResponse("2000","");
        mav.addObject("data",br);
        mav.addObject("zhifubao","zhifubao");
        mav.addObject("weixin","weixin");
        return mav;
    }


    /**
     * 积分提交订单
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/integral_order.html", method = RequestMethod.POST)
    public ModelAndView xiadan(@RequestParam(value = "jine", required = false) String jine,
                               @RequestParam(value = "guige", required = false) String guige,
                               HttpServletResponse response,
                               HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            if(isNull(jine)&&isNull(guige)){
                try{
                    Double jines=Double.parseDouble(jine);
                    IntegralUtil integralUtil=new IntegralUtil();
                    List<IntegralBo> list = integralUtil.list;
                    IntegralBo integralBobool=null;
                    for(IntegralBo integralBo : list){
                        if(guige.equals(integralBo.getIntegralId())){
                            integralBobool=integralBo;
                        }
                    }
                    if(integralBobool==null){
                        BaseResponse br=new BaseResponse("1000","商品信息错误!");
                        mav.addObject("data",br);
                    }else{
                        NewOrderBo newOrderBo=new NewOrderBo();
                        newOrderBo.setUserId(userbo.getId());
                        newOrderBo.setTradeMethod(integralUtil.METHOD);
                        newOrderBo.setUsername(userbo.getUsername());
                        newOrderBo.setNowVipLevel(userbo.getVipLevel());
                        int giftPoints = 0;
                        if("JF0".equals(guige)){
                            //总金额或者积分
                            newOrderBo.setTotalPrice(jines);
                            giftPoints = (int)(jines * 1000);
                        }else {
                            //总金额或者积分
                            newOrderBo.setTotalPrice(integralBobool.getFee());
                            giftPoints = (int)(integralBobool.getFee() * 1000);
                        }

                        newOrderBo.setGiftPoints(giftPoints);
                        List<orderProductBO> orderProductBOList=new ArrayList<orderProductBO>();
                        orderProductBO orderProductBO_=new orderProductBO();
                        orderProductBO_.setNum(1);
                        //商品名称
                        orderProductBO_.setName(integralUtil.TITLE);
                        //编号
                        orderProductBO_.setGoodsId(integralUtil.ID);

                        if("JF0".equals(guige)){
                            //成交价格
                            orderProductBO_.setDealPrice(jines);
                            //商品原价
                            orderProductBO_.setGoodsPrice(jines);
                        }else {
                            //成交价格
                            orderProductBO_.setDealPrice(integralBobool.getFee());
                            //商品原价
                            orderProductBO_.setGoodsPrice(integralBobool.getFee());
                        }
                        //商品浏览地址
                        orderProductBO_.setBrowseUrl( SpringCtxHolder.getProperty("ucdomain"));
                        //交易平台暂时写死，如果需要改到时候读取数据字典
                        orderProductBO_.setTradingChannels(integralUtil.JYQD);
                        //没有规格，直接用名称代替
                        orderProductBO_.setSpecInfo(integralUtil.TITLE);
                        orderProductBOList.add(orderProductBO_);

                        newOrderBo.setOrderProductBOList(orderProductBOList);

                        OrderResq br=SoaConnectionFactory.post(request,ConstantsUri.MEMBER_ORDER_PLACE,newOrderBo,OrderResq.class,userbo.getId());
                        mav.addObject("data",br);
                        if("2000".equals(br.getCode())){
                            mav.addObject("orderId",br.getData().getOrderNo());
                        }
                    }
                }catch (Exception e){
                    BaseResponse br=new BaseResponse("1000","金额错误，下单失败!");
                    mav.addObject("data",br);
                }
            }else{
                BaseResponse br=new BaseResponse("1000","缺少参数,下单失败!");
                mav.addObject("data",br);
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


    public String jfcz(String jine,String guige,UserBo userbo, CouponDataListBo couponDataListBo,double yhje,String tjr){
        try{
            Double jines=Double.parseDouble(jine);
            IntegralUtil integralUtil=new IntegralUtil();
            List<IntegralBo> list = integralUtil.list;
            IntegralBo integralBobool=null;
            for(IntegralBo integralBo : list){
                if(guige.equals(integralBo.getIntegralId())){
                    integralBobool=integralBo;
                }
            }
            if(integralBobool==null){
                return null;
            }else{
                NewOrderBo newOrderBo=new NewOrderBo();
                newOrderBo.setUserId(userbo.getId());
                newOrderBo.setTradeMethod(integralUtil.METHOD);
                newOrderBo.setUsername(userbo.getUsername());
                newOrderBo.setNowVipLevel(userbo.getVipLevel());
                newOrderBo.setRecommendUser(tjr);
                int giftPoints = 0;
                if("JF0".equals(guige)){
                    //总金额或者积分
                    newOrderBo.setTotalPrice(jines);
                    giftPoints = (int)(jines * 1000);
                }else {
                    //总金额或者积分
                    newOrderBo.setTotalPrice(integralBobool.getFee());
                    giftPoints = (int)(integralBobool.getFee() * 1000);
                }

                newOrderBo.setGiftPoints(giftPoints);
                List<orderProductBO> orderProductBOList=new ArrayList<orderProductBO>();
                orderProductBO orderProductBO_=new orderProductBO();
                orderProductBO_.setNum(1);
                //商品名称
                orderProductBO_.setName(integralUtil.TITLE);
                //编号
                orderProductBO_.setGoodsId(integralUtil.ID);

                if("JF0".equals(guige)){
                    //成交价格
                    orderProductBO_.setDealPrice(jines);
                    //商品原价
                    orderProductBO_.setGoodsPrice(jines);
                }else {
                    //成交价格
                    orderProductBO_.setDealPrice(integralBobool.getFee());
                    //商品原价
                    orderProductBO_.setGoodsPrice(integralBobool.getFee());
                }
                //商品浏览地址
                orderProductBO_.setBrowseUrl( SpringCtxHolder.getProperty("ucdomain"));
                //交易平台暂时写死，如果需要改到时候读取数据字典
                orderProductBO_.setTradingChannels(integralUtil.JYQD);
                //没有规格，直接用名称代替
                orderProductBO_.setSpecInfo(integralUtil.TITLE);
                orderProductBOList.add(orderProductBO_);
                couponDataListBo=paixuCounpon(couponDataListBo,jines);
                newOrderBo.setOrderProductBOList(orderProductBOList);
                String str=System.currentTimeMillis()+getFixLenthString(6);
                RedisOrderBo redisOrderBo=new RedisOrderBo();
                redisOrderBo.setLsh(str);
                redisOrderBo.setNewOrderBo(newOrderBo);
                redisOrderBo.setCouponDataListBo(couponDataListBo);
                redisOrderBo.setYhje(yhje);
                MD5 md5=new MD5(userbo.getId());
                getInfoService.setObject(RedisCode.TIME_5_60,RedisCode.USER_ORDER_SETTLEMENT+":"+md5.compute()+":"+str,redisOrderBo);
                return str;

            }
        }catch (Exception e){
            return null;
        }
    }





    public String jssp(UserBo userbo, GoodsBoResp goodsBoResp){
        try{
            if(goodsBoResp!=null){
                NewOrderBo newOrderBo=new NewOrderBo();
                newOrderBo.setUserId(userbo.getId());
                newOrderBo.setTradeMethod("RMB");
                newOrderBo.setUsername(userbo.getUsername());
                newOrderBo.setNowVipLevel(userbo.getVipLevel());
                Double sellingPrice = goodsBoResp.getData().getProductBOList().get(0).getSellingPrice();
                Double marketPrice = goodsBoResp.getData().getProductBOList().get(0).getMarketPrice();
                newOrderBo.setTotalPrice(sellingPrice);
                newOrderBo.setGiftPoints(goodsBoResp.getData().getGiftPoints());
                List<orderProductBO> orderProductBOList=new ArrayList<orderProductBO>();
                orderProductBO orderProductBO_=new orderProductBO();
                orderProductBO_.setNum(1);
                //商品名称
                orderProductBO_.setName(goodsBoResp.getData().getName());
                //编号
                orderProductBO_.setGoodsId(goodsBoResp.getData().getId());

                orderProductBO_.setDealPrice(sellingPrice);
                //商品原价
                orderProductBO_.setGoodsPrice(marketPrice);
                //商品浏览地址
                orderProductBO_.setBrowseUrl( SpringCtxHolder.getProperty("imagedomain")+goodsBoResp.getData().getImageUrl());
                //交易平台暂时写死，如果需要改到时候读取数据字典
                orderProductBO_.setTradingChannels("UCSC");
                //没有规格，直接用名称代替
                orderProductBO_.setSpecInfo(goodsBoResp.getData().getName());
                orderProductBO_.setProductId(goodsBoResp.getData().getProductBOList().get(0).getId());
                orderProductBOList.add(orderProductBO_);
                newOrderBo.setIsShipping(1);

                newOrderBo.setOrderProductBOList(orderProductBOList);

                String str=System.currentTimeMillis()+getFixLenthString(6);
                RedisOrderBo redisOrderBo=new RedisOrderBo();
                redisOrderBo.setLsh(str);
                redisOrderBo.setNewOrderBo(newOrderBo);
                //redisOrderBo.setCouponDataListBo(couponDataListBo);
                redisOrderBo.setYhje(0.00);
                MD5 md5=new MD5(userbo.getId());
                getInfoService.setObject(RedisCode.TIME_5_60,RedisCode.USER_ORDER_SETTLEMENT+":"+md5.compute()+":"+str,redisOrderBo);
                return str;
            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 寄送商品购买
     * @param goodsid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/goods_js/{goodsid}", method = RequestMethod.GET)
    public ModelAndView goods_js(@PathVariable(value = "goodsid") String goodsid,
                                   HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("pay/pay");
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            if(isNull(goodsid)){
                GoodsBoResp goodsBoResp = SoaConnectionFactory.get(request, ConstantsUri.GOODS_ID, null, GoodsBoResp.class, goodsid);
                String lsh=jssp(userbo,goodsBoResp);
                if(lsh==null){
                    BaseResponse br=new BaseResponse("1000","商品错误,下单失败!");
                    mav.addObject("data",br);
                }else{
                    mav.addObject("lsh",lsh);
                }
            }else{
                BaseResponse br=new BaseResponse("1000","缺少商品编号,下单失败!");
                mav.addObject("data",br);
            }
            mav.addObject("user",userbo);
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

    private CouponDataListBo paixuCounpon(CouponDataListBo couponDataListBo,Double jine){
        List<CouponBo> list = couponDataListBo.getDataList();
        if(list==null){
            return couponDataListBo;
        }
        Map<String,CouponBo> map=new HashMap<String,CouponBo>();
        Map<String,CouponBo> map1=new HashMap<String,CouponBo>();
        for(CouponBo couponBo : list){
            if("MANJIAN".equals(couponBo.getCouponType())){
                if(jine>=couponBo.getParam1()){
                    map1.put(couponBo.getId(),couponBo);
                }else{
                    map.put(couponBo.getId(),couponBo);
                }
            }else if("LIJIAN".equals(couponBo.getCouponType())){
                if(jine>couponBo.getParam2()){
                    map1.put(couponBo.getId(),couponBo);
                }else{
                    map.put(couponBo.getId(),couponBo);
                }
            }else if("ZHEKOU".equals(couponBo.getCouponType())){
                if(couponBo.getParam1()!=null){
                    if(jine>=couponBo.getParam2()){
                        map1.put(couponBo.getId(),couponBo);
                    }else{
                        map.put(couponBo.getId(),couponBo);
                    }
                }else{
                    map.put(couponBo.getId(),couponBo);
                }
            }
        }
        List<CouponBo> lists=new ArrayList<CouponBo>();
        for (String key : map1.keySet()) {
            lists.add(map1.get(key));
        }
        for (String key : map.keySet()) {
            lists.add(map.get(key));
        }

        couponDataListBo.setDataList(lists);
        return couponDataListBo;
    }

}
