package com.byaoh.cloud.framework.enums;

import com.byaoh.cloud.framework.base.BaseEnum;

/**
 * 状态枚举
 *
 * @author l
 * @date 2022/3/22 下午9:59
 */
public enum StatusEnum implements BaseEnum<Integer> {
	DISABLE(0, "关闭"),
	ENABLE(1, "开启"),
	;
	public final int status;
	public final String desc;

	StatusEnum(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	@Override
	public Integer getValue() {
		return status;
	}
}
