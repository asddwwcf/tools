package com.mine.tool.orm.mybatis.generator.domain;

import com.mine.tool.orm.mybatis.core.query.Example;
import com.mine.tool.orm.mybatis.core.util.TableUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("all")
public class EquipmentDictExample implements Example<EquipmentDict> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EquipmentDictExample() {
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
        String tableName = TableUtils.getTableName(EquipmentDict.class);
        String columnMapping = TableUtils.getColumnMapping(EquipmentDict.class);
        StringBuilder sql = new StringBuilder("select ").append(columnMapping).append(" from ").append(tableName);
        StringBuilder condition = new StringBuilder();
        for (EquipmentDictExample.Criteria criteria : this.oredCriteria){
            List<EquipmentDictExample.Criterion> criterions = criteria.getAllCriteria();
            if(null==criterions||criterions.isEmpty()){ continue; }
            int index = this.oredCriteria.indexOf(criteria);
            StringBuilder conditions = new StringBuilder();
            for (EquipmentDictExample.Criterion criterion : criterions){
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andKeyIsNull() {
            addCriterion("key is null");
            return (Criteria) this;
        }

        public Criteria andKeyIsNotNull() {
            addCriterion("key is not null");
            return (Criteria) this;
        }

        public Criteria andKeyEqualTo(String value) {
            addCriterion("key =", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotEqualTo(String value) {
            addCriterion("key <>", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyGreaterThan(String value) {
            addCriterion("key >", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyGreaterThanOrEqualTo(String value) {
            addCriterion("key >=", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLessThan(String value) {
            addCriterion("key <", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLessThanOrEqualTo(String value) {
            addCriterion("key <=", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLike(String value) {
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("key like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotLike(String value) {
            addCriterion("key not like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyIn(List<String> values) {
            addCriterion("key in", values, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotIn(List<String> values) {
            addCriterion("key not in", values, "key");
            return (Criteria) this;
        }

        public Criteria andKeyBetween(String value1, String value2) {
            addCriterion("key between", value1, value2, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotBetween(String value1, String value2) {
            addCriterion("key not between", value1, value2, "key");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNull() {
            addCriterion("default_value is null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNotNull() {
            addCriterion("default_value is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueEqualTo(String value) {
            addCriterion("default_value =", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotEqualTo(String value) {
            addCriterion("default_value <>", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThan(String value) {
            addCriterion("default_value >", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThanOrEqualTo(String value) {
            addCriterion("default_value >=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThan(String value) {
            addCriterion("default_value <", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThanOrEqualTo(String value) {
            addCriterion("default_value <=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLike(String value) {
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("default_value like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotLike(String value) {
            addCriterion("default_value not like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIn(List<String> values) {
            addCriterion("default_value in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotIn(List<String> values) {
            addCriterion("default_value not in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueBetween(String value1, String value2) {
            addCriterion("default_value between", value1, value2, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotBetween(String value1, String value2) {
            addCriterion("default_value not between", value1, value2, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andScopeLowIsNull() {
            addCriterion("scope_low is null");
            return (Criteria) this;
        }

        public Criteria andScopeLowIsNotNull() {
            addCriterion("scope_low is not null");
            return (Criteria) this;
        }

        public Criteria andScopeLowEqualTo(String value) {
            addCriterion("scope_low =", value, "scopeLow");
            return (Criteria) this;
        }

        public Criteria andScopeLowNotEqualTo(String value) {
            addCriterion("scope_low <>", value, "scopeLow");
            return (Criteria) this;
        }

        public Criteria andScopeLowGreaterThan(String value) {
            addCriterion("scope_low >", value, "scopeLow");
            return (Criteria) this;
        }

        public Criteria andScopeLowGreaterThanOrEqualTo(String value) {
            addCriterion("scope_low >=", value, "scopeLow");
            return (Criteria) this;
        }

        public Criteria andScopeLowLessThan(String value) {
            addCriterion("scope_low <", value, "scopeLow");
            return (Criteria) this;
        }

        public Criteria andScopeLowLessThanOrEqualTo(String value) {
            addCriterion("scope_low <=", value, "scopeLow");
            return (Criteria) this;
        }

        public Criteria andScopeLowLike(String value) {
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("scope_low like", value, "scopeLow");
            return (Criteria) this;
        }

        public Criteria andScopeLowNotLike(String value) {
            addCriterion("scope_low not like", value, "scopeLow");
            return (Criteria) this;
        }

        public Criteria andScopeLowIn(List<String> values) {
            addCriterion("scope_low in", values, "scopeLow");
            return (Criteria) this;
        }

        public Criteria andScopeLowNotIn(List<String> values) {
            addCriterion("scope_low not in", values, "scopeLow");
            return (Criteria) this;
        }

        public Criteria andScopeLowBetween(String value1, String value2) {
            addCriterion("scope_low between", value1, value2, "scopeLow");
            return (Criteria) this;
        }

        public Criteria andScopeLowNotBetween(String value1, String value2) {
            addCriterion("scope_low not between", value1, value2, "scopeLow");
            return (Criteria) this;
        }

        public Criteria andScopeHighIsNull() {
            addCriterion("scope_high is null");
            return (Criteria) this;
        }

        public Criteria andScopeHighIsNotNull() {
            addCriterion("scope_high is not null");
            return (Criteria) this;
        }

        public Criteria andScopeHighEqualTo(String value) {
            addCriterion("scope_high =", value, "scopeHigh");
            return (Criteria) this;
        }

        public Criteria andScopeHighNotEqualTo(String value) {
            addCriterion("scope_high <>", value, "scopeHigh");
            return (Criteria) this;
        }

        public Criteria andScopeHighGreaterThan(String value) {
            addCriterion("scope_high >", value, "scopeHigh");
            return (Criteria) this;
        }

        public Criteria andScopeHighGreaterThanOrEqualTo(String value) {
            addCriterion("scope_high >=", value, "scopeHigh");
            return (Criteria) this;
        }

        public Criteria andScopeHighLessThan(String value) {
            addCriterion("scope_high <", value, "scopeHigh");
            return (Criteria) this;
        }

        public Criteria andScopeHighLessThanOrEqualTo(String value) {
            addCriterion("scope_high <=", value, "scopeHigh");
            return (Criteria) this;
        }

        public Criteria andScopeHighLike(String value) {
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("scope_high like", value, "scopeHigh");
            return (Criteria) this;
        }

        public Criteria andScopeHighNotLike(String value) {
            addCriterion("scope_high not like", value, "scopeHigh");
            return (Criteria) this;
        }

        public Criteria andScopeHighIn(List<String> values) {
            addCriterion("scope_high in", values, "scopeHigh");
            return (Criteria) this;
        }

        public Criteria andScopeHighNotIn(List<String> values) {
            addCriterion("scope_high not in", values, "scopeHigh");
            return (Criteria) this;
        }

        public Criteria andScopeHighBetween(String value1, String value2) {
            addCriterion("scope_high between", value1, value2, "scopeHigh");
            return (Criteria) this;
        }

        public Criteria andScopeHighNotBetween(String value1, String value2) {
            addCriterion("scope_high not between", value1, value2, "scopeHigh");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andKeyGroupIsNull() {
            addCriterion("key_group is null");
            return (Criteria) this;
        }

        public Criteria andKeyGroupIsNotNull() {
            addCriterion("key_group is not null");
            return (Criteria) this;
        }

        public Criteria andKeyGroupEqualTo(Integer value) {
            addCriterion("key_group =", value, "keyGroup");
            return (Criteria) this;
        }

        public Criteria andKeyGroupNotEqualTo(Integer value) {
            addCriterion("key_group <>", value, "keyGroup");
            return (Criteria) this;
        }

        public Criteria andKeyGroupGreaterThan(Integer value) {
            addCriterion("key_group >", value, "keyGroup");
            return (Criteria) this;
        }

        public Criteria andKeyGroupGreaterThanOrEqualTo(Integer value) {
            addCriterion("key_group >=", value, "keyGroup");
            return (Criteria) this;
        }

        public Criteria andKeyGroupLessThan(Integer value) {
            addCriterion("key_group <", value, "keyGroup");
            return (Criteria) this;
        }

        public Criteria andKeyGroupLessThanOrEqualTo(Integer value) {
            addCriterion("key_group <=", value, "keyGroup");
            return (Criteria) this;
        }

        public Criteria andKeyGroupIn(List<Integer> values) {
            addCriterion("key_group in", values, "keyGroup");
            return (Criteria) this;
        }

        public Criteria andKeyGroupNotIn(List<Integer> values) {
            addCriterion("key_group not in", values, "keyGroup");
            return (Criteria) this;
        }

        public Criteria andKeyGroupBetween(Integer value1, Integer value2) {
            addCriterion("key_group between", value1, value2, "keyGroup");
            return (Criteria) this;
        }

        public Criteria andKeyGroupNotBetween(Integer value1, Integer value2) {
            addCriterion("key_group not between", value1, value2, "keyGroup");
            return (Criteria) this;
        }

        public Criteria andKeyTypeIsNull() {
            addCriterion("key_type is null");
            return (Criteria) this;
        }

        public Criteria andKeyTypeIsNotNull() {
            addCriterion("key_type is not null");
            return (Criteria) this;
        }

        public Criteria andKeyTypeEqualTo(Integer value) {
            addCriterion("key_type =", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeNotEqualTo(Integer value) {
            addCriterion("key_type <>", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeGreaterThan(Integer value) {
            addCriterion("key_type >", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("key_type >=", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeLessThan(Integer value) {
            addCriterion("key_type <", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeLessThanOrEqualTo(Integer value) {
            addCriterion("key_type <=", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeIn(List<Integer> values) {
            addCriterion("key_type in", values, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeNotIn(List<Integer> values) {
            addCriterion("key_type not in", values, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeBetween(Integer value1, Integer value2) {
            addCriterion("key_type between", value1, value2, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("key_type not between", value1, value2, "keyType");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoIsNull() {
            addCriterion("device_model_no is null");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoIsNotNull() {
            addCriterion("device_model_no is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoEqualTo(String value) {
            addCriterion("device_model_no =", value, "deviceModelNo");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoNotEqualTo(String value) {
            addCriterion("device_model_no <>", value, "deviceModelNo");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoGreaterThan(String value) {
            addCriterion("device_model_no >", value, "deviceModelNo");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoGreaterThanOrEqualTo(String value) {
            addCriterion("device_model_no >=", value, "deviceModelNo");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoLessThan(String value) {
            addCriterion("device_model_no <", value, "deviceModelNo");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoLessThanOrEqualTo(String value) {
            addCriterion("device_model_no <=", value, "deviceModelNo");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoLike(String value) {
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("device_model_no like", value, "deviceModelNo");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoNotLike(String value) {
            addCriterion("device_model_no not like", value, "deviceModelNo");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoIn(List<String> values) {
            addCriterion("device_model_no in", values, "deviceModelNo");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoNotIn(List<String> values) {
            addCriterion("device_model_no not in", values, "deviceModelNo");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoBetween(String value1, String value2) {
            addCriterion("device_model_no between", value1, value2, "deviceModelNo");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoNotBetween(String value1, String value2) {
            addCriterion("device_model_no not between", value1, value2, "deviceModelNo");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNull() {
            addCriterion("device_type is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNotNull() {
            addCriterion("device_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeEqualTo(Integer value) {
            addCriterion("device_type =", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotEqualTo(Integer value) {
            addCriterion("device_type <>", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThan(Integer value) {
            addCriterion("device_type >", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("device_type >=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThan(Integer value) {
            addCriterion("device_type <", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("device_type <=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIn(List<Integer> values) {
            addCriterion("device_type in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotIn(List<Integer> values) {
            addCriterion("device_type not in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeBetween(Integer value1, Integer value2) {
            addCriterion("device_type between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("device_type not between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andReadwriteIsNull() {
            addCriterion("readwrite is null");
            return (Criteria) this;
        }

        public Criteria andReadwriteIsNotNull() {
            addCriterion("readwrite is not null");
            return (Criteria) this;
        }

        public Criteria andReadwriteEqualTo(Integer value) {
            addCriterion("readwrite =", value, "readwrite");
            return (Criteria) this;
        }

        public Criteria andReadwriteNotEqualTo(Integer value) {
            addCriterion("readwrite <>", value, "readwrite");
            return (Criteria) this;
        }

        public Criteria andReadwriteGreaterThan(Integer value) {
            addCriterion("readwrite >", value, "readwrite");
            return (Criteria) this;
        }

        public Criteria andReadwriteGreaterThanOrEqualTo(Integer value) {
            addCriterion("readwrite >=", value, "readwrite");
            return (Criteria) this;
        }

        public Criteria andReadwriteLessThan(Integer value) {
            addCriterion("readwrite <", value, "readwrite");
            return (Criteria) this;
        }

        public Criteria andReadwriteLessThanOrEqualTo(Integer value) {
            addCriterion("readwrite <=", value, "readwrite");
            return (Criteria) this;
        }

        public Criteria andReadwriteIn(List<Integer> values) {
            addCriterion("readwrite in", values, "readwrite");
            return (Criteria) this;
        }

        public Criteria andReadwriteNotIn(List<Integer> values) {
            addCriterion("readwrite not in", values, "readwrite");
            return (Criteria) this;
        }

        public Criteria andReadwriteBetween(Integer value1, Integer value2) {
            addCriterion("readwrite between", value1, value2, "readwrite");
            return (Criteria) this;
        }

        public Criteria andReadwriteNotBetween(Integer value1, Integer value2) {
            addCriterion("readwrite not between", value1, value2, "readwrite");
            return (Criteria) this;
        }

        public Criteria andOrderIsNull() {
            addCriterion("order is null");
            return (Criteria) this;
        }

        public Criteria andOrderIsNotNull() {
            addCriterion("order is not null");
            return (Criteria) this;
        }

        public Criteria andOrderEqualTo(Integer value) {
            addCriterion("order =", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotEqualTo(Integer value) {
            addCriterion("order <>", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderGreaterThan(Integer value) {
            addCriterion("order >", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("order >=", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderLessThan(Integer value) {
            addCriterion("order <", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderLessThanOrEqualTo(Integer value) {
            addCriterion("order <=", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderIn(List<Integer> values) {
            addCriterion("order in", values, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotIn(List<Integer> values) {
            addCriterion("order not in", values, "order");
            return (Criteria) this;
        }

        public Criteria andOrderBetween(Integer value1, Integer value2) {
            addCriterion("order between", value1, value2, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("order not between", value1, value2, "order");
            return (Criteria) this;
        }

        public Criteria andKeyLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("key like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("key like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("key like", likeValues, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("key like", likeValues, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("key like", likeValues, "key");
            return (Criteria) this;
        }

        public Criteria andNameLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("name like", likeValues, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("name like", likeValues, "name");
            return (Criteria) this;
        }

        public Criteria andNameLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("name like", likeValues, "name");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("default_value like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("default_value like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("default_value like", likeValues, "default_value");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("default_value like", likeValues, "default_value");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("default_value like", likeValues, "default_value");
            return (Criteria) this;
        }

        public Criteria andScopeLowLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("scope_low like", value, "scopeLow");
            return (Criteria) this;
        }

        public Criteria andScopeLowLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("scope_low like", value, "scopeLow");
            return (Criteria) this;
        }

        public Criteria andScopeLowLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("scope_low like", likeValues, "scope_low");
            return (Criteria) this;
        }

        public Criteria andScopeLowLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("scope_low like", likeValues, "scope_low");
            return (Criteria) this;
        }

        public Criteria andScopeLowLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("scope_low like", likeValues, "scope_low");
            return (Criteria) this;
        }

        public Criteria andScopeHighLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("scope_high like", value, "scopeHigh");
            return (Criteria) this;
        }

        public Criteria andScopeHighLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("scope_high like", value, "scopeHigh");
            return (Criteria) this;
        }

        public Criteria andScopeHighLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("scope_high like", likeValues, "scope_high");
            return (Criteria) this;
        }

        public Criteria andScopeHighLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("scope_high like", likeValues, "scope_high");
            return (Criteria) this;
        }

        public Criteria andScopeHighLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("scope_high like", likeValues, "scope_high");
            return (Criteria) this;
        }

        public Criteria andNoteLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("note like", likeValues, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("note like", likeValues, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("note like", likeValues, "note");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("device_model_no like", value, "deviceModelNo");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("device_model_no like", value, "deviceModelNo");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("device_model_no like", likeValues, "device_model_no");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("device_model_no like", likeValues, "device_model_no");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNoLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("device_model_no like", likeValues, "device_model_no");
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
        private EquipmentDictExample example;

        protected Criteria(EquipmentDictExample example) {
            super();
            this.example = example;
        }

        public EquipmentDictExample example() {
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