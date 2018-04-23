package com.abc.soa;

/**
 * @author JiangZuoWei
 * @createTime 2015年11月11日 下午2:14:22
 * @description
 */
public enum ConstantsUri {

    //发票验旧
    QUERY_FP_ZL("发票种类查询接口", "fp/fpyj/getfpzldm"),

    USER_LOGIN("用户登录接口", "/uc/login"),
    USER_LOGIN_V2("用户登录接口", "/uc/v2/login"),


    USER_LOGOUT("用户登出","/uc/logout/{token}"),
    USER_REGISTER("用户注册接口", "/uc/register"),
    USER_REGISTER_V2("用户注册接口", "/uc/v2/register"),
    SMS_CODE("短信接口","/message/getcode"),
    SMS_CODE_NOTPHONE("短信接口","/uc/user/verifycode"),
    SMS_COD_PHONELOGIN("短信登录","/uc/user/phonelogin/code"),
    SMS_YZ_CODE("短信验证码验证","/message/verify"),
    SMS_YZ_CODE_USER("短信验证码验证","/uc/user/verify"),

    LOGIN_TODO("更新代办任务","/uc/login/todo"),

    SMS_OLDPHONE("验证原手机号","/uc/user/oldphone"),

    SMS_VERIFYLOGIN("没登陆短信验证码","/uc/verifylogin"),
    SMS_VERIFYPHONE("忘记密码短信验证码","/uc/verifyphone"),
    PHONE_LOGIN("手机验证码登录","/uc/verifylogin"),
    USER_PASSWROD("用户修改密码","/uc/user/password"),
    USER_PHONE("用户是否存在","/uc/user/u/{usernameOrPhone}"),
    USER_TZXX("获取用户拓展信息","/uc/user/extend/{id}"),
    USER_LEVEL("查询用户等级经验值","/uc/uexp/level"),
    USER_ADDRESS("收货地址列表","/uc/address/{userId}"),
    USER_INVOICE("发票列表","/uc/invoice/{userId}"),
    PROVINCE("省列表","/uc/province"),
    DICT("数据字典","/uc/dict/kv/{dictId}"),
    LOTTERY_TRIGGER("点击抽奖","/uc/lotteryactivity/luckdraw"),
    LOTTERY_WINNERS("中奖名单","/uc/lotterylog"),
    USER_INFO_TOKEN("通过Token获取用户信息","/uc/user/token/{token}"),
    LOTTERYACTIVITY_LIST("积分抽奖活动","/uc/lotteryactivity"),
    LOTTERYACTIVITY_INFO("积分抽奖活动单个","/uc/lotteryactivity/{id}"),
    LOTTERYACTIVITYPRIZE_LIST("积分抽奖活动商品","/uc/lotteryactivityprize"),
    LOTTERYLOG_LIST("积分抽奖日志列表","/uc/lotterylog"),
    LOTTERYLOG_INFO("积分抽奖日志","/uc/lotterylog/{id}"),
    CMS_TEMPLATE_VIEW("查看单个模板信息","/cms/template/{templateId}"),
    USER_GETLOTTERY("领取奖品接口","/uc/lotterylog/getLottery"),

    USER_U_PHONE("验证手机号码是否存在","/uc/user/u/phone/{phone}"),

    USER_SAVE_ADDRESS("新增收货地址","/uc/address/{userId}"),
    USER_DEL_ADDRESS("删除收货地址","/uc/address/{userId}/{id}"),
    USER_ID_ADDRESS("根据id获取收货地址","/uc/address/{userId}/{id}"),
    USER_UPD_ADDRESS("修改收货地址","/uc/address/{userId}/{id}"),
    USER_UPD_DEFAULT_ADDRESS("设置默认收货地址","/uc/address/default/{userId}/{id}"),

    SYSFILEUP("文件上传", "/message/sftp"),
    SYSFILEUP_BASE64("文件上传", "/message/sftp/upload"),

    USER_HISTORY_INVOICE("获取历史发票","/uc/invoice/history"),
    USER_ORDER_USER("获取订单列表","/uc/order/user"),
    USER_ID_INVICE("查询发票详细信息","/uc/invoice/user/{invoiceId}/{userId}"),
    USER_SAVE_INVOICE("索取发票","/uc/invoice/{userId}"),
    INVOICE_DOWLOAD("获取电子发票下载地址","/uc/invoice/detail/no"),
    USER_INVOICE_SHOUHUO("发票确认收货","/uc/invoice/confirm/{userId}/{id}"),

    USER_DZSB("电子申报","/uc/bind/dzsb/{userId}"),
    USER_HNGS("湖南国税","/uc/bind/hngs/{userId}"),
    USER_HNDS("湖南地税","/uc/bind/hnds/{userId}"),

    //电子申报忘记密码
    USER_DZSB_RESET("电子申报重置密码","/uc/shb/resetpassword"),

    USER_GSDZ_LIST("国税列表","/uc/bind/dzsbs/{userid}"),

    CITY("市地区接口", "/uc/city/{parentId}"),
    AREA("区地区接口", "/uc/area/{parentId}"),
    GET_RSA_PK("获取RSA公钥", "pk"),

    USER_INFO("个人信息查询接口","/uc/user/{id}"),

    USER_PASSWORD("用户修改密码","/uc/user/password"),
    USER_PASSWORD_V2("用户修改密码","/uc/v2/password"),

    USER_NSRBINDQUERY("查询用户绑定的纳税机构","/uc/nsrbindquery"),

    USER_SAVE("保存个人信息","/uc/user/{id}"),
    USER_SAVE_PHONE("保存个人信息","/uc/user/phone"),
    USER_SAVE_EXTEND("保存个人信息","/uc/user/extend/{userId}"),
    USER_VIP("根据等级获取名称","/uc/uvip/level/bo/{levelCode}"),

    UC_USER_LOGIN("获取用户信息","/uc/user/u/{usernameOrPhone}"),
    UC_USER_LOGIN_USERID("获取用户信息","/uc/user/{id}"),

    UC_PRIVATE("获取RSA私钥","/uc/rsa/private"),
    UC_PUBLIC("获取RSA公钥","/uc/rsa/public"),

    UC_PRIVATE_V2("获取RSA私钥","/uc/v2/rsa/private"),
    UC_PUBLIC_V2("获取RSA公钥","/uc/v2/rsa/public"),

    UC_RESET_PASSWORD("忘记密码修改密码","/uc/resetpassword"),
    UC_RESET_PASSWORD_V2("忘记密码修改密码","/uc/v2/resetpassword"),


    USER_DZSB_SAVE("绑定电子申报","/uc/bind/dzsb"),
    USER_HNGS_SAVE("绑定湖南国税","/uc/bind/hngs"),
    USER_HNDS_SAVE("绑定湖南地税","/uc/bind/hnds"),


    USER_DZSB_SAVE_V2("绑定电子申报","/uc/v2/bind/dzsb"),
    USER_HNGS_SAVE_V2("绑定湖南国税","/uc/v2/bind/hngs"),



    USER_HNDS_LOGIN("登录湖南地税","/message/hnds/login"),

    USER_DZSB_DEL("解绑电子申报","/uc/unbind/dzsb/{id}"),
    USER_HNGS_DEL("解绑湖南国税","/uc/unbind/hngs/{id}"),
    USER_HNDS_DEL("解绑湖南地税","/uc/unbind/hnds/{id}"),

    USER_DZSB_UPDATE("电子申报更新","/uc/bind/dzsb/{userId}/{nsrsbh}"),

    USER_BIND_DZSB_DETAIL("电子申报详情查看","/uc/bind/dzsb/detail/{id}"),
    USER_BIND_HNGS_DETAIL("湖南国税详情查看","/uc/bind/hngs/detail/{id}"),
    USER_BIND_HNDS_DETAIL("湖南地税详情查看","/uc/bind/hnds/detail/{id}"),
    USER_UPDATE_DZSB_GROUP("更新电子申报分组","/uc/update/dzsb/group"),

    GET_SOA_ACCESS_TOKEN("接口访问权限请求", "/uc/app/login"),

    MEMBER_VIP_LEVEL("会员等级列表接口","/uc/uvip/level"),
    MEMBER_VIP_LEVEL_ID("id获取会员等级","/uc/uvip/level/{id}"),
    MEMBER_PRIVILEGE_ALL("会员权益接口","/uc/uvip/privilegelevel"),
    MEMBER_PRIVILEGE_LEVEL("会员权益列表接口","/uc/uvip/privilegelevel/level"),
    MEMBER_PRIVILEGE_NAME("根据名称获取会员权益接口","/uc/uvip/privilegelevel/levelname"),
    MEMBER_GOODS("会员等级开通商品","/uc/goods/user"),
    MEMBER_GOODS_USER("积分商品","/uc/goods/user"),
    MEMBER_ORDER_PLACE("下单","/uc/order/submit/{userId}"),

    SELECT_ORDERNO("查询订单详情","/uc/order/select/{orderNo}"),

    GOODS_ID("根据ID获取商品详细信息","/uc/goods/user/{id}"),
    GOODS_SPEC("根据商品查询规格","/uc/product/spec/{goodsId}"),
    GOODS_PRODUCT("产品参数查询","/uc/product/select/{goodsId}"),

    GIFT_LIST("会员礼包列表","/uc/gift/show"),
    GIFT_ID("会员礼包详情","/uc/gift/{id}"),
    GIFT_APPLY_LIST("礼包兑换列表","/uc/gift/apply/user/list"),
    GIFT_APPLY("会员礼包兑换申请","/uc/gift/apply/user/{userId}"),
    GIFT_RECEIVE("会员礼包收货","/uc/gift/apply/receive/{applyId}"),
    GIFT_APPLY_LOG("会员礼包日志","/uc/gift/applylog/{applyId}"),
    GIFT_APPLY_DETAIL("会员礼包兑换申请","/uc/gift/apply/user/{userId}/{applyId}"),
    USER_AMOUNT("个人礼物金额","/uc/user/{userId}"),

    COUPON_USER_LIST("查询用户优惠券列表","/uc/coupon/user/{userId}"),
    COUPON_USER_ORDER("计算优惠金额","/uc/coupon/order"),
    COUPONS_LIST("优惠券列表","/uc/coupon/user/{userId}"),
    COUPONS_ACTIVITY_LIST("优惠券活动列表","/uc/coupon/activities"),
    COUPONS_GET("优惠券领取","/uc/coupon/user/{userId}/{activityId}"),
    COUPONS_DELETE("优惠券领取","/uc/coupon/user/{userId}/{couponId}"),
    COUPON_USED_LIST("优惠券使用情况列表查询","/uc/coupon/user/order"),

    //我的订单、任务、积分、經驗值
    ORDER("订单接口","/uc/order/user/all"),
    ORDER_ALL("订单接口","/uc/order/user/all/invoice"),//未开票和已开票的订单
    ORDER_COMPLETED("订单完成接口","/uc/order/user"),
    ORDER_DETAIL("订单详情","/uc/order/detail/{orderNo}"),
    ORDER_ID("订单详情接口","/uc/order/select/{orderNo}"),
    ORDER_WEB_ID("订单详情接口","/uc/order/web/{orderNo}"),
    ORDER_CANCEL("取消订单接口","/uc/order/cancel"),
    ORDER_DELETE("删除订单接口","/uc/order/delete/{userId}/{id}"),
    ORDER_TJR_UPDATE("修改推荐人","/uc/order/update/{userId}"),
    ORDER_LIST_STATUS("订单接口","/uc/order/user/list"),

    ORDER_CONFIRM("确认收货接口","/uc/order/confirm/{orderNo}/{userId}"),
    ORDER_BACK_RECORD("退换货记录","/uc/exchange/record"),
    ORDER_BACK("退换货申请接口","/uc/exchange"),
    ORDER_BACK_ID("退换货记录查看接口","/uc/exchange/{id}"),
    ORDER_BACK_RECEIVE("退换货确认收货","/uc/exchange/receive/{id}"),

    ORDER_LOG("订单日志接口","/uc/orderlog"),
    ORDER_PAY_POINT("积分支付接口","/uc/order/paypoints"),

    ORSER_PAY_SELECT_TRADE("交易流水号查询接口","/uc/order/select/trade/{tradeNo}"),

    LOTTERY_LOG_USERCOUNT("获取用户当天抽奖次数","/uc/lotterylog/userCount/{userId}"),
    UC_BOOL_GOUMAI("课程是否已购买","/uc/order/goods"),

    //我的消息
    UC_MESSAGES_BITCH("消息批量标记已读","/message/business/batch"),
    UC_MESSAGES("消息记录接口","/message/business"),
    UC_MESSAGES_ID("单条消息记录接口","/message/business/{id}"),

    GOODS_PROPRICE("会员价格列表","/uc/uvipprice/select/{productId}"),

    TASK("系统任务接口","/uc/tasks"),
    TASK_POINTS_ID("我的任务积分接口","/uc/task/mytask/{userId}"),
    TASK_INDEX_FINISH("积分经验值任务完成数接口","/uc/task/mytask/survey/{userId}"),

    TASK_DAILY("日常任务接口","/uc/todo/task/normal"),
    TASK_ONETIME("成长任务接口","/uc/todo/task/onetime"),
    TASK_SPECIAL("特殊任务接口","/uc/todo/task/special"),

    POINTLOG("积分日志接口","/uc/upoints/log"),
    POINTS("我的积分接口","/uc/points/{userId}"),
    POINTS_FOR_DATE("根据日期查询积分日志","/uc/upoints/log/user"),

    UEXP_LOG("经验日志接口","/uc/uexp/log"),
    UEXP_ID("我的经验值接口","/uc/experience/{userId}"),
    UEXP_FOR_DATE("根据日期查询经验日志","/uc/uexp/log/user"),
    UEXP_RULE("经验值规则","/uc/uexp/rule"),
    POINT_RULE("积分规则","/uc/upoints/rule"),

    //vip特权
    UVIP_PRIVILEGE_LEVEL("viplevel权益接口","/uc/uvip/privilegelevel/level"),
    UVIP_PRIVILEGE("vip权益接口","/uc/uvip/privilege"),
    UVIP_PRIVILEGE_ID("vip权益详情","/uc/uvip/privilege/{id}"),

    //签到
    UC_CHECKIN_LIST("本月已签到接口","/uc/check/list"),
    UC_CHECKIN_RANK("签到排行榜接口","/uc/check/rank"),
    UC_CHECKIN("签到接口","/uc/check"),
    UC_RECHECKIN("补签接口","/uc/recheck"),
    UC_CHECK_TOTAL("累计签到","/uc/check/total"),
    UC_RECHECK_POINT("补签需消耗的积分","/uc/upoints/rule/code/{code}"),

    //物流公司
    UC_EXPRESS("物流公司接口","/uc/express/comp/{id}"),

    BD_GROUP("分组查询接口","/uc/bind/dzsbgroup/{userid}"),

    UC_ACTIVITY("活动接口","/cms/event/topone/{category}"),
    //帮邦
    HELP_MY_COLLECTS("我的收藏","/bangbang/mybangbang/mycollect/{userId}"),
    HELP_CANCEL_COLLECT("取消收藏","/bangbang/collect/{askId}"),
    HELP_MY_FOLLOWS("我的关注","/bangbang/mybangbang/myfollow/{userId}"),
    HELP_CANCEL_FOLLOW("取消关注","/bangbang/follow/{followUserId}"),
    HELP_MY_FANS("我的粉丝","/bangbang/mybangbang/myfans/{userId}"),
    HELP_MY_TEAMS("我的团队","/bangbang/mybangbang/myteams/{userId}"),
    HELP_MY_PRIVATE_LETTERS("我的私信","/bangbang/letter"),
    CURRICULUM_HISTORY("学习历史","/bangbang/curriculum/selectStudyHistory"),
    CURRICULUM_COLLECT("课程收藏","/bangbang/curriculum/selectListCollect"),
    CURRICULUM_NUM("学习统计","/bangbang/curriculum/selectStudyNum"),
    CURRICULUM_PL_ADD("新增评价","/bangbang/currEvaluate"),

    LECTURER_FL_LIST("关注老师列表","/bangbang/followlecturer"),
    LECTURER_FL_UPDATE("关注与取消关注","/bangbang/followlecturer/{lecturerId}"),

    ALIPAY_PAYCODE("支付宝二维码接口","/uc/alipay/paycode"),
    ALIPAY_PAY("支付收银台接口","/uc/alipay/payform"),
    ALIPAY_QUERY("订单查询接口","/uc/alipay/alipayquery"),
    ALIPAY_CALLBACK("支付宝回调更新数据","/uc/payreturn/alipay"),
    ALIPAY_VALIDATE("验证支付宝返回信息是否被改","/uc/payreturn/validate"),


    WEIXIN_PALCODE("微信支付二维码","/uc/wxpay/payorder"),
    WEIXIN_QUERY("微信支付状态查询","/uc/wxpay/wxorderquery"),

    GETUSER_TOKEN("根据usertoken获取用户信息","/uc/user/token/{token}"),

    KEIHUDUAN_LOGIN("客户端uc登录","/uc/v2/dbappuserlogin"),

    /**电子发票开票发红包活动*/
    WX_REDPACK("电子发票开票抽奖","/uc/wx/redpack/{activityId}/{businessId}"),

    COUPON_ACTIVITIES("优惠券活动列表","/uc/coupon/activities"),
    COUPON_ID("查询活动优惠券详情","/uc/coupon/activities/coupon/{id}"),
    COUPON_ACTIVITIES_ID("查询活动详情","/uc/coupon/activity/{id}"),
    USER_COUPON("领取活动优惠券","/uc/coupon/user/{userId}/{activityId}"),


    HSQJ_CLIENT("汇算清缴企业注册","/uc/hsqj/register"),
    BB_IS_OPTIONAL("交易赠送校验","/bangbang/curriculum/isOptional"),

    ORDER_STATUS("修改订单状态支付中","/uc/order/payment"),
    DIQU_QUERY_ID("根据id查询省市区","/uc/provinceorcityorarea"),
    USERMSG_POST("发送站内消息","/message/business"),
    USERMSG_VIPPOST("发送消息","/uc/sendmsg/byqd"),
    JOINT_TAX_GO("网厅页面请求跳转", "/lhwtverify.do"),

    //消息订阅
    MSG_SUBSCRIPTIONS_INIT("消息订阅列表初始化","/message/subscription/initial"),
    MSG_SUBSCRIPTIONS_SETTING("消息订阅列表","/message/subscription/usersettings"),
    MSG_SUBSCRIPTIONS_SAVE("消息订阅列表设置","/message/subscription/usersetsave");

    public String describe;

    public String uri;

    private ConstantsUri(String describe, String uri) {
        this.describe = describe;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return this.uri;
    }
}
