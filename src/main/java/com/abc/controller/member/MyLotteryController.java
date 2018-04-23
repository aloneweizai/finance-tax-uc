package com.abc.controller.member;

import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.DateUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.AddressBo;
import com.abc.soa.response.AddressRes;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.UserTzxxRes;
import com.abc.soa.response.activity.lottery.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:zlk
 * @Description:我的中奖记录
 * @Date:2017-08-15
 * @Time:18:08
 */
@Controller
@RequestMapping(value = "/member")
public class MyLotteryController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(MyLotteryController.class);

    /**
     *查看中奖记录
     * @param session
     * @param request
     * @param lotteryLogId
     * @return
     */
    @GetMapping("/prizeEdit.html")
    public ModelAndView prizeEdit(HttpSession session, HttpServletRequest request, String lotteryLogId) {
        ModelAndView mav = new ModelAndView("userinfo/prizeEdit");
        if (lotteryLogId != null) {
            LotteryLogRs lotteryLogRs = null;
            try {
                lotteryLogRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYLOG_INFO, null, LotteryLogRs.class, lotteryLogId);
                mav.addObject("lotteryLogBo", lotteryLogRs.getData());
            } catch (SoaException e) {
                e.printStackTrace();
            }
            LotteryLogBO lotteryLogBO =null;
            if(lotteryLogRs!=null){
                lotteryLogBO = lotteryLogRs.getData();
            }
            String  addressId =null;
            if (lotteryLogBO != null && lotteryLogBO.getActivityId() != null) {
                LotteryActivityRs lotteryActivityRs = null;
                try {
                    lotteryActivityRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYACTIVITY_LIST, null, LotteryActivityRs.class);
                } catch (SoaException e) {
                    e.printStackTrace();
                }
                if(lotteryActivityRs!=null){
                    for (LotteryActivityBO obj : lotteryActivityRs.getDataList()) {
                        if (lotteryLogBO.getActivityId().equals(obj.getId())) {
                            mav.addObject("lotteryActivityBO", obj);
                            break;
                        }
                    }
                }
                addressId = lotteryLogBO.getAddressId();
            }
            if(addressId != null && !addressId.isEmpty()){
                UserBo userBo = getInfoService.getUserBo(request);
                try {
                    AddressRes addressRes = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS, null, AddressRes.class, userBo.getId());
                    List<AddressBo> addressBoList = addressRes.getDataList();
                    if (addressBoList != null) {
                        for (AddressBo obj : addressBoList) {
                            if (addressId.equals(obj.getId())) {
                                mav.addObject("userAddressBO", obj);
                                break;
                            }
                        }
                    }

                } catch (SoaException e) {
                    e.printStackTrace();
                }
            }
        }
        return mav;
    }

    /**
     * 领取奖品填写地址
     * @param session
     * @param request
     * @param lotteryLogId
     * @return
     */
    @GetMapping("/prizeAddress.html")
    public ModelAndView prizeAddress(HttpSession session, HttpServletRequest request, String lotteryLogId) {
        ModelAndView mav = new ModelAndView("userinfo/prizeAddress");

        if (lotteryLogId != null) {
            LotteryLogRs lotteryLogRs = null;
            try {
                lotteryLogRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYLOG_INFO, null, LotteryLogRs.class, lotteryLogId);
                mav.addObject("lotteryLogBo", lotteryLogRs.getData());
            } catch (SoaException e) {
                e.printStackTrace();
            }
            LotteryLogBO lotteryLogBO =null;
            if(lotteryLogRs!=null){
                lotteryLogBO = lotteryLogRs.getData();
            }
            if (lotteryLogBO != null && lotteryLogBO.getActivityId() != null) {
                LotteryActivityRs lotteryActivityRs = null;
                try {
                    lotteryActivityRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYACTIVITY_LIST, null, LotteryActivityRs.class);
                } catch (SoaException e) {
                    e.printStackTrace();
                }
                if(lotteryActivityRs!=null){
                    for (LotteryActivityBO obj : lotteryActivityRs.getDataList()) {
                        if (lotteryLogBO.getActivityId().equals(obj.getId())) {
                            mav.addObject("lotteryActivityBO", obj);
                            break;
                        }
                    }
                }
            }
        }
        UserBo userBo = getInfoService.getUserBo(request);
        try {
            AddressRes addressRes = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS, null, AddressRes.class, userBo.getId());
            mav.addObject("addressRes", addressRes.getDataList());
        } catch (SoaException e) {
            e.printStackTrace();
        }


        return mav;
    }

    /**
     * 提交领取信息
     * @param lotteryLogId
     * @param addressId
     * @param address
     * @param sendName
     * @param request
     * @return
     */
    @PostMapping("/getLottery.html")
    public ModelAndView lotteryActivityprizeSave(@RequestParam(required = true) String lotteryLogId, @RequestParam(required = false) String addressId,
                                                 @RequestParam(required = false) String address,@RequestParam(required = false) String sendName,
                                                 @RequestParam(required = false) String phone,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        LotteryLogRs returnObj = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lotteryLogId", lotteryLogId);
        if (addressId != null && !addressId.isEmpty()){
            map.put("addressId", addressId);
        }
        if (address != null && !address.isEmpty()) {
            map.put("address", address);
        }
        if (sendName != null && !sendName.isEmpty()) {
            map.put("sendName", sendName);
        }
        if(phone !=null && !phone.isEmpty()){
            map.put("phone",phone);
        }
        try {
            returnObj = SoaConnectionFactory.get(request, ConstantsUri.USER_GETLOTTERY, map, LotteryLogRs.class);
        } catch (SoaException e) {
            e.printStackTrace();
        }

        mav.addObject("result", returnObj);
        return mav;

    }

    /**
     * 中奖记录
     * @param session
     * @param request
     * @param datel
     * @param dater
     * @return
     */
    @GetMapping("/prize.html")
    public ModelAndView member_integral(HttpSession session, HttpServletRequest request,@RequestParam(required = false) String datel,@RequestParam(required = false) String dater) {
        try {
            ModelAndView mav = new ModelAndView("userinfo/prize");

            Object obj = getInfoService.getUserBo(request);
            if (obj != null) {
                UserBo loginUserBo = (UserBo) obj;
                mav.addObject("user", loginUserBo);
                UserTzxxRes userTzxxRes = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_TZXX, null, UserTzxxRes.class, loginUserBo.getId());
                mav.addObject("usertzxx", userTzxxRes.getData());

                LotteryLogRq lotteryLogRq = new LotteryLogRq();
                lotteryLogRq.setIsluck(1);
                lotteryLogRq.setUserId(loginUserBo.getId());
                LotteryLogRs lotteryLogRs = null;
                lotteryLogRq.setSize(0);
                if(datel != null){
                    lotteryLogRq.setStartTime(datel);

                }
                if(dater != null)    {
                    lotteryLogRq.setEndTime(dater);
                }
                mav.addObject("datel", datel);
                mav.addObject("dater", dater);
                try {
                    lotteryLogRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYLOG_LIST, lotteryLogRq, LotteryLogRs.class);
                } catch (SoaException e) {
                    e.printStackTrace();
                }
                List<LotteryLogBO> list=null;
                if(lotteryLogRs!=null) {
                    list = lotteryLogRs.getDataList();
                }
//                for (LotteryLogBO llog : list) {
//                    if (llog.getCreateTime() != null) {
//                        Date nDate = DateUtil.addDays(llog.getCreateTime(), 3);
//                        llog.setEndTime(nDate);
//                    }
//                }
                mav.addObject("logRs", list);
            }


            return mav;
        } catch (Exception e) {
            logger.debug("异常:" + e.getMessage());
            ModelAndView mav = new ModelAndView("soaerror");
            return mav;
        }
    }
}
