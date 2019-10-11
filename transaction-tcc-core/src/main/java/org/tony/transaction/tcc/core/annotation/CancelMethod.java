package org.tony.transaction.tcc.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface CancelMethod {
    /**
     * 超时时间秒
     * 
     * @return
     */
    int timeout() default 10 * 60;
    
    int retryCount() default 3;
}
