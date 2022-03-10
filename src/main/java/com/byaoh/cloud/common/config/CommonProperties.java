package com.byaoh.cloud.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Common
 *
 * @author luliangyu
 * @date 2022-03-10 18:04
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "common")
public class CommonProperties {
	/**
	 * 测试
	 */
	private String test = "COMMON-STARTER";
}
