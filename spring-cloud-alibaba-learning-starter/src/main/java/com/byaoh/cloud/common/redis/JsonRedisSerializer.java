package com.byaoh.cloud.common.redis;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * JSON序列化
 *
 * @author l
 * @date 2022/3/21 下午5:47
 */
public class JsonRedisSerializer<T> implements RedisSerializer<T> {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private final Class<T> clazz;

	public JsonRedisSerializer(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	@Override
	public byte[] serialize(T t) throws SerializationException {
		try {
			return OBJECT_MAPPER.writeValueAsBytes(t);
		} catch (JsonProcessingException e) {
			throw new SerializationException("redis序列化失败", e);
		}
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		return JSON.parseObject(bytes, clazz);
	}

	@Override
	public boolean canSerialize(Class<?> type) {
		return RedisSerializer.super.canSerialize(type);
	}
}
