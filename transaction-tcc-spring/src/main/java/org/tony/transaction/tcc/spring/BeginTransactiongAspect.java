package org.tony.transaction.tcc.spring;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import org.tony.transaction.tcc.core.CompositeTransaction;
import org.tony.transaction.tcc.core.TccTransactionManager;
import org.tony.transaction.tcc.core.TransactionContext;
import org.tony.transaction.tcc.core.annotation.TccTransaction;

@Aspect
public class BeginTransactiongAspect {

    @Autowired
    private TccTransactionManager tccTransactionManager;

    @Around("@annotation(org.tony.transaction.tcc.core.annotation.TccTransaction)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        TccTransaction tccTransaction = getAnnotation(joinPoint);
        String code = tccTransaction.value();
        CompositeTransaction ctx = tccTransactionManager.createTransaction(code);
        TransactionContext.setCompositeTransaction(ctx);
        try {
            Object o = joinPoint.proceed();
            ctx.submit();
            return o;
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            TransactionContext.clean();
        }
        return null;
    }

    private TccTransaction getAnnotation(ProceedingJoinPoint joinPoint) {
        Method m = getMethod(joinPoint);
        TccTransaction tccTransaction = m.getAnnotation(TccTransaction.class);
        return tccTransaction;
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        Signature s = joinPoint.getSignature();
        MethodSignature ms = (MethodSignature) s;
        Method m = ms.getMethod();
        return m;
    }

}
