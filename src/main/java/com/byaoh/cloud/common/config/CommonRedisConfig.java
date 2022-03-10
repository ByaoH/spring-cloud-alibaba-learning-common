package com.byaoh.cloud.common.config;

import lombok.extern.slf4j.Slf4j;

/**
 * RedisConfig
 *
 * @author luliangyu
 * @date 2022-03-10 17:38
 */
@Slf4j
public class CommonRedisConfig {
	private final CommonProperties commonProperties;

	public CommonRedisConfig(CommonProperties commonProperties) {
		this.commonProperties = commonProperties;
		log.error(commonProperties.getTest());
	}
}
