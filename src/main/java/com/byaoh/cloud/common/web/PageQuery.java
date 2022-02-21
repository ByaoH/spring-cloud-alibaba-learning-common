package com.byaoh.cloud.common.web;

import lombok.Data;

import java.util.Collection;

/**
 * 分页模型
 *
 * @author luliangyu
 * @date 2021-07-17 10:43
 */
@Data
public class PageQuery {
	/**
	 * 显示数量
	 */
	private Integer limit;
	/**
	 * 页码
	 */
	private Integer page;

	/**
	 * 排序列
	 */
	private Collection<String> orders;
}
