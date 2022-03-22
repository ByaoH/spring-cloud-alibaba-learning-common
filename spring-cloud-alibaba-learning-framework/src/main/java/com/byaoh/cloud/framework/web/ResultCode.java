package com.byaoh.cloud.framework.web;

/**
 * ResultCode
 *
 * @author luliangyu
 * @date 2021-07-16 14:11
 */
public enum ResultCode implements BaseResultCode {

	/**
	 * 成功调用
	 */
	SUCCESS(200, "成功"),
	/**
	 * 业务错误
	 */
	BUSINESS_ERROR(400, "业务错误"),
	UNAUTHORIZED(401, "未认证"),
	FORBIDDEN(403, "未授权"),
	/**
	 * 服务应用异常
	 */
	FAILED(500, "操作失败"),

	DATA_INTEGRITY_VIOLATION(531, "数据库约束冲突");

	private final Integer code;
	private final String message;

	ResultCode(Integer code, String message) {
		this.message = message;
		this.code = code;
	}

	@Override
	public String message() {
		return message;
	}

	@Override
	public Integer code() {
		return code;
	}
}
