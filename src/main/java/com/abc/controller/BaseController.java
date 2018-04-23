package com.abc.controller;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.CallbackConstants;
import com.abc.service.GetInfoService;
import com.abc.service.RedisCode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author JiangZuoWei
 * @createTime 2015年11月14日 下午7:42:07
 * @description 
 */
public class BaseController {

    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    protected SoaConnectionFactory soaConnectionFactory;

    @Autowired
    protected GetInfoService getInfoService;



	public BaseController() {
        super();
    }
    
    protected ModelAndView checkMap(ModelAndView mav, Map<String, String> map){
        if (map.isEmpty()) {
            mav.addObject("rescode", CallbackConstants.PARAMETER_EMPTY.code);
            mav.addObject("message", CallbackConstants.PARAMETER_EMPTY.msg);
            return mav;
        }
        Iterator<?> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            if(null == entry.getValue() || "".equals(entry.getValue().toString())){
                mav.addObject("rescode", CallbackConstants.PARAMETER_EMPTY.code);
                mav.addObject("message", CallbackConstants.PARAMETER_EMPTY.msg);
            }
        }
        return mav;
    }
    
    public ModelAndView callback(Object obj) {
    	ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView(), null);
    	modelAndView.addObject("result", obj);
    	return modelAndView;
    }
    
    public ModelAndView callbackMsg(ModelAndView mav, CallbackConstants constants) {
    	BaseResponse response = new BaseResponse(constants.code, constants.msg);
    	mav.addObject("result", response);
        return mav;
    }
    
    public ModelAndView callbackMsg(ModelAndView mav, BaseResponse response) {
        mav.addObject("result", response);
        return mav;
    }
    

    public void nativeResponseWithBytes(HttpServletResponse response, Map resultMap){
            ObjectMapper mapper = new ObjectMapper();
            String resultStr = null;
            PrintWriter out=null;
        try{
                resultStr = mapper.writeValueAsString(resultMap);
                response.setCharacterEncoding("UTF-8"); //设置编码格式
                response.setContentType("text/html");   //设置数据格式
                out = response.getWriter(); //获取写入对象
                out.print(resultStr); //将json数据写入流中
            }catch(Exception e){
                log.error(e.getMessage());
                e.printStackTrace();
            }finally{
                try {
                    if(out!=null){ out.flush(); out.close();}
                } catch (Exception e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
            }
    }

    public void nativeResponseWithJsonBytes(HttpServletResponse response, Map resultMap){
        ObjectMapper mapper = new ObjectMapper();
        String resultStr = null;
        PrintWriter out=null;
        try{
            resultStr = mapper.writeValueAsString(resultMap);
            response.setCharacterEncoding("UTF-8"); //设置编码格式
            response.setContentType("application/json");   //设置数据格式
            out = response.getWriter(); //获取写入对象
            out.print(resultStr); //将json数据写入流中
        }catch(Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }finally{
            try {
                if(out!=null){ out.flush(); out.close();}
            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断是否为空
     * @param objstr 字符串
     * @return boolean
     */
    public boolean isNull(String objstr){
        if(objstr!=null&&!"".equals(objstr)){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 判断图片验证码是否失效
     * @param code
     * @param session
     * @return
     */
    public BaseResponse isCodeTime(String code, HttpSession session){
        //long time=System.currentTimeMillis();
        String codef=RedisCode.CODE+":"+ session.getId();
        String obj=getInfoService.getString(codef);
        if(obj!=null){
            String codes=obj.toLowerCase();
            String codet=code.toLowerCase();
           if(codes.equals(codet)){
               BaseResponse br=new BaseResponse("2000","");
               return br;
           }else{
               BaseResponse br=new BaseResponse("1000","图片验证码错误");
               return br;
           }
        }else{
            BaseResponse br=new BaseResponse("1000","图片验证码失效");
            return br;
        }
    }



    /**
     * 根据会员等级获取会员等级名称
     * @param level
     * @return
     */
    public String getLevelName(String level){
        String levelName = null;
        switch (level){
            case "VIP0":
                levelName = "普通用户";
                break;
            case "VIP1":
                levelName = "银卡会员";
                break;
            case "VIP2":
                levelName = "黄金会员";
                break;
            case "VIP3":
                levelName = "钻石会员";
                break;
            case "VIP4":
                levelName = "超级会员";
                break;
            default:
                levelName = "普通用户";
                break;
        }
        return levelName;
    }

    /**
     * 计算会员有效期
     * @param vipDate
     * @return
     */
    public int calcDate(Date vipDate){
        int vipValidDay = -1;
        if(vipDate !=null){
            // 先比较年，再比较天
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            int current = calendar.get(Calendar.DAY_OF_YEAR);
            calendar.setTime(vipDate);
            int vipYear = calendar.get(Calendar.YEAR);
            int vip = calendar.get(Calendar.DAY_OF_YEAR);

            if(currentYear <=  vipYear) {
                vipValidDay = (vipYear- currentYear)*365 + vip - current;
            }
        }
        return vipValidDay;
    }
}
