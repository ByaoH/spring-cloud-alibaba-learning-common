package com.byaoh.cloud.common.exception;

import com.byaoh.cloud.common.web.BaseResultCode;

/**
 * ApiException
 *
 * @author luliangyu
 * @date 2021-07-16 14:36
 */
public class ApiException extends RuntimeException {
	private static final long serialVersionUID = -4566943994764734808L;
	private final BaseResultCode code;
	private final String message;

	public ApiException(BaseResultCode code) {
		this(code, code.message());
	}

	public ApiException(BaseResultCode code, String message) {
		this.code = code;
		this.message = message;
	}
}
