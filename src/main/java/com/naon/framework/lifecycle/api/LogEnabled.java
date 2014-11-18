package com.naon.framework.lifecycle.api;

import com.naon.framework.lifecycle.SimpleParser;

public interface LogEnabled {

	/**
	 * 서비스 로거를 설정한다.
	 * 
	 * @param logger
	 *            not null
	 */
	void setParser(SimpleParser parser);
}
