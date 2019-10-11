package org.tony.transaction.tcc.test.testcase;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.tony.transaction.tcc.core.Atom;
import org.tony.transaction.tcc.core.AtomContext;
import org.tony.transaction.tcc.core.AtomStatus;
import org.tony.transaction.tcc.core.Composite;
import org.tony.transaction.tcc.core.CompositeStatus;
import org.tony.transaction.tcc.core.CompositeTransaction;
import org.tony.transaction.tcc.core.recovery.CompositeTransactionRecoveryManager;
import org.tony.transaction.tcc.test.Result;

import org.tony.transaction.tcc.test.TestBase;

/**
 * 
 * 实现测试用例<br/>
 * 1.B2C正常,stock正常<br/>
 * 2.B2C 发生undo异常 库存接口不被调用<br/>
 * 3.B2C正常,stock undo<br/>
 * 4.B2C unknow,stock不被调用<br/>
 * 5.b2c正常，stock unknow<br/>
 * <br/>
 * 状态说明：<br/>
 * 组合单：<br/>
 * NUL->CRT 对应动作 TccTransactionManager.createTransaction() success 创建组合事务<br/>
 * CRT->TRY 对应动作 CompositeTransaction.submit() 开始执行原子事务tryMethod<br/>
 * TRY->CFM 对应动作 原子事务全部执行成功<br/>
 * TRY->UNDO 对应动作 原子事务实现失败或者部分失败或者状态未知<br/>
 * CFM->FINISH 对应动作 原子事务提交成功<br/>
 * CFM->RETRT 对应动作 原子事务提交失败或者状态未知<br/>
 * RETRT->FINISH 对应动作 原子事务全部提交成功<br/>
 * RETRT->MANUAL 对应动作 超过重试次数<br/>
 * <br/>
 * <br/>
 * 原子单：<br/>
 * NUL->CRT 对应动作 CompositeTransaction.regeditAtom() success 注册原子事务<br/>
 * CRT->TRY 对应动作 AtomTransaction.tryMethod() before 准备开始调用try方法<br/>
 * TRY->CFM 对应动作 AtomTransaction.tryMethod() success 原子事务调用成功<br/>
 * TRY->UNDO 对应动作 AtomTransaction.tryMethod() undo异常 原子事务明确调用失败<br/>
 * TRY->QUERY 对应动作 AtomTransaction.tryMethod() unknow异常 原子事务调用未知状态<br/>
 * QUERY->CFM 对应动作 AtomTransaction.query() CFM 查询远端状态结果为CFM<br/>
 * QUERY->UNDO 对应动作 AtomTransaction.query() UNDO 查询远端状态结果为UNDO<br/>
 * UNDO->FINISH 对应动作 AtomTransaction.cancel() success 原子事务回滚成功<br/>
 * UNDO->MANUAL 对应动作 AtomTransaction.cancel() UNKNOW 超过重试次数<br/>
 * CFM->FINISH 对应动作 AtomTransaction.confirm() success 原子事务提交成功<br/>
 * CFM->RETRT 对应动作 AtomTransaction.confirm() UNKNOW 原子事务提交失败<br/>
 * RETRT->FINISH 对应动作 AtomTransaction.confirm() success 原子事务提交成功<br/>
 * RETRT->MANUAL 对应动作 AtomTransaction.confirm() UNKNOW 超过重试次数<br/>
 * <br/>
 * <br/>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class StockB2cTransactionBase extends TestBase {

    public static ThreadLocal<CompositeTransaction>      ctx         = new ThreadLocal<CompositeTransaction>();

    @Resource
    CompositeTransactionRecoveryManager                  recoveryManager1;

    protected boolean                                    isTryErrorb = false;

    protected boolean                                    isUndob     = true;

    protected AtomStatus                                 atomStatusb = AtomStatus.CFM;

    protected boolean                                    isTryErrors = false;

    protected boolean                                    isUndos     = true;

    protected AtomStatus                                 atomStatuss = AtomStatus.CFM;
    protected static List<Result>                        results     = new ArrayList<>();
    protected static List<Result>                        rResults     = new ArrayList<>();

    protected static CompositeTransactionRecoveryManager recoveryManager;

    protected static CompositeAssertStatus               SUCCESS, B2CUNDO, STOCKUNDO, B2CUNKNOW, STOCKUNKNOW;

    @BeforeClass
    public static void init() {
        SUCCESS = new CompositeAssertStatus();
        SUCCESS.current = CompositeStatus.FINISH;
        SUCCESS.last = CompositeStatus.CFM;
        SUCCESS.batom.current = AtomStatus.FINISH;
        SUCCESS.batom.last = AtomStatus.CFM;
        SUCCESS.satom.current = AtomStatus.FINISH;
        SUCCESS.satom.last = AtomStatus.CFM;

        B2CUNDO = new CompositeAssertStatus();
        B2CUNDO.current = CompositeStatus.FINISH;
        B2CUNDO.last = CompositeStatus.UNDO;
        B2CUNDO.batom.current = AtomStatus.FINISH;
        B2CUNDO.batom.last = AtomStatus.UNDO;
        B2CUNDO.satom.current = AtomStatus.CRT;
        B2CUNDO.satom.last = AtomStatus.CRT;

        STOCKUNDO = new CompositeAssertStatus();
        STOCKUNDO.current = CompositeStatus.FINISH;
        STOCKUNDO.last = CompositeStatus.UNDO;
        STOCKUNDO.batom.current = AtomStatus.FINISH;
        STOCKUNDO.batom.last = AtomStatus.UNDO;
        STOCKUNDO.satom.current = AtomStatus.FINISH;
        STOCKUNDO.satom.last = AtomStatus.UNDO;

        B2CUNKNOW = new CompositeAssertStatus();
        B2CUNKNOW.current = CompositeStatus.UNKNOW;
        B2CUNKNOW.last = CompositeStatus.TRY;
        B2CUNKNOW.batom.current = AtomStatus.QUERY;
        B2CUNKNOW.batom.last = AtomStatus.TRY;
        B2CUNKNOW.satom.current = AtomStatus.CRT;
        B2CUNKNOW.satom.last = AtomStatus.CRT;

        STOCKUNKNOW = new CompositeAssertStatus();
        STOCKUNKNOW.current = CompositeStatus.UNKNOW;
        STOCKUNKNOW.last = CompositeStatus.TRY;
        STOCKUNKNOW.batom.current = AtomStatus.CFM;
        STOCKUNKNOW.batom.last = AtomStatus.TRY;
        STOCKUNKNOW.satom.current = AtomStatus.QUERY;
        STOCKUNKNOW.satom.last = AtomStatus.TRY;
    }

    /**
     * 序号：1 说明：b2c正常,stock正常<br/>
     * <br/>
     * 测试所有原子单都成功<br/>
     * <br/>
     * b2c tryMethod 执行 confirmMethod 执行 状态变化
     * <code>CRT->TRY->CFM->FINISH</code><br/>
     * stock tryMethod 执行 confirmMethod 执行 状态变化
     * <code>CRT->TRY->CFM->FINISH</code><br/>
     * 
     * 组合事务变化 <code>CRT->TRY->CFM->FINISH</code>
     * 
     */
    @Test
    public void test001StockSuccess() {
        // 实际执行事务方法
        CompositeTransaction compositeTransaction = submit();
        assertTransaction(compositeTransaction, SUCCESS);
    }

    /**
     * 序号：2 B2C 发生undo异常 库存接口不被调用<br/>
     * <br/>
     * b2c tryMethod执行 状态变化 CRT->TRY->UNDO->FINISH <br/>
     * stock tryMethod不执行 状态变化 CRT<br/>
     * <br/>
     * 组合事务变化 CRT->TRY->UNDO->FINISH<br/>
     * 
     */
    @Test
    public void test002B2CTryUNDOError() {
        setB2CMockData(true, true);
        // 实际执行事务方法
        CompositeTransaction compositeTransaction = submit();
        assertTransaction(compositeTransaction, B2CUNDO);
    }

    /**
     * 序号：3 测试Stock try 发生undo异常 B2CtryMethod已经被调用 需要被立即回滚<br/>
     * <br/>
     * b2c tryMethod执行 状态变化 CRT->TRY->CFM->FINISH <br/>
     * stock tryMethod不执行 状态变化 CRT->TRY->UNDO->FINISH<br/>
     * <br/>
     * 组合事务变化 CRT->TRY->UNDO->FINISH<br/>
     * <br/>
     */
    @Test
    public void test003StockTryUNDOError() {
        setStockMockData(true, true);
        // 实际执行事务方法
        CompositeTransaction compositeTransaction = submit();
        assertTransaction(compositeTransaction, STOCKUNDO);
    }

    /**
     * 序号：4 测试B2C try 发生 unkonw 异常 库存接口不被调用 <br/>
     * <br/>
     * b2c tryMethod执行 状态变化 CRT->TRY->QUERY <br/>
     * stock tryMethod不执行 状态变化 CRT <br/>
     * <br/>
     * 组合事务变化 CRT->TRY->UNDO <br/>
     * <br/>
     * 后续需要调度任务介入恢复调用 b2c原子单 query方法确定最终执行结果 <br/>
     * 
     */
    @Test
    public void test004B2CTryUNKNOWError() {
        setB2CMockData(true, false,AtomStatus.CFM);
        // 实际执行事务方法
        CompositeTransaction compositeTransaction = submit();
        assertTransaction(compositeTransaction, B2CUNKNOW);
    }

    /**
     * 序号：5 测试Stock try 发生unknow异常 B2CtryMethod已经被调用 <br/>
     * <br/>
     * b2c tryMethod执行 状态变化 CRT->TRY->CFM <br/>
     * stock tryMethod不执行 状态变化 CRT->TRY->QUERY <br/>
     * <br/>
     * 组合事务变化 CRT->TRY->UNDO <br/>
     * 
     */
    @Test
    public void test005StockTryUNKNOWError() {
        setStockMockData(true, false);
        // 实际执行事务方法
        CompositeTransaction compositeTransaction = submit();
        assertTransaction(compositeTransaction, STOCKUNKNOW);
    }

    /**
     * 序号：6 测试B2C try 发生 unkonw 异常 库存接口不被调用 -> 恢复时候使用undo策略 <br/>
     * <br/>
     * b2c tryMethod执行 状态变化 CRT->TRY->QUERY <br/>
     * stock tryMethod不执行 状态变化 CRT <br/>
     * <br/>
     * 组合事务变化 CRT->TRY->UNDO <br/>
     * <br/>
     * 后续需要调度任务介入恢复调用 b2c原子单 query方法确定最终执行结果 <br/>
     * 
     */
    @Test
    public void test006B2CTryUNKNOWErrorToUndo() {

        setB2CMockData(true, false, AtomStatus.UNDO);

        // 实际执行事务方法
        CompositeTransaction compositeTransaction = submit();

        assertTransaction(compositeTransaction, B2CUNKNOW);
    }

    /**
     * 序号：7 测试Stock try 发生unknow异常 B2CtryMethod已经被调用 <br/>
     * <br/>
     * b2c tryMethod执行 状态变化 CRT->TRY->CFM <br/>
     * stock tryMethod不执行 状态变化 CRT->TRY->QUERY <br/>
     * <br/>
     * 组合事务变化 CRT->TRY->UNDO <br/>
     * 
     */
    @Test
    public void test007StockTryUNKNOWErrorUndo() {
        setStockMockData(true, false, AtomStatus.UNDO);
        // 实际执行事务方法
        CompositeTransaction compositeTransaction = submit();
        assertTransaction(compositeTransaction, STOCKUNKNOW);
    }
    
    public boolean flag = true;
    
    @Test
    public void test008Recovery() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        
        flag = false;
        recoveryManager.recoveryTransaction();
        
        for (int i = 0; i < results.size(); i++) {
            Result rs = results.get(i);
            

            if (isAssertRecovery(rs)) {
                CompositeAssertStatus s = getCompositeStatus(rs);
                CompositeTransaction ct = loadComposite(rs);
                Result rscc =  new Result();
                BeanUtils.copyProperties(rscc, rs);
                rscc.setComposite(ct.getComposite()); ;
                rscc.setAtoms(getAtoms(ct.getAtoms()));
                rResults.add(rscc) ;

                assertTransaction(ct, s);
            } else {
                rResults.add(rs) ;
            }
        }
    }

    @After
    public void after() {
        if(!flag) return ;
        if (recoveryManager == null) {
            recoveryManager = recoveryManager1;
        }
        CompositeTransaction ct = StockB2cTransactionBase.ctx.get();
        Composite c = ct.getComposite();
        List<AtomContext> atomContexts = ct.getAtoms();
        Result result = new Result();
        result.setComposite(c); 
        result.setAtoms(getAtoms(atomContexts));
        result.setTryErrorb(isTryErrorb); 
        result.setUndob(isUndob);
        result.setAtomStatusb(atomStatusb);
        result.setTryErrors(isTryErrors);
        result.setUndos(isUndos);
        result.setAtomStatuss(atomStatuss);
        results.add(result);
    }

    private static CompositeAssertStatus getCompositeStatus(Result rs) {
        if (rs.isTryErrorb() && !rs.isUndob()) { // B2C unknow
            if (AtomStatus.UNDO.equals(rs.getAtomStatusb())) {
                return B2CUNDO;
            } else if (AtomStatus.QUERY.equals(rs.getAtomStatusb())) {
                return B2CUNKNOW;
            } else if (AtomStatus.CFM.equals(rs.getAtomStatusb())) {
                return SUCCESS;
            } else {
                return B2CUNKNOW;
            }
        } else if (rs.isTryErrors() && !rs.isUndos()) {
            if (AtomStatus.UNDO.equals(rs.getAtomStatuss())) {
                return STOCKUNDO;
            } else if (AtomStatus.QUERY.equals(rs.getAtomStatuss())) {
                return STOCKUNKNOW;
            } else if (AtomStatus.CFM.equals(rs.getAtomStatuss())) {
                return SUCCESS;
            } else {
                return STOCKUNKNOW;
            }
        }
        return null;
    }

    private static boolean isAssertRecovery(Result rs) {
        if (rs.isTryErrorb() && !rs.isUndob()) {
            return true;
        }
        if (rs.isTryErrors() && !rs.isUndos()) {
            return true;
        }
        return false;
    }

    @AfterClass
    public static void afterClass() {
        showInfo(results);
        
        System.out.println("################################################   事务恢复之后   ################################################");
        
        showInfo(rResults);
    }

    private static CompositeTransaction loadComposite(Result rs) {
        Composite c = recoveryManager.reloadComposite(rs.getComposite().getId());
        List<AtomContext> acs = recoveryManager.loadAtomsByComposite(c);
        return new CompositeTransaction(c, acs, recoveryManager.getPersistenceManager());
    }

    private static void showInfo(List<Result> results1) {
        for (int i = 0; i < results1.size(); i++) {
            Result rs = results1.get(i);
            Composite c = rs.getComposite();
            List<Atom> atoms = rs.getAtoms();
            System.out.println("测试用例" + (i + 1) + "：(" + getNode(rs) + ")");
            System.out.println("   TryError : " + rs.isTryErrorb() + ",\tundo : " + rs.isUndob() + ",\tQueryStatus : " + rs.getAtomStatusb() + "    \tb2c");
            System.out.println("   TryError : " + rs.isTryErrors() + ",\tundo : " + rs.isUndos() + ",\tQueryStatus : " + rs.getAtomStatuss() + "    \tstock");
            System.out.println("组合事务：" + c.getId() + ",\t当前状态：" + c.getCurrentStatus() + ",\t上次状态：" + c.getLastStatus());
            for (int j = 0; j < atoms.size(); j++) {
                Atom a = atoms.get(j);
                System.out.println("原子事务：" + a.getAtomId() + ",\t类型:" + a.getCode() + ",\t当前状态：" + a.getCurrentStatus() + ",\t上次状态：" + a.getLastStatus() + ",\t组合事务id:" + a.getCompositeId());
            }

            System.out.println();
            System.out.println("----------------------------------------------------------------------------------");
        }
    }

    private static String getNode(Result rs) {
        StringBuilder sb = new StringBuilder("b2c执行");
        sb.append(showNodeStatus(rs.isTryErrorb(), rs.isUndob(), rs.getAtomStatusb())).append(" stock执行").
        append(showNodeStatus(rs.isTryErrors(), rs.isUndos(), rs.getAtomStatuss()));
        return sb.toString();
    }

    private static String showNodeStatus( boolean isTryErrorb2, boolean isUndob2,AtomStatus as) {
        StringBuilder sb = new StringBuilder();
        if(isTryErrorb2) {
            if(isUndob2) {
                sb.append("发生 undo 异常，");
            } else {
                sb.append("发生unknow 异常,\t恢复查询状态为 : " + as.name() + ".");
            }
        } else {
            sb.append("正常。");
        }
        sb.append("\t");
        return sb.toString();
    }

    private static List<Atom> getAtoms(List<AtomContext> atomContexts) {
        List<Atom> atoms = new ArrayList<>();
        for (int i = 0; i < atomContexts.size(); i++) {
            atoms.add(atomContexts.get(i).getAtom());
        }
        return atoms;
    }

    

    public static class AtomAssertStatus {
        public AtomStatus last;

        public AtomStatus current;
    }

    public static class CompositeAssertStatus {

        public CompositeStatus  last;

        public CompositeStatus  current;

        public AtomAssertStatus batom = new AtomAssertStatus();

        public AtomAssertStatus satom = new AtomAssertStatus();

    }

    public void setB2CMockData(boolean isTryError, boolean isUndo) {
        this.isTryErrorb = isTryError;
        this.isUndob = isUndo;
    };

    public void setB2CMockData(boolean isTryError, boolean isUndo, AtomStatus atomStatus) {
        setB2CMockData(isTryError, isUndo);
        this.atomStatusb = atomStatus;
    };

    public void setStockMockData(boolean isTryError, boolean isUndo) {
        this.isTryErrors = isTryError;
        this.isUndos = isUndo;
    };

    public void setStockMockData(boolean isTryError, boolean isUndo, AtomStatus atomStatus) {
        setStockMockData(isTryError, isUndo);
        this.atomStatuss = atomStatus;
    };

    protected abstract CompositeTransaction submit();

    private static void assertTransaction(CompositeTransaction compositeTransaction, CompositeAssertStatus cas) {
        try {
            int assertAtomSize = 2;

            CompositeStatus compositeStatus = CompositeStatus.valueOf(compositeTransaction.getComposite().getCurrentStatus());
            CompositeStatus compositeLastStatus = CompositeStatus.valueOf(compositeTransaction.getComposite().getLastStatus());
            // 断言组合单状态
            Assert.assertEquals(cas.current, compositeStatus);
            Assert.assertEquals(cas.last, compositeLastStatus);

            Assert.assertNotNull(compositeTransaction.getAtoms());
            // 断言原子数量
            Assert.assertEquals(assertAtomSize, compositeTransaction.getAtoms().size());

            Atom b2cAtom = compositeTransaction.getAtoms().get(0).getAtom();
            Atom stockAtom = compositeTransaction.getAtoms().get(1).getAtom();

            Atom swap = null;

            if (!b2cAtom.getCode().equalsIgnoreCase("B2C")) {
                swap = b2cAtom;
                b2cAtom = stockAtom;
                stockAtom = swap;
            }

            AtomStatus b2cAtomStatus = AtomStatus.valueOf(b2cAtom.getCurrentStatus());
            AtomStatus b2cAtomLastStatus = AtomStatus.valueOf(b2cAtom.getLastStatus());
            // 断言b2c原子单状态
            Assert.assertEquals(cas.batom.current, b2cAtomStatus);
            Assert.assertEquals(cas.batom.last, b2cAtomLastStatus);

            AtomStatus stockAtomStatus = AtomStatus.valueOf(stockAtom.getCurrentStatus());
            AtomStatus stockAtomLastStatus = AtomStatus.valueOf(stockAtom.getLastStatus());
            // 断言stock原子单状态
            Assert.assertEquals(cas.satom.current, stockAtomStatus);
            Assert.assertEquals(cas.satom.last, stockAtomLastStatus);
        } catch (Throwable e) {
            throw new RuntimeException(compositeTransaction.getComposite().getId(), e);
        }
    }

}
