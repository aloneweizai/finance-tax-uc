package com.abc.controller.userinfo;

import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.LevelReq;
import com.abc.soa.response.LevelBo;
import com.abc.soa.response.userinfo.UserLevelBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:zlk
 * @Description:用户经验等级
 * @Date:2017-09-08
 * @Time:9:48
 */
@Controller
@RequestMapping(value = "/userinfo")
public class UserLevelController extends BaseController{
    private final static Logger logger = LoggerFactory.getLogger(UserLevelController.class);

    @RequestMapping(value = "/user_level_rule.html",method = RequestMethod.GET)
    public ModelAndView rule(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("userinfo/user_level_rule");
        try {
            LevelReq levelReq = new LevelReq();
            levelReq.setPage(0);
            levelReq.setSize(0);
            levelReq.setName(null);
            levelReq.setStatus(true);
            UserLevelBo userLevelBo= SoaConnectionFactory.get(request, ConstantsUri.USER_LEVEL, levelReq, UserLevelBo.class);
            List<LevelBo> dataList = userLevelBo.getDataList();
            mav.addObject("levelList",dataList);
            List<Map>list = new ArrayList<>();
            int num = 1;
            String medalName = null;
            if(dataList!= null && dataList.size()>0){
                for(int i = 0;i <dataList.size();i++){
                    if(medalName != null && medalName.equals(dataList.get(i).getMedal())){
                        num = 1;
                        continue;
                    }else {
                        medalName = dataList.get(i).getMedal();
                        for (int j = i + 1; j < dataList.size(); j++) {
                            if (medalName.equals(dataList.get(j).getMedal())) {
                                num++;
                            }
                        }
                        Map<String, Integer> map = new HashMap<>();
                        map.put(medalName, num);
                        list.add(map);
                    }
                }
            }
            mav.addObject("medals",list);

        }catch (Exception e){
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
