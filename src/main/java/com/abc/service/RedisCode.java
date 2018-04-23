package com.abc.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuy on 2017/9/26.
 */
public class RedisCode {


    public final static String ONLY="ONLY";
    public final static String USER_ID="USER_ID";
    public final static String CURRENT_USER="CURRENT_USER";
    public final static String USER_TOKEN="USER_TOKEN";
    public final static String CODE="USER:CODE";

    public final static String ACCESSTOKEN="TOKEN:ACCESSTOKEN";

    public final static String ORDER="USER:ORDER";

    public final static String USER_OBJECT="USER:USER_OBJECT";

    public final static String USER_CLIENT_OBJECT="USER:USER_CLIENT_OBJECT";


//    用户言缓存id
    public final static String USER_SALT="USER:USER_SALT";


//    缓存加密公钥
    public final static String PUBLIC_KEY="UTIL:PUBLIC_KEY";

//    缓存rsa私钥
    public final static String PRIVATE_KEY="UTIL:PRIVATE_KEY";

//    数据字典缓存
    public final static String DICT="INVARIANTDATA:DICT";

    //省份缓存
    public final static String PROVINCE_LIST="INVARIANTDATA:PROVINCE_LIST";

    //市缓存
    public final static String CITY_LIST="INVARIANTDATA:CITY_LIST";

    //区县缓存
    public final static String AREA_LIST="INVARIANTDATA:AREA_LIST";

    //客户端token缓存
    public final static String CLIENT_USER="USER:CLIENT_USER";

    //代办任务更新
    public final static String USER_TODO="USER:USER_TODO";

    //用户跳转结算页订单信息缓存
    public final static String USER_ORDER_SETTLEMENT="USER:ORDER:SETTLEMENT";

    //下单完成订单缓存执行支付操作
    public final static String USER_ORDER_DATA="USER:ORDER:DATA";

    //30分钟失效
    public final static long TIME_30=30*60;

    //一天失效
    public final static long TIME_24_60_60=24*60*60;

    //一个月失效
    public final static long TIME_30_24_60_60=30*24*60*60;

    //订单超时2小时
    public final static long TIME_2_60_60=2*60*60;

    public final static long TIME_5_60=30*60;

}
