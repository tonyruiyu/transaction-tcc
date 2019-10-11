package org.tony.transaction.tcc.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.tony.transaction.tcc.core.CompositeTransaction;
import org.tony.transaction.tcc.core.TransactionContext;
@Aspect
public class AtomTransactionAspect {
    

    @Around("@annotation(org.tony.transaction.tcc.core.annotation.TryMethod)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        
        CompositeTransaction ctx = TransactionContext.getCompositeTransaction();
        Object obj = joinPoint.getTarget();
        ctx.regeditAtom(obj, joinPoint.getArgs());
        return null;
    } 
}
