package com.byaoh.cloud.common.dataobj;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * SnowFlakeDo
 *
 * @author l
 * @date 2022/3/24 下午11:02
 */
public class SnowFlakeDo extends BaseDo {
	private static final long serialVersionUID = 4541431227446057357L;
	@Id
	@GeneratedValue(generator = "snowFlakeId")
	@GenericGenerator(name = "snowFlakeId", strategy = "com.byaoh.cloud.framework.base.SnowflakeGenerator")
	private Long id;
}
