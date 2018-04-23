package com.abc.controller;

import com.abc.application.SpringCtxHolder;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.PaginationReq;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.help.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author liuqi
 * @Date 2017/7/21 17:47
 */
@Controller
public class HelpController extends BaseController{

    private final static Logger logger = LoggerFactory.getLogger(HelpController.class);

    /* 我的收藏 GET */
    @GetMapping("/help/my/collect/list.html")
    public String collectList( PagerSpec pagerSpec, HttpServletRequest request,Model model){
        try {
            UserBo userBo=(UserBo) getInfoService.getUserBo(request);
            PaginationReq  req = new PaginationReq(pagerSpec.getCurrentPage(),pagerSpec.getPerPageNum());
            MyCollectListBORes res = SoaConnectionFactory.get(request, ConstantsUri.HELP_MY_COLLECTS, req, MyCollectListBORes.class, userBo.getId());
            pagerSpec.setTotalItems(res.getTotal());
            model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
            model.addAttribute("collects",res.getDataList());
            return "myHelp/collect/list";
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }
    /* 我的收藏 ajaxGET */
    @GetMapping("/help/my/collect/ajaxList.json")
    public @ResponseBody BaseResponse ajaxCollectList( PagerSpec pagerSpec, HttpServletRequest request,Model model){
        try {
            UserBo userBo=(UserBo) getInfoService.getUserBo(request);
            PaginationReq  req = new PaginationReq(pagerSpec.getCurrentPage(),pagerSpec.getPerPageNum());
            MyCollectListBORes res = SoaConnectionFactory.get(request, ConstantsUri.HELP_MY_COLLECTS, req, MyCollectListBORes.class, userBo.getId());
            return res;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                BaseResponse br=new BaseResponse("8888","服务器繁忙,请稍后再试...");
                return br;
            }else{
                BaseResponse br=new BaseResponse("7777","操作失败,请稍后再试...");
                return br;
            }
        }
    }
    /* 取消收藏 */
    @PostMapping("/help/my/collect/cancel/{askId}.json")
    public @ResponseBody BaseResponse cancelCollect(@PathVariable("askId") String askId, HttpServletRequest request){
        try {
            BaseResponse res = SoaConnectionFactory.deleteRestful(request, ConstantsUri.HELP_CANCEL_COLLECT, null, BaseResponse.class, askId);
            return res;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                BaseResponse br=new BaseResponse("8888","服务器繁忙,请稍后再试...");
                return br;
            }else{
                BaseResponse br=new BaseResponse("7777","操作失败,请稍后再试...");
                return br;
            }
        }
    }



    /* 我的粉丝 GET */
    @GetMapping("/help/my/fans/list.html")
    public String fansList( PagerSpec pagerSpec, HttpServletRequest request,Model model){
        try {
            UserBo userBo=(UserBo) getInfoService.getUserBo(request);
            PaginationReq  req = new PaginationReq(pagerSpec.getCurrentPage(),pagerSpec.getPerPageNum());
            MyFollowerListBORes res = SoaConnectionFactory.get(request, ConstantsUri.HELP_MY_FANS, req, MyFollowerListBORes.class, userBo.getId());
            pagerSpec.setTotalItems(res.getTotal());
            model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
            model.addAttribute("fans",res.getDataList());
            return "myHelp/fans/list";
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }
    /* 我的粉丝 ajaxGET */
    @GetMapping("/help/my/fans/ajaxList.json")
    public @ResponseBody BaseResponse ajaxfansList( PagerSpec pagerSpec, HttpServletRequest request,Model model){
        try {
            UserBo userBo=(UserBo) getInfoService.getUserBo(request);
            PaginationReq  req = new PaginationReq(pagerSpec.getCurrentPage(),pagerSpec.getPerPageNum());
            MyFollowerListBORes res = SoaConnectionFactory.get(request, ConstantsUri.HELP_MY_FANS, req, MyFollowerListBORes.class, userBo.getId());
            return res;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                BaseResponse br=new BaseResponse("8888","服务器繁忙,请稍后再试...");
                return br;
            }else{
                BaseResponse br=new BaseResponse("7777","操作失败,请稍后再试...");
                return br;
            }
        }
    }



    /* 我的关注 GET */
    @GetMapping("/help/my/follow/list.html")
    public String followList(PagerSpec pagerSpec, HttpServletRequest request,Model model){
        try {
            UserBo userBo=(UserBo) getInfoService.getUserBo(request);
            PaginationReq  req = new PaginationReq(pagerSpec.getCurrentPage(),pagerSpec.getPerPageNum());
            MyFollowListBORes res = SoaConnectionFactory.get(request, ConstantsUri.HELP_MY_FOLLOWS, req, MyFollowListBORes.class, userBo.getId());
            pagerSpec.setTotalItems(res.getTotal());
            model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
            model.addAttribute("follows",res.getDataList());
            return "myHelp/follow/list";
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }
    /* 我的关注 ajaxGET */
    @GetMapping("/help/my/follow/ajaxList.json")
    public @ResponseBody BaseResponse ajaxFollowList( PagerSpec pagerSpec, HttpServletRequest request,Model model){
        try {
            UserBo userBo=(UserBo) getInfoService.getUserBo(request);
            PaginationReq  req = new PaginationReq(pagerSpec.getCurrentPage(),pagerSpec.getPerPageNum());
            MyFollowListBORes res = SoaConnectionFactory.get(request, ConstantsUri.HELP_MY_FOLLOWS, req, MyFollowListBORes.class, userBo.getId());
            return res;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                BaseResponse br=new BaseResponse("8888","服务器繁忙,请稍后再试...");
                return br;
            }else{
                BaseResponse br=new BaseResponse("7777","操作失败,请稍后再试...");
                return br;
            }
        }
    }
    /* 取消关注 */
    @PostMapping("/help/my/follow/cancel/{followUserId}.json")
    public @ResponseBody BaseResponse cancelFollow( @PathVariable("followUserId") String followUserId, HttpServletRequest request){
        try {
            UserBo userBo=(UserBo) getInfoService.getUserBo(request);
            return SoaConnectionFactory.deleteRestful(request, ConstantsUri.HELP_CANCEL_FOLLOW, null, BaseResponse.class, followUserId);
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                BaseResponse br=new BaseResponse("8888","服务器繁忙,请稍后再试...");
                return br;
            }else{
                BaseResponse br=new BaseResponse("7777","操作失败,请稍后再试...");
                return br;
            }
        }
    }



    /* 我的团队 GET */
    @GetMapping("/help/my/team/list.html")
    public String teamList(PagerSpec pagerSpec, HttpServletRequest request,Model model){
        try {
            UserBo userBo=(UserBo) getInfoService.getUserBo(request);
            PaginationReq  req = new PaginationReq(pagerSpec.getCurrentPage(),pagerSpec.getPerPageNum());
            TeamBORes res = SoaConnectionFactory.get(request, ConstantsUri.HELP_MY_COLLECTS, req, TeamBORes.class, userBo.getId());
            pagerSpec.setTotalItems(res.getTotal());
            model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
            model.addAttribute("teams",res.getDataList());
            return "myHelp/team/list";
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }
    /* 我的团队 ajaxGET */
    @GetMapping("/help/my/team/ajaxList.json")
    public @ResponseBody BaseResponse ajaxTeamList( PagerSpec pagerSpec, HttpServletRequest request,Model model){
        try {
            UserBo userBo=(UserBo) getInfoService.getUserBo(request);
            PaginationReq  req = new PaginationReq(pagerSpec.getCurrentPage(),pagerSpec.getPerPageNum());
            TeamBORes res = SoaConnectionFactory.get(request, ConstantsUri.HELP_MY_COLLECTS, req, TeamBORes.class, userBo.getId());
            return res;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                BaseResponse br=new BaseResponse("8888","服务器繁忙,请稍后再试...");
                return br;
            }else{
                BaseResponse br=new BaseResponse("7777","操作失败,请稍后再试...");
                return br;
            }
        }
    }




    /* 我的私信 GET */
    @GetMapping("/help/my/privateMsg/list.html")
    public String privateMsgList(PagerSpec pagerSpec, HttpServletRequest request,Model model){
        try {
            LetterListBOListRes res = SoaConnectionFactory.getRestful(request, ConstantsUri.HELP_MY_PRIVATE_LETTERS, null, LetterListBOListRes.class);
            model.addAttribute("privateMsgs",res.getDataList());
            pagerSpec.setTotalItems(res.getTotal());
            model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
            return "myHelp/privateMsg/list";
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }
    /* 我的私信 ajaxGET */
    @GetMapping("/help/my/privateMsg/ajaxList.json")
    public @ResponseBody BaseResponse ajaxPrivateMsgList(UserBo userBo, PagerSpec pagerSpec, HttpServletRequest request,Model model){
//        PaginationReq  req = new PaginationReq(pagerSpec.getCurrentPage(),pagerSpec.getPerPageNum());
//        TeamBORes res = SoaConnectionFactory.getWithoutToken(request, ConstantsUri.HELP_MY_COLLECTS, req, TeamBORes.class, userBo.getId());
        return null;
    }

}
