package com.abc.controller.pay;

import com.abc.bean.userinfo.OrderBO;
import com.abc.bean.userinfo.OrderProductBO;
import com.abc.bean.userinfo.TradeBO;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.MD5;
import com.abc.controller.BaseController;
import com.abc.service.RedisCode;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.*;
import com.abc.soa.request.order.OrderTradeDataBo;
import com.abc.soa.request.userinfo.AddressBoReq;
import com.abc.soa.request.userinfo.OrderBoReq;
import com.abc.soa.request.userinfo.OrderResq;
import com.abc.soa.response.*;
import com.abc.soa.response.userinfo.OrderDetailResp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuy on 2018/1/23.
 */
@Controller
@RequestMapping(value = "/order_settlement")
public class OrderSettlementController extends BaseController {


    /**
     * 跳转订单结算页面
     * @param lsh
     * @param request
     * @param response
     */
    @RequestMapping(value = "/{lsh}", method = RequestMethod.GET)
    public ModelAndView sh_default(@PathVariable(value = "lsh") String lsh, HttpServletRequest request, HttpServletResponse response) {
        UserBo userbo = getInfoService.getUserBo(request);
        MD5 md5=new MD5(userbo.getId());
        RedisOrderBo redisOrderBo=getInfoService.getObject(RedisCode.USER_ORDER_SETTLEMENT+":"+md5.compute()+":"+lsh, RedisOrderBo.class);
        if(redisOrderBo==null){
            ModelAndView mav= new ModelAndView("pay/soaerror");
            return mav;
        }
        List<CurriculumGiftBo> gifbolist = redisOrderBo.getCurriculumGiftBoList();
        if(gifbolist!=null&&gifbolist.size()>0){
            for(CurriculumGiftBo curriculumGiftBo : gifbolist){
                if("COUPON".equals(curriculumGiftBo.getOperType())){
                    try {
                        CouponActivityDataBo couponActivityDataBo=SoaConnectionFactory.get(request,ConstantsUri.COUPON_ACTIVITIES_ID,null,CouponActivityDataBo.class,curriculumGiftBo.getOperValue());
                        curriculumGiftBo.setMsg(couponActivityDataBo.getData().getActivityName());
                    } catch (SoaException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ModelAndView mav= new ModelAndView("pay/settlement");
        mav.addObject("data",redisOrderBo);
        String url="userinfo/order.php";
        String urls = new BASE64Encoder().encodeBuffer(url.getBytes());
        mav.addObject("url",urls);
        try {
            //添加地址
            AddressRes addressRes=SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS,null, AddressRes.class,userbo.getId());
            List<AddressBo> data = addressRes.getDataList();
            for (AddressBo addressBo : data){
                DizhiIDBo dizhiIDBo=new DizhiIDBo();
                boolean bool=false;
                if(isNull(addressBo.getProvince())){
                    bool=true;
                    dizhiIDBo.setProvinceId(addressBo.getProvince());
                }
                if(isNull(addressBo.getCity())){
                    bool=true;
                    dizhiIDBo.setCityId(addressBo.getCity());
                }
                if(isNull(addressBo.getArea())){
                    bool=true;
                    dizhiIDBo.setAreaId(addressBo.getArea());
                }
                if(bool){
                    DizhiNameBo dizhiNameBo=SoaConnectionFactory.get(request,ConstantsUri.DIQU_QUERY_ID,dizhiIDBo,DizhiNameBo.class);
                    addressBo.setDizhi(dizhiNameBo);
                }
            }
            mav.addObject("addresslist",addressRes.getDataList());
        } catch (SoaException e) {
            mav= new ModelAndView("soaerror");
            return mav;
        }
        return mav;
    }

    /**
     * 跳转订单结算页面
     * @param yhjid
     * @param request
     * @param response
     */
    @RequestMapping(value = "/yhj/{yhjid}/{lsh}", method = RequestMethod.POST)
    public ModelAndView yhj(@PathVariable(value = "yhjid") String yhjid,@PathVariable(value = "lsh") String lsh, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        UserBo userbo = getInfoService.getUserBo(request);
        if(userbo==null){
            BaseResponse br=new BaseResponse("1000","用户已失效！");
            mav.addObject("data",br);
            return mav;
        }
        MD5 md5=new MD5(userbo.getId());
        RedisOrderBo redisOrderBo=getInfoService.getObject(RedisCode.USER_ORDER_SETTLEMENT+":"+md5.compute()+":"+lsh, RedisOrderBo.class);
        if(redisOrderBo!=null){
            CouponCalculateBO couponCalculateBO=new CouponCalculateBO();
            couponCalculateBO.setUserId(userbo.getId());
            couponCalculateBO.setAmount(redisOrderBo.getNewOrderBo().getTotalPrice());
            couponCalculateBO.setCategoryId(redisOrderBo.getNewOrderBo().getOrderProductBOList().get(0).getTradingChannels());
            couponCalculateBO.setUseCouponId(yhjid);
            try {
                OrderAmountBo orderAmountBo=SoaConnectionFactory.post(request,ConstantsUri.COUPON_USER_ORDER,couponCalculateBO, OrderAmountBo.class);
                mav.addObject("data",orderAmountBo);
                mav.addObject("yfje",orderAmountBo.getData());
                if("2000".equals(orderAmountBo.getCode())){
                    mav.addObject("yhje",redisOrderBo.getNewOrderBo().getTotalPrice()-orderAmountBo.getData());
                }else{
                    mav.addObject("yhje",0.00);
                }
            } catch (SoaException e) {
                e.printStackTrace();
            }
        }else{
            BaseResponse br=new BaseResponse("1000","订单已过期,请重新下单！");
            mav.addObject("data",br);
        }
        return mav;
    }


    /**
     * 提交订单到SOA
     * @param request
     * @param response
     */
    @PostMapping(value = "/order/{lsh}")
    public ModelAndView order(@PathVariable(value = "lsh") String lsh, @RequestBody OrderRequestSoaBo orderRequestSoaBo, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        UserBo userbo = getInfoService.getUserBo(request);
        if(userbo==null){
            BaseResponse br=new BaseResponse("1000","用户已失效！");
            mav.addObject("data",br);
            return mav;
        }
        MD5 md5=new MD5(userbo.getId());
        RedisOrderBo redisOrderBo=getInfoService.getObject(RedisCode.USER_ORDER_SETTLEMENT+":"+md5.compute()+":"+orderRequestSoaBo.getLshid(), RedisOrderBo.class);
        if(redisOrderBo!=null){
            if(!redisOrderBo.getBool()){
                NewOrderBo newOrderbo = redisOrderBo.getNewOrderBo();
                if(orderRequestSoaBo.getBz()!=null&&!"".equals(orderRequestSoaBo.getBz())){
                    newOrderbo.setRemark(orderRequestSoaBo.getBz());
                }
                if(isNull(orderRequestSoaBo.getTjr())){
                    newOrderbo.setRecommendUser(orderRequestSoaBo.getTjr().trim());
                }
                if(isNull(orderRequestSoaBo.getYyzs())){
                    String[] strs = orderRequestSoaBo.getYyzs().split(",");
                    List<CurriculumGiftBo> curriculumGiftBoList=new ArrayList<CurriculumGiftBo>();
                    for(String str : strs){
                        CurriculumGiftBo curriculumGiftBo=new CurriculumGiftBo();
                        curriculumGiftBo.setId(str);
                        curriculumGiftBoList.add(curriculumGiftBo);
                    }
                    if(curriculumGiftBoList!=null&&curriculumGiftBoList.size()>0){
                        try {
                            BaseIsOptionalBo br=SoaConnectionFactory.post(request,ConstantsUri.BB_IS_OPTIONAL,curriculumGiftBoList,BaseIsOptionalBo.class);
                            if(br.getData()){
                                newOrderbo.setOrderGiftBOList(curriculumGiftBoList);
                            }else{
                                BaseResponse brs=new BaseResponse("1000","赠送选择不符合要求");
                                mav.addObject("data",brs);
                                return mav;
                            }
                        } catch (SoaException e) {
                            BaseResponse br=new BaseResponse("1000","赠送选择不符合要求!");
                            mav.addObject("data",br);
                            return mav;
                        }
                    }
                }
                if(orderRequestSoaBo.getAddreId()!=null&&!"".equals(orderRequestSoaBo.getAddreId())){
                    try {
                        AddressIDRes addressIDRes=SoaConnectionFactory.get(request,ConstantsUri.USER_ID_ADDRESS,null,AddressIDRes.class,userbo.getId(),orderRequestSoaBo.getAddreId());
                        newOrderbo.setConsignee(addressIDRes.getData().getName());
                        newOrderbo.setContactNumber(addressIDRes.getData().getPhone());
                        DizhiIDBo dizhiIDBo=new DizhiIDBo();
                        boolean bool=false;
                        if(isNull(addressIDRes.getData().getProvince())){
                            bool=true;
                            dizhiIDBo.setProvinceId(addressIDRes.getData().getProvince());
                        }
                        if(isNull(addressIDRes.getData().getCity())){
                            bool=true;
                            dizhiIDBo.setCityId(addressIDRes.getData().getCity());
                        }
                        if(isNull(addressIDRes.getData().getArea())){
                            bool=true;
                            dizhiIDBo.setAreaId(addressIDRes.getData().getArea());
                        }
                        if(bool){
                            DizhiNameBo dizhiNameBo=SoaConnectionFactory.get(request,ConstantsUri.DIQU_QUERY_ID,dizhiIDBo,DizhiNameBo.class);
                            newOrderbo.setShippingAddress(dizhiNameBo.getProvince().getProvince()+dizhiNameBo.getCity().getCity()+dizhiNameBo.getArea().getArea()+addressIDRes.getData().getDetail());
                        }
                    } catch (SoaException e) {
                        BaseResponse br=new BaseResponse("1000","地址错误!");
                        mav.addObject("data",br);
                    }
                }

                try {
                    if(orderRequestSoaBo.getYhjid()!=null&&!"".equals(orderRequestSoaBo.getYhjid())){
                        newOrderbo.setUseCouponId(orderRequestSoaBo.getYhjid());
                    }
                    if("xianjin".equals(orderRequestSoaBo.getType())){
                        newOrderbo.setTradeMethod("RMB");
                    }else if("jifen".equals(orderRequestSoaBo.getType())){
                        double jiage = Double.parseDouble(newOrderbo.getPointsPrice());
                        BigInteger points = userbo.getPoints();
                        if(Double.parseDouble(points.toString())>jiage){
                            newOrderbo.setTradeMethod("POINTS");
                            newOrderbo.setTotalPrice(Double.parseDouble(newOrderbo.getPointsPrice()));
                            newOrderbo.setGiftPoints(0);
                            newOrderbo.getOrderProductBOList().get(0).setDealPrice(Double.parseDouble(newOrderbo.getPointsPrice()));
                        }else{
                            BaseResponse br=new BaseResponse("1000","积分不够无法下单!");
                            mav.addObject("data",br);
                            return mav;
                        }
                    }else{
                        BaseResponse br=new BaseResponse("1000","非法的交易类型!");
                        mav.addObject("data",br);
                        return mav;
                    }
                    OrderResq br=SoaConnectionFactory.post(request,ConstantsUri.MEMBER_ORDER_PLACE,newOrderbo,OrderResq.class,userbo.getId());
                    mav.addObject("data",br);
                    if("2000".equals(br.getCode())){
                        redisOrderBo.setNewOrderBo(newOrderbo);
                        redisOrderBo.setBool(true);
                        getInfoService.setObject(RedisCode.TIME_5_60,RedisCode.USER_ORDER_SETTLEMENT+":"+md5.compute()+":"+orderRequestSoaBo.getLshid(),redisOrderBo);
                        mav.addObject("orderId",br.getData().getOrderNo());
                        mav.addObject("lsh",orderRequestSoaBo.getLshid());
                    }
                } catch (SoaException e) {
                    e.printStackTrace();
                }
            }else{
                BaseResponse br=new BaseResponse("1000","订单已经下单完成不能重复下单!");
                mav.addObject("data",br);
            }
        }else{
            BaseResponse br=new BaseResponse("1000","订单已过期，请重新下单!");
            mav.addObject("data",br);
        }

        return mav;
    }

    /**
     * 订单支付页面
     * @param request
     * @param response
     */
    @RequestMapping(value = "/order_pay/{orderid}", method = RequestMethod.GET)
    public ModelAndView order_pay(@PathVariable(value = "orderid") String orderid, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("pay/order_pay");
        UserBo userbo = getInfoService.getUserBo(request);
        try {
            OrderDetailResp orderDetailResp = SoaConnectionFactory.getRestful(request, ConstantsUri.ORDER_WEB_ID, null, OrderDetailResp.class,orderid);
            if(userbo.getId().equals(orderDetailResp.getData().getUserId())){
                if(orderDetailResp.getData()!=null){
                    List<OrderProductBO> list = orderDetailResp.getData().getOrderProductBOList();
                    StringBuffer sb=new StringBuffer();
                    for(OrderProductBO orderProductBO : list){
                        sb.append(orderProductBO.getName()).append(",");
                    }
                    mav.addObject("name",sb.toString());
                    mav.addObject("data",orderDetailResp.getData());
                    //mav.addObject("lsh",lsh);
                    String url="userinfo/order.php";
                    String urls = new BASE64Encoder().encodeBuffer(url.getBytes());
                    mav.addObject("url",urls);
                }
            }else{
                mav= new ModelAndView("pay/soaerror");
                return mav;
            }
        } catch (SoaException e) {
            mav= new ModelAndView("soaerror");
            return mav;
        }

        return mav;
    }


    /**
     * 跳转订单结算页面
     * @param lsh
     * @param request
     * @param response
     */
    @RequestMapping(value = "/integral/{lsh}", method = RequestMethod.GET)
    public ModelAndView integral_default(@PathVariable(value = "lsh") String lsh, HttpServletRequest request, HttpServletResponse response) {

        UserBo userbo = getInfoService.getUserBo(request);
        MD5 md5=new MD5(userbo.getId());
        RedisOrderBo redisOrderBo=getInfoService.getObject(RedisCode.USER_ORDER_SETTLEMENT+":"+md5.compute()+":"+lsh, RedisOrderBo.class);
        if(redisOrderBo==null){
            ModelAndView mav= new ModelAndView("pay/soaerror");
            return mav;
        }
        ModelAndView mav= new ModelAndView("pay/integral_settlement");
        mav.addObject("data",redisOrderBo);
        String url="userinfo/order.php";
        String urls = new BASE64Encoder().encodeBuffer(url.getBytes());
        mav.addObject("url",urls);
        try {
            //添加地址
            AddressRes addressRes=SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS,null, AddressRes.class,userbo.getId());
            List<AddressBo> data = addressRes.getDataList();
            for (AddressBo addressBo : data){
                DizhiIDBo dizhiIDBo=new DizhiIDBo();
                boolean bool=false;
                if(isNull(addressBo.getProvince())){
                    bool=true;
                    dizhiIDBo.setProvinceId(addressBo.getProvince());
                }
                if(isNull(addressBo.getCity())){
                    bool=true;
                    dizhiIDBo.setCityId(addressBo.getCity());
                }
                if(isNull(addressBo.getArea())){
                    bool=true;
                    dizhiIDBo.setAreaId(addressBo.getArea());
                }
                if(bool){
                    DizhiNameBo dizhiNameBo=SoaConnectionFactory.get(request,ConstantsUri.DIQU_QUERY_ID,dizhiIDBo,DizhiNameBo.class);
                    addressBo.setDizhi(dizhiNameBo);
                }
            }
            mav.addObject("addresslist",addressRes.getDataList());
        } catch (SoaException e) {
            mav= new ModelAndView("soaerror");
            return mav;
        }


        return mav;
    }



    /**
     * 提交订单到SOA
     * @param request
     * @param response
     */
    @PostMapping(value = "/integral_lsh/{lsh}")
    public ModelAndView integral_lsh(@PathVariable(value = "lsh") String lsh, @RequestBody OrderRequestSoaBo orderRequestSoaBo, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        UserBo userbo = getInfoService.getUserBo(request);
        if(userbo==null){
            BaseResponse br=new BaseResponse("1000","用户已失效！");
            mav.addObject("data",br);
            return mav;
        }
        MD5 md5=new MD5(userbo.getId());
        RedisOrderBo redisOrderBo=getInfoService.getObject(RedisCode.USER_ORDER_SETTLEMENT+":"+md5.compute()+":"+orderRequestSoaBo.getLshid(), RedisOrderBo.class);
        if(redisOrderBo!=null){
            if(!redisOrderBo.getBool()){
                OrderBoReq orderboreq = redisOrderBo.getOrderBoReq();
                if(orderRequestSoaBo.getBz()!=null&&!"".equals(orderRequestSoaBo.getBz())){
                    orderboreq.setRemark(orderRequestSoaBo.getBz());
                }
                try {
                    //添加地址
                    if(orderRequestSoaBo.getAddreId() != null) {
                        AddressBoReq addressBoReq = new AddressBoReq();
                        addressBoReq.setAddressId(orderRequestSoaBo.getAddreId());
                        AddressRes addressRes = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS, addressBoReq, AddressRes.class, userbo.getId());
                        List<AddressBo> addressBoList = addressRes.getDataList();
                        for(AddressBo addressBo : addressBoList){
                            if(addressBo.getId().equals(orderRequestSoaBo.getAddreId())){
                                orderboreq.setConsignee(addressBo.getName());
                                orderboreq.setContactNumber(addressBo.getPhone());
                                String shipAddress = addressBo.getProvinceName() + addressBo.getCityName()+ addressBo.getAreaName() + addressBo.getDetail();
                                orderboreq.setShippingAddress(shipAddress);
                            }
                        }
                    }

                    BigInteger points = userbo.getPoints();
                    Double price = orderboreq.getTotalPrice();
                    if(price>Double.parseDouble(points+"")){
                        BaseResponse br=new BaseResponse("1000","下单失败，积分不足!");
                        mav.addObject("data",br);
                        return mav;
                    }

                    OrderResq brs=SoaConnectionFactory.post(request,ConstantsUri.MEMBER_ORDER_PLACE,orderboreq,OrderResq.class,userbo.getId());
                    //mav.addObject("data",brs);
                    if("2000".equals(brs.getCode())){
                        redisOrderBo.setOrderBoReq(orderboreq);
                        redisOrderBo.setBool(true);
                        getInfoService.setObject(RedisCode.TIME_5_60,RedisCode.USER_ORDER_SETTLEMENT+":"+md5.compute()+":"+orderRequestSoaBo.getLshid(),redisOrderBo);
                        String tradeNo = brs.getData().getTradeBO().getTradeNo();
                        OrderPayBO orderPayBO = new OrderPayBO();
                        orderPayBO.setTradeNo(tradeNo);
                        orderPayBO.setUserId(userbo.getId());
                        orderPayBO.setIsPay(2);
                        orderPayBO.setPayMethod("POINTS");
                        orderPayBO.setTradeMethod("POINTS");
                        BaseResponse brt=SoaConnectionFactory.post(request,ConstantsUri.ORDER_PAY_POINT,orderPayBO,BaseResponse.class);
                        mav.addObject("data",brt);
                        mav.addObject("no",tradeNo);
                    }else{
                        mav.addObject("data",brs);
                    }
                } catch (SoaException e) {
                    e.printStackTrace();
                }
            }else{
                BaseResponse br=new BaseResponse("1000","订单已经下单完成不能重复下单!");
                mav.addObject("data",br);
            }
        }else{
            BaseResponse br=new BaseResponse("1000","订单已失效!");
            mav.addObject("data",br);
        }
        return mav;
    }

    /**
     * 跳转订单结算页面
     * @param request
     * @param response
     */
    @RequestMapping(value = "/integral_success/{type}/{no}", method = RequestMethod.GET)
    public ModelAndView integral_default(@PathVariable(value = "type") String type,@PathVariable(value = "no") String no, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav= new ModelAndView("pay/success");
        UserBo userbo = getInfoService.getUserBo(request);
        MD5 md5=new MD5(userbo.getId());
        try {
            OrderTradeDataBo orderTradeDataBo=SoaConnectionFactory.get(request,ConstantsUri.ORSER_PAY_SELECT_TRADE,null, OrderTradeDataBo.class,no);
//        RedisOrderBo redisOrderBo=getInfoService.getObject(RedisCode.USER_ORDER_SETTLEMENT+":"+md5.compute()+":"+lsh, RedisOrderBo.class);
//        OrderBoReq orderboreq = redisOrderBo.getOrderBoReq();
//        if(orderboreq!=null){
//            mav.addObject("zje",orderTradeDataBo.getData().getTradeAmount());
//            mav.addObject("orderid",no);
//            mav.addObject("type",orderboreq.getTradeMethod());
//        }else{
//            NewOrderBo newOrderBo = redisOrderBo.getNewOrderBo();
//            mav.addObject("zje",newOrderBo.getTotalPrice());
//            mav.addObject("orderid",no);
//            mav.addObject("type",newOrderBo.getTradeMethod());
//        }
            mav.addObject("zje",orderTradeDataBo.getData().getTradeAmount());
            mav.addObject("orderid",orderTradeDataBo.getData().getOrderNo());
            mav.addObject("no",no);
            mav.addObject("type",type);
            mav.addObject("user",userbo);
        } catch (SoaException e) {
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



    @RequestMapping(value = "/integral_zhifu/{no}", method = RequestMethod.GET)
    public ModelAndView integral_zhifu(@PathVariable(value = "no") String no, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        UserBo userbo = getInfoService.getUserBo(request);
        if(userbo==null){
            BaseResponse br=new BaseResponse("1000","用户已失效！");
            mav.addObject("data",br);
            return mav;
        }
        try {
            OrderPayBO orderPayBO = new OrderPayBO();
            orderPayBO.setTradeNo(no);
            orderPayBO.setUserId(userbo.getId());
            orderPayBO.setIsPay(2);
            orderPayBO.setPayMethod("POINTS");
            orderPayBO.setTradeMethod("POINTS");
            BaseResponse brt=SoaConnectionFactory.post(request,ConstantsUri.ORDER_PAY_POINT,orderPayBO,BaseResponse.class);
            mav.addObject("data",brt);
            mav.addObject("no",no);
        } catch (SoaException e) {
            e.printStackTrace();
        }
        return mav;
    }

}
