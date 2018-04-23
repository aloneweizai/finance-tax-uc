package com.abc.controller;

import com.abc.application.SpringCtxHolder;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CodeUtil;
import com.abc.common.util.DesCryptUtil;
import com.abc.service.RedisCode;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.CompanyBo;
import com.abc.soa.response.CompanySoaBo;
import com.abc.soa.response.UserBo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by stuy on 2018/2/9.
 */
@Controller
@RequestMapping(value = "/company")
public class CompanyController extends BaseController {

    @Autowired
    DesCryptUtil dcu;

    /**
     * 企业
     * @param request
     * @return
     */
    @GetMapping
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mav= new ModelAndView("company");
        return mav;
    }


    /**
     * 生成在线支付页面链接，可保持登录状态
     * @param request
     * @return
     */
    @GetMapping("/toPayServiceCharge")
    public ModelAndView toPayServiceCharge(@RequestParam(value = "nsrsbh", required = false ,defaultValue = "") String nsrsbh, HttpServletRequest request, HttpServletResponse response   ){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);

        String userToken = "";
        String accessToken ="";
        String userId = "";
        UserBo userbo = getInfoService.getUserBo(request);
        if(userbo!=null) {
            Map<String, String> map = this.getInfoService.getCookie(request);
            String usertoken = this.getInfoService.getRedisKey(map, RedisCode.USER_TOKEN);
            userToken = this.getInfoService.getString(usertoken);
            if(userToken==null){
                return mav.addObject("toUrl",SpringCtxHolder.getProperty("SCP_URL"));
            }
        }else{
            return mav.addObject("toUrl",SpringCtxHolder.getProperty("SCP_URL"));
        }
        if(userbo!=null) {
            ServletContext servletcontext = request.getSession().getServletContext();
            try {
                accessToken=(String)servletcontext.getAttribute("accessToken");
            } catch (Exception e) {
                return mav.addObject("toUrl",SpringCtxHolder.getProperty("SCP_URL"));
            }
        }else{
            return mav.addObject("toUrl",SpringCtxHolder.getProperty("SCP_URL"));
        }

        if(userToken != null && userId!=null && userToken.length()>0 && userId.length()>0 && accessToken!=null ){
            try {
//                String content = "8290c10db7fb751bb8d0bf47e15ea4c1|4a8e0160a0a94bfab6bc2bbc820c1b90|457aafc7cf834b7ea32418277e689c4d";
                String content = accessToken + "|" + userToken + "|" + userId ;
//                log.debug("跳转支付页面content="+content);
                byte[] strTemp = dcu.symmetricEncrypt(SpringCtxHolder.getProperty("SCP_CRYPT").getBytes(), content.getBytes() );
                String encodeStr = CodeUtil.encodeContent("BASE64", strTemp);
//                log.debug("跳转支付页面encodeStr="+encodeStr);

                if (strTemp != null) {
//                    return mav.addObject("toUrl", SpringCtxHolder.getProperty("SCP_URL")+"/abcolp/OrderFWNF.aspx?p="+ URLEncoder.encode(encodeStr,"utf-8"));
                    return mav.addObject("toUrl", SpringCtxHolder.getProperty("SCP_URL")+"/OrderFWNF.aspx?p="+ URLEncoder.encode(encodeStr, "utf-8")+"&nsrsbh="+nsrsbh);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                return mav.addObject("toUrl",SpringCtxHolder.getProperty("SCP_URL"));
            }
        }
        return mav.addObject("toUrl",SpringCtxHolder.getProperty("SCP_URL"));
    }

    /**
     * 汇算清缴企业注册
     * @param request
     * @return
     */
    @PostMapping("/save.html")
    public ModelAndView save(@RequestBody CompanyBo companyBo, HttpServletRequest request) {
        ModelAndView mav= new ModelAndView(new MappingJackson2JsonView(), null);
        if(isNull(companyBo.getNsrsbh())&&isNull(companyBo.getNsrqymc())){
            try {
                CompanySoaBo companySoaBo=new CompanySoaBo();
                companySoaBo.setNsrmc(companyBo.getNsrqymc());
                companySoaBo.setNsrsbh(companyBo.getNsrsbh());
                BaseResponse br = SoaConnectionFactory.post(request, ConstantsUri.HSQJ_CLIENT, companySoaBo, BaseResponse.class);
                mav.addObject("data",br);
            } catch (SoaException e) {
                //logger.debug("异常:"+e.getMessage());
                if ( e instanceof SoaException){
                    mav.addObject("soacode","8888");
                    mav.addObject("message","服务器繁忙,请稍后再试...");
                }else{
                    mav.addObject("soacode","7777");
                    mav.addObject("message","操作失败,请稍后再试...");
                }
            }
        }else{
            BaseResponse br=new BaseResponse("1000","纳税人识别或者纳税企业名称不能为空!");
            mav.addObject("data",br);
        }
        return mav;
    }



}
