package com.abc.controller.member;

import com.abc.application.SpringCtxHolder;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.BaseObject;
import com.abc.common.util.DateUtil;
import com.abc.common.util.MsgUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.DizhiIDBo;
import com.abc.soa.request.LotteryActivityprizeRq;
import com.abc.soa.response.*;
import com.abc.soa.response.activity.lottery.*;
import com.abc.soa.response.activity.lottery.LotteryLogBO;
import com.abc.soa.response.activity.lottery.LotteryLogRs;
import com.abc.soa.response.userinfo.UserPointsResp;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * @Author liuQi
 * @Date 2017/9/8 17:58
 */

@Controller
@RequestMapping(value = "/member/lottery")
public class LotteryController extends BaseController{

    private final static Logger logger = LoggerFactory.getLogger(LotteryController.class);

       public String getRemortIP(HttpServletRequest request) {
        //为了调试
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }


    /**获取用户当前积分*/
    @GetMapping("/getjf.php")
    public void getjf(HttpServletRequest request, HttpServletResponse response)  {
        UserBo user = getInfoService.getUserBo(request);
            if(user != null){
                try {

                    UserPointsResp userPointsResp = SoaConnectionFactory.getRestful(request, ConstantsUri.POINTS, null, UserPointsResp.class,user.getId());
                    Integer points = userPointsResp.getData().getMyPoints();

                    response.getWriter().write(points.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


    }
    /**获取用户当天抽奖次数*/
    @GetMapping("/userCount.php")
    public void userCount(HttpServletRequest request, HttpServletResponse response)  {
        UserBo user = getInfoService.getUserBo(request);
        if(user ==null || user.getId() == null || user.getId().isEmpty()){
            return;
        }
        try {
            IntRs rs =  SoaConnectionFactory.get(request, ConstantsUri.LOTTERY_LOG_USERCOUNT, null, IntRs.class,user.getId());
            if(rs.getData() != null){
                response.getWriter().write(rs.getData().toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /* 点击抽奖 */
    @PostMapping("/start.php")
    public @ResponseBody BaseResponse start(HttpServletRequest request, HttpSession session, Model model)  {
          LotteryActivityBO lotteryActivityBO = getActivity(request);
        if(!lotteryActivityBO.getStatus()){
            return new BaseResponse("","活动正在维护，请稍后再试...");
        }
        if(!DateUtil.dateIn(lotteryActivityBO.getStartTime(),lotteryActivityBO.getEndTime(),new Date())){
            return new BaseResponse("", "活动未开始或已结束，请稍后再试...");

        }

        try {
            UserBo user = getInfoService.getUserBo(request);

            if(user ==null || user.getId() == null || user.getId().isEmpty()){
                return new BaseResponse("","用户信息异常");
            }
            //userId=6c5abbd9-626b-4282-8747-b1762037f79b&activityId=a8376e70-1f1c-47bd-b9d4-2805b88e84b0&ip=111
            HashMap map = new HashMap();
            map.put("userId",user.getId());
            map.put("activityId",lotteryActivityBO.getId());
            map.put("ip",getRemortIP(request));

            if (   user.getLevel() != null){
               String level =   lotteryActivityBO.getUserLevel();
               if (level != null && !level.isEmpty()){
                   if(user.getLevel().length()>2){
                       String userLevel  = user.getLevel().substring(2);
                       Integer userLevelI =  Integer.parseInt(userLevel);
                       Integer activityLevelI =  Integer.parseInt(level);
                       if(activityLevelI > userLevelI){

                           return new BaseResponse("","用户等级不足，请提升等级再试！");
                       }

                   }
               }

            }
//            String activityLevel = lotteryActivityBO.getUserLevel();
//           String userLevel =  user.getLevel();

            LotteryLogRs lotteryLogRs =  SoaConnectionFactory.get(request, ConstantsUri.LOTTERY_TRIGGER, map, LotteryLogRs.class);
            if (!lotteryLogRs.isSuccess()){

                return new BaseResponse("",lotteryLogRs.getMessage());
            }
            LotteryLogBO lotteryLogBO = lotteryLogRs.getData();
            List<Integer> listI = new ArrayList<>();
            int fori =-1 ;
            List<LotteryActivityprizeBO> jpList =  getLotteryList(request,lotteryActivityBO);
            for (LotteryActivityprizeBO obj: jpList                 ) {
                fori++;
                if(lotteryLogBO.getLotteryId() == null ) {
                    if (obj.getNoluck() ) {
                        listI.add(fori);
                    }
                } else if(lotteryLogBO.getLotteryId().equals(obj.getLotteryId())){
                    listI.add(fori);
                }
            }
            //这里处理重复奖品
            if (listI.size() == 0){

                return new BaseResponse("","奖品错误");
            }
            Random random = new Random(System.currentTimeMillis());
            Integer ra = random.nextInt(listI.size());
            Integer returnI = listI.get(ra);
            LotteryReturnRs lotteryReturnRs = new LotteryReturnRs();
            lotteryReturnRs.setCode("2000");
            lotteryReturnRs.setMessage("处理成功");
            LotteryReturnBO  lotteryReturnBO = new LotteryReturnBO();
            lotteryReturnBO.setIndex(returnI);
            if(lotteryLogBO.getLotteryId() == null ) {

                lotteryReturnBO.setLuck(false);
            }else{
//                //中奖了
//                BusinessMessage businessMessage  = new BusinessMessage();
//                businessMessage.setUserId(user.getId());
//                businessMessage.setBusinessId(lotteryLogBO.getLotteryId());
//                if(lotteryLogBO.getLotteryLevel() == null){
//                    lotteryLogBO.setLotteryLevel("");
//                }else{
//                    lotteryLogBO.setLotteryLevel(lotteryLogBO.getLotteryLevel()+":");
//                }
//                businessMessage.setContent("恭喜您抽中" + lotteryLogBO.getLotteryLevel()  + lotteryLogBO.getLotteryName() +" ，奖品将于7个工作日内发放，谢谢您的参与！");
//                businessMessage.setType("1");
//                MsgUtil.userMsgInsert(request,businessMessage);
                String lotterName = lotteryLogBO.getLotteryName();
                String msg = "抽奖结果通知 " + lotterName +" ，奖品将于7个工作日内发放，谢谢您的参与！";
                MsgUtil.userMsgVipLottery(request,msg,user.getId(),lotteryLogBO.getLotteryName(), DateUtil.getCurrentTime("yyyy-MM-dd"));

                lotteryReturnBO.setLuck(true);
                lotteryReturnBO.setLotteryId(lotteryLogBO.getLotteryId());
                lotteryReturnBO.setLotteryName(lotteryLogBO.getLotteryName());
            }
            lotteryReturnRs.setData(lotteryReturnBO);
            return lotteryReturnRs;
        } catch (SoaException e) {
            logger.error("异常:" + e.getMessage());
            return new BaseResponse("","系统异常");
        }
    }

    /* 中奖者名单 */
    @GetMapping("/winners.php")
    public @ResponseBody BaseResponse winners(HttpServletRequest request, Model model){
        try {
            HashMap map = new HashMap();
            map.put("page","1");
            map.put("size","20");
            map.put("notluck","0");
            LotteryLogListRes rs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERY_WINNERS, map, LotteryLogListRes.class);
            return rs;
        } catch (SoaException e) {
            logger.error("异常:" + e.getMessage());
            return new BaseResponse("","系统异常");
        }
    }

    private AddressBo getDefaultAddress(HttpServletRequest request, HttpSession session) throws SoaException {
        UserBo user = getInfoService.getUserBo(request);
        AddressRes addressRes = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS,null, AddressRes.class, user.getId());
        List<AddressBo> dataList = addressRes.getDataList();
        AddressBo defaultAddress = null;
        if(dataList!= null && !dataList.isEmpty()){
            for (AddressBo address : dataList){
                if(address.getIsDefault() != null && address.getIsDefault()){
                    defaultAddress = address;
                    break;
                }
            }
            if(defaultAddress == null){
                defaultAddress = dataList.get(0);
            }
        }
        return defaultAddress;
    }

    private  TemplateBo getTemplateBo(HttpServletRequest request, LotteryActivityBO lotteryActivityBO ){

        LotteryTemplateExRs tplList = null;
        if (lotteryActivityBO.getTemplateId()==null){
            throw  new  RuntimeException("模版错误");
        }
        try {

            tplList = SoaConnectionFactory.get(request, ConstantsUri.CMS_TEMPLATE_VIEW, null, LotteryTemplateExRs.class, lotteryActivityBO.getTemplateId());
        } catch (SoaException e) {
            e.printStackTrace();
        }
        if(tplList == null){
            TemplateBo templateBo = new TemplateBo();
            templateBo.setTemplatePath("csw/template/lottery/zp.html");
            return templateBo;

        }else{
            return tplList.getData();
        }
    }
    private  List<LotteryActivityprizeBO> getLotteryList(HttpServletRequest request,LotteryActivityBO lotteryActivityBO){
        LotteryActivityprizeRq lotteryActivityprizeRq = new LotteryActivityprizeRq();
        lotteryActivityprizeRq.setActivityId(lotteryActivityBO.getId());
        LotteryActivityprizeRs lotteryActivityprizeRs = null;
        try {
            lotteryActivityprizeRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYACTIVITYPRIZE_LIST, lotteryActivityprizeRq, LotteryActivityprizeRs.class);
        } catch (SoaException e) {
            e.printStackTrace();
        }
        List<LotteryActivityprizeBO> list=null;
        if(lotteryActivityprizeRs!=null){
            list = lotteryActivityprizeRs.getDataList();
            //int tmpi = 0;
            //int size = list.size();
            for (LotteryActivityprizeBO obj : list) {
                String img = obj.getLotteryImage();
                if(img != null && !img.isEmpty()){
                    obj.setLotteryImage(SpringCtxHolder.getProperty("imagedomain") + img);
                }
            }
        }
        //这里获得 奖品
        return list;
    }

    private LotteryActivityBO getActivity(HttpServletRequest request){
        //String activityId = "a8376e70-1f1c-47bd-b9d4-2805b88e84b0";
        String activityId = "8971dc97-fa5d-413a-bdfb-fb2da34eb957";
        LotteryActivityRs lotteryActivityRs = null;
        try {
            lotteryActivityRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYACTIVITY_INFO, null, LotteryActivityRs.class,activityId);
        } catch (SoaException e) {
            e.printStackTrace();
        }
        LotteryActivityBO lotteryActivityBO=null;
        if(lotteryActivityRs!=null){
            lotteryActivityBO =lotteryActivityRs.getData();
            if(lotteryActivityBO == null){
                throw new RuntimeException("活动不存在");
            }
        }
        return lotteryActivityBO;
    }
    @GetMapping("/view.php")
    public void renderContent(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> root = new HashMap<>();
        LotteryActivityBO lotteryActivityBO = getActivity(request);

        List<LotteryActivityprizeBO> jpList =  getLotteryList(request,lotteryActivityBO);
        root.put("listRs", jpList);

        TemplateBo templateBo = getTemplateBo(request,lotteryActivityBO);

        LotteryLogRq lotteryLogRq = new LotteryLogRq();
        lotteryLogRq.setIsluck(1);
        LotteryLogRs lotteryLogRs = null;
        try {
            lotteryLogRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYLOG_LIST, lotteryLogRq, LotteryLogRs.class);
        } catch (SoaException e) {
            e.printStackTrace();
        }
        List<LotteryLogBO> list=null;
        if(lotteryLogRs!=null){
            list = lotteryLogRs.getDataList();
        }
        root.put("logRs",list);
        root.put("lotteryActivityBO",lotteryActivityBO);
        templateDo(request, response, templateBo, root);
    }
    public   String addRootToPath(String relativePath){
        return SpringCtxHolder.getProperty("TEMPLATE_ROOT_PATH") +"/"+ relativePath;
    }
    public void templateDo(HttpServletRequest request, HttpServletResponse response,TemplateBo templateBo, Map root) {
        root.put("snsUrl", SpringCtxHolder.getProperty("snsdomain"));
         root.put("picUrl", SpringCtxHolder.getProperty("imagedomain"));
        root.put("cswUrl", SpringCtxHolder.getProperty("cswdomain"));
        String ucUrl = SpringCtxHolder.getProperty("ucdomain");
        root.put("ucUrl", ucUrl);

        String uri = ucUrl + "/moban";
        root.put("uri", uri);
        Configuration config = new Configuration(Configuration.VERSION_2_3_24);
        //List<String> generatedContentIdArray = new ArrayList<String>();

        String tplPathName = templateBo.getTemplatePath();
        String tplPathNameWithRoot = addRootToPath(tplPathName);
        String tplPath = tplPathNameWithRoot.substring(0, tplPathNameWithRoot.lastIndexOf("/"));
        String tplName = tplPathNameWithRoot.substring(tplPathNameWithRoot.lastIndexOf("/") + 1);

        try {
            File file = ResourceUtils.getFile(tplPath);
            config.setDirectoryForTemplateLoading(file);//设置模板路径
        } catch (IOException e) {
            logger.error("moban get failed:"+ e.getCause().getMessage());
            e.printStackTrace();
        }
        config.setDefaultEncoding("UTF-8");//编码
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Writer out = null;
        try {
            out = response.getWriter();
            Template temple = config.getTemplate(tplName);
            temple.process(root, out);//处理
            out.flush();
        } catch (Exception e) {
            logger.error("moban flush failed:"+ e.getCause().getMessage());
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("out close failed:"+ e.getCause().getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
