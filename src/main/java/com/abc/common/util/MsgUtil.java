package com.abc.common.util;

import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.BuMsg;
import com.abc.soa.response.BuMsgRs;

import com.abc.soa.response.BusinessMessageRs;
import com.abc.soa.response.activity.lottery.LotteryLogRs;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsz on 2017/11/13 0013.
 * 554600654@qq.com
 */
public class MsgUtil {

    /**
     * 根据用户会员判断发送消息
     * @param request
     * @param buMsg
     * @return
     */
    public static BuMsg userMsgVip(HttpServletRequest request, BuMsg buMsg){
        try {
            BuMsgRs businessMessageRs =  SoaConnectionFactory.post(request, ConstantsUri.USERMSG_VIPPOST, buMsg, BuMsgRs.class);
            if(businessMessageRs != null && businessMessageRs.isSuccess()) {
                return businessMessageRs.getData();
            }
        } catch (SoaException e) {
            e.printStackTrace();
        }
        return null;

    }
    //  抽奖结果通知id  25daDubPh6moBLzzYPf_WvSFapivLosehyOqALBaIgI
    //    {{first.DATA}}
    //    奖品类别：{{keyword1.DATA}}
    //    中奖日期：{{keyword2.DATA}}
    //    {{remark.DATA}}   抽奖结果通知 奖品类别：三个月流量 中奖日期：2017年9月8日 感谢您参与抽奖活动！
    public static BuMsg userMsgVipLottery(HttpServletRequest request,String msg,String userId,String lotteryName,String lotteryDate){
        BuMsg buMsg = new BuMsg();
        buMsg.setWebMsg(msg);
        buMsg.setPhoneMsg(msg);
        buMsg.setUserId(userId);
        buMsg.setType("2");
        buMsg.setBusiType("BANGBANG_ACTIVES");
        List<String> userIds = new ArrayList<>();
        userIds.add(userId);
        buMsg.setUserIds(userIds);
        buMsg.setBusinessId(userId);
        buMsg.setTemplateid("25daDubPh6moBLzzYPf_WvSFapivLosehyOqALBaIgI");
        Map<String, String> map = new HashMap<String, String>();
        map.put("first","抽奖结果通知");
        map.put("keyword1",lotteryName);
        map.put("keyword2",lotteryDate);
        map.put("remark","感谢您参与抽奖活动！");
        buMsg.setDataList(map);
        return userMsgVip(request,buMsg);
    }
}
