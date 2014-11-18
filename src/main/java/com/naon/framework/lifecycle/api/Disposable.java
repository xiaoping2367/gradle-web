package com.naon.framework.lifecycle.api;

/**
 * 이 인터페이스를 구현한 클래스는 객체 소멸 시에, 특별하게 다뤄져야 한다.
 * 그래서, {@link #dispose()} 메서드가 호출되어야 한다.

 * @author 최종성(jhonson@naonsoft.com)
 */
public interface Disposable {

    /**
     * 객체를 처분한다.
     */
    void dispose();
}
