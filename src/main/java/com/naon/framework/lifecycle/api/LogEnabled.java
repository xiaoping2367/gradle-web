package com.naon.framework.lifecycle.api;

import org.slf4j.Logger;

/**
 * 일반적인 서비스 로깅을 필요로 하는 서비스를 위한 인터페이스.
 * 칸텍스트 로그는 칸텍스트 안의 것이 선호된다.

 * @author 최종성(jhonson@naonsoft.com)
 */
public interface LogEnabled {

    /**
     * 서비스 로거를 설정한다.
     * 
     * @param logger
     *            not null
     */
    void setLogger(Logger logger);
}
