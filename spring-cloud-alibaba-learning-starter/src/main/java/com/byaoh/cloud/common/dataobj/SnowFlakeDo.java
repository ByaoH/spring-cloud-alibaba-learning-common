package com.byaoh.cloud.common.dataobj;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * SnowFlakeDo
 *
 * @author l
 * @date 2022/3/24 下午11:02
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class SnowFlakeDo extends BaseDo {
	private static final long serialVersionUID = 4541431227446057357L;
	@Id
	@GeneratedValue(generator = "snowFlakeId")
	@GenericGenerator(name = "snowFlakeId", strategy = "com.byaoh.cloud.framework.base.SnowflakeGenerator")
	private Long id;
}
