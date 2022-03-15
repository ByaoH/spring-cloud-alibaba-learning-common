package com.byaoh.cloud.common.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Common
 *
 * @author luliangyu
 * @date 2022-03-10 18:04
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "common")
public class CommonProperties {
	/**
	 * 系统管理员id
	 */
	private Long systemUserId;
}
