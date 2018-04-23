package com.abc.controller;

import com.abc.cache.RedisCacheService;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.region.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 省市区
 * @Author liuqi
 * @Date 2017/5/25 15:43
 */
@Controller
public class RegionController extends BaseController{

    private final static Logger logger = LoggerFactory.getLogger(RegionController.class);

    /* 列表 GET */
    @GetMapping("/user/region/province/list.html")
    public ModelAndView provinceList(HttpServletRequest request, Model model){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            ProvinceListRs list = RedisCacheService.getRedisProvince(request,getInfoService);
            mav.addObject("list",list.getDataList());
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

    /* 列表 GET */
    @GetMapping("/user/region/city/list.html")
    public ModelAndView cityList(@RequestParam(value = "pid") String pid, HttpServletRequest request, Model model){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            CityListRs cityListRs = RedisCacheService.getRedisCity(request,getInfoService,pid);
            mav.addObject("list",cityListRs.getDataList());
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

    /* 列表 GET */
    @GetMapping("/user/region/county/list.html")
    public ModelAndView countyList(@RequestParam(value = "pid") String pid, HttpServletRequest request, Model model){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            AreaListRs areaListRs = RedisCacheService.getRedisArea(request,getInfoService,pid);
            mav.addObject("list",areaListRs.getDataList());
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
