package com.byaoh.cloud.framework.base;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * hibernate 雪花id生成器
 *
 * @author luliangyu
 * @date 2022-03-03 14:02
 */
public class SnowflakeGenerator implements IdentifierGenerator {
	private static final Snowflake SNOWFLAKE = IdUtil.getSnowflake();

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return SNOWFLAKE.nextId();
	}
}