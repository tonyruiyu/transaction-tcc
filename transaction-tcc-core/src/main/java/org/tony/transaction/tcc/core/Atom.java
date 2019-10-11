package org.tony.transaction.tcc.core;

import java.io.Serializable;
import java.util.Date;

public class Atom implements Serializable {

    private static final long serialVersionUID = -441982071110249074L;

    /** 原子单类型 */
    private String            code;

    /** 组合单id */
    private String            compositeId;

    /** 原子单id */
    private String            atomId;

    /** 上次执行状态 首次为CRT */
    private String            lastStatus;

    /** 当前执行状态 */
    private String            currentStatus;

    /** 创建时间 */
    private Date              createDate;

    /** 当前执行时间 */
    private Date              currentTime;

    /** ioc service id */
    private String            iocId;

    /** 重试次数 */
    private int               currentRetryCnt  = 0;

    /** 参数 */
    private Object[]          args;

    private byte[]            argsContent;

    private int               lockStatus;

    private Date              lockTime;
    
    
    private Integer atomSort;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompositeId() {
        return compositeId;
    }

    public void setCompositeId(String compositeId) {
        this.compositeId = compositeId;
    }

    public String getAtomId() {
        return atomId;
    }

    public void setAtomId(String atomId) {
        this.atomId = atomId;
    }

    public String getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus = lastStatus;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public int getCurrentRetryCnt() {
        return currentRetryCnt;
    }

    public void setCurrentRetryCnt(int currentRetryCnt) {
        this.currentRetryCnt = currentRetryCnt;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getIocId() {
        return iocId;
    }

    public void setIocId(String iocId) {
        this.iocId = iocId;
    }


    public byte[] getArgsContent() {
        return argsContent;
    }

    public void setArgsContent(byte[] argsContent) {
        this.argsContent = argsContent;
    }

    public int getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(int lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public Integer getAtomSort() {
        return atomSort;
    }

    public void setAtomSort(Integer atomSort) {
        this.atomSort = atomSort;
    }
}
