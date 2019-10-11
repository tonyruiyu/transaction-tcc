package org.tony.transaction.tcc.test.testcase;

import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.tony.transaction.tcc.core.CompositeTransaction;
import org.tony.transaction.tcc.core.TccTransactionManager;
import org.tony.transaction.tcc.test.TestBase;
import org.tony.transaction.tcc.test.stock.B2CTransactionApiThread;
import org.tony.transaction.tcc.test.stock.BusinessService;
import org.tony.transaction.tcc.test.stock.StockTransactionApiThread;

public class ThreadTransactionCase extends TestBase {
    @Resource
    BusinessService businessService;
    @Resource
    TccTransactionManager tccTransactionManager;

    @Autowired
    B2CTransactionApiThread b2cTransactionApiThread;
    
    @Autowired
    StockTransactionApiThread stockTransactionApiThread;
    
    @Test
    public void testThread() throws InterruptedException {
        long a = System.currentTimeMillis();
        int threadCnt = 100;
        final CountDownLatch cdl = new CountDownLatch(threadCnt);
        final int loopCnt = 10000;
        for (int i = 0; i < threadCnt; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread " + Thread.currentThread().getId() + " up");
                    for (int j = 0; j < loopCnt; j++) {
                        businessService.runThread();
                    }
                    cdl.countDown();
                    System.out.println("thread " + Thread.currentThread().getId() + " down");
                }
            }).start();
        }
        
        cdl.await();
        long b = System.currentTimeMillis();
        System.out.println("耗时" +( b -a ));
    }
    @Test
    public void testThreadInterface() throws InterruptedException {
        long a = System.currentTimeMillis();
        int threadCnt = 100;
        final CountDownLatch cdl = new CountDownLatch(threadCnt);
        final int loopCnt = 10000;
        for (int i = 0; i < threadCnt; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread " + Thread.currentThread().getId() + " up");

                    for (int j = 0; j < loopCnt; j++) {
                        CompositeTransaction compositeTransaction = tccTransactionManager.createTransaction("b2cstock");
                        StockB2cTransactionBase.ctx.set(compositeTransaction);
                        //注册b2c原子事务
                        compositeTransaction.regeditAtom(b2cTransactionApiThread, new Object[] {});
                        //注册库存原子事务
                        compositeTransaction.regeditAtom(stockTransactionApiThread, new Object[] {});
                        //提交事务
                        try {
                            compositeTransaction.submit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    
                    cdl.countDown();
                    System.out.println("thread " + Thread.currentThread().getId() + " down");
                }
            }).start();
        }
        
        cdl.await();
        long b = System.currentTimeMillis();
        System.out.println("耗时" +( b -a ));
    }
    
    
    

}
