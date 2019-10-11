package org.tony.transaction.tcc.core;

import java.io.Serializable;
import java.util.Date;

/**
 * 组合事务单据
 * 
 * @author tonyruiyu
 *
 */
public class Composite implements Serializable {

    private static final long serialVersionUID = -330332848427625402L;

    /** 组合单code */
    private String            code;

    /** 组合单id */
    private String            id;

    /** 上次执行状态 首次为CRT */
    private String            lastStatus;

    /** 当前执行状态 */
    private String            currentStatus;

    /** 创建时间 */
    private Date              createDate;

    /** 当前执行时间 */
    private Date              currentTime;

    /** 锁定时间 */
    private Date              lockTime;

    /** 锁定类型 */
    private int               lockStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public int getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(int lockStatus) {
        this.lockStatus = lockStatus;
    }

}
