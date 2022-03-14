package com.byaoh.cloud.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * CommonConfig
 *
 * @author l
 * @date 2022/3/10 下午9:09
 */
@Configuration
@EnableConfigurationProperties(CommonProperties.class)
public class CommonConfigured {
}
