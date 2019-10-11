package org.tony.transaction.tcc.mysql.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AtomExample {
    /**
     * atom
     */
    protected String orderByClause;

    /**
     * atom
     */
    protected boolean distinct;

    /**
     * atom
     */
    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public AtomExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void limit(Integer offset, Integer limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    /**
     * atom null
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAtomIdIsNull() {
            addCriterion("atom_id is null");
            return (Criteria) this;
        }

        public Criteria andAtomIdIsNotNull() {
            addCriterion("atom_id is not null");
            return (Criteria) this;
        }

        public Criteria andAtomIdEqualTo(String value) {
            addCriterion("atom_id =", value, "atomId");
            return (Criteria) this;
        }

        public Criteria andAtomIdNotEqualTo(String value) {
            addCriterion("atom_id <>", value, "atomId");
            return (Criteria) this;
        }

        public Criteria andAtomIdGreaterThan(String value) {
            addCriterion("atom_id >", value, "atomId");
            return (Criteria) this;
        }

        public Criteria andAtomIdGreaterThanOrEqualTo(String value) {
            addCriterion("atom_id >=", value, "atomId");
            return (Criteria) this;
        }

        public Criteria andAtomIdLessThan(String value) {
            addCriterion("atom_id <", value, "atomId");
            return (Criteria) this;
        }

        public Criteria andAtomIdLessThanOrEqualTo(String value) {
            addCriterion("atom_id <=", value, "atomId");
            return (Criteria) this;
        }

        public Criteria andAtomIdLike(String value) {
            addCriterion("atom_id like", value, "atomId");
            return (Criteria) this;
        }

        public Criteria andAtomIdNotLike(String value) {
            addCriterion("atom_id not like", value, "atomId");
            return (Criteria) this;
        }

        public Criteria andAtomIdIn(List<String> values) {
            addCriterion("atom_id in", values, "atomId");
            return (Criteria) this;
        }

        public Criteria andAtomIdNotIn(List<String> values) {
            addCriterion("atom_id not in", values, "atomId");
            return (Criteria) this;
        }

        public Criteria andAtomIdBetween(String value1, String value2) {
            addCriterion("atom_id between", value1, value2, "atomId");
            return (Criteria) this;
        }

        public Criteria andAtomIdNotBetween(String value1, String value2) {
            addCriterion("atom_id not between", value1, value2, "atomId");
            return (Criteria) this;
        }

        public Criteria andAtomCodeIsNull() {
            addCriterion("atom_code is null");
            return (Criteria) this;
        }

        public Criteria andAtomCodeIsNotNull() {
            addCriterion("atom_code is not null");
            return (Criteria) this;
        }

        public Criteria andAtomCodeEqualTo(String value) {
            addCriterion("atom_code =", value, "atomCode");
            return (Criteria) this;
        }

        public Criteria andAtomCodeNotEqualTo(String value) {
            addCriterion("atom_code <>", value, "atomCode");
            return (Criteria) this;
        }

        public Criteria andAtomCodeGreaterThan(String value) {
            addCriterion("atom_code >", value, "atomCode");
            return (Criteria) this;
        }

        public Criteria andAtomCodeGreaterThanOrEqualTo(String value) {
            addCriterion("atom_code >=", value, "atomCode");
            return (Criteria) this;
        }

        public Criteria andAtomCodeLessThan(String value) {
            addCriterion("atom_code <", value, "atomCode");
            return (Criteria) this;
        }

        public Criteria andAtomCodeLessThanOrEqualTo(String value) {
            addCriterion("atom_code <=", value, "atomCode");
            return (Criteria) this;
        }

        public Criteria andAtomCodeLike(String value) {
            addCriterion("atom_code like", value, "atomCode");
            return (Criteria) this;
        }

        public Criteria andAtomCodeNotLike(String value) {
            addCriterion("atom_code not like", value, "atomCode");
            return (Criteria) this;
        }

        public Criteria andAtomCodeIn(List<String> values) {
            addCriterion("atom_code in", values, "atomCode");
            return (Criteria) this;
        }

        public Criteria andAtomCodeNotIn(List<String> values) {
            addCriterion("atom_code not in", values, "atomCode");
            return (Criteria) this;
        }

        public Criteria andAtomCodeBetween(String value1, String value2) {
            addCriterion("atom_code between", value1, value2, "atomCode");
            return (Criteria) this;
        }

        public Criteria andAtomCodeNotBetween(String value1, String value2) {
            addCriterion("atom_code not between", value1, value2, "atomCode");
            return (Criteria) this;
        }

        public Criteria andCompositeIdIsNull() {
            addCriterion("composite_id is null");
            return (Criteria) this;
        }

        public Criteria andCompositeIdIsNotNull() {
            addCriterion("composite_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompositeIdEqualTo(String value) {
            addCriterion("composite_id =", value, "compositeId");
            return (Criteria) this;
        }

        public Criteria andCompositeIdNotEqualTo(String value) {
            addCriterion("composite_id <>", value, "compositeId");
            return (Criteria) this;
        }

        public Criteria andCompositeIdGreaterThan(String value) {
            addCriterion("composite_id >", value, "compositeId");
            return (Criteria) this;
        }

        public Criteria andCompositeIdGreaterThanOrEqualTo(String value) {
            addCriterion("composite_id >=", value, "compositeId");
            return (Criteria) this;
        }

        public Criteria andCompositeIdLessThan(String value) {
            addCriterion("composite_id <", value, "compositeId");
            return (Criteria) this;
        }

        public Criteria andCompositeIdLessThanOrEqualTo(String value) {
            addCriterion("composite_id <=", value, "compositeId");
            return (Criteria) this;
        }

        public Criteria andCompositeIdLike(String value) {
            addCriterion("composite_id like", value, "compositeId");
            return (Criteria) this;
        }

        public Criteria andCompositeIdNotLike(String value) {
            addCriterion("composite_id not like", value, "compositeId");
            return (Criteria) this;
        }

        public Criteria andCompositeIdIn(List<String> values) {
            addCriterion("composite_id in", values, "compositeId");
            return (Criteria) this;
        }

        public Criteria andCompositeIdNotIn(List<String> values) {
            addCriterion("composite_id not in", values, "compositeId");
            return (Criteria) this;
        }

        public Criteria andCompositeIdBetween(String value1, String value2) {
            addCriterion("composite_id between", value1, value2, "compositeId");
            return (Criteria) this;
        }

        public Criteria andCompositeIdNotBetween(String value1, String value2) {
            addCriterion("composite_id not between", value1, value2, "compositeId");
            return (Criteria) this;
        }

        public Criteria andLastStatusIsNull() {
            addCriterion("last_status is null");
            return (Criteria) this;
        }

        public Criteria andLastStatusIsNotNull() {
            addCriterion("last_status is not null");
            return (Criteria) this;
        }

        public Criteria andLastStatusEqualTo(String value) {
            addCriterion("last_status =", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusNotEqualTo(String value) {
            addCriterion("last_status <>", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusGreaterThan(String value) {
            addCriterion("last_status >", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusGreaterThanOrEqualTo(String value) {
            addCriterion("last_status >=", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusLessThan(String value) {
            addCriterion("last_status <", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusLessThanOrEqualTo(String value) {
            addCriterion("last_status <=", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusLike(String value) {
            addCriterion("last_status like", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusNotLike(String value) {
            addCriterion("last_status not like", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusIn(List<String> values) {
            addCriterion("last_status in", values, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusNotIn(List<String> values) {
            addCriterion("last_status not in", values, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusBetween(String value1, String value2) {
            addCriterion("last_status between", value1, value2, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusNotBetween(String value1, String value2) {
            addCriterion("last_status not between", value1, value2, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentStatusIsNull() {
            addCriterion("atom_current_status is null");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentStatusIsNotNull() {
            addCriterion("atom_current_status is not null");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentStatusEqualTo(String value) {
            addCriterion("atom_current_status =", value, "atomCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentStatusNotEqualTo(String value) {
            addCriterion("atom_current_status <>", value, "atomCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentStatusGreaterThan(String value) {
            addCriterion("atom_current_status >", value, "atomCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentStatusGreaterThanOrEqualTo(String value) {
            addCriterion("atom_current_status >=", value, "atomCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentStatusLessThan(String value) {
            addCriterion("atom_current_status <", value, "atomCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentStatusLessThanOrEqualTo(String value) {
            addCriterion("atom_current_status <=", value, "atomCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentStatusLike(String value) {
            addCriterion("atom_current_status like", value, "atomCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentStatusNotLike(String value) {
            addCriterion("atom_current_status not like", value, "atomCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentStatusIn(List<String> values) {
            addCriterion("atom_current_status in", values, "atomCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentStatusNotIn(List<String> values) {
            addCriterion("atom_current_status not in", values, "atomCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentStatusBetween(String value1, String value2) {
            addCriterion("atom_current_status between", value1, value2, "atomCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentStatusNotBetween(String value1, String value2) {
            addCriterion("atom_current_status not between", value1, value2, "atomCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentTimeIsNull() {
            addCriterion("atom_current_time is null");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentTimeIsNotNull() {
            addCriterion("atom_current_time is not null");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentTimeEqualTo(Date value) {
            addCriterion("atom_current_time =", value, "atomCurrentTime");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentTimeNotEqualTo(Date value) {
            addCriterion("atom_current_time <>", value, "atomCurrentTime");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentTimeGreaterThan(Date value) {
            addCriterion("atom_current_time >", value, "atomCurrentTime");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("atom_current_time >=", value, "atomCurrentTime");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentTimeLessThan(Date value) {
            addCriterion("atom_current_time <", value, "atomCurrentTime");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentTimeLessThanOrEqualTo(Date value) {
            addCriterion("atom_current_time <=", value, "atomCurrentTime");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentTimeIn(List<Date> values) {
            addCriterion("atom_current_time in", values, "atomCurrentTime");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentTimeNotIn(List<Date> values) {
            addCriterion("atom_current_time not in", values, "atomCurrentTime");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentTimeBetween(Date value1, Date value2) {
            addCriterion("atom_current_time between", value1, value2, "atomCurrentTime");
            return (Criteria) this;
        }

        public Criteria andAtomCurrentTimeNotBetween(Date value1, Date value2) {
            addCriterion("atom_current_time not between", value1, value2, "atomCurrentTime");
            return (Criteria) this;
        }

        public Criteria andIocIdIsNull() {
            addCriterion("ioc_id is null");
            return (Criteria) this;
        }

        public Criteria andIocIdIsNotNull() {
            addCriterion("ioc_id is not null");
            return (Criteria) this;
        }

        public Criteria andIocIdEqualTo(String value) {
            addCriterion("ioc_id =", value, "iocId");
            return (Criteria) this;
        }

        public Criteria andIocIdNotEqualTo(String value) {
            addCriterion("ioc_id <>", value, "iocId");
            return (Criteria) this;
        }

        public Criteria andIocIdGreaterThan(String value) {
            addCriterion("ioc_id >", value, "iocId");
            return (Criteria) this;
        }

        public Criteria andIocIdGreaterThanOrEqualTo(String value) {
            addCriterion("ioc_id >=", value, "iocId");
            return (Criteria) this;
        }

        public Criteria andIocIdLessThan(String value) {
            addCriterion("ioc_id <", value, "iocId");
            return (Criteria) this;
        }

        public Criteria andIocIdLessThanOrEqualTo(String value) {
            addCriterion("ioc_id <=", value, "iocId");
            return (Criteria) this;
        }

        public Criteria andIocIdLike(String value) {
            addCriterion("ioc_id like", value, "iocId");
            return (Criteria) this;
        }

        public Criteria andIocIdNotLike(String value) {
            addCriterion("ioc_id not like", value, "iocId");
            return (Criteria) this;
        }

        public Criteria andIocIdIn(List<String> values) {
            addCriterion("ioc_id in", values, "iocId");
            return (Criteria) this;
        }

        public Criteria andIocIdNotIn(List<String> values) {
            addCriterion("ioc_id not in", values, "iocId");
            return (Criteria) this;
        }

        public Criteria andIocIdBetween(String value1, String value2) {
            addCriterion("ioc_id between", value1, value2, "iocId");
            return (Criteria) this;
        }

        public Criteria andIocIdNotBetween(String value1, String value2) {
            addCriterion("ioc_id not between", value1, value2, "iocId");
            return (Criteria) this;
        }

        public Criteria andCurrentRetryCntIsNull() {
            addCriterion("current_retry_cnt is null");
            return (Criteria) this;
        }

        public Criteria andCurrentRetryCntIsNotNull() {
            addCriterion("current_retry_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentRetryCntEqualTo(Integer value) {
            addCriterion("current_retry_cnt =", value, "currentRetryCnt");
            return (Criteria) this;
        }

        public Criteria andCurrentRetryCntNotEqualTo(Integer value) {
            addCriterion("current_retry_cnt <>", value, "currentRetryCnt");
            return (Criteria) this;
        }

        public Criteria andCurrentRetryCntGreaterThan(Integer value) {
            addCriterion("current_retry_cnt >", value, "currentRetryCnt");
            return (Criteria) this;
        }

        public Criteria andCurrentRetryCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("current_retry_cnt >=", value, "currentRetryCnt");
            return (Criteria) this;
        }

        public Criteria andCurrentRetryCntLessThan(Integer value) {
            addCriterion("current_retry_cnt <", value, "currentRetryCnt");
            return (Criteria) this;
        }

        public Criteria andCurrentRetryCntLessThanOrEqualTo(Integer value) {
            addCriterion("current_retry_cnt <=", value, "currentRetryCnt");
            return (Criteria) this;
        }

        public Criteria andCurrentRetryCntIn(List<Integer> values) {
            addCriterion("current_retry_cnt in", values, "currentRetryCnt");
            return (Criteria) this;
        }

        public Criteria andCurrentRetryCntNotIn(List<Integer> values) {
            addCriterion("current_retry_cnt not in", values, "currentRetryCnt");
            return (Criteria) this;
        }

        public Criteria andCurrentRetryCntBetween(Integer value1, Integer value2) {
            addCriterion("current_retry_cnt between", value1, value2, "currentRetryCnt");
            return (Criteria) this;
        }

        public Criteria andCurrentRetryCntNotBetween(Integer value1, Integer value2) {
            addCriterion("current_retry_cnt not between", value1, value2, "currentRetryCnt");
            return (Criteria) this;
        }

        public Criteria andLockTimeIsNull() {
            addCriterion("lock_time is null");
            return (Criteria) this;
        }

        public Criteria andLockTimeIsNotNull() {
            addCriterion("lock_time is not null");
            return (Criteria) this;
        }

        public Criteria andLockTimeEqualTo(Date value) {
            addCriterion("lock_time =", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotEqualTo(Date value) {
            addCriterion("lock_time <>", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeGreaterThan(Date value) {
            addCriterion("lock_time >", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lock_time >=", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeLessThan(Date value) {
            addCriterion("lock_time <", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeLessThanOrEqualTo(Date value) {
            addCriterion("lock_time <=", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeIn(List<Date> values) {
            addCriterion("lock_time in", values, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotIn(List<Date> values) {
            addCriterion("lock_time not in", values, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeBetween(Date value1, Date value2) {
            addCriterion("lock_time between", value1, value2, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotBetween(Date value1, Date value2) {
            addCriterion("lock_time not between", value1, value2, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockStatusIsNull() {
            addCriterion("lock_status is null");
            return (Criteria) this;
        }

        public Criteria andLockStatusIsNotNull() {
            addCriterion("lock_status is not null");
            return (Criteria) this;
        }

        public Criteria andLockStatusEqualTo(Integer value) {
            addCriterion("lock_status =", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotEqualTo(Integer value) {
            addCriterion("lock_status <>", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusGreaterThan(Integer value) {
            addCriterion("lock_status >", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("lock_status >=", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusLessThan(Integer value) {
            addCriterion("lock_status <", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusLessThanOrEqualTo(Integer value) {
            addCriterion("lock_status <=", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusIn(List<Integer> values) {
            addCriterion("lock_status in", values, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotIn(List<Integer> values) {
            addCriterion("lock_status not in", values, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusBetween(Integer value1, Integer value2) {
            addCriterion("lock_status between", value1, value2, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("lock_status not between", value1, value2, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andAtomSortIsNull() {
            addCriterion("atom_sort is null");
            return (Criteria) this;
        }

        public Criteria andAtomSortIsNotNull() {
            addCriterion("atom_sort is not null");
            return (Criteria) this;
        }

        public Criteria andAtomSortEqualTo(Integer value) {
            addCriterion("atom_sort =", value, "atomSort");
            return (Criteria) this;
        }

        public Criteria andAtomSortNotEqualTo(Integer value) {
            addCriterion("atom_sort <>", value, "atomSort");
            return (Criteria) this;
        }

        public Criteria andAtomSortGreaterThan(Integer value) {
            addCriterion("atom_sort >", value, "atomSort");
            return (Criteria) this;
        }

        public Criteria andAtomSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("atom_sort >=", value, "atomSort");
            return (Criteria) this;
        }

        public Criteria andAtomSortLessThan(Integer value) {
            addCriterion("atom_sort <", value, "atomSort");
            return (Criteria) this;
        }

        public Criteria andAtomSortLessThanOrEqualTo(Integer value) {
            addCriterion("atom_sort <=", value, "atomSort");
            return (Criteria) this;
        }

        public Criteria andAtomSortIn(List<Integer> values) {
            addCriterion("atom_sort in", values, "atomSort");
            return (Criteria) this;
        }

        public Criteria andAtomSortNotIn(List<Integer> values) {
            addCriterion("atom_sort not in", values, "atomSort");
            return (Criteria) this;
        }

        public Criteria andAtomSortBetween(Integer value1, Integer value2) {
            addCriterion("atom_sort between", value1, value2, "atomSort");
            return (Criteria) this;
        }

        public Criteria andAtomSortNotBetween(Integer value1, Integer value2) {
            addCriterion("atom_sort not between", value1, value2, "atomSort");
            return (Criteria) this;
        }
    }

    /**
     * atom
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * atom null
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}