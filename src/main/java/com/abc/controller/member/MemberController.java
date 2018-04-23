package com.abc.controller.member;

import com.abc.application.SpringCtxHolder;
import com.abc.bean.userinfo.*;
import com.abc.cache.RedisCacheService;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.*;
import com.abc.soa.request.userinfo.*;
import com.abc.soa.request.userinfo.OrderResq;
import com.abc.soa.response.*;
import com.abc.soa.response.userinfo.*;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by stuy on 2017/8/2.
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(MemberController.class);

    /**
     * 会员首页
     * @param url
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/member_index.html")
    public ModelAndView external_index(
            @RequestParam(value = "url", required = false) String url,
            HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            ModelAndView mav= new ModelAndView("member/member_index");
            UserBo loginUserBo = getInfoService.getUserBo(request);
            if(loginUserBo!=null) {
                mav.addObject("user", loginUserBo);
                String vipcode="VIP0";
                if (isNull(loginUserBo.getVipLevel())) {
                    vipcode=loginUserBo.getVipLevel();
                }

                VipLeveRes baseResponse = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_VIP, null, VipLeveRes.class, vipcode);
                mav.addObject("vip", baseResponse.getData());
            }else{
                mav.addObject("user",new UserBo());
            }
            if(isNull(url)){
                BASE64Decoder base64Decoder=new BASE64Decoder();
                try {
                    url=new String (base64Decoder.decodeBuffer(url),"UTF-8");
                    mav.addObject("url",url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                mav.addObject("url","my_index.html");
            }
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 财税客户端会员首页
     * @param url
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/external_member_index.html")
    public ModelAndView external_member_index(
            @RequestParam(value = "url", required = false) String url,
            HttpSession session, HttpServletRequest request) {
        try {
            ModelAndView mav= new ModelAndView("member/external_member_index");
            UserBo loginUserBo = getInfoService.getUserBo(request);
            if(loginUserBo!=null) {
                mav.addObject("user", loginUserBo);
                String vipcode="VIP0";
                if (isNull(loginUserBo.getVipLevel())) {
                    vipcode=loginUserBo.getVipLevel();
                }

                VipLeveRes baseResponse = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_VIP, null, VipLeveRes.class, vipcode);
                mav.addObject("vip", baseResponse.getData());
            }else{
                mav.addObject("user",new UserBo());
            }
            if(isNull(url)){
                BASE64Decoder base64Decoder=new BASE64Decoder();
                try {
                    url=new String (base64Decoder.decodeBuffer(url),"UTF-8");
                    mav.addObject("url",url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                mav.addObject("url","my_index.html");
            }
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }


    /**
     * 会员iframe首页
     * @param request
     * @return
     */
    @GetMapping("/my_index.html")
    public ModelAndView my_index( HttpServletRequest request) {
        try {
            ModelAndView mav= new ModelAndView("member/my_index");
            UserBo loginUserBo = getInfoService.getUserBo(request);
            if(loginUserBo!=null){
                mav.addObject("user",loginUserBo);

                //会员中心首页-->我的订单信息
                OrderListReq orderListReq =  new OrderListReq();
                orderListReq.setUserId(loginUserBo.getId());
                orderListReq.setName(loginUserBo.getUsername());
                orderListReq.setPage(1);
                orderListReq.setSize(5);//限制显示为5条
                orderListReq.setGoodsType("HYCZ");//会员服务
                OrderListResp orders = SoaConnectionFactory.get(request, ConstantsUri.ORDER, orderListReq, OrderListResp.class);
                List<OrderBO> orderList = JSON.parseArray(orders.getDataList(), OrderBO.class);
                mav.addObject("orders", orderList);
                DictRes obj = RedisCacheService.getRedisDictRes(request, getInfoService, "orderStatus");
                //查询并添加订单状态数据字典
                mav.addObject("orderStatus", obj.getDataList());

                VipPrivilegeLevelReq privilegeLevelReq = new VipPrivilegeLevelReq();
                if(loginUserBo.getVipExpireDate()!= null && calcDate(loginUserBo.getVipExpireDate())>0) {
                    privilegeLevelReq.setLevel(loginUserBo.getVipLevel());
                }else{
                    privilegeLevelReq.setLevel("VIP0");
                }
                privilegeLevelReq.setPage(0);
                privilegeLevelReq.setSize(0);
                VipPrivilegeLevelResp privilgeLevelResp = SoaConnectionFactory.get(request, ConstantsUri.UVIP_PRIVILEGE_LEVEL, privilegeLevelReq, VipPrivilegeLevelResp.class);
                List<VipPrivilegeLevel> privilegeLevels = privilgeLevelResp.getDataList();
                mav.addObject("levels", privilegeLevels);

                //我的vip特权
                VipPrivilegeReq vipPrivilgeReq = new VipPrivilegeReq();
                vipPrivilgeReq.setStatus(true);
                vipPrivilgeReq.setPage(0);
                vipPrivilgeReq.setSize(0);
                VipPrivilegeResp privilgeResp = SoaConnectionFactory.get(request, ConstantsUri.UVIP_PRIVILEGE, vipPrivilgeReq, VipPrivilegeResp.class);
                List<VipPrivilege> privilegeList = privilgeResp.getDataList();
                mav.addObject("privilegeList",privilegeList);

            }else{
                mav.addObject("user",new UserBo());
            }
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }


    /**
     * 会员vip等级权益介绍
     * @param request
     * @return
     */
    @RequestMapping(value = "/order_member.html", method = RequestMethod.GET)
    public ModelAndView sh_default(HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("member/order_member");
            Map map=new HashMap();
            map.put("status","true");
            VipLevelDataListBO vipLevelDataListBO=SoaConnectionFactory.get(request,ConstantsUri.MEMBER_VIP_LEVEL,map, VipLevelDataListBO.class);
            mav.addObject("data",vipLevelDataListBO.getDataList());
            UserBo userbo = getInfoService.getUserBo(request);
            UserTzxxRes userTzxxRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_TZXX,null, UserTzxxRes.class,userbo.getId());
            mav.addObject("usertzxx",userTzxxRes.getData());
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 会员vip商品列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/member_rights.html", method = RequestMethod.GET)
    public ModelAndView member_rights(HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("member/member_rights");
            Map map=new HashMap();
            map.put("status", "true");
            VipLevelDataListBO vipLevelDataListBO=SoaConnectionFactory.get(request,ConstantsUri.MEMBER_VIP_LEVEL,map, VipLevelDataListBO.class);
            mav.addObject("data",vipLevelDataListBO.getDataList());

            //vip特权
            VipPrivilegeReq vipPrivilgeReq = new VipPrivilegeReq();
            vipPrivilgeReq.setStatus(true);
            vipPrivilgeReq.setPage(0);
            vipPrivilgeReq.setSize(0);
            VipPrivilegeResp privilgeResp = SoaConnectionFactory.get(request, ConstantsUri.UVIP_PRIVILEGE, vipPrivilgeReq, VipPrivilegeResp.class);
            List<VipPrivilege> privilegeList = privilgeResp.getDataList();
            mav.addObject("privilegeList", privilegeList);

            VipPrivilegeAllReq privilegeAllReq = new VipPrivilegeAllReq();
            privilegeAllReq.setStatus(true);
            privilegeAllReq.setPage(0);
            privilegeAllReq.setSize(0);
            VipPrivilegeAllResp privilegeLevelResp = SoaConnectionFactory.get(request, ConstantsUri.MEMBER_PRIVILEGE_ALL, privilegeAllReq, VipPrivilegeAllResp.class);
            mav.addObject("vipDatasList", privilegeLevelResp.getDataList());

            UserBo userbo = getInfoService.getUserBo(request);
            UserTzxxRes userTzxxRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_TZXX,null, UserTzxxRes.class,userbo.getId());
            mav.addObject("usertzxx",userTzxxRes.getData());
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 跳转充值支付页面
     * @param goodsid
     * @param orderid
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/{goodsid}/{orderid}")
    public ModelAndView payment(@PathVariable(value = "goodsid") String goodsid,
                                @PathVariable(value = "orderid") String orderid,HttpSession session, HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("member/payment");
            OrderResq baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.ORDER_DETAIL,null,OrderResq.class,orderid);
            OrderBO orderBO = baseResponse.getData();
            mav.addObject("orderid",orderid);
            mav.addObject("order",orderBO);
            String tradeNo = null;
            if(orderBO.getTradeBO() != null) {
                tradeNo = orderBO.getTradeBO().getTradeNo();
            }else{
                List<TradeBO>tradeBOList = orderBO.getTradeBOList();
                if(tradeBOList != null && tradeBOList.size() >0){
                    tradeNo =tradeBOList.get(0).getTradeNo();
                }
            }
            mav.addObject("tradeNo",tradeNo);

            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 查询积分商品列表(个人中心)
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/member_integral_uc.html", method = RequestMethod.GET)
    public ModelAndView member_integral_uc( HttpSession session, HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("member/member_integral_uc");
            UserBo obj = getInfoService.getUserBo(request);
            MemberGoodsBo memberGoodsBo=new MemberGoodsBo();
            memberGoodsBo.setPage(0);
            memberGoodsBo.setSize(0);
            memberGoodsBo.setTradeMethod("POINTS");
            GoodsRes goodsRes=SoaConnectionFactory.get(request,ConstantsUri.MEMBER_GOODS_USER,memberGoodsBo,GoodsRes.class);
            mav.addObject("data",goodsRes.getDataList());
            mav.addObject("user",obj);
            mav.addObject("path",SpringCtxHolder.getProperty("imagedomain"));

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
     * 查询积分商品列表(会员中心)
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/member_integral.html", method = RequestMethod.GET)
    public ModelAndView member_integral( HttpSession session, HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("member/member_integral_uc");
            UserBo obj = getInfoService.getUserBo(request);
            MemberGoodsBo memberGoodsBo=new MemberGoodsBo();
            memberGoodsBo.setPage(0);
            memberGoodsBo.setSize(0);
            memberGoodsBo.setTradeMethod("POINTS");
            GoodsRes goodsRes=SoaConnectionFactory.get(request,ConstantsUri.MEMBER_GOODS_USER,memberGoodsBo,GoodsRes.class);
            mav.addObject("data",goodsRes.getDataList());
            mav.addObject("user",obj);
            mav.addObject("path",SpringCtxHolder.getProperty("imagedomain"));

            UserTzxxRes userTzxxRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_TZXX,null, UserTzxxRes.class,obj.getId());
            mav.addObject("usertzxx",userTzxxRes.getData());

            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }


    @RequestMapping(value = "/privilege_introduce/{id}",method = RequestMethod.GET)
    public ModelAndView member_vip(@PathVariable(value = "id")String id,HttpServletRequest request,HttpSession session) {
        UserBo userBo = getInfoService.getUserBo(request);
        try {
            ModelAndView mav = new ModelAndView("member/privilege_introduce");
            //我的会员权益
            VipLeveRes baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_VIP,null,VipLeveRes.class,userBo.getVipLevel());
            mav.addObject("vipLevel", baseResponse.getData());

            VipPrivilegeLevelReq privilegeLevelReq = new VipPrivilegeLevelReq();
            privilegeLevelReq.setLevel(baseResponse.getData().getLevelCode());
            privilegeLevelReq.setPage(0);
            privilegeLevelReq.setSize(0);
            VipPrivilegeLevelResp privilgeLevelResp = SoaConnectionFactory.get(request, ConstantsUri.UVIP_PRIVILEGE_LEVEL, privilegeLevelReq, VipPrivilegeLevelResp.class);
            List<VipPrivilegeLevel> privilegeLevels = privilgeLevelResp.getDataList();
            mav.addObject("levels", privilegeLevels);

            //会员有效期
            mav.addObject("vipExpireDate", calcDate(userBo.getVipExpireDate()));

            //普通用户权益
            VipPrivilegeLevelReq ordinarysReq = new VipPrivilegeLevelReq();
            ordinarysReq.setLevel("VIP0");
            ordinarysReq.setPage(0);
            ordinarysReq.setSize(0);
            VipPrivilegeLevelResp ordinarysResp = SoaConnectionFactory.get(request, ConstantsUri.UVIP_PRIVILEGE_LEVEL, privilegeLevelReq, VipPrivilegeLevelResp.class);
            List<VipPrivilegeLevel> ordinarysLevels = ordinarysResp.getDataList();
            mav.addObject("ordinarys", ordinarysLevels);

            //所有vip特权
            VipPrivilegeReq vipPrivilgeReq = new VipPrivilegeReq();
            vipPrivilgeReq.setPage(0);
            vipPrivilgeReq.setSize(0);
            vipPrivilgeReq.setStatus(true);
            VipPrivilegeResp privilgeResp = SoaConnectionFactory.get(request, ConstantsUri.UVIP_PRIVILEGE, vipPrivilgeReq, VipPrivilegeResp.class);
            List<VipPrivilege> privilegeList = privilgeResp.getDataList();
            mav.addObject("privilegeList", privilegeList);

            VipPrivilegeBO vipPrivilegeBO = SoaConnectionFactory.getRestful(request, ConstantsUri.UVIP_PRIVILEGE_ID, null, VipPrivilegeBO.class, id);
            mav.addObject("vipPrivilege",vipPrivilegeBO.getData());
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }


    @RequestMapping(value = "/privilege_introduce/all",method = RequestMethod.GET)
    public ModelAndView member_vip_all(HttpServletRequest request,HttpSession session) {
        UserBo userBo = getInfoService.getUserBo(request);
        try {
            ModelAndView mav = new ModelAndView("member/privilege_introduce");
           //我的会员权益
            VipLeveRes baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_VIP,null,VipLeveRes.class,userBo.getVipLevel());
            mav.addObject("vipLevel", baseResponse.getData());

            VipPrivilegeLevelReq privilegeLevelReq = new VipPrivilegeLevelReq();
            privilegeLevelReq.setLevel(baseResponse.getData().getLevelCode());
            privilegeLevelReq.setPage(0);
            privilegeLevelReq.setSize(0);
            VipPrivilegeLevelResp privilgeLevelResp = SoaConnectionFactory.get(request, ConstantsUri.UVIP_PRIVILEGE_LEVEL, privilegeLevelReq, VipPrivilegeLevelResp.class);
            List<VipPrivilegeLevel> privilegeLevels = privilgeLevelResp.getDataList();
            mav.addObject("levels", privilegeLevels);

            //会员有效期
            mav.addObject("vipExpireDate", calcDate(userBo.getVipExpireDate()));

            //普通用户权益
            VipPrivilegeLevelReq ordinarysReq = new VipPrivilegeLevelReq();
            ordinarysReq.setLevel("VIP0");
            ordinarysReq.setPage(0);
            ordinarysReq.setSize(0);
            VipPrivilegeLevelResp ordinarysResp = SoaConnectionFactory.get(request, ConstantsUri.UVIP_PRIVILEGE_LEVEL, privilegeLevelReq, VipPrivilegeLevelResp.class);
            List<VipPrivilegeLevel> ordinarysLevels = ordinarysResp.getDataList();
            mav.addObject("ordinarys", ordinarysLevels);

            //所有vip特权
            VipPrivilegeReq vipPrivilgeReq = new VipPrivilegeReq();
            vipPrivilgeReq.setStatus(true);
            vipPrivilgeReq.setPage(0);
            vipPrivilgeReq.setSize(0);
            VipPrivilegeResp privilgeResp = SoaConnectionFactory.get(request, ConstantsUri.UVIP_PRIVILEGE, vipPrivilgeReq, VipPrivilegeResp.class);
            List<VipPrivilege> privilegeList = privilgeResp.getDataList();
            mav.addObject("privilegeList", privilegeList);

            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    @RequestMapping(value = "/member_mall.html", method = RequestMethod.GET)
    public ModelAndView member_mall(HttpSession session, HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("member/member_mall");
            UserBo obj = getInfoService.getUserBo(request);

            UserSlatBo loginUserBo=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_AMOUNT,null,UserSlatBo.class,obj.getId());
            UserBo userBo = loginUserBo.getUser();
            if(userBo != null) {
                mav.addObject("userAmount", userBo.getAmount());
            }
            UserTzxxRes userTzxxRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_TZXX,null, UserTzxxRes.class,obj.getId());
            mav.addObject("usertzxx",userTzxxRes.getData());

            GiftReq giftReq=new GiftReq();
            giftReq.setPage(0);
            giftReq.setSize(0);
            giftReq.setCategory(null);
            giftReq.setStatus("2");//上架
            giftReq.setName(null);
            //会员礼包列表
            GiftListResp giftListResp= SoaConnectionFactory.get(request, ConstantsUri.GIFT_LIST, giftReq, GiftListResp.class);
            mav.addObject("data",giftListResp.getDataList());
            mav.addObject("user",obj);

            //会员礼包申请列表
            UgiftApplyListReq applyListReq =new UgiftApplyListReq();
            applyListReq.setUserId(obj.getId());
            applyListReq.setPage(0);
            applyListReq.setSize(0);
            UgiftApplyListResp applyListResp= SoaConnectionFactory.get(request, ConstantsUri.GIFT_APPLY_LIST, applyListReq, UgiftApplyListResp.class);
            mav.addObject("applyList",applyListResp.getDataList());
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }
}
