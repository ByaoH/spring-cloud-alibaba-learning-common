package com.byaoh.cloud.common.exception;

/**
 * 业务错误
 *
 * @author luliangyu
 * @date 2021-07-16 15:10
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -7629918346458705235L;

	public BusinessException() {
		this("业务错误!");
	}

	public BusinessException(String message) {
		super(message);
	}
}
