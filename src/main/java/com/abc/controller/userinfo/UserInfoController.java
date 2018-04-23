package com.abc.controller.userinfo;

import com.abc.application.SpringCtxHolder;
import com.abc.cache.RedisCacheService;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.*;
import com.abc.controller.BaseController;
import com.abc.controller.LoginController;
import com.abc.service.ImgCompress;
import com.abc.service.RsaV2Service;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.*;
import com.abc.soa.request.SmsBo;
import com.abc.soa.request.userinfo.UserDzsbReset;
import com.abc.soa.response.*;
import com.abc.soa.response.userinfo.HndsLoginBo;
import com.abc.soa.response.userinfo.UserInfoBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.interfaces.RSAPublicKey;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * @author tankh
 * @createTime 2017年7月18日
 * @description 个人信息Controller
 */
@Controller
@RequestMapping(value = "/userinfo")
public class UserInfoController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);


    /**
     * 个人信息显示
     * @param session
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/userinfolist.html")
    public ModelAndView list(HttpSession session, Model model, HttpServletRequest request) {
        ModelAndView mav =new ModelAndView("userinfo/user_info");
        try {
            UserBo userBo =  (UserBo)getInfoService.getUserBo(request);
            String id = userBo.getId();
            UserInfoBo userInfo = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_INFO,null,UserInfoBo.class,id);
            model.addAttribute("user",userInfo.getUser());
            UserTzxxBo userextend = userInfo.getUser_extend();
            userextend=UserString.stringIdcardHandle(userextend);
            model.addAttribute("userextend",userextend);
            model.addAttribute("path",SpringCtxHolder.getProperty("imagedomain"));
            DizhiIDBo dizhiIDBo=new DizhiIDBo();
            boolean bool=false;
            if(userInfo.getUser_extend()!=null){
                if(isNull(userInfo.getUser_extend().getProvince())){
                    bool=true;
                    dizhiIDBo.setProvinceId(userInfo.getUser_extend().getProvince());
                }
                if(isNull(userInfo.getUser_extend().getCity())){
                    bool=true;
                    dizhiIDBo.setCityId(userInfo.getUser_extend().getCity());
                }
                if(isNull(userInfo.getUser_extend().getArea())){
                    bool=true;
                    dizhiIDBo.setAreaId(userInfo.getUser_extend().getArea());
                }
            }
            if(bool){
                DizhiNameBo dizhiNameBo=SoaConnectionFactory.get(request,ConstantsUri.DIQU_QUERY_ID,dizhiIDBo,DizhiNameBo.class);
                mav.addObject("dizhi", dizhiNameBo);
            }else{
                mav.addObject("dizhi", new DizhiNameBo());
            }

            String vip="VIP0";
            if(userBo.getVipLevel()!=null&&!"".equals(userBo.getVipLevel())){
                vip=userBo.getVipLevel();
            }
            VipLeveRes baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_VIP,null,VipLeveRes.class,vip);
            mav.addObject("vip", baseResponse.getData());

            DictRes industry = RedisCacheService.getRedisDictRes(request,getInfoService,"industry");
            DictRes goodat = RedisCacheService.getRedisDictRes(request,getInfoService,"goodat");
            //查询并添加订单状态数据字典
            mav.addObject("industrys", industry.getDataList());
            mav.addObject("goodats", goodat.getDataList());
            mav.addObject("ip",getRemortIP(request));
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

    private String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }



    /**
     * 跳转头像修改页面
     * @return
     */
    @GetMapping("/imageeditshow.html")
    public String info_image_edit_show() {
        return "userinfo/user_image_edit";
    }


    /**
     * 跳转个人信息修改页面
     * @param session
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/infoeditshow.html")
    public String info_edit_show(HttpSession session, Model model, HttpServletRequest request) {
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            String id = userBo.getId();
            UserInfoBo userInfo = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_INFO,null,UserInfoBo.class,id);
            model.addAttribute("user",userInfo.getUser());
            model.addAttribute("userextend",userInfo.getUser_extend());

            DictRes industry = RedisCacheService.getRedisDictRes(request,getInfoService,"industry");
            DictRes goodat = RedisCacheService.getRedisDictRes(request,getInfoService,"goodat");
            //查询并添加订单状态数据字典
            model.addAttribute("industrys", industry.getDataList());
            model.addAttribute("goodats", goodat.getDataList());
            return "userinfo/user_info_edit";
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }


    /**
     * 修改个人信息保存操作
     * @param userUpdateInfoBo
     * @param session
     * @param request
     * @return
     */
    @PostMapping("/infoedit.html")
    public ModelAndView info_edit(@RequestBody UserUpdateInfoBo userUpdateInfoBo,HttpSession session, HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            String id = userBo.getId();
            userBo.setNickname(userUpdateInfoBo.getNickname());
            userBo.setUsername(userUpdateInfoBo.getUsername());
            //添加邮箱
            userBo.setRegMail(userUpdateInfoBo.getEmail());

            UserTzxxBo userTzxxBo=new UserTzxxBo();
            userTzxxBo.setArea(userUpdateInfoBo.getArea());
            userTzxxBo.setCity(userUpdateInfoBo.getCity());
            userTzxxBo.setProvince(userUpdateInfoBo.getProvince());
            userTzxxBo.setPostAddress(userUpdateInfoBo.getPostAddress());
            userTzxxBo.setEducation(userUpdateInfoBo.getEducation());
            userTzxxBo.setCareerDuration(userUpdateInfoBo.getCareerDuration());
            userTzxxBo.setTags(userUpdateInfoBo.getTags());
            userTzxxBo.setSex(userUpdateInfoBo.getSex());
            //添加行业和QQ
            userTzxxBo.setQq(userUpdateInfoBo.getQq());
            userTzxxBo.setOccupation(userUpdateInfoBo.getIndustry());

            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            if(userUpdateInfoBo.getBirthday()!=null&&!"".equals(userUpdateInfoBo.getBirthday())){
                try {
                    userTzxxBo.setBirthday(dateFormat.parse(userUpdateInfoBo.getBirthday()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            userTzxxBo.setRealName(userUpdateInfoBo.getRealName());
            UserIdBo userBos = SoaConnectionFactory.put(request, ConstantsUri.USER_SAVE,userBo,UserIdBo.class,id);
            if("2000".equals(userBos.getCode())){
                getInfoService.setUserBo(request, userBo);
                logger.info("编辑用户基本信息成功");
                BaseResponse userInfos = SoaConnectionFactory.put(request, ConstantsUri.USER_SAVE_EXTEND,userTzxxBo,BaseResponse.class,id);
                if("2000".equals(userInfos.getCode())) {
                    mav.addObject("data",userBos);
                }else{
                    System.out.println("接口错误");
                    mav.addObject("data",userInfos);
                }
            }else{
                System.out.println("接口错误");
                mav.addObject("data",userBos);
            }
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



    @PostMapping("/phoneedit.html")
    public String phone_edit(){
        return "userinfo/user_info";
    }



    private int size=15;
    /**
     * 纳税企业列表显示
     * @param session
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/enterprise.html")
    public ModelAndView user_enterprise(HttpSession session, Model model, HttpServletRequest request){

        try {
            ModelAndView mav= new ModelAndView("userinfo/user_enterprise");
            UserBo userBo = getInfoService.getUserBo(request);
            Map map=new HashMap();
            map.put("page","1");
            map.put("size",size+"");
            UserDzsbRes userDzsbRes=SoaConnectionFactory.get(request,ConstantsUri.USER_DZSB,map,UserDzsbRes.class,userBo.getId());
            UserHngsRes userHngsRes=SoaConnectionFactory.get(request,ConstantsUri.USER_HNGS,map,UserHngsRes.class,userBo.getId());
            UserHndsRes userHndsRes=SoaConnectionFactory.get(request,ConstantsUri.USER_HNDS,map,UserHndsRes.class,userBo.getId());
            model.addAttribute("userdzsb",userDzsbRes);
            model.addAttribute("userhngs",userHngsRes);
            model.addAttribute("userhnds",userHndsRes);

            model.addAttribute("data",userBo);
            UserTzxxRes userTzxxRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_TZXX,null, UserTzxxRes.class,userBo.getId());
            model.addAttribute("tzxx",userTzxxRes.getData());
            if(userDzsbRes.getTotal()>0){
                mav.addObject("count",userDzsbRes.getTotal());
                mav.addObject("page",1);
            }else{
                model.addAttribute("page",0);
                model.addAttribute("count",0);
            }
            if(userHngsRes.getTotal()>0){
                mav.addObject("count1",userHngsRes.getTotal());
                mav.addObject("page1",1);
            }else{
                model.addAttribute("page1",0);
                model.addAttribute("count1",0);
            }

            if(userHndsRes.getTotal()>0){
                mav.addObject("count2",userHndsRes.getTotal());
                mav.addObject("page2",1);
            }else{
                model.addAttribute("page2",0);
                model.addAttribute("count2",0);
            }

            UserDzsbRes udr = SoaConnectionFactory.get(request, ConstantsUri.USER_GSDZ_LIST, null, UserDzsbRes.class, userBo.getId());
            mav.addObject("dzsblist",udr);
            return mav;
        } catch (Exception e) {
            ModelAndView mav= new ModelAndView("soaerror");
            logger.debug("异常:"+e.getMessage());
            return mav;
        }
    }

    /**
     * 纳税企业列表显示
     * @param session
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/enterprise_page.html")
    public ModelAndView enterprise_page(
            @RequestParam(value = "index", required = false,defaultValue = "") String index,
            @RequestParam(value = "name", required = false,defaultValue = "") String name,
            @RequestParam(value = "nsrsbh", required = false,defaultValue = "") String nsrsbh,
            @RequestParam(value = "bdgroup", required = false,defaultValue = "") String bdgroup,
                                         HttpSession session, Model model, HttpServletRequest request){

        try {
            ModelAndView mav= new ModelAndView("userinfo/abcdzsb");
            UserBo userBo = getInfoService.getUserBo(request);
            Map map=new HashMap();
            map.put("page",index);
            map.put("size",size+"");
            map.put("nsrsbh",nsrsbh);
            map.put("nsrmc",name);
            map.put("bdgroup",bdgroup);
            UserDzsbRes userDzsbRes=SoaConnectionFactory.get(request,ConstantsUri.USER_DZSB,map,UserDzsbRes.class,userBo.getId());
            model.addAttribute("userdzsb",userDzsbRes);
            if(userDzsbRes.getTotal()>0){
                mav.addObject("count",userDzsbRes.getTotal());
                mav.addObject("page",Integer.parseInt(index));
            }else{
                model.addAttribute("page",0);
                model.addAttribute("count",0);
            }
            return mav;
        } catch (Exception e) {
            ModelAndView mav= new ModelAndView("soaerror");
            logger.debug("异常:"+e.getMessage());
            return mav;
        }
    }


    /**
     * 纳税企业列表显示
     * @param session
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/bdgroup.html")
    public ModelAndView bdgroup(
            HttpSession session, Model model, HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            BdGroupListBo bdGroupListBo=SoaConnectionFactory.get(request,ConstantsUri.BD_GROUP,null,BdGroupListBo.class,userBo.getId());
            mav.addObject("data",bdGroupListBo.getDataList());
            return mav;
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
    }

    /**
     * 纳税企业列表显示
     * @param session
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/update/bdgroup.html")
    public ModelAndView updatebdgroup(@RequestParam(value = "bdgroup", required = false,defaultValue = "") String bdgroup,
                                      @RequestParam(value = "id", required = false,defaultValue = "") String id,
            HttpSession session, Model model, HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            BdGroupBo bdGroupBo=new BdGroupBo();
            bdGroupBo.setBdgroup(bdgroup);
            bdGroupBo.setId(id);
            BaseResponse br = SoaConnectionFactory.put(request, ConstantsUri.USER_UPDATE_DZSB_GROUP, bdGroupBo, BaseResponse.class);
            mav.addObject("data",br);
            return mav;
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
    }


    /**
     * 纳税企业列表显示
     * @param session
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/enterprise_page1.html")
    public ModelAndView enterprise_page1( @RequestParam(value = "index", required = false,defaultValue = "") String index,
                                          @RequestParam(value = "name", required = false,defaultValue = "") String name,
                                          @RequestParam(value = "nsrsbh", required = false,defaultValue = "") String nsrsbh,
                                          HttpSession session, Model model, HttpServletRequest request){

        try {
            ModelAndView mav= new ModelAndView("userinfo/abcgsdz");
            UserBo userBo = getInfoService.getUserBo(request);
            Map map=new HashMap();
            map.put("page",index);
            map.put("size",size+"");
            map.put("nsrsbh",nsrsbh);
            map.put("nsrmc",name);
            UserHngsRes userHngsRes=SoaConnectionFactory.get(request,ConstantsUri.USER_HNGS,map,UserHngsRes.class,userBo.getId());
            model.addAttribute("userhngs",userHngsRes);
            if(userHngsRes.getTotal()>0){
                mav.addObject("count1",userHngsRes.getTotal());
                mav.addObject("page1",Integer.parseInt(index));
            }else{
                model.addAttribute("page1",0);
                model.addAttribute("count1",0);
            }
            return mav;
        } catch (Exception e) {
            ModelAndView mav= new ModelAndView("soaerror");
            logger.debug("异常:"+e.getMessage());
            return mav;
        }
    }

    /**
     * 纳税企业列表显示
     * @param session
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/enterprise_page2.html")
    public ModelAndView enterprise_page2( @RequestParam(value = "index", required = false,defaultValue = "") String index,
                                          @RequestParam(value = "name", required = false,defaultValue = "") String name,
                                          @RequestParam(value = "nsrsbh", required = false,defaultValue = "") String nsrsbh,
                                          HttpSession session, Model model, HttpServletRequest request){

        try {
            ModelAndView mav= new ModelAndView("userinfo/abcdswt");
            UserBo userBo = getInfoService.getUserBo(request);
            Map map=new HashMap();
            map.put("page",index);
            map.put("size",size+"");
            map.put("nsrsbh",nsrsbh);
            map.put("nsrmc",name);
            UserHndsRes userHndsRes=SoaConnectionFactory.get(request,ConstantsUri.USER_HNDS,map,UserHndsRes.class,userBo.getId());
            model.addAttribute("userhnds",userHndsRes);
            if(userHndsRes.getTotal()>0){
                mav.addObject("count2",userHndsRes.getTotal());
                mav.addObject("page2",Integer.parseInt(index));
            }else{
                model.addAttribute("page2",0);
                model.addAttribute("count2",0);
            }
            return mav;
        } catch (Exception e) {
            ModelAndView mav= new ModelAndView("soaerror");
            logger.debug("异常:"+e.getMessage());
            return mav;
        }
    }

    /**
     * 纳税企业列表显示
     * @param session
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/nsqyxq.html")
    public ModelAndView nsqyxq( @RequestParam(value = "id", required = false) String id,
                                          @RequestParam(value = "type", required = false) String type,
                                          HttpSession session, Model model, HttpServletRequest request){
        try {
            ModelAndView mav=null;
            if("dzsb".equals(type)){
                mav= new ModelAndView("userinfo/abcdzsbxq");
                UserDzsbDataBo userDzsbDataBo=SoaConnectionFactory.get(request,ConstantsUri.USER_BIND_DZSB_DETAIL,null,UserDzsbDataBo.class,id);
                mav.addObject("data",userDzsbDataBo.getData());
                //mav.addObject("data",null);
            }else if("gsdz".equals(type)){
                mav= new ModelAndView("userinfo/abcgsdzxq");
                UserHngsDataBo userHngsDataBo=SoaConnectionFactory.get(request,ConstantsUri.USER_BIND_HNGS_DETAIL,null,UserHngsDataBo.class,id);
                mav.addObject("data",userHngsDataBo.getData());
                //mav.addObject("data",null);

            }else if("dswt".equals(type)){
                mav= new ModelAndView("userinfo/abcdswtxq");
                UserHndsDataBo userHndsDataBo=SoaConnectionFactory.get(request,ConstantsUri.USER_BIND_HNDS_DETAIL,null,UserHndsDataBo.class,id);
                mav.addObject("data",userHndsDataBo.getData());
                //mav.addObject("data",null);
            }
            return mav;
        } catch (Exception e) {
            ModelAndView mav= new ModelAndView("soaerror");
            logger.debug("异常:"+e.getMessage());
            return mav;
        }
    }

    /**
     * 纳税企业列表显示
     * @param session
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/update/nsqyxq.html")
    public ModelAndView nsqyxq( @RequestParam(value = "nsrsbh", required = false) String nsrsbh,
                                HttpSession session, Model model, HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            BaseResponse br = SoaConnectionFactory.put(request, ConstantsUri.USER_DZSB_UPDATE, null, BaseResponse.class, userbo.getId(), nsrsbh);
            mav.addObject("data",br);
            return mav;
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
    }






    /**
     * 微信解绑
     * @param session
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/wxjb.html")
    public ModelAndView wxjb(HttpSession session, Model model, HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            userBo.setWxheadimg("");
            userBo.setWxnickname("");
            userBo.setWxopenid("");
            UserIdBo userBos = SoaConnectionFactory.put(request, ConstantsUri.USER_SAVE,userBo,UserIdBo.class,userBo.getId());
            UserBo user = userBos.getData();
            if(isNull(user.getPhone())){
                String phones=user.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
                user.setXinphone(phones);
            }
            getInfoService.setUserBo(request, user);
            mav.addObject("data",userBos);
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


    /**
     * 修改手机号码
     * @param userPasswordBo
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/updpassword.html", method = RequestMethod.POST)
    public ModelAndView sh_default(@RequestBody UserPasswordBo userPasswordBo, HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(isNull(userPasswordBo.getCode())&&isNull(userPasswordBo.getXphone())){
//                SmsBo smsBo=new SmsBo();
//                smsBo.setCode(userPasswordBo.getCode());
//                smsBo.setPhone(userPasswordBo.getXphone());
//                if(userPasswordBo.getType()==null||"".equals(userPasswordBo.getType())){
//                    smsBo.setType(SmsType.SJGG);
//                }else{
//                    smsBo.setType(userPasswordBo.getType());
//                }
                UserBo userbo = getInfoService.getUserBo(request);
//                BaseResponse baseResponse=SoaConnectionFactory.post(request,ConstantsUri.SMS_YZ_CODE,smsBo,BaseResponse.class);
//                if(baseResponse!=null){
//                    if("2000".equals(baseResponse.getCode())){
//                        userbo.setPhone(userPasswordBo.getXphone());
//                        baseResponse=SoaConnectionFactory.put(request,ConstantsUri.USER_SAVE_PHONE,userbo,BaseResponse.class,userbo.getId());
//                        mav.addObject("data",baseResponse);
//                        getInfoService.setUserBo(request, userbo);
//                    }else{
//                        BaseResponse br=new BaseResponse("1000","验证码错误");
//                        mav.addObject("data",br);
//                    }
//                }else{
//                    BaseResponse br=new BaseResponse("1000","短信验证异常");
//                    mav.addObject("data",br);
//                }

                BindPhoneBO bindPhoneBO=new BindPhoneBO();
                bindPhoneBO.setCode(userPasswordBo.getCode());
                bindPhoneBO.setNewPhone(userPasswordBo.getXphone());
                bindPhoneBO.setOldPhone(userPasswordBo.getQuerenphone());
                bindPhoneBO.setType(userPasswordBo.getType());
                bindPhoneBO.setUserId(userbo.getId());
                BaseResponse br = SoaConnectionFactory.put(request, ConstantsUri.USER_SAVE_PHONE, bindPhoneBO, BaseResponse.class);
                mav.addObject("data",br);
                if("2000".equals(br.getCode())){
                    UserInfoBo userInfo = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_INFO,null,UserInfoBo.class,userbo.getId());
                    getInfoService.setUserBo(request, userInfo.getUser());
                }
            }
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


    /**
     * 修改密码操作
     * @param updPassBo
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/updpass.html", method = RequestMethod.POST)
    public ModelAndView updpass(@RequestBody UpdPassBo updPassBo, HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(isNull(updPassBo.getPasscode())&&isNull(updPassBo.getPassword())){
                UserBo userbo = getInfoService.getUserBo(request);
                SmsBo smsBo=new SmsBo();
                smsBo.setCode(updPassBo.getPasscode());
                smsBo.setUserId(userbo.getId());
                smsBo.setType(SmsType.XGMM);
                BaseResponse baseResponses=SoaConnectionFactory.post(request,ConstantsUri.SMS_YZ_CODE_USER,smsBo,BaseResponse.class);
                if(baseResponses!=null) {
                    if ("2000".equals(baseResponses.getCode())) {
//                        if(updPassBo.getPhone().indexOf("****")>-1){
//                            updPassBo.setPhone(userbo.getPhone());
//                        }
                        RSAPublicKey publickey = RedisCacheService.getRedisPublicKey(request,getInfoService);
                        if(publickey==null){
                            BaseResponse brs=new BaseResponse("1000","获取公钥失败!");
                            mav.addObject("data",brs);
                            return mav;
                        }
                        UserSlatBo loginUserBo=SoaConnectionFactory.get(request,ConstantsUri.UC_USER_LOGIN_USERID,null,UserSlatBo.class,userbo.getId());
                        //UserSlatBo loginUserBo=SoaConnectionFactory.getRestful(request,ConstantsUri.UC_USER_LOGIN,null,UserSlatBo.class,updPassBo.getPhone());
                        MD5 md5 = new MD5(updPassBo.getPassword() + loginUserBo.getUser().getSalt());
                        String rsapassword = RSA.encryptString(publickey, md5.compute());
                        BASE64Encoder base64Encoder=new BASE64Encoder();
                        updPassBo.setPassword(base64Encoder.encodeBuffer(rsapassword.getBytes()));
                        BaseResponse baseResponse=SoaConnectionFactory.put(request,ConstantsUri.USER_PASSWORD,updPassBo,BaseResponse.class);
                        mav.addObject("data",baseResponse);
//                        if("2000".equals(baseResponse.getCode())){
//                            getInfoService.removeKeys(userbo.getId());
//                        }
                    }else{
                        BaseResponse br=new BaseResponse("1000","验证码错误");
                        mav.addObject("data",br);
                    }
                }else{
                    BaseResponse br=new BaseResponse("1000","短信验证异常");
                    mav.addObject("data",br);
                }
            }
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

    /**
     * 修改密码操作
     * @param updPassBo
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/updpass_v2.html", method = RequestMethod.POST)
    public ModelAndView updpass_v2(@RequestBody UpdPassBo updPassBo, HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(isNull(updPassBo.getPasscode())&&isNull(updPassBo.getPassword())){
                UserBo userbo = getInfoService.getUserBo(request);
                SmsBo smsBo=new SmsBo();
                smsBo.setCode(updPassBo.getPasscode());
                smsBo.setUserId(userbo.getId());
                smsBo.setType(SmsType.XGMM);
                BaseResponse baseResponses=SoaConnectionFactory.post(request,ConstantsUri.SMS_YZ_CODE_USER,smsBo,BaseResponse.class);
                if(baseResponses!=null) {
                    if ("2000".equals(baseResponses.getCode())) {
//                        if(updPassBo.getPhone().indexOf("****")>-1){
//                            updPassBo.setPhone(userbo.getPhone());
//                        }
                        RSAPublicKey publickey = RedisCacheService.getRedisV2PublicKey(request,getInfoService);
                        if(publickey==null){
                            BaseResponse brs=new BaseResponse("1000","获取公钥失败!");
                            mav.addObject("data",brs);
                            return mav;
                        }
                        UserSlatBo loginUserBo=SoaConnectionFactory.get(request,ConstantsUri.UC_USER_LOGIN_USERID,null,UserSlatBo.class,userbo.getId());
                        //UserSlatBo loginUserBo=SoaConnectionFactory.getRestful(request,ConstantsUri.UC_USER_LOGIN,null,UserSlatBo.class,updPassBo.getPhone());
                        MD5 md5 = new MD5(updPassBo.getPassword() + loginUserBo.getUser().getSalt());
//                        String rsapassword = RSA.encryptString(publickey, md5.compute());
                        byte[] rsapassword = RsaV2Service.rsaV2Encryption(md5.compute(), publickey);
                        BASE64Encoder base64Encoder=new BASE64Encoder();
                        updPassBo.setPassword(base64Encoder.encodeBuffer(rsapassword));
                        BaseResponse baseResponse=SoaConnectionFactory.put(request,ConstantsUri.USER_PASSWORD_V2,updPassBo,BaseResponse.class);
                        mav.addObject("data",baseResponse);
//                        if("2000".equals(baseResponse.getCode())){
//                            getInfoService.removeKeys(userbo.getId());
//                        }
                    }else{
                        BaseResponse br=new BaseResponse("1000","验证码错误");
                        mav.addObject("data",br);
                    }
                }else{
                    BaseResponse br=new BaseResponse("1000","短信验证异常");
                    mav.addObject("data",br);
                }
            }
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


    /**
     * 电子申报绑定操作
     * @param userDzsbSaveBo
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/bddzsb.html", method = RequestMethod.POST)
    public ModelAndView bddzsb( @RequestBody UserDzsbSaveBo userDzsbSaveBo, HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(isNull(userDzsbSaveBo.getNsrsbhOrShxydm())&&isNull(userDzsbSaveBo.getFwmm())&&isNull(userDzsbSaveBo.getCode())) {
                UserBo userbo = getInfoService.getUserBo(request);
                if (!isNull(userbo.getPhone())) {
                    BaseResponse baseResponse = SoaConnectionFactory.put(request, ConstantsUri.USER_SAVE, userbo, BaseResponse.class, userbo.getId());
                    getInfoService.setUserBo(request, userbo);
                }
                userDzsbSaveBo.setFwmm(userDzsbSaveBo.getFwmm());
                BaseResponse baseResponse = SoaConnectionFactory.post(request, ConstantsUri.USER_DZSB_SAVE, userDzsbSaveBo, BaseResponse.class);
                mav.addObject("data", baseResponse);
            }
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
    /**
     * 电子申报绑定操作
     * @param userDzsbSaveBo
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/bddzsb_v2.html", method = RequestMethod.POST)
    public ModelAndView bddzsb_v2( @RequestBody UserDzsbSaveBo userDzsbSaveBo, HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(isNull(userDzsbSaveBo.getNsrsbhOrShxydm())&&isNull(userDzsbSaveBo.getFwmm())&&isNull(userDzsbSaveBo.getCode())) {
                UserBo userbo = getInfoService.getUserBo(request);
                if (!isNull(userbo.getPhone())) {
                    BaseResponse baseResponse = SoaConnectionFactory.put(request, ConstantsUri.USER_SAVE, userbo, BaseResponse.class, userbo.getId());
                    getInfoService.setUserBo(request, userbo);
                }
                userDzsbSaveBo.setFwmm(userDzsbSaveBo.getFwmm());
                BaseResponse baseResponse = SoaConnectionFactory.post(request, ConstantsUri.USER_DZSB_SAVE_V2, userDzsbSaveBo, BaseResponse.class);
                mav.addObject("data", baseResponse);
            }
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

    /**
     * 国税绑定操作
     * @param userHngsSaveBo
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/bdhngs.html", method = RequestMethod.POST)
    public ModelAndView bdhngs( @RequestBody  UserHngsSaveBo userHngsSaveBo , HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(isNull(userHngsSaveBo.getBsy())&&isNull(userHngsSaveBo.getPassword())&&isNull(userHngsSaveBo.getRole())){
                UserBo userbo = getInfoService.getUserBo(request);
                if(!isNull(userbo.getPhone())){
                    BaseResponse baseResponse = SoaConnectionFactory.put(request, ConstantsUri.USER_SAVE, userbo, BaseResponse.class, userbo.getId());
                    getInfoService.setUserBo(request, userbo);
                }
                userHngsSaveBo.setPassword(userHngsSaveBo.getPassword());
                BaseResponse baseResponse=SoaConnectionFactory.post(request,ConstantsUri.USER_HNGS_SAVE,userHngsSaveBo,BaseResponse.class);
                mav.addObject("data",baseResponse);
            }
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
    /**
     * 国税绑定操作
     * @param userHngsSaveBo
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/bdhngs_v2.html", method = RequestMethod.POST)
    public ModelAndView bdhngs_v2( @RequestBody  UserHngsSaveBo userHngsSaveBo , HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(isNull(userHngsSaveBo.getBsy())&&isNull(userHngsSaveBo.getPassword())&&isNull(userHngsSaveBo.getRole())){
                UserBo userbo = getInfoService.getUserBo(request);
                if(!isNull(userbo.getPhone())){
                    BaseResponse baseResponse = SoaConnectionFactory.put(request, ConstantsUri.USER_SAVE, userbo, BaseResponse.class, userbo.getId());
                    getInfoService.setUserBo(request, userbo);
                }
                userHngsSaveBo.setPassword(userHngsSaveBo.getPassword());
                BaseResponse baseResponse=SoaConnectionFactory.post(request,ConstantsUri.USER_HNGS_SAVE_V2,userHngsSaveBo,BaseResponse.class);
                mav.addObject("data",baseResponse);
            }
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


    /**
     * 地税绑定操作
     * @param userHndsSaveBo
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/bdhnds.html", method = RequestMethod.POST)
    public ModelAndView bdhnds( @RequestBody UserHndsSaveBo userHndsSaveBo, HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(isNull(userHndsSaveBo.getPassword())&&isNull(userHndsSaveBo.getSubuser())){
                UserBo userbo = getInfoService.getUserBo(request);
                if(!isNull(userbo.getPhone())){
                    BaseResponse baseResponse = SoaConnectionFactory.put(request, ConstantsUri.USER_SAVE, userbo, BaseResponse.class, userbo.getId());
                    getInfoService.setUserBo(request, userbo);
                }
                userHndsSaveBo.setPassword(userHndsSaveBo.getPassword());
                HndsLoginBo hndsLoginBo=new HndsLoginBo();
                hndsLoginBo.setMm(userHndsSaveBo.getPassword());
                hndsLoginBo.setUserId(userbo.getId());
                hndsLoginBo.setSubuserid(userHndsSaveBo.getUsername());
                hndsLoginBo.setTaxtype("2");
                hndsLoginBo.setMainuserid(userHndsSaveBo.getSubuser());
                hndsLoginBo.setNsrmc(userHndsSaveBo.getNsrmc());
                BaseResponse baseResponse = SoaConnectionFactory.post(request, ConstantsUri.USER_HNDS_LOGIN, hndsLoginBo, BaseResponse.class);
               // BaseResponse baseResponse=SoaConnectionFactory.post(request,ConstantsUri.USER_HNDS_SAVE,userHndsSaveBo,BaseResponse.class);
                mav.addObject("data",baseResponse);
            }
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

    /**
     * 电子申报查看详情
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/dzsb/detail/{id}", method = RequestMethod.GET)
    public ModelAndView delhnds( @PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/userinfo/abcdzsbxq");
        try {
            if(isNull(id)){
                UserDzsbDataBo userDzsbDataBo=SoaConnectionFactory.get(request,ConstantsUri.USER_BIND_DZSB_DETAIL,null,UserDzsbDataBo.class,id);
                mav.addObject("data",userDzsbDataBo.getData());
            }
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




    /**
     * 纳税企业解绑操作
     * @param id
     * @param type
     * @param request
     * @return
     */
    @RequestMapping(value = "/{type}/{id}", method = RequestMethod.POST)
    public ModelAndView delhnds( @PathVariable(value = "id") String id,@PathVariable(value = "type") String type, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(isNull(id)&&isNull(type)){
                BaseResponse baseResponse=null;
                if("hngs".equals(type)){
                    baseResponse=SoaConnectionFactory.put(request,ConstantsUri.USER_HNGS_DEL,null,BaseResponse.class,id);
                }else if("hnds".equals(type)){
                    baseResponse=SoaConnectionFactory.put(request,ConstantsUri.USER_HNDS_DEL,null,BaseResponse.class,id);
                }else if("dzsb".equals(type)){
                    baseResponse=SoaConnectionFactory.put(request,ConstantsUri.USER_DZSB_DEL,null,BaseResponse.class,id);
                }
                mav.addObject("data",baseResponse);
            }
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


    /**
     * 实名身份证图片上传并且保存实名认证信息
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload.html", method = RequestMethod.POST)
    public ModelAndView upload(
             @RequestBody SmrzDataBo smrzDataBo
            , HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            if(isNull(smrzDataBo.getCode())){
                if(!(smrzDataBo.getXphone()!=null&&!"".equals(smrzDataBo.getXphone()))){
                    smrzDataBo.setXphone(userbo.getPhone());
                }
                SmsBo smsBo=new SmsBo();
                smsBo.setCode(smrzDataBo.getCode());
                smsBo.setUserId(userbo.getId());
                smsBo.setType(SmsType.SMRZ);
                BaseResponse baseResponse=SoaConnectionFactory.post(request,ConstantsUri.SMS_YZ_CODE_USER,smsBo,BaseResponse.class);
                if("2000".equals(baseResponse.getCode())){
                    if(!isNull(userbo.getPhone())){
                        userbo.setPhone(smrzDataBo.getXphone());
                        String phones=smrzDataBo.getXphone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
                        userbo.setXinphone(phones);
                        SoaConnectionFactory.put(request,ConstantsUri.USER_SAVE,userbo,BaseResponse.class,userbo.getId());
                    }else {
                        if(isNull(smrzDataBo.getZsxm())&&isNull(smrzDataBo.getSfzhm())){
                            CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();
                            getPicFileType();
                            FjDto fjDto = new FjDto();
                            String uuid=UUID.randomUUID().toString();
                            fjDto.setFileName(uuid+".jpg");
                            try {
                                fjDto.setFileContent(smrzDataBo.getZmbase64());
                                cmsFileUploadDto.setDirectory(userbo.getId());
                                cmsFileUploadDto.getFjBo().add(fjDto);
                                fjDto = new FjDto();
                                uuid=UUID.randomUUID().toString();
                                fjDto.setFileName(uuid+".jpg");
                                fjDto.setFileContent(smrzDataBo.getFmbase64());
                                cmsFileUploadDto.getFjBo().add(fjDto);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            FileListDto fileListDto = SoaConnectionFactory.post(request, ConstantsUri.SYSFILEUP_BASE64, cmsFileUploadDto, FileListDto.class);
                            UserTzxxRes userTzxxRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_TZXX,null, UserTzxxRes.class,userbo.getId());
                            UserTzxxBo data = null;
                            if(userTzxxRes.getData()!=null){
                                data=userTzxxRes.getData();
                            }else{
                                data=new UserTzxxBo();
                            }
                            data.setBackImage("/images"+fileListDto.getDataList().get(0).getFilePath());
                            data.setFrontImage("/images"+fileListDto.getDataList().get(1).getFilePath());
                            data.setIdcard(smrzDataBo.getSfzhm());
                            data.setRealName(smrzDataBo.getZsxm());
                            data.setValidStatus("1");
                            data.setRemark("");
                            if(fileListDto.getDataList().size()>0){
                                BaseResponse userInfos = SoaConnectionFactory.put(request, ConstantsUri.USER_SAVE_EXTEND,data,BaseResponse.class,userbo.getId());
                                if(userInfos.getCode()=="2000"){
                                    BaseResponse base =new BaseResponse("200", "已经上传，请耐心等待审核");
                                    mav.addObject("data",base);
                                    return mav;
                                }else{
                                    mav.addObject("data",userInfos);
                                    return mav;
                                }

                            }else{
                                BaseResponse base =new BaseResponse("300", "上传失败");
                                mav.addObject("data",base);
                                return mav;
                            }
                        }
                    }
                }else{
                    BaseResponse base =new BaseResponse("300", "验证码错误");
                    mav.addObject("data",base);
                    return mav;
                }
            }else{
                BaseResponse base =new BaseResponse("300", "验证码");
                mav.addObject("data",base);
                return mav;
            }
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

    public Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();
    public String getFileTypeByStream(String filename) {
        String name=filename.substring(filename.indexOf(".")+1,filename.length());
       // String [] name=filename.split(".");
        if(name!=null&&!"".equals(name)){
            if("jpg".equals(name.toLowerCase())||"png".equals(name.toLowerCase())||"bmp".equals(name.toLowerCase())||"jpeg".equals(name.toLowerCase())){
                return "jpg";
            }
        }
        return null;
    }

    public void getPicFileType() {
        FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
        FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
        FILE_TYPE_MAP.put("bmp","424D");
        FILE_TYPE_MAP.put("bmp","89504E47");
    }

    public String getFileHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 用户网上申报新增页面
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/user_swsbadd.html")
    public String user_swsbadd(HttpSession session, Model model,HttpServletRequest request){
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            model.addAttribute("data",userbo);
            UserTzxxRes userTzxxRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_TZXX,null, UserTzxxRes.class,userbo.getId());
            model.addAttribute("tzxx",userTzxxRes.getData());
            return "userinfo/user_swsbadd";
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }

    /**
     * 用户绑定地税
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/user_swdsadd.html")
    public String user_swdsadd(HttpSession session, Model model,HttpServletRequest request){
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            model.addAttribute("data",userbo);
            UserTzxxRes userTzxxRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_TZXX,null, UserTzxxRes.class,userbo.getId());
            model.addAttribute("tzxx",userTzxxRes.getData());
            return "userinfo/user_swdsadd";
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }

    /**
     * 用户绑定网上国税
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/user_swwsadd.html")
    public String user_swwsadd(HttpSession session, Model model,HttpServletRequest request){
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            model.addAttribute("data",userbo);
            UserTzxxRes userTzxxRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_TZXX,null, UserTzxxRes.class,userbo.getId());
            model.addAttribute("tzxx",userTzxxRes.getData());
            return "userinfo/user_swwsadd";
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }


    /**
     * 验证手机号码是不是存在
     * @param phone
     * @param request
     * @return
     */
    @RequestMapping("/user_forgot_.html")
    public ModelAndView forgot_(
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "xphone", required = false) String xphone,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(!isNull(phone)){
                phone=xphone;
            }
            UserBo userbo = getInfoService.getUserBo(request);
            BASE64Decoder base64Decoder=new BASE64Decoder();
            phone=new String(base64Decoder.decodeBuffer(phone));
            ForgotBo baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_PHONE,null, ForgotBo.class,phone);
            if("2000".equals(baseResponse.getCode())){
                if(baseResponse.getData()==null){
                    mav.addObject("ok", "正确");
                }else if(baseResponse.getData()!=null&&userbo.getId().equals(baseResponse.getData().getId())){
                    mav.addObject("ok", "正确");
                }else{
                    mav.addObject("error","手机号码已经存在");
                }
            }else{
                mav.addObject("ok", "正确");
            }
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

    /**
     * 验证手机号码是不是存在
     * @param phone
     * @param request
     * @return
     */
    @RequestMapping("/user_forgot.html")
    public ModelAndView forgot(
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "xphone", required = false) String xphone,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(!isNull(phone)){
                phone=xphone;
            }
            BASE64Decoder base64Decoder=new BASE64Decoder();
            phone=new String(base64Decoder.decodeBuffer(phone));
            BaseResponse baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_PHONE,null, BaseResponse.class,phone);
            if("2000".equals(baseResponse.getCode())){
                mav.addObject("error","手机号码已经存在");
            }else{
                mav.addObject("ok", "正确");
            }
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


    /**
     * 验证手机号码是不是存在
     * @param phone
     * @param request
     * @return
     */
    @RequestMapping("/phone_forgot_.html")
    public ModelAndView phone_forgot_(
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "xphone", required = false) String xphone,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(!isNull(phone)){
                phone=xphone;
            }
            BASE64Decoder base64Decoder=new BASE64Decoder();
            phone=new String(base64Decoder.decodeBuffer(phone));
            PhoneBo baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.UC_USER_LOGIN,null, PhoneBo.class,phone);
            if("2000".equals(baseResponse.getCode())&&baseResponse.getData()!=null){
                mav.addObject("error","手机号码已经存在");
            }else{
                mav.addObject("ok", "正确");
            }
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


    /**
     * 验证手机号码是不是存在
     * @param phone
     * @param request
     * @return
     */
    @RequestMapping("/username_forgot.html")
    public ModelAndView username_forgot(
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "xphone", required = false) String xphone,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            if(!isNull(phone)){
                phone=xphone;
            }
            BASE64Decoder base64Decoder=new BASE64Decoder();
            phone=new String(base64Decoder.decodeBuffer(phone));
            LoginUserBo baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_PHONE,null, LoginUserBo.class,phone);
            if(("2000".equals(baseResponse.getCode())&&userbo.getId().equals(baseResponse.getData().getUser().getId()))||!"2000".equals(baseResponse.getCode())){
                mav.addObject("ok", "正确");
            }else{
                mav.addObject("error","用户名已经存在");
            }
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


    /**
     * 验证手机号码是不是存在
     * @param phone
     * @param session
     * @return
     */
    @RequestMapping("/phone_forgot.html")
    public ModelAndView phone_forgot(@RequestParam(value = "phone", required = false) String phone,
                                     @RequestParam(value = "querenphone", required = false) String querenphone,
                                     HttpSession session,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(!(phone!=null&&!"".equals(phone))){
                phone=querenphone;
            }
            BASE64Decoder base64Decoder=new BASE64Decoder();
            phone=new String(base64Decoder.decodeBuffer(phone));
            Map map=new HashMap();
            map.put("oldPhone",phone);
            BaseResponse baseResponse=SoaConnectionFactory.post(request,ConstantsUri.SMS_OLDPHONE,map,BaseResponse.class);
            if("2000".equals(baseResponse.getCode())){
                mav.addObject("ok", "正确");
            }else{
                mav.addObject("error","当前手机号不正确!");
            }
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


    /**
     * 上传用户头像
     * @param multipartRequest
     * @param width
     * @param height
     * @param x
     * @param y
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadtx.html", method = RequestMethod.POST)
    public ModelAndView uploadtx(MultipartHttpServletRequest multipartRequest,
                                 @RequestParam(value = "width", required = false) String width,
                                 @RequestParam(value = "height", required = false) String height,
                                 @RequestParam(value = "x", required = false) String x,
                                 @RequestParam(value = "y", required = false) String y,
                                 HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            if(isNull(width)&&isNull(height)&&isNull(x)&&isNull(y)){
                MultipartFile file =  multipartRequest.getFile("file");
                try {
                    String fileName = file.getOriginalFilename();
                    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
                    InputStream is = file.getInputStream();
                    if("jpg".equals(prefix.toLowerCase())){
                        prefix="jpeg";
                    }
                    Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(prefix);
                    ImageReader reader = it.next();
                    // 获取图片流
                    ImageInputStream iis = ImageIO.createImageInputStream(is);
                /*
                 * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。
                 * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
                 */
                    reader.setInput(iis, true);
                /*
                 * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
                 * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 将从其 ImageReader 实现的
                 * getDefaultReadParam 方法中返回 ImageReadParam 的实例。
                 */
                    ImageReadParam param = reader.getDefaultReadParam();
                /*
                 * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
                 * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
                 */
                int xx=Integer.parseInt(x);
                int yy=Integer.parseInt(y);
                int widths=Integer.parseInt(width);
                int heights=Integer.parseInt(height);
                    Rectangle rect = new Rectangle(xx, yy, widths, heights);
                    // 提供一个 BufferedImage，将其用作解码像素数据的目标。
                    param.setSourceRegion(rect);
                /*
                 * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 它作为一个完整的
                 * BufferedImage 返回。
                 */
                    BufferedImage bi = reader.read(0, param);
                    ByteArrayOutputStream os=new ByteArrayOutputStream();//新建流。
                    ImageIO.write(bi, prefix, os);//利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。
                    byte[] b = os.toByteArray();
                    BASE64Encoder encoder = new BASE64Encoder();
                    String imageString = encoder.encode(b);
                    FjDto fjDto = new FjDto();
                    fjDto.setFileContent(imageString);
                    fjDto.setFileName(file.getOriginalFilename());
                    CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();
                    cmsFileUploadDto.setDirectory(userbo.getUsername());
                    cmsFileUploadDto.getFjBo().add(fjDto);
                    FileListDto fileListDto =SoaConnectionFactory.post(request,ConstantsUri.SYSFILEUP_BASE64,cmsFileUploadDto,FileListDto.class);
                    //FileListDto fileListDto = SoaConnectionFactory.post(multipartRequest, ConstantsUri.SYSFILEUP, cmsFileUploadDto, FileListDto.class);
                    if("2000".equals(fileListDto.getCode())){
                        userbo.setUserPicturePath("/images"  + fileListDto.getDataList().get(0).getFilePath());
                        getInfoService.setUserBo(request, userbo);
                        BaseResponse brs = SoaConnectionFactory.put(request, ConstantsUri.USER_SAVE, userbo, BaseResponse.class, userbo.getId());
                        mav.addObject("data",brs);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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



    /**
     * 上传用户头像
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadbase64tx.html", method = RequestMethod.POST)
    public ModelAndView uploadtx(@RequestParam(value = "base64", required = false) String base64,
                                 HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);

            if(isNull(base64)){
                FjDto fjDto = new FjDto();
                fjDto.setFileContent(base64);
                fjDto.setFileName(System.currentTimeMillis()+".png");
                CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();
                cmsFileUploadDto.setDirectory(userbo.getUsername());
                cmsFileUploadDto.getFjBo().add(fjDto);
                FileListDto fileListDto =SoaConnectionFactory.post(request,ConstantsUri.SYSFILEUP_BASE64,cmsFileUploadDto,FileListDto.class);
                //FileListDto fileListDto = SoaConnectionFactory.post(multipartRequest, ConstantsUri.SYSFILEUP, cmsFileUploadDto, FileListDto.class);
                if("2000".equals(fileListDto.getCode())){
                    userbo.setUserPicturePath("/images"  + fileListDto.getDataList().get(0).getFilePath());
                    getInfoService.setUserBo(request, userbo);
                    SoaConnectionFactory.put(request,ConstantsUri.USER_SAVE,userbo,BaseResponse.class,userbo.getId());
                }
            }
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

    /**
     * 电子申报忘记密码
     * @param userDzsbReset
     * @param request
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/user_swdsadd_forgot.html",method = RequestMethod.POST)
    public ModelAndView user_swdsadd_forgot(@RequestBody UserDzsbReset userDzsbReset, HttpServletRequest request,HttpSession session, Model model){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            SmsBo smsBo=new SmsBo();
            smsBo.setCode(userDzsbReset.getCode());
            smsBo.setUserId(userbo.getId());
            smsBo.setType(SmsType.TJNSQY);
            BaseResponse br=SoaConnectionFactory.post(request,ConstantsUri.SMS_YZ_CODE_USER,smsBo,BaseResponse.class);
            if(br!=null) {
                if ("2000".equals(br.getCode())) {

                    BaseResponse baseResponse = SoaConnectionFactory.post(request,ConstantsUri.USER_DZSB_RESET,userDzsbReset,BaseResponse.class);
                    mav.addObject("data", baseResponse);
                }else{
                    BaseResponse brs=new BaseResponse("1000","验证码错误");
                    mav.addObject("data",brs);
                }
            }else{
                BaseResponse brs=new BaseResponse("1000","短信验证异常");
                mav.addObject("data",brs);
            }
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


    /**
     * 图片压缩
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/imagecompress.html")
    public ModelAndView imagecompress(HttpSession session,HttpServletRequest request,@RequestParam(value = "imgbase64", required = false) String imgbase64) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
//        try {
////            imgbase64=imgbase64.replaceAll("\\n","");
////            ImgCompress imgCompress=new ImgCompress(imgbase64);
////            String imgbase64fanhui=imgCompress.resizeFix(500,300);
////            mav.addObject("image",imgbase64fanhui);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return mav;
    }


    /**
     * 实名身份证图片上传并且保存实名认证信息
     * @param multipartRequest
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/base64upload.html", method = RequestMethod.POST)
    public ModelAndView upload(MultipartHttpServletRequest multipartRequest,
                               @RequestParam(value = "filename", required = false) String filename,
             HttpSession session, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        MultipartFile file =  multipartRequest.getFile(filename);
        try {
            byte[] img = file.getBytes();
            if(file.getSize()>1024*5*1024){
                BaseResponse base = new BaseResponse("300", "文件大小不能大于5M");
                mav.addObject("data",base);
                return mav;
            }
            if (getFileTypeByStream(file.getOriginalFilename()) == null) {
                BaseResponse base =new BaseResponse("300", "上传文件类型错误!");
                mav.addObject("data",base);
                return mav;
            }
            InputStream is = file.getInputStream();
//            ImgCompress imgCompress=new ImgCompress(is);

            String imags=ImgCompress.getCompressBase64FromUrl(is,500,300);
            mav.addObject("base64",imags);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

}
