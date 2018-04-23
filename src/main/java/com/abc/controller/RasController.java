package com.abc.controller;

import com.abc.cache.RedisCacheService;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.GetRsaKey;
import com.abc.common.util.RSA;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by stuy on 2017/8/11.
 */
@Controller
public class RasController extends BaseController{

    private final static Logger logger = LoggerFactory.getLogger(RasController.class);

    /**
     * 财税客户端获取加密key
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/rsa.html")
    public ModelAndView clientjump(
            HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        RSAPublicKey publicKey = null;
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            publicKey= RedisCacheService.getRedisPublicKey(request,getInfoService);
//        publicKey.toString()
            if(publicKey!=null){
                mav.addObject("code","2000");
                mav.addObject("message","生成公钥成功");
                mav.addObject("format",publicKey.getFormat());
                mav.addObject("algorithm",publicKey.getAlgorithm());
                mav.addObject("modulus",new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
                mav.addObject("exponent",new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
            }else{
                mav.addObject("code","1000");
                mav.addObject("message","生成公钥失败");
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
     * 财税客户端获取加密key
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/rsa_v2.html")
    public ModelAndView clientjump_v2(
            HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        RSAPublicKey publicKey = null;
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            publicKey= RedisCacheService.getRedisV2PublicKey(request,getInfoService);
//        publicKey.toString()
            if(publicKey!=null){
                mav.addObject("code","2000");
                mav.addObject("message","生成公钥成功");
                mav.addObject("format",publicKey.getFormat());
                mav.addObject("algorithm",publicKey.getAlgorithm());
                mav.addObject("modulus",new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
                mav.addObject("exponent",new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
            }else{
                mav.addObject("code","1000");
                mav.addObject("message","生成公钥失败");
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
