package com.byaoh.cloud.framework.exception;

import com.byaoh.cloud.framework.web.BaseResultCode;
import com.byaoh.cloud.framework.web.ResultCode;

/**
 * ApiException
 *
 * @author luliangyu
 * @date 2021-07-16 14:36
 */
public class ApiException extends RuntimeException {
	private static final long serialVersionUID = -4566943994764734808L;
	private static final ResultCode DEFAULT_CODE = ResultCode.BUSINESS_ERROR;
	private final BaseResultCode code;

	public ApiException(BaseResultCode code) {
		this(code, code.message());
	}

	public ApiException(BaseResultCode code, String message) {
		super(message);
		this.code = code;
	}

	public ApiException(String msg) {
		this(DEFAULT_CODE, msg);
	}

	public ApiException(BaseResultCode code, String msg, Throwable throwable) {
		super(msg, throwable);
		this.code = code;
	}

	public BaseResultCode getCode() {
		return code;
	}
}
