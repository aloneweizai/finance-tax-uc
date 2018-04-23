package com.abc.controller.member;

import com.abc.bean.userinfo.GiftApplyBO;
import com.abc.bean.userinfo.UgiftApplyBO;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.DizhiIDBo;
import com.abc.soa.request.UserSlatBo;
import com.abc.soa.request.userinfo.AddressBoReq;
import com.abc.soa.request.userinfo.GiftReq;
import com.abc.soa.request.userinfo.UgiftApplyReq;
import com.abc.soa.response.*;
import com.abc.soa.response.userinfo.GiftListResp;
import com.abc.soa.response.userinfo.GiftResp;
import com.abc.soa.response.userinfo.UgiftApplyResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andy on 2017-12-25.
 */
@Controller
@RequestMapping(value = "/gift")
public class GiftController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(GiftController.class);

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ModelAndView member_mall(HttpServletRequest request,
                                    @RequestParam(value = "category",defaultValue = "")String category) {
        try {
            ModelAndView mav = new ModelAndView("member/member_mall_gift");
            UserBo obj = getInfoService.getUserBo(request);
            GiftReq giftReq=new GiftReq();
            giftReq.setPage(0);
            giftReq.setSize(0);
            if(isNull(category)) {
                giftReq.setCategory(category);
            }
            giftReq.setStatus("2");//上架
            giftReq.setName(null);
            //会员礼包列表
            GiftListResp giftListResp= SoaConnectionFactory.get(request, ConstantsUri.GIFT_LIST, giftReq, GiftListResp.class);
            mav.addObject("data",giftListResp.getDataList());
            mav.addObject("user",obj);

            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 会员礼包申请详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/apply/{applyId}", method = RequestMethod.GET)
    public ModelAndView gift_apply_detail(HttpServletRequest request,@PathVariable(value = "applyId")String applyId) {
        try {
            ModelAndView mav = new ModelAndView("member/mall_gift_apply_detail");
            UserBo obj = getInfoService.getUserBo(request);
            Map<String,Object> map = new HashMap<>();
            map.put("userId", obj.getId());
            map.put("applyId", applyId);
            UgiftApplyResp ugiftApplyResp= SoaConnectionFactory.getRestful(request, ConstantsUri.GIFT_APPLY_DETAIL, map, UgiftApplyResp.class, obj.getId(), applyId);
            UgiftApplyBO applyBO = ugiftApplyResp.getData();
            if(applyBO != null) {
                mav.addObject("apply", applyBO);
                mav.addObject("gifts", applyBO.getGiftApplyBOList());
                mav.addObject("logs", applyBO.getUgiftLogBOList());
                String id = applyBO.getGiftApplyBOList().get(0).getGiftId();
                GiftResp giftResp= SoaConnectionFactory.getRestful(request, ConstantsUri.GIFT_ID, null, GiftResp.class,id);
                mav.addObject("giftImg",giftResp.getData().getImageUrl());
            }

            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 会员礼包详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView gift_detail(HttpServletRequest request,@PathVariable(value = "id")String id) {
        try {
            ModelAndView mav = new ModelAndView("member/mall_gift_detail");
            UserBo obj = getInfoService.getUserBo(request);

            UserSlatBo loginUserBo=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_AMOUNT,null,UserSlatBo.class,obj.getId());
            UserBo userBo = loginUserBo.getUser();
            if(userBo != null) {
                mav.addObject("userAmount", userBo.getAmount()==null? 0:userBo.getAmount());
            }

            GiftResp giftResp= SoaConnectionFactory.getRestful(request, ConstantsUri.GIFT_ID, null, GiftResp.class,id);
            mav.addObject("data",giftResp.getData());
            mav.addObject("user",obj);

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
     * 会员礼包申请
     * @param request
     * @return
     */
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public ModelAndView gift_apply(HttpServletRequest request,@RequestBody String remark,
                                   @RequestParam(value = "giftId")String giftId,
                                   @RequestParam(value = "addressId")String addressId) {
        try {
            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
            UserBo obj = getInfoService.getUserBo(request);

            UgiftApplyReq applyReq = new UgiftApplyReq();
            applyReq.setUserId(obj.getId());
            if(isNull(remark)&& !remark.equals("\"\"")) {
                applyReq.setRemark(remark);
            }
            List<GiftApplyBO> applyBOList= new ArrayList<>();
            GiftApplyBO giftApplyBO = new GiftApplyBO();
            giftApplyBO.setGiftNum(1);
            giftApplyBO.setGiftId(giftId);
            applyBOList.add(giftApplyBO);
            applyReq.setGiftApplyBOList(applyBOList);
            //添加地址
            if(addressId != null ) {
                AddressBoReq addressBoReq = new AddressBoReq();
                addressBoReq.setAddressId(addressId);
                AddressRes addressRes = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS, addressBoReq, AddressRes.class, obj.getId());
                List<AddressBo> addressBoList = addressRes.getDataList();
                for(AddressBo addressBo : addressBoList){
                    if(addressId .equals(addressBo.getId())) {
                        applyReq.setName(addressBo.getName());
                        applyReq.setPhone(addressBo.getPhone());
                        String shipAddress = addressBo.getProvinceName() + addressBo.getCityName() + addressBo.getAreaName() + addressBo.getDetail();
                        applyReq.setAddress(shipAddress);
                    }
                }
            }
            BaseResponse baseResponse= SoaConnectionFactory.post(request, ConstantsUri.GIFT_APPLY, applyReq, BaseResponse.class, obj.getId(), giftId);
            mav.addObject("data",baseResponse);

            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }


    /**
     * 会员礼包确认收货
     * @param request
     * @return
     */
    @RequestMapping(value = "/receive/{id}", method = RequestMethod.POST)
    public ModelAndView gift_receive(HttpServletRequest request,@PathVariable(value = "id")String id) {
        try {
            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
            BaseResponse response= SoaConnectionFactory.put(request, ConstantsUri.GIFT_RECEIVE, null, BaseResponse.class,id);
            mav.addObject("data", response);
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

}
