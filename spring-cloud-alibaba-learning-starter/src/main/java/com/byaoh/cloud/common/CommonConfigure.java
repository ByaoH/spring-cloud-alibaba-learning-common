package com.byaoh.cloud.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

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

	@Bean
	@Primary
	@ConditionalOnMissingBean(ObjectMapper.class)
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();

		// 全局配置序列化返回 JSON 处理
		SimpleModule simpleModule = new SimpleModule();
		/*
		    序列换成json时,将所有的long变成string
		    因为js中得数字类型不能包含所有的java long值
		 */
		//JSON Long ==> String
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		objectMapper.registerModule(simpleModule);
		return objectMapper;
	}
}
