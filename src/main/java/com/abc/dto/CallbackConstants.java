package com.abc.dto;
/**
 * @author JiangZuoWei
 * @createTime 2015年11月14日 下午7:22:07
 * @description 
 */
public enum CallbackConstants implements java.io.Serializable{
	
	SUCCESS("100000", "处理成功"),
	FAIL("100001", "处理失败"),
	PARAMETER_EMPTY("100002", "请求参数不能为空"),
	NETWORK_ERROR("100003", "网络异常"),

	FP_PT_LIST_FAIL("100101", "获取代开普通发票列表异常"),

	TSLX_LIST("100201", "税收优惠项为空，请先进行税收优惠资格备案"),
	FP_DK_PRIV("100202", "校验失败! 请确认：纳税资格为小规模、营业状态正常等"),
	FILE_UPLOAD_ERROR("100203", "附件处理失败,请检查上传的附件内容"),
	FILE_UPLOAD_SIZE_ERROR("100204", "附件大小过大"),

	PW_WRONG("100102","原密码错误"),
	MOD_INFO_FAIL("100103","修改用户资料异常"),
	LOGIN_FAIL("100104","登录异常"),
	NOT_LOGIN("100105","未登录"),
	USER_LOGIN("4018","用户不存在");

	
	public String code;
	
	public String msg;
	
	private CallbackConstants(String code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	@Override
    public String toString() {
		return this.msg;
	}
}
