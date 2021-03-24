package com.mine.demo.model;

import java.util.ArrayList;
import java.util.List;

public class VehicleStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VehicleStatisticsExample() {
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

    public VehicleStatisticsExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public VehicleStatisticsExample orderBy(String ... orderByClauses) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderByClauses.length; i++) {
            sb.append(orderByClauses[i]);
            if (i < orderByClauses.length - 1) {
                sb.append(" , ");
            }
        }
        this.setOrderByClause(sb.toString());
        return this;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public static Criteria newAndCreateCriteria() {
        VehicleStatisticsExample example = new VehicleStatisticsExample();
        return example.createCriteria();
    }

    public VehicleStatisticsExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public VehicleStatisticsExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        } else {
            otherwise.example(this);
        }
        return this;
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

        public Criteria andIdEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("id <= ").append(column.getEscapedColumnName()).toString());
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

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("customer_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("customer_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("customer_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("customer_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("customer_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("customer_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("amount = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("amount <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("amount > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("amount >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("amount < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("amount <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andBrandDistributionIsNull() {
            addCriterion("brand_distribution is null");
            return (Criteria) this;
        }

        public Criteria andBrandDistributionIsNotNull() {
            addCriterion("brand_distribution is not null");
            return (Criteria) this;
        }

        public Criteria andBrandDistributionEqualTo(String value) {
            addCriterion("brand_distribution =", value, "brandDistribution");
            return (Criteria) this;
        }

        public Criteria andBrandDistributionEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("brand_distribution = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBrandDistributionNotEqualTo(String value) {
            addCriterion("brand_distribution <>", value, "brandDistribution");
            return (Criteria) this;
        }

        public Criteria andBrandDistributionNotEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("brand_distribution <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBrandDistributionGreaterThan(String value) {
            addCriterion("brand_distribution >", value, "brandDistribution");
            return (Criteria) this;
        }

        public Criteria andBrandDistributionGreaterThanColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("brand_distribution > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBrandDistributionGreaterThanOrEqualTo(String value) {
            addCriterion("brand_distribution >=", value, "brandDistribution");
            return (Criteria) this;
        }

        public Criteria andBrandDistributionGreaterThanOrEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("brand_distribution >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBrandDistributionLessThan(String value) {
            addCriterion("brand_distribution <", value, "brandDistribution");
            return (Criteria) this;
        }

        public Criteria andBrandDistributionLessThanColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("brand_distribution < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBrandDistributionLessThanOrEqualTo(String value) {
            addCriterion("brand_distribution <=", value, "brandDistribution");
            return (Criteria) this;
        }

        public Criteria andBrandDistributionLessThanOrEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("brand_distribution <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBrandDistributionLike(String value) {
            addCriterion("brand_distribution like", value, "brandDistribution");
            return (Criteria) this;
        }

        public Criteria andBrandDistributionNotLike(String value) {
            addCriterion("brand_distribution not like", value, "brandDistribution");
            return (Criteria) this;
        }

        public Criteria andBrandDistributionIn(List<String> values) {
            addCriterion("brand_distribution in", values, "brandDistribution");
            return (Criteria) this;
        }

        public Criteria andBrandDistributionNotIn(List<String> values) {
            addCriterion("brand_distribution not in", values, "brandDistribution");
            return (Criteria) this;
        }

        public Criteria andBrandDistributionBetween(String value1, String value2) {
            addCriterion("brand_distribution between", value1, value2, "brandDistribution");
            return (Criteria) this;
        }

        public Criteria andBrandDistributionNotBetween(String value1, String value2) {
            addCriterion("brand_distribution not between", value1, value2, "brandDistribution");
            return (Criteria) this;
        }

        public Criteria andRangeConditionIsNull() {
            addCriterion("range_condition is null");
            return (Criteria) this;
        }

        public Criteria andRangeConditionIsNotNull() {
            addCriterion("range_condition is not null");
            return (Criteria) this;
        }

        public Criteria andRangeConditionEqualTo(Integer value) {
            addCriterion("range_condition =", value, "rangeCondition");
            return (Criteria) this;
        }

        public Criteria andRangeConditionEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("range_condition = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRangeConditionNotEqualTo(Integer value) {
            addCriterion("range_condition <>", value, "rangeCondition");
            return (Criteria) this;
        }

        public Criteria andRangeConditionNotEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("range_condition <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRangeConditionGreaterThan(Integer value) {
            addCriterion("range_condition >", value, "rangeCondition");
            return (Criteria) this;
        }

        public Criteria andRangeConditionGreaterThanColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("range_condition > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRangeConditionGreaterThanOrEqualTo(Integer value) {
            addCriterion("range_condition >=", value, "rangeCondition");
            return (Criteria) this;
        }

        public Criteria andRangeConditionGreaterThanOrEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("range_condition >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRangeConditionLessThan(Integer value) {
            addCriterion("range_condition <", value, "rangeCondition");
            return (Criteria) this;
        }

        public Criteria andRangeConditionLessThanColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("range_condition < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRangeConditionLessThanOrEqualTo(Integer value) {
            addCriterion("range_condition <=", value, "rangeCondition");
            return (Criteria) this;
        }

        public Criteria andRangeConditionLessThanOrEqualToColumn(VehicleStatistics.Column column) {
            addCriterion(new StringBuilder("range_condition <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRangeConditionIn(List<Integer> values) {
            addCriterion("range_condition in", values, "rangeCondition");
            return (Criteria) this;
        }

        public Criteria andRangeConditionNotIn(List<Integer> values) {
            addCriterion("range_condition not in", values, "rangeCondition");
            return (Criteria) this;
        }

        public Criteria andRangeConditionBetween(Integer value1, Integer value2) {
            addCriterion("range_condition between", value1, value2, "rangeCondition");
            return (Criteria) this;
        }

        public Criteria andRangeConditionNotBetween(Integer value1, Integer value2) {
            addCriterion("range_condition not between", value1, value2, "rangeCondition");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private VehicleStatisticsExample example;

        protected Criteria(VehicleStatisticsExample example) {
            super();
            this.example = example;
        }

        public VehicleStatisticsExample example() {
            return this.example;
        }

        @Deprecated
        public Criteria andIf(boolean ifAdd, ICriteriaAdd add) {
            if (ifAdd) {
                add.add(this);
            }
            return this;
        }

        public Criteria when(boolean condition, ICriteriaWhen then) {
            if (condition) {
                then.criteria(this);
            }
            return this;
        }

        public Criteria when(boolean condition, ICriteriaWhen then, ICriteriaWhen otherwise) {
            if (condition) {
                then.criteria(this);
            } else {
                otherwise.criteria(this);
            }
            return this;
        }

        @Deprecated
        public interface ICriteriaAdd {
            Criteria add(Criteria add);
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

    public interface ICriteriaWhen {
        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {
        void example(com.mine.demo.model.VehicleStatisticsExample example);
    }
}