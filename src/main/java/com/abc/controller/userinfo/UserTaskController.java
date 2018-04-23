package com.abc.controller.userinfo;

import com.abc.application.SpringCtxHolder;
import com.abc.bean.userinfo.DailyTask;
import com.abc.bean.userinfo.NormalTask;
import com.abc.bean.userinfo.SysTask;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.PaginationReq;
import com.abc.soa.request.userinfo.TaskReq;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.userinfo.DailyCountResp;
import com.abc.soa.response.userinfo.NormalTaskResp;
import com.abc.soa.response.userinfo.TaskResp;
import com.abc.soa.response.userinfo.UserTaskBoResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 我的任务
 * Created by zlk on 2017-07-19.
 */
@Controller
public class UserTaskController extends BaseController{
    private final static Logger logger = LoggerFactory.getLogger(UserTaskController.class);

    //任务类型:新手1,日常2,特殊3
    private String noviceType = "1";
    private String dailyType = "2";
    private String specialType = "3";

    /**
     * 我的任务
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/userinfo/task.php")
    public String list( HttpServletRequest request, PagerSpec pagerSpec,Model model,
                        @RequestParam(value = "taskType",defaultValue = "2") String taskType) {
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            String userId = userBo.getId();
            TaskReq taskReq = new TaskReq();
            taskReq.setUserId(userId);
            taskReq.setStatus("1");//任务已启用
            taskReq.setName(null);
            taskReq.setType(null);
            taskReq.setPage(0);
            taskReq.setSize(0);

            //我的任务积分
            UserTaskBoResp taskBo = SoaConnectionFactory.getRestful(request, ConstantsUri.TASK_POINTS_ID, null, UserTaskBoResp.class, userId);
            model.addAttribute("taskBo",taskBo.getData());

            NormalTaskResp normalTaskResp = SoaConnectionFactory.get(request, ConstantsUri.TASK_DAILY, taskReq, NormalTaskResp.class);
            List<NormalTask> dailyTask = normalTaskResp.getDataList();
            model.addAttribute("dailyTask", dailyTask);

            model.addAttribute("path", SpringCtxHolder.getProperty("imagedomain"));
            model.addAttribute("taskType",taskType);
            return "userinfo/task";
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }


    /**
     * 我的任务
     * @param request
     * @return
     */
    @GetMapping("/task/type")
    public ModelAndView  list_type(HttpServletRequest request,PagerSpec pagerSpec,
                                   @RequestParam(value = "taskType",defaultValue = "2") String taskType) {
        ModelAndView mav = null;
        if(taskType.equals(dailyType)) { //获取日常任务
             mav = new ModelAndView("userinfo/task_daily");
        }else if(taskType.equals(noviceType)){//获取新手任务
             mav = new ModelAndView("userinfo/task_novice");
        }else if(taskType.equals(specialType)){ //获取特殊任务
             mav = new ModelAndView("userinfo/task_special");
        }
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            String userId = userBo.getId();
            TaskReq taskReq = new TaskReq();
            taskReq.setUserId(userId);
            taskReq.setStatus("1");//任务已启用
            taskReq.setName(null);
            taskReq.setType(null);
            taskReq.setPage(0);
            taskReq.setSize(0);

            //我的任务积分
            UserTaskBoResp taskBo = SoaConnectionFactory.getRestful(request, ConstantsUri.TASK_POINTS_ID, null, UserTaskBoResp.class, userId);
            mav.addObject("taskBo", taskBo.getData());

            if(taskType.equals(dailyType)) { //获取日常任务
                NormalTaskResp normalTaskResp = SoaConnectionFactory.get(request, ConstantsUri.TASK_DAILY, taskReq, NormalTaskResp.class);
                List<NormalTask> dailyTask = normalTaskResp.getDataList();
                mav.addObject("dailyTask", dailyTask);
            }else if(taskType.equals(noviceType)){//获取新手任务
                NormalTaskResp noviceTaskResp = SoaConnectionFactory.get(request, ConstantsUri.TASK_ONETIME, taskReq, NormalTaskResp.class);
                List<NormalTask> noviceTask =noviceTaskResp.getDataList();
                mav.addObject("noviceTask", noviceTask);
            }else if(taskType.equals(specialType)){ //获取特殊任务
                NormalTaskResp specialTaskResp = SoaConnectionFactory.get(request, ConstantsUri.TASK_SPECIAL, taskReq, NormalTaskResp.class);
                List<NormalTask> specialTask =specialTaskResp.getDataList();
                mav.addObject("specialTask", specialTask);
            }

            mav.addObject("path", SpringCtxHolder.getProperty("imagedomain"));
            mav.addObject("taskType", taskType);
            return mav;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            mav= new ModelAndView("soaerror");
            return mav;
        }
    }

}
