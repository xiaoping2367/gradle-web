package com.naon.framework.lifecycle.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

/**
 * 라이프사이클에 따른 처리를 하려면, BeanPostProcessors가 확장해야 하는 추상 기초 클래스이다.
 * 
 * 
 * BeanPostProcessor는 
 * 자신만의 초기화 로직, 의존성 해결 로직을 구현할 수 있도록 callback 메서드를 정의한다.
 * 
 * 
 * ApplicationContext는 자동적으로 BeanPostProcessor 인터페이스를 구현한 빈들을 발견하고 
 * post-processors로 이러한 빈들을 등록한다. 
 * 
 * 초기화 과정
 * 
 *  1) Bean의 인스턴스화(생성자 호출)
 *  2) 필드값 지정
 *  3) etBeanName() 메서드 호출(BeanNameAware 인터페이스를 구현하고 있는 경우)
 *  4) setBeanFactory() 메서드 호출(BeanFactoryAware 인터페이스를 구현하고 있는 경우)
 * 
 * 
 * @param <T>
 */
public abstract class AbstractLifecycleBeanPostProcessor<T> implements BeanPostProcessor, PriorityOrdered, BeanFactoryAware {

    private int order = Ordered.HIGHEST_PRECEDENCE;
    private ListableBeanFactory factory;

    @Override
    public void setBeanFactory(BeanFactory factory) throws BeansException {
        this.factory = (ListableBeanFactory) factory;
    }

    
    
    /**
     * 빈의 초기화 전에 호출
     * @see org.springframework.beans.factory.config.BeanPostProcessor
     * #postProcessBeforeInitialization(java.lang.Object, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public final Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
        try {
            Class<T> lClass = getLifeCycleInterface();
            if (lClass.isInstance(bean)) {
                // Check if the bean is registered in the context.
                // If not it was created by the container and so there
                // is no need to execute the callback.
                if (factory.containsBeanDefinition(name)) {
                    executeLifecycleMethodBeforeInit((T) bean, name);
                }
            }
        } catch (Exception e) {
            throw new FatalBeanException("Unable to execute lifecycle method on bean" + name, e);
        }
        return bean;
    }

    
    /**
     * 빈의 초기화 후에 
     * @see org.springframework.beans.factory.config.BeanPostProcessor
     * #postProcessAfterInitialization(java.lang.Object, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public final Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
        try {
            Class<T> lClass = getLifeCycleInterface();
            if (lClass.isInstance(bean)) {
                // Check if the bean is registered in the context.
                // If not it was created by the container and so there is no
                // need to execute the callback.
                if (factory.containsBeanDefinition(name)) {
                    executeLifecycleMethodAfterInit((T) bean, name);
                }
            }
        } catch (Exception e) {
            throw new FatalBeanException("Unable to execute lifecycle method on bean" + name, e);
        }
        return bean;
    }

    /**
     * Return the class which mark the lifecycle.
     * 
     * @return interfaceClass
     */
    protected abstract Class<T> getLifeCycleInterface();

    /**
     * Method which gets executed if the bean implement the LifeCycleInterface.
     * Override this if you wish perform any action. Default is to do nothing
     * 
     * @param bean
     *            the actual bean
     * @param beanName
     *            then name of the bean
     * @throws Exception
     */
    protected abstract void executeLifecycleMethodBeforeInit(T bean, String beanName) throws Exception;

    /**
     * Method which gets executed if the bean implement the LifeCycleInterface.
     * Override this if you wish perform any action. Default is to do nothing
     * 
     * @param bean
     *            the actual bean
     * @param beanName
     *            then name of the bean
     * @throws Exception
     */
    protected abstract void executeLifecycleMethodAfterInit(T bean, String beanName) throws Exception;

    /**
     * @param order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * @see org.springframework.core.Ordered#getOrder()
     */
    public int getOrder() {
        return order;
    }

}
