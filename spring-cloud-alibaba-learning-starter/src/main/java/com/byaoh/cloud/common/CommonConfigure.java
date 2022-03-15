package com.byaoh.cloud.common;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * CommonConfig
 *
 * @author l
 * @date 2022/3/10 下午9:09
 */
@ComponentScan(basePackageClasses = CommonConfigure.class)
@Configuration
@EnableConfigurationProperties(com.byaoh.cloud.common.CommonProperties.class)
public class CommonConfigure {
}
