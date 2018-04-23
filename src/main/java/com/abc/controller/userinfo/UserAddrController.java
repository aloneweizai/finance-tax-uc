package com.abc.controller.userinfo;

import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.DizhiIDBo;
import com.abc.soa.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by stuy on 2017/7/19.
 */
@Controller
@RequestMapping(value = "/userinfo")
public class UserAddrController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(UserAddrController.class);

    /**
     * 收货地址列表
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/useraddr.html")
    public ModelAndView user_index(HttpSession session, HttpServletRequest request) {
        try {
            ModelAndView mav= new ModelAndView("userinfo/dizhi");
            UserBo loginUserBo = getInfoService.getUserBo(request);
            if(loginUserBo!=null){
                AddressRes addressRes=SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS,null, AddressRes.class,loginUserBo.getId());
                List<AddressBo> data = addressRes.getDataList();
                for (AddressBo addressBo : data){
                    DizhiIDBo dizhiIDBo=new DizhiIDBo();
                    boolean bool=false;
                    if(isNull(addressBo.getProvince())){
                        bool=true;
                        dizhiIDBo.setProvinceId(addressBo.getProvince());
                    }
                    if(isNull(addressBo.getCity())){
                        bool=true;
                        dizhiIDBo.setCityId(addressBo.getCity());
                    }
                    if(isNull(addressBo.getArea())){
                        bool=true;
                        dizhiIDBo.setAreaId(addressBo.getArea());
                    }
                    if(bool){
                        DizhiNameBo dizhiNameBo=SoaConnectionFactory.get(request,ConstantsUri.DIQU_QUERY_ID,dizhiIDBo,DizhiNameBo.class);
                        addressBo.setDizhi(dizhiNameBo);
                    }
                }
                mav.addObject("addresslist",data);
            }
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 新增收货地址跳转页面
     * @return
     */
    @GetMapping("/addaddr.html")
    public ModelAndView addaddr() {
        ModelAndView mav= new ModelAndView("userinfo/adddizhi");
        mav.addObject("address",new AddressBo());
        return mav;
    }

    /**
     * 新增收货地址保存操作
     * @param session
     * @param request
     * @param addressBo
     * @return
     */
    @RequestMapping(value = "/save.html",method = RequestMethod.POST)
    public ModelAndView add(HttpSession session,HttpServletRequest request, @RequestBody AddressBo addressBo) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            AddressDataBo addressDataBo=null;
            if(addressBo.getId()!=null&&!"".equals(addressBo.getId())){
                addressDataBo=SoaConnectionFactory.put(request,ConstantsUri.USER_UPD_ADDRESS,addressBo, AddressDataBo.class,userbo.getId(),addressBo.getId());
            }else{
                addressDataBo=SoaConnectionFactory.post(request,ConstantsUri.USER_SAVE_ADDRESS,addressBo, AddressDataBo.class,userbo.getId());
            }
            mav.addObject("data",addressDataBo);
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
     * 删除收货地址
     * @param session
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public ModelAndView del(HttpSession session,HttpServletRequest request,@PathVariable(value = "id") String id) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            BaseResponse baseResponse=SoaConnectionFactory.delete(request,ConstantsUri.USER_DEL_ADDRESS,null, BaseResponse.class,userbo.getId(),id);
            mav.addObject("data",baseResponse);
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
     * 跳转至收货地址修改页面修改页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") String id,HttpSession session, HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("userinfo/adddizhi");
            UserBo userbo = getInfoService.getUserBo(request);
            AddressIDRes addressIDRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_ID_ADDRESS,null, AddressIDRes.class,userbo.getId(),id);
            mav.addObject("address",addressIDRes.getData());
            mav.addObject("data","修改地址");
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 设置默认收货地址
     * @param id
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/default/{id}", method = RequestMethod.POST)
    public ModelAndView sh_default(@PathVariable(value = "id") String id,HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            AddressBo addressBo=new AddressBo();
            addressBo.setIsDefault(true);
            BaseResponse addressIDRes=SoaConnectionFactory.put(request,ConstantsUri.USER_UPD_DEFAULT_ADDRESS,addressBo, BaseResponse.class,userbo.getId(),id);
            mav.addObject("data",addressIDRes);
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
