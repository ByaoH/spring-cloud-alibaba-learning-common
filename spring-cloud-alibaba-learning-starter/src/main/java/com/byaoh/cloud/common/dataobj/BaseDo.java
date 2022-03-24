package com.byaoh.cloud.common.dataobj;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * BaseDo
 *
 * @author luliangyu
 * @date 2021-07-16 11:49
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
public abstract class BaseDo implements Serializable {

	private static final long serialVersionUID = 2111109988936237205L;

	/** 创建人 */
	@CreatedBy
	@Column(name = "create_by")
	private Long createBy;

	/** 修改人 */
	@LastModifiedBy
	@Column(name = "update_by")
	private Long updateBy;

	/** 创建时间 */
	@CreationTimestamp
	@Column(name = "create_time")
	private Date createTime;

	/** 修改时间 */
	@UpdateTimestamp
	@Column(name = "update_time")
	private Date updateTime;

}