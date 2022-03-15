package com.byaoh.cloud.framework.web;

import lombok.Data;

import java.io.Serializable;

/**
 * web日志
 *
 * @author luliangyu
 * @date 2022/2/21 16:14
 */
@Data
public class ApiLog implements Serializable {
	private static final long serialVersionUID = 803914779843300810L;
	/**
	 * 操作描述
	 */
	private String description;

	/**
	 * 操作用户(用户名)
	 */
	private String username;

	/**
	 * 操作时间
	 */
	private Long startTime;

	/**
	 * 消耗时间 ms
	 */
	private Integer spendTime;

	/**
	 * URI
	 */
	private String uri;

	/**
	 * URL
	 */
	private String url;

	/**
	 * 请求类型
	 */
	private String method;

	/**
	 * IP地址
	 */
	private String ip;

	/**
	 * 请求参数
	 */
	private Object parameter;

	/**
	 * 请求返回的结果
	 */
	private Object result;
}