package com.abc.controller.member;

import com.abc.bean.userinfo.Points;
import com.abc.bean.userinfo.UserExp;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.controller.userinfo.UserAddrController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.PaginationReq;
import com.abc.soa.request.userinfo.CheckInReq;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.userinfo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:zlk
 * @Description:用户签到
 * @Date:2017-08-22
 * @Time:10:25
 */
@Controller
@RequestMapping(value = "/member")
public class UserCheckInController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(UserCheckInController.class);

    @GetMapping(value = "/checkIn.php")
    public ModelAndView list(HttpServletRequest request,HttpSession session){
        try {
            ModelAndView mav = new ModelAndView("member/member_checkIn");
            PaginationReq paginationReq = new PaginationReq();
            paginationReq.setPage(1);
            paginationReq.setSize(16);
            CheckRankResp checkRankResp = SoaConnectionFactory.get(request, ConstantsUri.UC_CHECKIN_RANK, paginationReq, CheckRankResp.class);
            mav.addObject("ranks",checkRankResp.getDataList());

            //查询补签需消耗的积分
            PrecheckBoResp precheckBoResp = SoaConnectionFactory.get(request, ConstantsUri.UC_RECHECK_POINT, null, PrecheckBoResp.class,"P-recheck");
            if(precheckBoResp !=null && precheckBoResp.getData()!=null) {
                mav.addObject("precheck", Math.abs(precheckBoResp.getData().getPoints()));
            }else{
                mav.addObject("precheck", 0);
            }
            mav.addObject("year",Calendar.getInstance().get(Calendar.YEAR)+"");
            int month = Calendar.getInstance().get(Calendar.MONTH);
            mav.addObject("month",month);
            int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            mav.addObject("day",day);
            return mav;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 当月已签到的日期
     * @param yearMonth
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/checkIn/{yearMonth}",method = RequestMethod.GET)
    public ModelAndView list(@PathVariable(value = "yearMonth")String yearMonth,HttpServletRequest request,HttpSession session){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try {
            CheckInReq checkInReq = new CheckInReq();
            checkInReq.setYearMonth(yearMonth);
            CheckInResp checkInResp = SoaConnectionFactory.get(request, ConstantsUri.UC_CHECKIN_LIST, checkInReq, CheckInResp.class);
            //mav.addObject("checks",checkInResp.getDataList());
            mav.addObject("data",checkInResp);
        } catch (SoaException e) {
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
     * 签到
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkForId.html",method = RequestMethod.POST)
    public ModelAndView checkIn(HttpServletRequest request,@RequestParam(value = "date")String date){
        UserBo userBo = getInfoService.getUserBo(request);
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(userBo != null) {
                String id = userBo.getId();
                CheckInReq checkInReq = new CheckInReq();
                checkInReq.setUserId(id);
                checkInReq.setDate(date);
                ReCheckBO reCheckBO = SoaConnectionFactory.post(request, ConstantsUri.UC_CHECKIN, checkInReq, ReCheckBO.class);
                mav.addObject("data", reCheckBO);

                //刷新session
                if("2000".equals(reCheckBO.getCode())){
                    //我的积分、经验值
                    UserPointsResp userPointsResp = SoaConnectionFactory.getRestful(request, ConstantsUri.POINTS, null, UserPointsResp.class,userBo.getId());
                    Points points= userPointsResp.getData();
                    int points_count = 0;
                    if(points!=null){
                        points_count=points.getMyPoints();
                    }
                    mav.addObject("points", points_count);
                    UserExpResp userExpResp = SoaConnectionFactory.getRestful(request, ConstantsUri.UEXP_ID, null, UserExpResp.class,userBo.getId());
                    UserExp userExp = userExpResp.getData();
                    int exp=0;
                    if(userExp!=null){
                        exp= userExp.getExp();
                    }
                    mav.addObject("userExp", exp);
                    if(userBo != null){
                        if(points != null) {
                            userBo.setPoints(BigInteger.valueOf(points.getMyPoints()));
                        }
                        if(userExp != null) {
                            userBo.setExp(BigInteger.valueOf(userExp.getExp()));
                        }
                    }
                    getInfoService.setUserBo(request,userBo);
                }
            }
        } catch (SoaException e) {
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
     * 补签
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/check/{date}",method = RequestMethod.POST)
    public ModelAndView reCheck(@PathVariable(value = "date")String date, HttpServletRequest request,HttpSession session){
        UserBo userBo = getInfoService.getUserBo(request);
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(userBo != null) {
                String id = userBo.getId();
                CheckInReq checkInReq = new CheckInReq();
                checkInReq.setUserId(id);
                checkInReq.setDate(date);
                BaseResponse baseResponse = SoaConnectionFactory.post(request, ConstantsUri.UC_RECHECKIN, checkInReq, BaseResponse.class);
                mav.addObject("data", baseResponse);

                //刷新session
                if("2000".equals(baseResponse.getCode())){
                    //我的积分、经验值
                    UserPointsResp userPointsResp = SoaConnectionFactory.getRestful(request, ConstantsUri.POINTS, null, UserPointsResp.class,userBo.getId());
                    Points points= userPointsResp.getData();
                    int points_count = 0;
                    if(points!=null){
                        points_count=points.getMyPoints();
                    }
                    mav.addObject("points", points_count);
                    UserExpResp userExpResp = SoaConnectionFactory.getRestful(request, ConstantsUri.UEXP_ID, null, UserExpResp.class,userBo.getId());
                    UserExp userExp = userExpResp.getData();
                    int exp=0;
                    if(userExp!=null){
                        exp= userExp.getExp();
                    }
                    mav.addObject("userExp", exp);
                    if(userBo != null){
                        if(points != null) {
                            userBo.setPoints(BigInteger.valueOf(points.getMyPoints()));
                        }
                        if(userExp != null) {
                            userBo.setExp(BigInteger.valueOf(userExp.getExp()));
                        }
                    }
                    getInfoService.setUserBo(request,userBo);
                }
            }
        } catch (SoaException e) {
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
