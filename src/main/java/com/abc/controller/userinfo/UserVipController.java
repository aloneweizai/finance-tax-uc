package com.abc.controller.userinfo;

import com.abc.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zlk on 2017-07-26.
 */
@Controller
public class UserVipController extends BaseController{
    @GetMapping("/userinfo/user_vip.php")
    public String getVip(){
        return "userinfo/user_vip";
    }
}
