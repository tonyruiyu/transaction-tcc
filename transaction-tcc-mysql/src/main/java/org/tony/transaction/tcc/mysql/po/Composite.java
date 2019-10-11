package org.tony.transaction.tcc.mysql.po;

import java.io.Serializable;
import java.util.Date;

public class Composite implements Serializable {
    /**
     * 组合事务id
     */
    private String id;

    /**
     * 组合事务
     */
    private String cCode;

    /**
     * 上次执行状态
     */
    private String lastStatus;

    /**
     * 当前执行状态
     */
    private String cCurrentStatus;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 最后一次执行时间
     */
    private Date cCurrentTime;

    /**
     * 最后锁定时间
     */
    private Date lockTime;

    /**
     * 锁定状态0否1是
     */
    private Integer lockStatus;

    /**
     * composite
     */
    private static final long serialVersionUID = 1L;

    /**
     * 组合事务id
     * @return id 组合事务id
     */
    public String getId() {
        return id;
    }

    /**
     * 组合事务id
     * @param id 组合事务id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 组合事务
     * @return c_code 组合事务
     */
    public String getcCode() {
        return cCode;
    }

    /**
     * 组合事务
     * @param cCode 组合事务
     */
    public void setcCode(String cCode) {
        this.cCode = cCode == null ? null : cCode.trim();
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
     * @return c_current_status 当前执行状态
     */
    public String getcCurrentStatus() {
        return cCurrentStatus;
    }

    /**
     * 当前执行状态
     * @param cCurrentStatus 当前执行状态
     */
    public void setcCurrentStatus(String cCurrentStatus) {
        this.cCurrentStatus = cCurrentStatus == null ? null : cCurrentStatus.trim();
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
     * 最后一次执行时间
     * @return c_current_time 最后一次执行时间
     */
    public Date getcCurrentTime() {
        return cCurrentTime;
    }

    /**
     * 最后一次执行时间
     * @param cCurrentTime 最后一次执行时间
     */
    public void setcCurrentTime(Date cCurrentTime) {
        this.cCurrentTime = cCurrentTime;
    }

    /**
     * 最后锁定时间
     * @return lock_time 最后锁定时间
     */
    public Date getLockTime() {
        return lockTime;
    }

    /**
     * 最后锁定时间
     * @param lockTime 最后锁定时间
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    /**
     * 锁定状态0否1是
     * @return lock_status 锁定状态0否1是
     */
    public Integer getLockStatus() {
        return lockStatus;
    }

    /**
     * 锁定状态0否1是
     * @param lockStatus 锁定状态0否1是
     */
    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }
}