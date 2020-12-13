package com.mine.tool.orm.mybatis.core.query;

import java.util.ArrayList;
import java.util.List;

public class CriteriaExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> criteriaList;

    protected int pageNo = 0;

    protected int pageSize = 15;

    public CriteriaExample() {
        criteriaList = new ArrayList<>();
    }

    /**
     * 默认值<0,这样配合xml不生成limit
     */
    public int getStartOffSet() {
        return (pageNo - 1) * pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public CriteriaExample setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public CriteriaExample setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public CriteriaExample limit(int pageNo,int pageSize) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        return this;
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

    public List<Criteria> getCriteriaList() {
        return criteriaList;
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        criteriaList.add(criteria);
        return criteria;
    }

    public Criteria and() {
        Criteria criteria = createCriteriaInternal();
        criteriaList.add(criteria);
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    public void clear() {
        criteriaList.clear();
        orderByClause = null;
        distinct = false;
    }


    public CriteriaExample orderBy(String ... orderByClauses) {
        StringBuilder buffer = new StringBuilder();
        if(orderByClauses == null) throw new RuntimeException("order by field cannot be null");
        for(String field : orderByClauses) {
            if(field == null || field.trim().length() == 0) throw new RuntimeException("order by field cannot be null");
            buffer.append(field);
            buffer.append(",");
        }
        if(buffer.length() == 0) return this;
        this.setOrderByClause(buffer.substring(0, buffer.length() - 1));
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
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value) {
            criteria.add(new Criterion(condition, value));
        }

        public Criteria eq(String column,Object value) {
            addCriterion(column + " = ",value);
            return (Criteria) this;
        }

        public Criteria neq(String column,Object value) {
            addCriterion(column + " <> ",value);
            return (Criteria) this;
        }

        public Criteria gt(String column,Object value) {
            addCriterion(column + " > ",value);
            return (Criteria) this;
        }

        public Criteria gte(String column,Object value) {
            addCriterion(column + " >= ",value);
            return (Criteria) this;
        }

        public Criteria lt(String column,Object value) {
            addCriterion(column + " < ",value);
            return (Criteria) this;
        }

        public Criteria lte(String column,Object value) {
            addCriterion(column + " <= ",value);
            return (Criteria) this;
        }

        public Criteria like(String column,String value) {
            if (value != null && value.trim().length() != 0) {
                String newValue = value.trim() + "%";
                addCriterion(column + " like ",newValue);
            }
            return (Criteria) this;
        }

        public Criteria like(String column,List<String> values) {
            addCriterion(column + " like ",values);
            return (Criteria) this;
        }

        public Criteria notLike(String column,String value) {
            if (value != null && value.trim().length() != 0) {
                String newValue = value.trim() + "%";
                addCriterion(column + " not like ",newValue);
            }
            return (Criteria) this;
        }

        public Criteria isNull(String column) {
            addCriterion(column + " is null ");
            return (Criteria) this;
        }

        public Criteria isNotNull(String column) {
            addCriterion(column + " is not null ");
            return (Criteria) this;
        }

        public Criteria in(String column,List<?> values) {
            addCriterion(column + " in ",values);
            return (Criteria) this;
        }

        public Criteria notIn(String column,List<?> values) {
            addCriterion(column + " not in ",values);
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private CriteriaExample criteriaExample;

        protected Criteria(CriteriaExample criteriaExample) {
            super();
            this.criteriaExample = criteriaExample;
        }

        public CriteriaExample example() {
            return this.criteriaExample;
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean likeListValue;

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

        public boolean isLikeListValue() {
            return likeListValue;
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
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                List<?> l = (List<?>)value;
                if(!l.isEmpty()) {
                    if(condition.contains("like")) {
                        this.likeListValue = true;
                    } else if(condition.contains("in")) {
                        this.listValue = true;
                    }
                }
            } else {
                this.singleValue = true;
                if(value == null || value.toString().trim().length() == 0) {
                    this.noValue = true;
                }
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

    public static void main(String[] args) {

    }
}