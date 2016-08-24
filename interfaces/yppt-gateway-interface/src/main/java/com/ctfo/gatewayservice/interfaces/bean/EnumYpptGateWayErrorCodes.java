package com.ctfo.gatewayservice.interfaces.bean;

/**
 * 新油品错误码枚举类
 * 
 * @author sunchuanfu
 */
public enum EnumYpptGateWayErrorCodes {
	// 新油品错误
	YPPT_GATEWAY_ERROR("GW00", "新油品网关服务错误"),
	YPPT_GET_CASHIER_URL_ERROR("GW01", "获取取银台扣款URL报错"),
	YPPT_ADD_VEHICLE_ERROR("GW02", "添加车辆时报错"),
	YPPT_QUERY_VEHICLE_ERROR("GW03", "查询车辆时报错"),
	// 短信服务
	YPPT_SEND_PHONE_MESSAGE_ERROR("GW04", "发送短信失败"),
	YPPT_PHONE_OR_MESSAGE_ERROR("GW03", "手机号或者短信内容为空"),
	
    // 兼容车辆中心错误码
	CL_SUCCESS("CL888888", "成功"),
    CL_FAILURE("CL000000", "失败"),
    CL_SYSTEM_ERROR1("CL000001", "系统发生错误1"),
    CL_SYSTEM_ERROR2("CL000002", "系统发生错误2"),
    CL_PARAM_NULL("CL100001", "传递的参数为空"),
    CL_USERCODE_NULL("CL100002", "用户编码为空"),
    CL_USERCODE_WRONG("CL100003", "用户编码不正确"),
    CL_METHODCODE_NULL("CL100004", "方法编码为空"),
    CL_METHODCODE_WRONG("CL100005", "方法编码不正确"),
    CL_NEED_PARAM_ID("CL200001", "缺少参数id"),
    CL_PARAM_ID_TYPE_ERROR("CL200002", "参数id的值类型不是List"),
    CL_NEED_PARAM_BEAN("CL300001", "缺少参数bean"),
    CL_EXISTED_ERRORFIELD_IN_PARAM_BEAN("CL300002", "参数bean里头存在不正确的字段"),
    CL_EXISTED_ERRORFIELD_IN_PARAM_EQUAL("CL300003", "参数equal里头存在不正确的字段"),
    CL_EXISTED_ERRORFIELD_IN_PARAM_LIKE("CL300004", "参数like里头存在不正确的字段"),
    CL_EXISTED_ERRORFIELD_IN_PARAM_BEGIN("CL300005", "参数begin里头存在不正确的字段"),
    CL_EXISTED_ERRORFIELD_IN_PARAM_END("CL300006", "参数end里头存在不正确的字段"),
    CL_EXISTED_ERRORFIELD_IN_PARAM_IN("CL300007", "参数in里头存在不正确的字段"),
    CL_EXISTED_ERRORFIELD_IN_PARAM_NOTIN("CL300008", "参数notIn里头存在不正确的字段"),
    CL_EXISTED_ERROR_IN_PARAM_ORDER("CL300009", "参数order的内容不正确"),
    CL_EXISTED_ILLEGAL_IN_PARAM_SORT("CL300010", "参数sort的内容不是desc或者asc"),
    CL_NEED_PARAM_USERID_FOR_VEHICLE("CL300015", "缺少参数userId"),
    CL_VEHICLE_NOT_EXSITED("CL300017", "车辆不存在"),
    CL_SIM_IS_EXSITED("CL300018", "SIM卡号已经存在"),
    CL_VEHICLE_EXISTED("CL300020", "车辆已经存在"),
    // 兼容支付平台错误码
    ZF_USER_NOT_EXISTED("ZF1100", "用户不存在"),
    ZF_SUCCESS("ZF0000", "执行成功"),
    ZF_FAIL("ZF1111", "执行失败"),
    ZF_PARAM_NOT_STANDARD("ZF1001", "参数不符合规格"),
    ZF_BALANCE_IS_NOT_ENOUGH("ZF1002", "余额不足"),
    ZF_GATEWAY_FAIL("ZF1005", "调用网关结果返回失败"),
    ZF_ACCOUNT_NOT_EXIST("ZF1100", "账户不存在"),
    ZF_ACCOUNT_STATUS_ERROR("ZF1101", "账户状态异常"),
    ZF_ACCOUNT_STATUS_LOCKED("ZF1102", "账户被锁定"),
    ZF_ACCOUNT_NOT_CERTIFICATED("ZF1103", "账户未进行实名认证"),
    ZF_ACCOUNT_CONCURRENT_OPERATION("ZF1104", "并发操作账户"),
    ZF_ACCOUNT_FROZEN_AMOUNT_INSUFFICIENT("ZF1105", "账户冻结金额不足"),
    ZF_ACCOUNT_USABLE_AMOUNT_INSUFFICIENT("ZF1106", "账户可用金额不足"),
    ZF_FREEZE_RECORD_NOT_EXIST("ZF1107", "冻结记录不存在"),
    ZF_ACCOUNT_UNLOCK_FAILURE("ZF1108", "账户解锁失败"),
    ZF_ACCOUNT_CREATE_R("ZF1109", "创建重复帐号"),
    ZF_ACCOUNT_N("ZF1110", "帐号错误"),
    ZF_ACCOUNT_SET_PWD_Y("ZF1118", "账户已设置过密码"),
    ZF_MOBILENO_N("ZF1200", "手机号码不正确"),
    ZF_ACCOUNT_MOBILENO_N("ZF1201", "不是帐户所绑定的手机号码"),
    ZF_MOBILE_NO_SMSCODE("ZF1202", "短信验证码不正确"),
    ZF_OLD_MD5("ZF1203", "原密码不对"),
    ZF_USER_ID_N("ZF1204", "用户ID错误"),
    ZF_USER_LOGIN_NAME_N("ZF1205", "用户登录名错误"),
    ZF_ORDER_NO_N("ZF1301", "支付订单号为空"),
    ZF_ERROR("ZF2222", "执行异常");

  
	/**
	 * 错误代码
	 */
	private String code;

	/**
	 * 错误代码对应的中文意义
	 */
	private String msg;

	EnumYpptGateWayErrorCodes(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 根据错误码获取错误中文意义
	 * 
	 * @param code
	 *            错误码
	 * @return 错误代码对应的中文意义
	 */
	public static String getNameByCode(String code) {
		for (EnumYpptGateWayErrorCodes v : EnumYpptGateWayErrorCodes.values()) {
			if (v.getCode().equals(code)) {
				return v.msg;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
