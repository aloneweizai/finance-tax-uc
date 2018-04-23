package com.abc.controller.member;

import com.abc.application.SpringCtxHolder;
import com.abc.bean.userinfo.*;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.MD5;
import com.abc.controller.BaseController;
import com.abc.service.IntegralUtil;
import com.abc.service.RedisCode;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.*;
import com.abc.soa.request.userinfo.AddressBoReq;
import com.abc.soa.request.userinfo.OrderBoReq;
import com.abc.soa.request.userinfo.OrderProductBoReq;
import com.abc.soa.response.*;
import com.abc.soa.response.userinfo.GoodsBoResp;
import com.abc.soa.response.userinfo.OrderDetailResp;
import com.abc.soa.response.userinfo.UvipPriceResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by stuy on 2017/8/4.
 */
@Controller
@RequestMapping(value = "/integral")
public class IntegralController extends BaseController{


    private final static Logger logger = LoggerFactory.getLogger(IntegralController.class);

    /**
     * 显示积分兑换明细信息
     * @param goodsid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/{goodsid}", method = RequestMethod.GET)
    public ModelAndView member_integral(@PathVariable(value = "goodsid") String goodsid, HttpSession session, HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("member/integral_details");
            UserBo obj = getInfoService.getUserBo(request);
            GoodsBoResp goodsRes= SoaConnectionFactory.getRestful(request, ConstantsUri.GOODS_ID,null,GoodsBoResp.class,goodsid);
            DictListBo dictListBo=SoaConnectionFactory.getRestful(request,ConstantsUri.GOODS_SPEC,null,DictListBo.class,goodsid);
            List<List<UvipPrice>> dataList = new ArrayList<List<UvipPrice>>();
            if(goodsRes.getData().getProductBOList()!=null&&goodsRes.getData().getProductBOList().size()>0){
                if(goodsRes.getData().getProductBOList().size()>1){
                    for(ProductBO orderProduct: goodsRes.getData().getProductBOList()) {
                        if (orderProduct.equals(goodsRes.getData().getProductBOList().get(0))) {
                            String productId = orderProduct.getId();
                            UvipPriceResp rs = SoaConnectionFactory.getRestful(request, ConstantsUri.GOODS_PROPRICE, null, UvipPriceResp.class, productId);
                            if (rs.getDataList() != null && rs.getDataList().size() > 0) {
                                dataList.add(rs.getDataList());
                            }
                            IntegralListBo integralListBo= SoaConnectionFactory.getRestful(request,ConstantsUri.GOODS_PRODUCT,null,IntegralListBo.class,goodsid);
                            List<ProductBO> productBOs = integralListBo.getDataList();
                            if(productBOs!=null && productBOs.size()>0){
                                for(ProductBO productBO :productBOs){
                                    if(productId.equals(productBO.getId())){
                                        List<Map<String,String>> list=new ArrayList<Map<String,String>>();
                                        for(DictBO dictBo :productBO.getDictList()){
                                            Map<String,String> maps=new HashMap<String,String>();
                                            maps.put("value",dictBo.getFieldValue());
                                            maps.put("text",dictBo.getFieldKey());
                                            maps.put("keyname",dictBo.getDictId());
                                            list.add(maps);
                                        }
                                        mav.addObject("defaults",list);
                                    }
                                }
                            }
                        }
                    }
                }else{
                    String productId = goodsRes.getData().getProductBOList().get(0).getId();
                    UvipPriceResp rs= SoaConnectionFactory.getRestful(request, ConstantsUri.GOODS_PROPRICE, null, UvipPriceResp.class, productId);
                    if(rs.getDataList()!= null && rs.getDataList().size()>0) {
                        dataList.add(rs.getDataList());
                    }
                }
            }
            Map<String,List<Map<String,String>>> map=new HashMap<String,List<Map<String,String>>>();
            if(dictListBo.getDataList()!=null&&dictListBo.getDataList().size()>0){
                for(DictBo dictBo: dictListBo.getDataList()){
                    if(map.size()==0){
                        List<Map<String,String>> list=new ArrayList<Map<String,String>>();
                        Map<String,String> maps=new HashMap<String,String>();
                        maps.put("value",dictBo.getFieldValue());
                        maps.put("text",dictBo.getFieldKey());
                        maps.put("keyname",dictBo.getDictId());
                        list.add(maps);
                        map.put(dictBo.getDictName(),list);
                    }else{
                        List<Map<String,String>> list = map.get(dictBo.getDictName());
                        if(list!=null&&list.size()>0){
                            Map<String,String> maps=new HashMap<String,String>();
                            maps.put("value",dictBo.getFieldValue());
                            maps.put("text",dictBo.getFieldKey());
                            maps.put("keyname",dictBo.getDictId());
                            list.add(maps);
                        }else{
                            List<Map<String,String>> lists=new ArrayList<Map<String,String>>();
                            Map<String,String> maps=new HashMap<String,String>();
                            maps.put("value",dictBo.getFieldValue());
                            maps.put("text",dictBo.getFieldKey());
                            maps.put("keyname",dictBo.getDictId());
                            lists.add(maps);
                            map.put(dictBo.getDictName(),lists);
                        }
                    }
                }
            }

            mav.addObject("data",goodsRes.getData());
            mav.addObject("user",obj);
            //mav.addObject("path", SpringCtxHolder.getProperty("imagedomain"));
            mav.addObject("map",map);
            mav.addObject("vipPrices",dataList);
            //添加地址
            AddressRes addressRes=SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS,null, AddressRes.class,obj.getId());
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
            UserTzxxRes userTzxxRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_TZXX,null, UserTzxxRes.class,obj.getId());
            mav.addObject("usertzxx",userTzxxRes.getData());
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }


    /**
     * 跳转积分充值页面
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/integral_payment.html", method = RequestMethod.GET)
    public ModelAndView member_integral( HttpSession session, HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("member/integral_payment");
            UserBo obj = getInfoService.getUserBo(request);
            IntegralUtil integralUtil=new IntegralUtil();
            List<IntegralBo> list = integralUtil.list;
            mav.addObject("data",list);
            mav.addObject("user",obj);
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 积分兑换操作
     * @param goodsid
     * @param guigeid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/{goodsid}", method = RequestMethod.POST)
    public ModelAndView guigeid(@PathVariable(value = "goodsid") String goodsid,
                                @RequestParam(value = "guigeid") String guigeid,
                                HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo obj = getInfoService.getUserBo(request);
            if(isNull(goodsid)){
                String lsh=jfdh(obj,request,guigeid,goodsid);
                BaseResponse br=new BaseResponse("2000","成功");
                mav.addObject("data",br);
                mav.addObject("lsh",lsh);
            }else{
                BaseResponse br=new BaseResponse("1000","缺少商品编号,下单失败!");
                mav.addObject("data",br);
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试" +
                        "...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }


    public String jfdh(UserBo obj,HttpServletRequest request,String guigeid,String goodsid) throws SoaException{
        OrderBoReq orderBoReq = new OrderBoReq();
        OrderProductBoReq orderProductBoReq = new OrderProductBoReq();
        Double totalPrice = 0.0;
        String specInfo = "";
        if(isNull(guigeid)){
            IntegralListBo integralListBo= SoaConnectionFactory.getRestful(request,ConstantsUri.GOODS_PRODUCT,null,IntegralListBo.class,goodsid);
            String [] gui=guigeid.split(",");
            int count=0;
            for(ProductBO productBO :integralListBo.getDataList()){
                List<DictBO> dictlist = productBO.getDictList();
                for(DictBO dict : dictlist){
                    if(guigeid.indexOf(dict.getFieldValue())>-1){
                        count++;
                    }else{
                        count=0;
                        break;
                    }
                }
                if(count==gui.length){
                    guigeid=productBO.getId();
                    break;
                }
            }
            for(int i=0;i<integralListBo.getDataList().size();i++){
                ProductBO p = integralListBo.getDataList().get(i);
                if(p.getId().equals(guigeid)){
                    totalPrice = p.getSellingPrice();
                    if(p.getDictList() != null && p.getDictList().size()>0){
                        for(DictBO dictBO :p.getDictList()){
                            specInfo += dictBO.getDictName()+ ":" + dictBO.getFieldKey();
                        }
                    }
                }
            }


        }



        GoodsBoResp goodsRes= SoaConnectionFactory.getRestful(request, ConstantsUri.GOODS_ID,null,GoodsBoResp.class,goodsid);
        GoodsBO goodsBO = goodsRes.getData();
        List<ProductBO> productBOList =  goodsBO.getProductBOList();
        if(isNull(guigeid)){
            UvipPriceResp rs= SoaConnectionFactory.getRestful(request, ConstantsUri.GOODS_PROPRICE, null, UvipPriceResp.class, guigeid);
            String levelName = getLevelName(obj.getVipLevel());
            if(rs.getDataList() != null && rs.getDataList().size() > 0 ){
                for(UvipPrice uvipPrice :rs.getDataList()){
                    if(levelName.equals(uvipPrice.getVipLevel())||obj.getVipLevel().equals(uvipPrice.getVipLevel())){
                        totalPrice =uvipPrice.getTradePrice();
                    }
                }
            }
            if(totalPrice<=0){
                totalPrice=goodsRes.getData().getProductBOList().get(0).getSellingPrice();
            }
        }else{

            guigeid=goodsRes.getData().getProductBOList().get(0).getId();
            UvipPriceResp rs= SoaConnectionFactory.getRestful(request, ConstantsUri.GOODS_PROPRICE, null, UvipPriceResp.class, guigeid);
            String levelName = getLevelName(obj.getVipLevel());
            if(rs.getDataList() != null && rs.getDataList().size() > 0 ){
                for(UvipPrice uvipPrice :rs.getDataList()){
                    if(levelName.equals(uvipPrice.getVipLevel())||obj.getVipLevel().equals(uvipPrice.getVipLevel())){
                        totalPrice =uvipPrice.getTradePrice();
                    }
                }
                if(totalPrice<=0){
                    totalPrice=goodsRes.getData().getProductBOList().get(0).getSellingPrice();
                }
            }else{
                totalPrice=goodsRes.getData().getProductBOList().get(0).getSellingPrice();
            }
        }

        orderBoReq.setUserId(obj.getId());
        orderBoReq.setUsername(obj.getUsername());
        orderBoReq.setIsShipping(goodsBO.getIsShipping());
        orderBoReq.setIsFreeShipping(goodsBO.getIsFreeShipping());
        orderBoReq.setTotalPrice(totalPrice);
        orderBoReq.setTradeMethod(goodsBO.getTradeMethod());
        orderBoReq.setNowVipLevel(obj.getVipLevel());
        orderBoReq.setGiftPoints(goodsBO.getGiftPoints());
        orderProductBoReq.setName(goodsBO.getName());
        orderProductBoReq.setNum(1);
        orderProductBoReq.setImageUrl(goodsBO.getImageUrl());
        orderProductBoReq.setIsReturn(goodsBO.getIsReturn());
        orderProductBoReq.setIsExchange(goodsBO.getIsExchange());
        if(productBOList!= null && productBOList.size() > 0) {
            for(ProductBO productBO: productBOList){
                if(productBO.getId().equals(guigeid)){
                    orderProductBoReq.setGoodsId(productBO.getGoodsId());
                    orderProductBoReq.setGoodsPrice(productBO.getSellingPrice());
                    orderProductBoReq.setDealPrice(totalPrice);//实付金额
                }
            }
        }

        orderProductBoReq.setBrowseUrl( SpringCtxHolder.getProperty("ucdomain"));
        if(!(specInfo != null&&!"".equals(specInfo))){
            specInfo ="-";
        }
        orderProductBoReq.setSpecInfo(specInfo);
        orderProductBoReq.setTradingChannels("UCSC");
        orderProductBoReq.setProductId(guigeid);
        List<OrderProductBoReq> dataList = new ArrayList<>();
        dataList.add(orderProductBoReq);
        orderBoReq.setOrderProductBOList(dataList);

        String str=System.currentTimeMillis()+getFixLenthString(6);
        RedisOrderBo redisOrderBo=new RedisOrderBo();
        redisOrderBo.setOrderBoReq(orderBoReq);
        redisOrderBo.setLsh(str);
        redisOrderBo.setYhje(0.00);
        MD5 md5=new MD5(obj.getId());
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
     *  根据规格查询对应的价格
     * @param goodsid
     * @param guigevalue
     * @param request
     * @return
     */
    @RequestMapping(value = "/guige/{goodsid}/{guigevalue}", method = RequestMethod.GET)
    public ModelAndView goods_product(@PathVariable(value = "goodsid") String goodsid,
                                      @PathVariable(value = "guigevalue") String guigevalue,
                                      HttpServletRequest request,HttpSession session) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try {
            UserBo obj = getInfoService.getUserBo(request);
            IntegralListBo integralListBo= SoaConnectionFactory.getRestful(request,ConstantsUri.GOODS_PRODUCT,null,IntegralListBo.class,goodsid);
            String [] gui=guigevalue.split(",");
            int count=0;
            double jiage=0;
            String guigeid="";
            for(ProductBO productBO :integralListBo.getDataList()){
                List<DictBO> dictlist = productBO.getDictList();
                for(DictBO dict : dictlist){
                    if(guigevalue.indexOf(dict.getFieldValue())>-1){
                        count++;
                    }else{
                        count=0;
                        break;
                    }
                }
                if(count==gui.length){
                    jiage=productBO.getSellingPrice();
                    guigeid=productBO.getId();
                    break;
                }
            }
            mav.addObject("jiage",jiage);
            mav.addObject("guigeid",guigeid);
            mav.addObject("user",obj);

            UvipPriceResp rs= SoaConnectionFactory.getRestful(request, ConstantsUri.GOODS_PROPRICE, null, UvipPriceResp.class, guigeid);
            String levelName = getLevelName(obj.getVipLevel());
            if(rs.getDataList() != null && rs.getDataList().size() > 0 ) {
                for (UvipPrice uvipPrice : rs.getDataList()) {
                    if (levelName.equals(uvipPrice.getVipLevel())|| obj.getVipLevel().equals(uvipPrice.getVipLevel())) {
                        double vipPrice = uvipPrice.getTradePrice();
                        mav.addObject("vipPrice", vipPrice);
                    }
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
}
