package org.tony.transaction.tcc.mysql.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompositeExample {
    /**
     * composite
     */
    protected String orderByClause;

    /**
     * composite
     */
    protected boolean distinct;

    /**
     * composite
     */
    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public CompositeExample() {
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
     * composite null
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCCodeIsNull() {
            addCriterion("c_code is null");
            return (Criteria) this;
        }

        public Criteria andCCodeIsNotNull() {
            addCriterion("c_code is not null");
            return (Criteria) this;
        }

        public Criteria andCCodeEqualTo(String value) {
            addCriterion("c_code =", value, "cCode");
            return (Criteria) this;
        }

        public Criteria andCCodeNotEqualTo(String value) {
            addCriterion("c_code <>", value, "cCode");
            return (Criteria) this;
        }

        public Criteria andCCodeGreaterThan(String value) {
            addCriterion("c_code >", value, "cCode");
            return (Criteria) this;
        }

        public Criteria andCCodeGreaterThanOrEqualTo(String value) {
            addCriterion("c_code >=", value, "cCode");
            return (Criteria) this;
        }

        public Criteria andCCodeLessThan(String value) {
            addCriterion("c_code <", value, "cCode");
            return (Criteria) this;
        }

        public Criteria andCCodeLessThanOrEqualTo(String value) {
            addCriterion("c_code <=", value, "cCode");
            return (Criteria) this;
        }

        public Criteria andCCodeLike(String value) {
            addCriterion("c_code like", value, "cCode");
            return (Criteria) this;
        }

        public Criteria andCCodeNotLike(String value) {
            addCriterion("c_code not like", value, "cCode");
            return (Criteria) this;
        }

        public Criteria andCCodeIn(List<String> values) {
            addCriterion("c_code in", values, "cCode");
            return (Criteria) this;
        }

        public Criteria andCCodeNotIn(List<String> values) {
            addCriterion("c_code not in", values, "cCode");
            return (Criteria) this;
        }

        public Criteria andCCodeBetween(String value1, String value2) {
            addCriterion("c_code between", value1, value2, "cCode");
            return (Criteria) this;
        }

        public Criteria andCCodeNotBetween(String value1, String value2) {
            addCriterion("c_code not between", value1, value2, "cCode");
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

        public Criteria andCCurrentStatusIsNull() {
            addCriterion("c_current_status is null");
            return (Criteria) this;
        }

        public Criteria andCCurrentStatusIsNotNull() {
            addCriterion("c_current_status is not null");
            return (Criteria) this;
        }

        public Criteria andCCurrentStatusEqualTo(String value) {
            addCriterion("c_current_status =", value, "cCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andCCurrentStatusNotEqualTo(String value) {
            addCriterion("c_current_status <>", value, "cCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andCCurrentStatusGreaterThan(String value) {
            addCriterion("c_current_status >", value, "cCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andCCurrentStatusGreaterThanOrEqualTo(String value) {
            addCriterion("c_current_status >=", value, "cCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andCCurrentStatusLessThan(String value) {
            addCriterion("c_current_status <", value, "cCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andCCurrentStatusLessThanOrEqualTo(String value) {
            addCriterion("c_current_status <=", value, "cCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andCCurrentStatusLike(String value) {
            addCriterion("c_current_status like", value, "cCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andCCurrentStatusNotLike(String value) {
            addCriterion("c_current_status not like", value, "cCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andCCurrentStatusIn(List<String> values) {
            addCriterion("c_current_status in", values, "cCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andCCurrentStatusNotIn(List<String> values) {
            addCriterion("c_current_status not in", values, "cCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andCCurrentStatusBetween(String value1, String value2) {
            addCriterion("c_current_status between", value1, value2, "cCurrentStatus");
            return (Criteria) this;
        }

        public Criteria andCCurrentStatusNotBetween(String value1, String value2) {
            addCriterion("c_current_status not between", value1, value2, "cCurrentStatus");
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

        public Criteria andCCurrentTimeIsNull() {
            addCriterion("c_current_time is null");
            return (Criteria) this;
        }

        public Criteria andCCurrentTimeIsNotNull() {
            addCriterion("c_current_time is not null");
            return (Criteria) this;
        }

        public Criteria andCCurrentTimeEqualTo(Date value) {
            addCriterion("c_current_time =", value, "cCurrentTime");
            return (Criteria) this;
        }

        public Criteria andCCurrentTimeNotEqualTo(Date value) {
            addCriterion("c_current_time <>", value, "cCurrentTime");
            return (Criteria) this;
        }

        public Criteria andCCurrentTimeGreaterThan(Date value) {
            addCriterion("c_current_time >", value, "cCurrentTime");
            return (Criteria) this;
        }

        public Criteria andCCurrentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("c_current_time >=", value, "cCurrentTime");
            return (Criteria) this;
        }

        public Criteria andCCurrentTimeLessThan(Date value) {
            addCriterion("c_current_time <", value, "cCurrentTime");
            return (Criteria) this;
        }

        public Criteria andCCurrentTimeLessThanOrEqualTo(Date value) {
            addCriterion("c_current_time <=", value, "cCurrentTime");
            return (Criteria) this;
        }

        public Criteria andCCurrentTimeIn(List<Date> values) {
            addCriterion("c_current_time in", values, "cCurrentTime");
            return (Criteria) this;
        }

        public Criteria andCCurrentTimeNotIn(List<Date> values) {
            addCriterion("c_current_time not in", values, "cCurrentTime");
            return (Criteria) this;
        }

        public Criteria andCCurrentTimeBetween(Date value1, Date value2) {
            addCriterion("c_current_time between", value1, value2, "cCurrentTime");
            return (Criteria) this;
        }

        public Criteria andCCurrentTimeNotBetween(Date value1, Date value2) {
            addCriterion("c_current_time not between", value1, value2, "cCurrentTime");
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
    }

    /**
     * composite
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * composite null
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