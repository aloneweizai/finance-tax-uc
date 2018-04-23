package com.abc.controller.userinfo;

import com.abc.application.SpringCtxHolder;
import com.abc.bean.userinfo.ExpLog;
import com.abc.bean.userinfo.ExpLogDate;
import com.abc.bean.userinfo.ExpLogUcBO;
import com.abc.bean.userinfo.ExpRuleBO;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.LevelReq;
import com.abc.soa.request.PaginationReq;
import com.abc.soa.request.userinfo.ExpListReq;
import com.abc.soa.request.userinfo.RuleReq;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.userinfo.ExpForDateResp;
import com.abc.soa.response.userinfo.ExpRuleListResp;
import com.abc.soa.response.userinfo.UserExpLogResp;
import com.abc.soa.response.userinfo.UserExpResp;
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
 * Created by zlk on 2017-07-24.
 */
@Controller
public class UserExpController extends BaseController{
    private final static Logger logger = LoggerFactory.getLogger(UserExpController.class);

    @GetMapping("/userinfo/expLog.php")
    public String list(PagerSpec pagerSpec, HttpSession session, HttpServletRequest request, Model model) {
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            String userId = userBo.getId();

            //我的经验值
            UserExpResp userExpResp = SoaConnectionFactory.getRestful(request, ConstantsUri.UEXP_ID, null, UserExpResp.class,userId);
            model.addAttribute("userExp",userExpResp.getData());

            //经验值日志列表
            ExpLogDate expLogDate = new ExpLogDate();
            expLogDate.setStart(null);
            expLogDate.setEnd(null);
            expLogDate.setPage(pagerSpec.getCurrentPage());
            expLogDate.setSize(pagerSpec.getPerPageNum());
            ExpForDateResp expForDateResp = SoaConnectionFactory.get(request, ConstantsUri.UEXP_FOR_DATE, expLogDate, ExpForDateResp.class);
            List<ExpLogUcBO> expLogList = expForDateResp.getDataList();
            model.addAttribute("expLogList", expLogList);

            pagerSpec.setTotalItems(expForDateResp.getTotal());
            model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
            return "userinfo/expLog";
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }

    /**
     * 局部刷新列表
     * @param expLogDate
     * @param pagerSpec
     * @param model
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/userinfo/expForDate.html",method = RequestMethod.POST)
    public ModelAndView expLogForDate(@RequestBody ExpLogDate expLogDate,PagerSpec pagerSpec,Model model,
                                      HttpSession session,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView(),null);
        try {
            ExpForDateResp expForDateResp = SoaConnectionFactory.get(request, ConstantsUri.UEXP_FOR_DATE, expLogDate, ExpForDateResp.class);
            modelAndView.addObject("data",expForDateResp);

            //PaginationReq req = new PaginationReq(pagerSpec.getCurrentPage(),pagerSpec.getPerPageNum());
            pagerSpec.setTotalItems(expForDateResp.getTotal());
            pagerSpec.setPerPageNum(3);
            modelAndView.addObject("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
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

    /**
     * 全局刷新页面
     * @param pagerSpec
     * @param model
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/userinfo/expLog",method = RequestMethod.GET)
    public ModelAndView expAllLogForDate(@RequestParam(value = "start",required = false)String start,
                                         @RequestParam(value = "end",required = false)String end,
                                         PagerSpec pagerSpec,Model model,
                                         HttpSession session,HttpServletRequest request){
        try {
            ModelAndView mav = new ModelAndView("/userinfo/expLog_all");
            UserBo userBo = getInfoService.getUserBo(request);
            String userId = userBo.getId();
            //String name = userBo.getUsername();
            ExpListReq expListRep = new ExpListReq();
            expListRep.setUserId(userId);
            expListRep.setPage(pagerSpec.getCurrentPage());
            expListRep.setSize(pagerSpec.getPerPageNum());

            //我的经验值
            UserExpResp userExpResp = SoaConnectionFactory.getRestful(request, ConstantsUri.UEXP_ID, null, UserExpResp.class,userId);
            mav.addObject("userExp",userExpResp.getData());

            ExpLogDate expLogDate = new ExpLogDate();
            expLogDate.setStart(start);
            expLogDate.setEnd(end);
            expLogDate.setPage(pagerSpec.getCurrentPage());
            expLogDate.setSize(pagerSpec.getPerPageNum());
            ExpForDateResp expForDateResp = SoaConnectionFactory.get(request, ConstantsUri.UEXP_FOR_DATE, expLogDate, ExpForDateResp.class);
            List<ExpLogUcBO> expLogList = expForDateResp.getDataList();
            mav.addObject("expLogList", expLogList);
            mav.addObject("start",start);
            mav.addObject("end",end);

            pagerSpec.setTotalItems(expForDateResp.getTotal());
            mav.addObject("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
            return mav;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }


    @RequestMapping(value = "/userinfo/user_exp_rule.html",method = RequestMethod.GET)
    public ModelAndView rule(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("userinfo/user_exp_rule");
        try {
            RuleReq ruleReq = new RuleReq();
            ruleReq.setPage(0);
            ruleReq.setSize(0);
            ruleReq.setStatus("1");//启用
            ExpRuleListResp ruleListResp = SoaConnectionFactory.get(request, ConstantsUri.UEXP_RULE, ruleReq, ExpRuleListResp.class);
            List<ExpRuleBO> expRuleBOList = ruleListResp.getDataList();
            mav.addObject("expRules",expRuleBOList);
        }catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            mav= new ModelAndView("soaerror");
            return mav;
        }
        return mav;
    }

}
