package com.abc.common.util;

import com.abc.soa.response.UserBo;
import com.abc.soa.response.UserTzxxBo;

/**
 * Created by stuy on 2017/10/11.
 */
public class UserString {






    public static UserBo stringTelHandle(UserBo userBo){
        if(userBo!=null){
            if(userBo.getPhone()!=null&&!"".equals(userBo.getPhone())){
                String phones=userBo.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
                userBo.setXinphone(phones);
            }
            return userBo;
        }else{
            return null;
        }
    }


    public static UserTzxxBo stringIdcardHandle(UserTzxxBo userTzxxBo){
        if(userTzxxBo!=null){
            if(userTzxxBo.getIdcard()!=null&&!"".equals(userTzxxBo.getIdcard())){
                String idcard=userTzxxBo.getIdcard().replaceAll("(\\d{4})\\d{10}(\\d{4})","$1**********$2");
                userTzxxBo.setXinidcard(idcard);
            }
            if(userTzxxBo.getRealName()!=null&&!"".equals(userTzxxBo.getRealName())){
                String name=userTzxxBo.getRealName().substring(0,1);
                userTzxxBo.setXinrealName(name+"**");
            }
            return userTzxxBo;
        }else{
            return null;
        }
    }
}
