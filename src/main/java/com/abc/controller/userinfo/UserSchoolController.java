package com.abc.controller.userinfo;

import com.abc.application.SpringCtxHolder;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.CurriculumEvaluateBo;
import com.abc.soa.request.school.CurrMyStudyNumRes;
import com.abc.soa.request.school.CurrMyStudyRes;
import com.abc.soa.request.school.CurriculumListsyRes;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.UserDzsbRes;
import com.abc.soa.response.UserHndsRes;
import com.abc.soa.response.UserHngsRes;
import com.abc.soa.response.userinfo.LecturerListResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by stuy on 2017/8/30.
 */
@Controller
@RequestMapping(value = "/school")
public class UserSchoolController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(UserSchoolController.class);
    /**
     * 纳税企业列表显示
     * @param session
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/list.html")
    public ModelAndView user_enterprise(HttpSession session, Model model, HttpServletRequest request){
        try {
            ModelAndView mav=new ModelAndView("userinfo/school");
            UserBo userBo = getInfoService.getUserBo(request);
            Map<String,String> map=new HashMap<String,String>();
            map.put("userId",userBo.getId());
            CurriculumListsyRes currMyStudyRes=SoaConnectionFactory.get(request,ConstantsUri.CURRICULUM_COLLECT,map,CurriculumListsyRes.class);
            CurrMyStudyRes curriculumListsyRes=SoaConnectionFactory.get(request,ConstantsUri.CURRICULUM_HISTORY,map,CurrMyStudyRes.class);
            CurrMyStudyNumRes currMyStudyNumRes=SoaConnectionFactory.get(request,ConstantsUri.CURRICULUM_NUM,map,CurrMyStudyNumRes.class);

            //关注讲师列表
            map.put("status",1+"");
            map.put("page",0+"");
            map.put("size",0+"");
            LecturerListResp lecturerListResp=SoaConnectionFactory.get(request,ConstantsUri.LECTURER_FL_LIST,map,LecturerListResp.class);
            model.addAttribute("lecturerRes",lecturerListResp.getDataList());

            model.addAttribute("currMyStudyRes",curriculumListsyRes.getDataList());
            model.addAttribute("curriculumListsyRes",currMyStudyRes.getDataList());
            model.addAttribute("currMyStudyNumRes",currMyStudyNumRes.getData());
            //model.addAttribute("path", SpringCtxHolder.getProperty("imagedomain"));
            return mav;
        } catch (SoaException e) {
            ModelAndView mav=new ModelAndView("soaerror");
            //logger.debug("异常:"+e.getMessage());
            return mav;
        }
    }

    /**
     * 课堂详情页
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/pj/userpj.html",method = RequestMethod.POST)
    public ModelAndView userpj(HttpSession session, HttpServletRequest request, @RequestParam(value = "curriculumId", required = false,defaultValue = "") String curriculumId,
                               @RequestParam(value = "grade", required = false,defaultValue = "") String grade,
                               @RequestParam(value = "studyFeel", required = false,defaultValue = "") String studyFeel) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        UserBo obj = getInfoService.getUserBo(request);
        CurriculumEvaluateBo curriculumEvaluateBo=new CurriculumEvaluateBo();
        curriculumEvaluateBo.setUserId(obj.getId());
        curriculumEvaluateBo.setCurriculumId(curriculumId);
        curriculumEvaluateBo.setGrade(Integer.parseInt(grade));
        curriculumEvaluateBo.setStudyFeel(studyFeel);
        BaseResponse br= null;
        try {
            br = SoaConnectionFactory.post(request, ConstantsUri.CURRICULUM_PL_ADD,curriculumEvaluateBo,BaseResponse.class);
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
        mav.addObject("data",br);
        return mav;
    }
}
