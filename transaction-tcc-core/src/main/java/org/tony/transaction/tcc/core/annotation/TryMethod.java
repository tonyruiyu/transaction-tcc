package org.tony.transaction.tcc.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.tony.transaction.tcc.core.UndoException;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface TryMethod {

    /**
     * 原子代码
     * 
     * @return
     */
    String atomCode();

    /**
     * 指定回滚异常
     * 
     * @return
     */
    Class<? extends Exception> rollback() default UndoException.class;

    /**
     * 超时时间秒
     * 
     * @return
     */
    int timeout() default 10 * 60;
    
    int qeuryCount() default 3;
}
