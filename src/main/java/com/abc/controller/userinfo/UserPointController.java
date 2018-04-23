package com.abc.controller.userinfo;

import com.abc.application.SpringCtxHolder;
import com.abc.bean.userinfo.*;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.LevelReq;
import com.abc.soa.request.PaginationReq;
import com.abc.soa.request.userinfo.CompleteOrderReq;
import com.abc.soa.request.userinfo.PointLogReq;
import com.abc.soa.request.userinfo.RuleReq;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.userinfo.*;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zlk on 2017-07-20.
 */
@Controller
@RequestMapping(value = "/pointsExchange")
public class UserPointController extends BaseController{
    private final static Logger logger = LoggerFactory.getLogger(UserOrderController.class);

    //积分兑换查询
    private String tradeMethod = "POINTS";

    /**
     * 我的积分
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/points.php",method = RequestMethod.GET)
    public String list(HttpServletRequest request,Model model,
                       @RequestParam(value = "time1",required = false,defaultValue = "")String time1,
                       @RequestParam(value = "time2",required = false,defaultValue = "")String time2,
                       @RequestParam(value = "type",required = false,defaultValue = "01")String type,
                       PagerSpec pagerSpec1,PagerSpec pagerSpec2){
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            String userId = userBo.getId();
            UserPointsResp userPointsResp = SoaConnectionFactory.getRestful(request, ConstantsUri.POINTS, null, UserPointsResp.class, userId);
            model.addAttribute("points",userPointsResp.getData());
            if("01".equals(type)){
                ExpLogDate expLogDate = new ExpLogDate();
                if(isNull(time1)){
                    expLogDate.setStart(time1);
                }
                if(isNull(time2)){
                    expLogDate.setEnd(time2);
                }
                expLogDate.setPage(pagerSpec1.getCurrentPage());
                expLogDate.setSize(pagerSpec1.getPerPageNum());
                PointsForDateResp pointForDateResp = SoaConnectionFactory.get(request, ConstantsUri.POINTS_FOR_DATE, expLogDate, PointsForDateResp.class);
                model.addAttribute("PointsLogs", pointForDateResp.getDataList());
                //积分分页
                pagerSpec1.setTotalItems(pointForDateResp.getTotal());
                model.addAttribute("pagerSpec1", PagerUtil.calculatePagerSpec(pagerSpec1));
            }else{
                //积分兑换记录
                CompleteOrderReq orderListReq =  new CompleteOrderReq();
                orderListReq.setPage(pagerSpec2.getCurrentPage());
                orderListReq.setSize(pagerSpec2.getPerPageNum());
                orderListReq.setUserId(userId);
                orderListReq.setStatus("6");//订单状态为已完成
                if(isNull(time1)) {
                    orderListReq.setStartTime(time1);
                }
                if (isNull(time2)) {
                    orderListReq.setEndTime(time2);
                }
                orderListReq.setTradeMethod(tradeMethod);
                OrderListResp orders = SoaConnectionFactory.get(request, ConstantsUri.ORDER_COMPLETED, orderListReq, OrderListResp.class);
                List<OrderBO> orderList = JSON.parseArray(orders.getDataList(), OrderBO.class);
                model.addAttribute("orderList",orderList);
                //兑换分页
                pagerSpec2.setTotalItems(orders.getTotal());
                model.addAttribute("pagerSpec2", PagerUtil.calculatePagerSpec(pagerSpec2));
            }
            //我的积分
            model.addAttribute("type",type);



            model.addAttribute("picurl",SpringCtxHolder.getProperty("imagedomain"));


            return "userinfo/points";
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }

    /**
     * 根据日期查询积分明细记录
     * @param start
     * @param end
     * @param request
     * @return
     */
    @RequestMapping(value = "/pointsDate",method = RequestMethod.GET)
    public ModelAndView pointsLogRefresh(@RequestParam(value = "start",required = false)String start,
                                         @RequestParam(value = "end",required = false)String end,
                                         PagerSpec pagerSpec,HttpServletRequest request){
        try {
            ModelAndView mav = new ModelAndView("userinfo/points_refresh");
            UserBo userBo = getInfoService.getUserBo(request);
            String userId = userBo.getId();

            //我的积分
            UserPointsResp userPointsResp = SoaConnectionFactory.getRestful(request, ConstantsUri.POINTS, null, UserPointsResp.class,userId);
            mav.addObject("points", userPointsResp.getData());

            ExpLogDate expLogDate = new ExpLogDate();
            expLogDate.setStart(start);
            expLogDate.setEnd(end);
            expLogDate.setPage(pagerSpec.getCurrentPage());
            expLogDate.setSize(pagerSpec.getPerPageNum());
            PointsForDateResp pointForDateResp = SoaConnectionFactory.get(request, ConstantsUri.POINTS_FOR_DATE, expLogDate, PointsForDateResp.class);
            mav.addObject("PointsLogs",pointForDateResp.getDataList());
            mav.addObject("start",start);
            mav.addObject("end",end);

            PagerSpec pagerSpec1 = new PagerSpec();
            pagerSpec1.setTotalItems(pointForDateResp.getTotal());
            mav.addObject("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec1));

            return mav;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 根据日期查询积分兑换记录,局部刷新
     * @param request
     * @return
     */
    @RequestMapping(value = "/exchangeDate",method = RequestMethod.POST)
    public ModelAndView duiHuanForDate(@RequestParam(value = "start",required = false)String start,
                                       @RequestParam(value = "end",required = false)String end,
                                       PagerSpec pagerSpec,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/userinfo/exchange_refresh");
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            String userId = userBo.getId();
            CompleteOrderReq orderListReq = new CompleteOrderReq();
            orderListReq.setStartTime(start);
            orderListReq.setEndTime(end);
            orderListReq.setPage(pagerSpec.getCurrentPage());
            orderListReq.setSize(pagerSpec.getPerPageNum());
            orderListReq.setUserId(userId);
            //订单状态为已完成
            orderListReq.setStatus("6");
            orderListReq.setTradeMethod(tradeMethod);
            OrderListResp orders = SoaConnectionFactory.get(request, ConstantsUri.ORDER_COMPLETED, orderListReq, OrderListResp.class);
            List<OrderBO> orderList = JSON.parseArray(orders.getDataList(), OrderBO.class);
            modelAndView.addObject("data", orderList);

            PagerSpec pagerSpec2 = new PagerSpec();
            pagerSpec2.setTotalItems(orders.getTotal());
            modelAndView.addObject("pagerSpec2", PagerUtil.calculatePagerSpec(pagerSpec2));
            modelAndView.addObject("start",start);
            modelAndView.addObject("end",end);
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                modelAndView.addObject("soacode","8888");
                modelAndView.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                modelAndView.addObject("soacode","7777");
                modelAndView.addObject("message","操作失败,请稍后再试...");
            }
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/user_point_rule.html",method = RequestMethod.GET)
    public ModelAndView rule(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("userinfo/user_point_rule");
        try {
            RuleReq ruleReq = new RuleReq();
            ruleReq.setPage(0);
            ruleReq.setSize(0);
            ruleReq.setStatus("1");//启用
            PointRuleListResp ruleListResp = SoaConnectionFactory.get(request, ConstantsUri.POINT_RULE, ruleReq, PointRuleListResp.class);
            List<PointRuleBO> pointRuleBOList = ruleListResp.getDataList();
            mav.addObject("pointRules",pointRuleBOList);
        }catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            mav= new ModelAndView("soaerror");
            return mav;
        }
        return mav;
    }
}
