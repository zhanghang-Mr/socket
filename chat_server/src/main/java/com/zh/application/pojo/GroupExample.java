package com.zh.application.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroupExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupBuildIdIsNull() {
            addCriterion("group_build_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupBuildIdIsNotNull() {
            addCriterion("group_build_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupBuildIdEqualTo(Long value) {
            addCriterion("group_build_id =", value, "groupBuildId");
            return (Criteria) this;
        }

        public Criteria andGroupBuildIdNotEqualTo(Long value) {
            addCriterion("group_build_id <>", value, "groupBuildId");
            return (Criteria) this;
        }

        public Criteria andGroupBuildIdGreaterThan(Long value) {
            addCriterion("group_build_id >", value, "groupBuildId");
            return (Criteria) this;
        }

        public Criteria andGroupBuildIdGreaterThanOrEqualTo(Long value) {
            addCriterion("group_build_id >=", value, "groupBuildId");
            return (Criteria) this;
        }

        public Criteria andGroupBuildIdLessThan(Long value) {
            addCriterion("group_build_id <", value, "groupBuildId");
            return (Criteria) this;
        }

        public Criteria andGroupBuildIdLessThanOrEqualTo(Long value) {
            addCriterion("group_build_id <=", value, "groupBuildId");
            return (Criteria) this;
        }

        public Criteria andGroupBuildIdIn(List<Long> values) {
            addCriterion("group_build_id in", values, "groupBuildId");
            return (Criteria) this;
        }

        public Criteria andGroupBuildIdNotIn(List<Long> values) {
            addCriterion("group_build_id not in", values, "groupBuildId");
            return (Criteria) this;
        }

        public Criteria andGroupBuildIdBetween(Long value1, Long value2) {
            addCriterion("group_build_id between", value1, value2, "groupBuildId");
            return (Criteria) this;
        }

        public Criteria andGroupBuildIdNotBetween(Long value1, Long value2) {
            addCriterion("group_build_id not between", value1, value2, "groupBuildId");
            return (Criteria) this;
        }

        public Criteria andGroupBuildTimeIsNull() {
            addCriterion("group_build_time is null");
            return (Criteria) this;
        }

        public Criteria andGroupBuildTimeIsNotNull() {
            addCriterion("group_build_time is not null");
            return (Criteria) this;
        }

        public Criteria andGroupBuildTimeEqualTo(Date value) {
            addCriterion("group_build_time =", value, "groupBuildTime");
            return (Criteria) this;
        }

        public Criteria andGroupBuildTimeNotEqualTo(Date value) {
            addCriterion("group_build_time <>", value, "groupBuildTime");
            return (Criteria) this;
        }

        public Criteria andGroupBuildTimeGreaterThan(Date value) {
            addCriterion("group_build_time >", value, "groupBuildTime");
            return (Criteria) this;
        }

        public Criteria andGroupBuildTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("group_build_time >=", value, "groupBuildTime");
            return (Criteria) this;
        }

        public Criteria andGroupBuildTimeLessThan(Date value) {
            addCriterion("group_build_time <", value, "groupBuildTime");
            return (Criteria) this;
        }

        public Criteria andGroupBuildTimeLessThanOrEqualTo(Date value) {
            addCriterion("group_build_time <=", value, "groupBuildTime");
            return (Criteria) this;
        }

        public Criteria andGroupBuildTimeIn(List<Date> values) {
            addCriterion("group_build_time in", values, "groupBuildTime");
            return (Criteria) this;
        }

        public Criteria andGroupBuildTimeNotIn(List<Date> values) {
            addCriterion("group_build_time not in", values, "groupBuildTime");
            return (Criteria) this;
        }

        public Criteria andGroupBuildTimeBetween(Date value1, Date value2) {
            addCriterion("group_build_time between", value1, value2, "groupBuildTime");
            return (Criteria) this;
        }

        public Criteria andGroupBuildTimeNotBetween(Date value1, Date value2) {
            addCriterion("group_build_time not between", value1, value2, "groupBuildTime");
            return (Criteria) this;
        }

        public Criteria andGroupSummaryIsNull() {
            addCriterion("group_summary is null");
            return (Criteria) this;
        }

        public Criteria andGroupSummaryIsNotNull() {
            addCriterion("group_summary is not null");
            return (Criteria) this;
        }

        public Criteria andGroupSummaryEqualTo(String value) {
            addCriterion("group_summary =", value, "groupSummary");
            return (Criteria) this;
        }

        public Criteria andGroupSummaryNotEqualTo(String value) {
            addCriterion("group_summary <>", value, "groupSummary");
            return (Criteria) this;
        }

        public Criteria andGroupSummaryGreaterThan(String value) {
            addCriterion("group_summary >", value, "groupSummary");
            return (Criteria) this;
        }

        public Criteria andGroupSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("group_summary >=", value, "groupSummary");
            return (Criteria) this;
        }

        public Criteria andGroupSummaryLessThan(String value) {
            addCriterion("group_summary <", value, "groupSummary");
            return (Criteria) this;
        }

        public Criteria andGroupSummaryLessThanOrEqualTo(String value) {
            addCriterion("group_summary <=", value, "groupSummary");
            return (Criteria) this;
        }

        public Criteria andGroupSummaryLike(String value) {
            addCriterion("group_summary like", value, "groupSummary");
            return (Criteria) this;
        }

        public Criteria andGroupSummaryNotLike(String value) {
            addCriterion("group_summary not like", value, "groupSummary");
            return (Criteria) this;
        }

        public Criteria andGroupSummaryIn(List<String> values) {
            addCriterion("group_summary in", values, "groupSummary");
            return (Criteria) this;
        }

        public Criteria andGroupSummaryNotIn(List<String> values) {
            addCriterion("group_summary not in", values, "groupSummary");
            return (Criteria) this;
        }

        public Criteria andGroupSummaryBetween(String value1, String value2) {
            addCriterion("group_summary between", value1, value2, "groupSummary");
            return (Criteria) this;
        }

        public Criteria andGroupSummaryNotBetween(String value1, String value2) {
            addCriterion("group_summary not between", value1, value2, "groupSummary");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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