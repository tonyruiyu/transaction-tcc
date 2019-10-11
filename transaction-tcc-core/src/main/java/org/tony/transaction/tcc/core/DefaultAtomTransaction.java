package org.tony.transaction.tcc.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.tony.transaction.tcc.core.annotation.CancelMethod;
import org.tony.transaction.tcc.core.annotation.ConfirmMethod;
import org.tony.transaction.tcc.core.annotation.QueryMethod;
import org.tony.transaction.tcc.core.annotation.TryMethod; 

public class DefaultAtomTransaction implements AtomTransaction {

    private Object target;

    public DefaultAtomTransaction(Object obj) {
        if (AopUtils.isAopProxy(obj)) {
            if (AopUtils.isJdkDynamicProxy(obj)) {
                try {
                    this.target = getJdkDynamicProxyTargetObject(obj);
                } catch (Exception e) {
                    throw new TccTransactionException(e.getMessage(), e);
                }
            } else { // cglib
                try {
                    this.target = getCglibProxyTargetObject(obj);
                } catch (Exception e) {
                    throw new TccTransactionException(e.getMessage(), e);
                }
            }
        } else {
            this.target = obj;
        }
    }

    public Object tryMethod(Object... args) {
        try {
            return execTryMethod(args);
        } catch (UndoException e) {
            throw e;
        } catch (InvocationTargetException e) {
            Throwable ex = e.getTargetException();
            if (ex instanceof UndoException) {
                throw (UndoException) ex;
            }
            throw new TccTransactionException(e);
        } catch (Exception e) {
            throw new TccTransactionException(e);
        }
    }

    private Object execTryMethod(Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method tmethod = getMethodByAnnotation(TryMethod.class);
        return tmethod.invoke(target, args);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Method getMethodByAnnotation(Class c) {
        Method[] methods = target.getClass().getMethods();
        Method tmethod = null;
        for (int i = 0; i < methods.length; i++) {
            Method m = methods[i];
            Object ann = m.getAnnotation(c);
            if (ann != null) {
                tmethod = m;
                break;
            }
        }
        return tmethod;
    }

    public Object confirm(Object... args) {
        try {
            Method tmethod = getMethodByAnnotation(ConfirmMethod.class);
            return tmethod.invoke(target, args);
        } catch (Exception e) {
            throw new TccTransactionException(e.getMessage(), e);
        }
    }

    public Object cancel(Object... args) {
        try {
            Class<?> c = CancelMethod.class;
            Method tmethod = getMethodByAnnotation(c);
            return tmethod.invoke(target, args);
        } catch (Exception e) {
            throw new TccTransactionException(e.getMessage(), e);
        }
    }

    public AtomStatus query(String atomId) {
        try {

            Method tmethod = getMethodByAnnotation(QueryMethod.class);
            return (AtomStatus) tmethod.invoke(target, atomId);
        } catch (Exception e) {
            throw new TccTransactionException(e.getMessage(), e);
        }
    } 

    public String getAtomCode() {
        Method tmethod = getMethodByAnnotation(TryMethod.class);
        TryMethod tryMethod = tmethod.getAnnotation(TryMethod.class);
        return tryMethod.atomCode(); 
    }

    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);

        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
 
        return target;
    }

    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
    	
    	
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);

        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();

        return target;
    }

}
