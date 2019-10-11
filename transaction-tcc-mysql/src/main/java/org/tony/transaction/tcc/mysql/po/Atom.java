package org.tony.transaction.tcc.mysql.po;

import java.io.Serializable;
import java.util.Date;

public class Atom implements Serializable {
    /**
     * 
     */
    private String atomId;

    /**
     * 原子事务类型
     */
    private String atomCode;

    /**
     * 
     */
    private String compositeId;

    /**
     * 上次执行状态
     */
    private String lastStatus;

    /**
     * 当前执行状态
     */
    private String atomCurrentStatus;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 当前执行时间
     */
    private Date atomCurrentTime;

    /**
     * 
     */
    private String iocId;

    /**
     * 
     */
    private Integer currentRetryCnt;

    /**
     * 锁定时间
     */
    private Date lockTime;

    /**
     * 锁定时间0否1是
     */
    private Integer lockStatus;

    /**
     * 
     */
    private Integer atomSort;

    /**
     * 
     */
    private byte[] argsContent;

    /**
     * atom
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return atom_id 
     */
    public String getAtomId() {
        return atomId;
    }

    /**
     * 
     * @param atomId 
     */
    public void setAtomId(String atomId) {
        this.atomId = atomId == null ? null : atomId.trim();
    }

    /**
     * 原子事务类型
     * @return atom_code 原子事务类型
     */
    public String getAtomCode() {
        return atomCode;
    }

    /**
     * 原子事务类型
     * @param atomCode 原子事务类型
     */
    public void setAtomCode(String atomCode) {
        this.atomCode = atomCode == null ? null : atomCode.trim();
    }

    /**
     * 
     * @return composite_id 
     */
    public String getCompositeId() {
        return compositeId;
    }

    /**
     * 
     * @param compositeId 
     */
    public void setCompositeId(String compositeId) {
        this.compositeId = compositeId == null ? null : compositeId.trim();
    }

    /**
     * 上次执行状态
     * @return last_status 上次执行状态
     */
    public String getLastStatus() {
        return lastStatus;
    }

    /**
     * 上次执行状态
     * @param lastStatus 上次执行状态
     */
    public void setLastStatus(String lastStatus) {
        this.lastStatus = lastStatus == null ? null : lastStatus.trim();
    }

    /**
     * 当前执行状态
     * @return atom_current_status 当前执行状态
     */
    public String getAtomCurrentStatus() {
        return atomCurrentStatus;
    }

    /**
     * 当前执行状态
     * @param atomCurrentStatus 当前执行状态
     */
    public void setAtomCurrentStatus(String atomCurrentStatus) {
        this.atomCurrentStatus = atomCurrentStatus == null ? null : atomCurrentStatus.trim();
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 当前执行时间
     * @return atom_current_time 当前执行时间
     */
    public Date getAtomCurrentTime() {
        return atomCurrentTime;
    }

    /**
     * 当前执行时间
     * @param atomCurrentTime 当前执行时间
     */
    public void setAtomCurrentTime(Date atomCurrentTime) {
        this.atomCurrentTime = atomCurrentTime;
    }

    /**
     * 
     * @return ioc_id 
     */
    public String getIocId() {
        return iocId;
    }

    /**
     * 
     * @param iocId 
     */
    public void setIocId(String iocId) {
        this.iocId = iocId == null ? null : iocId.trim();
    }

    /**
     * 
     * @return current_retry_cnt 
     */
    public Integer getCurrentRetryCnt() {
        return currentRetryCnt;
    }

    /**
     * 
     * @param currentRetryCnt 
     */
    public void setCurrentRetryCnt(Integer currentRetryCnt) {
        this.currentRetryCnt = currentRetryCnt;
    }

    /**
     * 锁定时间
     * @return lock_time 锁定时间
     */
    public Date getLockTime() {
        return lockTime;
    }

    /**
     * 锁定时间
     * @param lockTime 锁定时间
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    /**
     * 锁定时间0否1是
     * @return lock_status 锁定时间0否1是
     */
    public Integer getLockStatus() {
        return lockStatus;
    }

    /**
     * 锁定时间0否1是
     * @param lockStatus 锁定时间0否1是
     */
    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    /**
     * 
     * @return atom_sort 
     */
    public Integer getAtomSort() {
        return atomSort;
    }

    /**
     * 
     * @param atomSort 
     */
    public void setAtomSort(Integer atomSort) {
        this.atomSort = atomSort;
    }

    /**
     * 
     * @return args_content 
     */
    public byte[] getArgsContent() {
        return argsContent;
    }

    /**
     * 
     * @param argsContent 
     */
    public void setArgsContent(byte[] argsContent) {
        this.argsContent = argsContent;
    }
}