package com.naon.framework.lifecycle.api;

/**
 * {@link Disposable} 타입의 객체이면, 객체를 처분한다.
 *
 * @author 최종성(jhonson@naonsoft.com)
 */
public class LifecycleUtil {

    /**
     * {@link Disposable} 타입의 객체이면, 객체를 처분한다.
     * 
     * @param obj
     */
    public static void dispose(Object obj) {
        if (obj instanceof Disposable && obj != null) {
            ((Disposable) obj).dispose();
        }
    }
}
