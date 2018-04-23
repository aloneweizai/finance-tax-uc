package com.abc.controller;

import com.abc.common.util.ValidateCode;
import com.abc.service.RedisCode;
import com.abc.soa.request.CodeTimeBo;
import com.abc.soa.response.ImgCodeRes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by stuy on 2017/7/18.
 */
@Controller
public class ValiDateCodeController extends BaseController{




    /**
     * 图片验证码请求
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/validatecode.html", method = RequestMethod.GET)
    public String batchDel(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        ValidateCode vCode = new ValidateCode(80,30,4,60);
        CodeTimeBo codeTimeBo=new CodeTimeBo();
        codeTimeBo.setCode(vCode.getCode());
        codeTimeBo.setTime(System.currentTimeMillis());
        String code= RedisCode.CODE+":"+session.getId();
        getInfoService.setString(2*60,code,vCode.getCode());
        try {
            vCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 财税客户端图片验证码请求
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/validatecode.html", method = RequestMethod.POST)
    public ModelAndView validatecode(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        ValidateCode vCode = new ValidateCode(80,30,4,60);
        ImgCodeRes imgCodeRes=new ImgCodeRes();
        imgCodeRes.setCode(vCode.getCode());
        imgCodeRes.setBase64(vCode.write());
        mav.addObject("base64",imgCodeRes.getBase64());
        mav.addObject("code",imgCodeRes.getCode());
        return mav;
    }
}
