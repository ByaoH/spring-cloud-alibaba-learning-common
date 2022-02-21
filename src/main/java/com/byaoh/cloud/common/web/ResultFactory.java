package com.byaoh.cloud.common.web;

/**
 * 响应对象工厂
 *
 * @author luliangyu
 * @date 2021-07-16 14:38
 */
public class ResultFactory {
	private ResultFactory() {
	}

	public static <T> Result<T> validation(String message) {
		return build(ResultCode.BUSINESS_ERROR, message);
	}

	public static <T> Result<T> failed() {
		return failed(null);
	}

	public static <T> Result<T> failed(String message) {
		return build(ResultCode.FAILED, message);
	}

	public static <T> Result<T> success() {
		return success(null);
	}

	public static <T> Result<T> success(T data) {
		return success(ResultCode.SUCCESS.message(), data);
	}

	public static <T> Result<T> success(String message, T data) {
		return build(ResultCode.SUCCESS, message, data);
	}

	public static <T> Result<T> build(BaseResultCode code) {
		return build(code.code(), code.message(), null);
	}

	public static <T> Result<T> build(BaseResultCode code, String message) {
		return build(code.code(), message, null);
	}

	public static <T> Result<T> build(BaseResultCode code, T data) {
		return build(code.code(), code.message(), data);
	}

	public static <T> Result<T> build(BaseResultCode code, String message, T data) {
		return build(code.code(), message, data);
	}

	/**
	 * 构建响应对象
	 *
	 * @param code    状态码
	 * @param message 消息信息
	 * @param data    数据
	 * @param <T>     数据类型
	 * @return 响应对象
	 */
	public static <T> Result<T> build(int code, String message, T data) {
		Result<T> result = new Result<>();
		result.setCode(code);
		result.setMessage(message);
		result.setData(data);
		return result;
	}
}
