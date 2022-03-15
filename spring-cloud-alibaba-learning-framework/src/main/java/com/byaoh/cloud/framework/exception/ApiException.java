package com.byaoh.cloud.framework.exception;

import com.byaoh.cloud.framework.web.BaseResultCode;

/**
 * ApiException
 *
 * @author luliangyu
 * @date 2021-07-16 14:36
 */
public class ApiException extends RuntimeException {
	private static final long serialVersionUID = -4566943994764734808L;
	private final BaseResultCode code;

	public ApiException(BaseResultCode code) {
		this(code, code.message());
	}

	public ApiException(BaseResultCode code, String message) {
		super(message);
		this.code = code;
	}

	public ApiException(BaseResultCode code, String msg, Throwable throwable) {
		super(msg, throwable);
		this.code = code;
	}

	public BaseResultCode getCode() {
		return code;
	}
}
