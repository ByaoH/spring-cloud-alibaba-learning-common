package com.byaoh.cloud.common;

import org.springframework.stereotype.Service;

@Service
public class CommonService {
	public void serr() {
		System.err.println(this.getClass());
	}
}
