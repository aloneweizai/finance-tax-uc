package com.abc.controller.member;

import com.abc.application.SpringCtxHolder;
import com.abc.bean.userinfo.*;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:zlk
 * @Description:实物商城
 * @Date:2017-09-13
 * @Time:9:31
 */
@Controller
@RequestMapping(value = "/shopBop")
public class ShopBopController extends BaseController{

    private final static Logger logger = LoggerFactory.getLogger(ShopBopController.class);
    /**
     * 查询实物商城列表
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/member_shopBop.html", method = RequestMethod.GET)
    public ModelAndView member_integral( HttpSession session, HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("member/member_shop_bop");
            UserBo obj = getInfoService.getUserBo(request);
            MemberGoodsBo memberGoodsBo=new MemberGoodsBo();
            memberGoodsBo.setTradeMethod("RMB");
            GoodsRes goodsRes= SoaConnectionFactory.get(request, ConstantsUri.MEMBER_GOODS_USER, memberGoodsBo, GoodsRes.class);
            mav.addObject("data",goodsRes.getDataList());
            mav.addObject("user",obj);
            //mav.addObject("path", SpringCtxHolder.getProperty("imagedomain"));
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 显示商品明细信息
     * @param goodsid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/{goodsid}", method = RequestMethod.GET)
    public ModelAndView member_integral(@PathVariable(value = "goodsid") String goodsid, HttpSession session, HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("member/shop_good_detail");
            UserBo obj = getInfoService.getUserBo(request);
            GoodsBoResp goodsRes= SoaConnectionFactory.getRestful(request, ConstantsUri.GOODS_ID,null,GoodsBoResp.class,goodsid);
            DictListBo dictListBo=SoaConnectionFactory.getRestful(request,ConstantsUri.GOODS_SPEC,null,DictListBo.class,goodsid);
            double da=0;
            double xiao=0;
            if(goodsRes.getData().getProductBOList()!=null&&goodsRes.getData().getProductBOList().size()>0){
                if(goodsRes.getData().getProductBOList().size()>1){
                    for(ProductBO orderProduct: goodsRes.getData().getProductBOList()){
                        if(da==0){
                            da=orderProduct.getSellingPrice();
                            xiao=orderProduct.getSellingPrice();
                        }else{
                            if(orderProduct.getSellingPrice()>da){
                                if(da<xiao){
                                    xiao=da;
                                }
                                da=orderProduct.getSellingPrice();
                            }else{
                                if(xiao>orderProduct.getSellingPrice()){
                                    xiao=orderProduct.getSellingPrice();
                                }
                            }
                        }
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
            mav.addObject("da",da);
            mav.addObject("xiao",xiao);
            mav.addObject("map",map);

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

            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }


    /**
     * 商品购买下单
     * @param goodsid
     * @param guigeid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/{goodsid}/{guigeid}", method = RequestMethod.POST)
    public ModelAndView guigeid(@PathVariable(value = "goodsid") String goodsid,
                                @PathVariable(value = "guigeid") String guigeid,
                                @RequestBody String addressid,
                                HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo obj = getInfoService.getUserBo(request);
            if(isNull(goodsid)&&isNull(guigeid)){
                OrderBoReq orderBoReq = new OrderBoReq();
                OrderProductBoReq orderProductBoReq = new OrderProductBoReq();
                Double totalPrice = 0.0;
                IntegralListBo integralListBo= SoaConnectionFactory.getRestful(request,ConstantsUri.GOODS_PRODUCT,null,IntegralListBo.class,goodsid);
                for(int i=0;i<integralListBo.getDataList().size();i++){
                    ProductBO p = integralListBo.getDataList().get(i);
                    if(p.getId().equals(guigeid)){
                        totalPrice = p.getSellingPrice();
                    }
                }
                GoodsBoResp goodsRes= SoaConnectionFactory.getRestful(request, ConstantsUri.GOODS_ID,null,GoodsBoResp.class,goodsid);
                GoodsBO goodsBO = goodsRes.getData();
                List<ProductBO> productBOList =  goodsBO.getProductBOList();

                UvipPriceResp rs= SoaConnectionFactory.getRestful(request, ConstantsUri.GOODS_PROPRICE, null, UvipPriceResp.class, guigeid);
                String levelName = getLevelName(obj.getVipLevel());
                if(rs.getDataList() != null && rs.getDataList().size() > 0 ){
                    for(UvipPrice uvipPrice :rs.getDataList()){
                        if(levelName.equals(uvipPrice.getVipLevel())||obj.getVipLevel().equals(uvipPrice.getVipLevel())){
                            totalPrice =uvipPrice.getTradePrice();
                        }
                    }
                }

                //添加地址
                if(addressid != null) {
                    AddressBoReq addressBoReq = new AddressBoReq();
                    addressBoReq.setAddressId(addressid);
                    AddressRes addressRes = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS, addressBoReq, AddressRes.class, obj.getId());
                    List<AddressBo> addressBoList = addressRes.getDataList();
                    for(AddressBo addressBo : addressBoList){
                        orderBoReq.setConsignee(addressBo.getName());
                        orderBoReq.setContactNumber(addressBo.getPhone());
                        String shipAddress = addressBo.getProvinceName() + addressBo.getCityName()+ addressBo.getAreaName() + addressBo.getDetail();
                        orderBoReq.setShippingAddress(shipAddress);
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
                if(productBOList!= null && productBOList.size() > 0) {
                    for(ProductBO productBO: productBOList){
                        if(productBO.getId().equals(guigeid)){
                            orderProductBoReq.setGoodsId(productBO.getGoodsId());
                            orderProductBoReq.setGoodsPrice(productBO.getMarketPrice());
                            orderProductBoReq.setDealPrice(productBO.getSellingPrice());
                        }
                    }
                }
                orderProductBoReq.setBrowseUrl(goodsBO.getImageUrl());
                orderProductBoReq.setSpecInfo(goodsBO.getName());
                orderProductBoReq.setTradingChannels("2");
                orderProductBoReq.setProductId(guigeid);
                orderProductBoReq.setSpecInfo(goodsBO.getName());
                List<OrderProductBoReq> dataList = new ArrayList<>();
                dataList.add(orderProductBoReq);
                orderBoReq.setOrderProductBOList(dataList);

                OrderDetailResp br=SoaConnectionFactory.post(request,ConstantsUri.MEMBER_ORDER_PLACE,orderBoReq,OrderDetailResp.class,obj.getId());
                mav.addObject("data",br.getData());
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


}
