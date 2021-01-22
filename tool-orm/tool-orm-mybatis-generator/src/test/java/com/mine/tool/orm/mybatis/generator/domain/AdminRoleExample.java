package com.mine.tool.orm.mybatis.generator.domain;

import com.mine.tool.orm.mybatis.core.query.Example;
import com.mine.tool.orm.mybatis.core.util.TableUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("all")
public class AdminRoleExample implements Example<AdminRole> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdminRoleExample() {
        oredCriteria = new ArrayList<>();
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

    private Object transfer(String condition, Object values) {
        return condition + " \"" + StringUtils.join((List)values,"\" or " + condition + " \"") + "\"";
    }

    private Object transfer(Object value) {
        if(value instanceof String){
            return "\""+value+"\"";
        }
        if(value instanceof List){
            List line = (List) value;
            Object first = line.get(0);
            if(first instanceof String){
                return StringUtils.join((List)value,"\",\"");
            }else{
                return StringUtils.join((List)value,",");
            }
        }
        return value;
    }

    @Override
    public String getCondition() {
        String tableName = TableUtils.getTableName(AdminRole.class);
        String columnMapping = TableUtils.getColumnMapping(AdminRole.class);
        StringBuilder sql = new StringBuilder("select ").append(columnMapping).append(" from ").append(tableName);
        StringBuilder condition = new StringBuilder();
        for (AdminRoleExample.Criteria criteria : this.oredCriteria){
            List<AdminRoleExample.Criterion> criterions = criteria.getAllCriteria();
            if(null==criterions||criterions.isEmpty()){ continue; }
            int index = this.oredCriteria.indexOf(criteria);
            StringBuilder conditions = new StringBuilder();
            for (AdminRoleExample.Criterion criterion : criterions){
                if(criterion.isSingleValue() && !criterion.isNoValue() && null != criterion.getValue()){
                    if(criterions.indexOf(criterion) > 0){
                        condition.append(" and ");
                    }
                    condition.append(criterion.getCondition())
                    .append(" ")
                    .append(transfer(criterion.getValue()));
                }
                if(criterion.isBetweenValue() && null != criterion.getValue() && null != criterion.getSecondValue()){
                    if(criterions.indexOf(criterion) > 0){
                        condition.append(" and ");
                    }
                    condition.append(criterion.getCondition())
                    .append(" ")
                    .append(transfer(criterion.getValue()))
                    .append(" and ")
                    .append(transfer(criterion.getSecondValue()));
                }
                if(criterion.isListValue() && null != criterion.getValue() && !((List)criterion.getValue()).isEmpty()){
                    if(criterions.indexOf(criterion) > 0){
                        condition.append(" and ");
                    }
                    condition.append(criterion.getCondition())
                    .append("(")
                    .append(transfer(criterion.getValue()))
                    .append(")");
                }
                if(criterion.isLikeListValue() && null != criterion.getValue() && !((List)criterion.getValue()).isEmpty()){
                    if(criterions.indexOf(criterion) > 0){
                        condition.append(" and ");
                    }
                    List<String> values = (List<String>) criterion.getValue();
                    condition.append("(")
                    .append(transfer(criterion.getCondition(),values))
                    .append(")");
                }
            }
            if(null==condition || condition.toString().trim().length() == 0){
                continue;
            }
            if(index > 0){
                conditions.insert(0," or (");
            }else{
                conditions.insert(0,"(");
            }
            conditions.append(condition);
            conditions.append(")");
        }
        if(null != condition && condition.length() > 0){
            sql.append(" where ").append(condition);
        }
        if(null != this.orderByClause && this.orderByClause.length() > 0){
            sql.append(" order by ").append(this.orderByClause);
        }
        return sql.toString();
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
        Criteria criteria = new Criteria(this);
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
            criteria = new ArrayList<>();
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
                return;
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                return;
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                return;
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Integer value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Integer value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Integer value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Integer value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Integer value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Integer> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Integer> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Integer value1, Integer value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNull() {
            addCriterion("role_name is null");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNotNull() {
            addCriterion("role_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNameEqualTo(String value) {
            addCriterion("role_name =", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotEqualTo(String value) {
            addCriterion("role_name <>", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThan(String value) {
            addCriterion("role_name >", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("role_name >=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThan(String value) {
            addCriterion("role_name <", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThanOrEqualTo(String value) {
            addCriterion("role_name <=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLike(String value) {
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("role_name like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotLike(String value) {
            addCriterion("role_name not like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameIn(List<String> values) {
            addCriterion("role_name in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotIn(List<String> values) {
            addCriterion("role_name not in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameBetween(String value1, String value2) {
            addCriterion("role_name between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotBetween(String value1, String value2) {
            addCriterion("role_name not between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeIsNull() {
            addCriterion("role_describe is null");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeIsNotNull() {
            addCriterion("role_describe is not null");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeEqualTo(String value) {
            addCriterion("role_describe =", value, "roleDescribe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeNotEqualTo(String value) {
            addCriterion("role_describe <>", value, "roleDescribe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeGreaterThan(String value) {
            addCriterion("role_describe >", value, "roleDescribe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("role_describe >=", value, "roleDescribe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeLessThan(String value) {
            addCriterion("role_describe <", value, "roleDescribe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeLessThanOrEqualTo(String value) {
            addCriterion("role_describe <=", value, "roleDescribe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeLike(String value) {
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("role_describe like", value, "roleDescribe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeNotLike(String value) {
            addCriterion("role_describe not like", value, "roleDescribe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeIn(List<String> values) {
            addCriterion("role_describe in", values, "roleDescribe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeNotIn(List<String> values) {
            addCriterion("role_describe not in", values, "roleDescribe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeBetween(String value1, String value2) {
            addCriterion("role_describe between", value1, value2, "roleDescribe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeNotBetween(String value1, String value2) {
            addCriterion("role_describe not between", value1, value2, "roleDescribe");
            return (Criteria) this;
        }

        public Criteria andRoleTypeIsNull() {
            addCriterion("role_type is null");
            return (Criteria) this;
        }

        public Criteria andRoleTypeIsNotNull() {
            addCriterion("role_type is not null");
            return (Criteria) this;
        }

        public Criteria andRoleTypeEqualTo(Integer value) {
            addCriterion("role_type =", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeNotEqualTo(Integer value) {
            addCriterion("role_type <>", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeGreaterThan(Integer value) {
            addCriterion("role_type >", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_type >=", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeLessThan(Integer value) {
            addCriterion("role_type <", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeLessThanOrEqualTo(Integer value) {
            addCriterion("role_type <=", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeIn(List<Integer> values) {
            addCriterion("role_type in", values, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeNotIn(List<Integer> values) {
            addCriterion("role_type not in", values, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeBetween(Integer value1, Integer value2) {
            addCriterion("role_type between", value1, value2, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("role_type not between", value1, value2, "roleType");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Long value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Long value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Long value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Long value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Long value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Long value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Long> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Long> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Long value1, Long value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Long value1, Long value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andRoleNameLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("role_name like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("role_name like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("role_name like", likeValues, "role_name");
            return (Criteria) this;
        }

        public Criteria andRoleNameLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("role_name like", likeValues, "role_name");
            return (Criteria) this;
        }

        public Criteria andRoleNameLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("role_name like", likeValues, "role_name");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("role_describe like", value, "roleDescribe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("role_describe like", value, "roleDescribe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("role_describe like", likeValues, "role_describe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("role_describe like", likeValues, "role_describe");
            return (Criteria) this;
        }

        public Criteria andRoleDescribeLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("role_describe like", likeValues, "role_describe");
            return (Criteria) this;
        }

        private List<String> transferLikeValue(List<String>  values, String sign) {
            List<String> likeValues = new ArrayList<>();
            for (String value : values){
                if(null==value||value.length()==0){continue;}
                if(value.indexOf("%") >=0 ){ continue; }
                if("prefix".equals(sign)){
                    value = "%"+value;
                }
                if("suffix".equals(sign)){
                    value = value+"%";
                }
                if("around".equals(sign)){
                    value = "%"+value+"%";
                }
                likeValues.add(value);
            }
            return likeValues;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private AdminRoleExample example;

        protected Criteria(AdminRoleExample example) {
            super();
            this.example = example;
        }

        public AdminRoleExample example() {
            return this.example;
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

        private boolean likeListValue;

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
                if(condition.contains("like")){
                    this.likeListValue = true;
                }else{
                    this.listValue = true;
                }
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

        public boolean isLikeListValue() {
            return likeListValue;
        }
    }
}