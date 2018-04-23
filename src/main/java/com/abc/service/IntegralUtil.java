package com.abc.service;

import com.abc.soa.request.IntegralBo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stuy on 2017/10/26.
 */

/**
 * 积分充值
 */
public class IntegralUtil {

    /**
     * 积分列表集合
     */
    public List<IntegralBo> list=new ArrayList<IntegralBo>();

    /**
     * 积分充值标题
     */
    public final String TITLE="积分充值";

    public final String ID="JF0000000001";

    //  货币方式
    public final String METHOD="RMB";

    /**
     * 交易渠道
     */
    public final String JYQD="JFCZ";

    public IntegralUtil(){
        IntegralBo integralBo=new IntegralBo();
        integralBo.setIntegralId("JF20");
        integralBo.setFee(20);
        list.add(integralBo);
        integralBo=new IntegralBo();
        integralBo.setIntegralId("JF50");
        integralBo.setFee(50);
        list.add(integralBo);
        integralBo=new IntegralBo();
        integralBo.setIntegralId("JF100");
        integralBo.setFee(100);
        list.add(integralBo);
        integralBo=new IntegralBo();
        integralBo.setIntegralId("JF200");
        integralBo.setFee(200);
        list.add(integralBo);
        integralBo=new IntegralBo();
        integralBo.setIntegralId("JF0");
        integralBo.setFee(0);
        list.add(integralBo);
    }

}
