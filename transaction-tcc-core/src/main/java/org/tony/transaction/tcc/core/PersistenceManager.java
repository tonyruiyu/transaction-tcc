package org.tony.transaction.tcc.core;

import java.util.List;

public interface PersistenceManager {

    /**
     * 保存原子单数据,如果保存失败需要抛出异常
     * 
     * @param atom
     */
    void insertAtom(Atom atom);

    /**
     * 保存组合单状态
     * 
     * @param composite
     */
    void insertComposite(Composite composite);

    /**
     * 更新原子单状态
     * 
     * @param atomId
     *            原子单id
     * @param currentStatus
     *            当前原子单状态
     * @param lastStatus
     *            上次原子单状态
     */
    void nextAtomStatus(String atomId, String currentStatus, String lastStatus);

    /**
     * 更新组合单状态
     * @param id
     *            组合单id
     * @param currentStatus
     *            当前状态
     * @param lastStatus
     *            上次状态
     */
    void nextCompositeStatus(String id, String currentStatus, String lastStatus);
    
    List<Composite> loadCompositeByStatus(String code, List<String> status, long timeout);

    List<Atom> loadAtomsByComposite(String cid);

    boolean obtainCompositeLock(String id,long timeout);

    Composite findComposite(String id);

    boolean releaseCompositeByStatus(String cid);

    /**
     * 
     * @param codes 
     * @param saveDay 保留天数
     */
    void clearCompostie(String codes, int saveDay);

}
