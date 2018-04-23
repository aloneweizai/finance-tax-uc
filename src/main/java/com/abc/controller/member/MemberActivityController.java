package com.abc.controller.member;

import com.abc.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author:zlk
 * @Description:会员活动
 * @Date:2017-08-15
 * @Time:18:08
 */
@Controller
@RequestMapping(value = "/member")
public class MemberActivityController extends BaseController{

    @GetMapping("/member_activity.php")
    private String activty(){
        return "member/member_activity";
    }

}
