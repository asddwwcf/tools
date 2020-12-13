package com.mine.tool.orm.mybatis.generator.domain;

import com.bruce.tool.orm.mybatis.core.query.Example;
import com.bruce.tool.orm.mybatis.core.util.TableUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("all")
public class RegionExample implements Example<Region> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RegionExample() {
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
        String tableName = TableUtils.getTableName(Region.class);
        String columnMapping = TableUtils.getColumnMapping(Region.class);
        StringBuilder sql = new StringBuilder("select ").append(columnMapping).append(" from ").append(tableName);
        StringBuilder condition = new StringBuilder();
        for (RegionExample.Criteria criteria : this.oredCriteria){
            List<RegionExample.Criterion> criterions = criteria.getAllCriteria();
            if(null==criterions||criterions.isEmpty()){ continue; }
            int index = this.oredCriteria.indexOf(criteria);
            StringBuilder conditions = new StringBuilder();
            for (RegionExample.Criterion criterion : criterions){
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

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andIsDirectIsNull() {
            addCriterion("is_direct is null");
            return (Criteria) this;
        }

        public Criteria andIsDirectIsNotNull() {
            addCriterion("is_direct is not null");
            return (Criteria) this;
        }

        public Criteria andIsDirectEqualTo(Integer value) {
            addCriterion("is_direct =", value, "isDirect");
            return (Criteria) this;
        }

        public Criteria andIsDirectNotEqualTo(Integer value) {
            addCriterion("is_direct <>", value, "isDirect");
            return (Criteria) this;
        }

        public Criteria andIsDirectGreaterThan(Integer value) {
            addCriterion("is_direct >", value, "isDirect");
            return (Criteria) this;
        }

        public Criteria andIsDirectGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_direct >=", value, "isDirect");
            return (Criteria) this;
        }

        public Criteria andIsDirectLessThan(Integer value) {
            addCriterion("is_direct <", value, "isDirect");
            return (Criteria) this;
        }

        public Criteria andIsDirectLessThanOrEqualTo(Integer value) {
            addCriterion("is_direct <=", value, "isDirect");
            return (Criteria) this;
        }

        public Criteria andIsDirectIn(List<Integer> values) {
            addCriterion("is_direct in", values, "isDirect");
            return (Criteria) this;
        }

        public Criteria andIsDirectNotIn(List<Integer> values) {
            addCriterion("is_direct not in", values, "isDirect");
            return (Criteria) this;
        }

        public Criteria andIsDirectBetween(Integer value1, Integer value2) {
            addCriterion("is_direct between", value1, value2, "isDirect");
            return (Criteria) this;
        }

        public Criteria andIsDirectNotBetween(Integer value1, Integer value2) {
            addCriterion("is_direct not between", value1, value2, "isDirect");
            return (Criteria) this;
        }

        public Criteria andPcodeIsNull() {
            addCriterion("pcode is null");
            return (Criteria) this;
        }

        public Criteria andPcodeIsNotNull() {
            addCriterion("pcode is not null");
            return (Criteria) this;
        }

        public Criteria andPcodeEqualTo(String value) {
            addCriterion("pcode =", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeNotEqualTo(String value) {
            addCriterion("pcode <>", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeGreaterThan(String value) {
            addCriterion("pcode >", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeGreaterThanOrEqualTo(String value) {
            addCriterion("pcode >=", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeLessThan(String value) {
            addCriterion("pcode <", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeLessThanOrEqualTo(String value) {
            addCriterion("pcode <=", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeLike(String value) {
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("pcode like", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeNotLike(String value) {
            addCriterion("pcode not like", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeIn(List<String> values) {
            addCriterion("pcode in", values, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeNotIn(List<String> values) {
            addCriterion("pcode not in", values, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeBetween(String value1, String value2) {
            addCriterion("pcode between", value1, value2, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeNotBetween(String value1, String value2) {
            addCriterion("pcode not between", value1, value2, "pcode");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
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

        public Criteria andHrefIsNull() {
            addCriterion("href is null");
            return (Criteria) this;
        }

        public Criteria andHrefIsNotNull() {
            addCriterion("href is not null");
            return (Criteria) this;
        }

        public Criteria andHrefEqualTo(String value) {
            addCriterion("href =", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotEqualTo(String value) {
            addCriterion("href <>", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefGreaterThan(String value) {
            addCriterion("href >", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefGreaterThanOrEqualTo(String value) {
            addCriterion("href >=", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLessThan(String value) {
            addCriterion("href <", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLessThanOrEqualTo(String value) {
            addCriterion("href <=", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLike(String value) {
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("href like", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotLike(String value) {
            addCriterion("href not like", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefIn(List<String> values) {
            addCriterion("href in", values, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotIn(List<String> values) {
            addCriterion("href not in", values, "href");
            return (Criteria) this;
        }

        public Criteria andHrefBetween(String value1, String value2) {
            addCriterion("href between", value1, value2, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotBetween(String value1, String value2) {
            addCriterion("href not between", value1, value2, "href");
            return (Criteria) this;
        }

        public Criteria andRefererIsNull() {
            addCriterion("referer is null");
            return (Criteria) this;
        }

        public Criteria andRefererIsNotNull() {
            addCriterion("referer is not null");
            return (Criteria) this;
        }

        public Criteria andRefererEqualTo(String value) {
            addCriterion("referer =", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererNotEqualTo(String value) {
            addCriterion("referer <>", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererGreaterThan(String value) {
            addCriterion("referer >", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererGreaterThanOrEqualTo(String value) {
            addCriterion("referer >=", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererLessThan(String value) {
            addCriterion("referer <", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererLessThanOrEqualTo(String value) {
            addCriterion("referer <=", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererLike(String value) {
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("referer like", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererNotLike(String value) {
            addCriterion("referer not like", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererIn(List<String> values) {
            addCriterion("referer in", values, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererNotIn(List<String> values) {
            addCriterion("referer not in", values, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererBetween(String value1, String value2) {
            addCriterion("referer between", value1, value2, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererNotBetween(String value1, String value2) {
            addCriterion("referer not between", value1, value2, "referer");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(Double value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(Double value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(Double value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(Double value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<Double> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<Double> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(Double value1, Double value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Double value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Double value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Double value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Double value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Double> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Double> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Double value1, Double value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andPcodeLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("pcode like", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("pcode like", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("pcode like", likeValues, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("pcode like", likeValues, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("pcode like", likeValues, "pcode");
            return (Criteria) this;
        }

        public Criteria andCodeLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("code like", likeValues, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("code like", likeValues, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("code like", likeValues, "code");
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

        public Criteria andHrefLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("href like", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("href like", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("href like", likeValues, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("href like", likeValues, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("href like", likeValues, "href");
            return (Criteria) this;
        }

        public Criteria andRefererLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("referer like", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("referer like", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("referer like", likeValues, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("referer like", likeValues, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("referer like", likeValues, "referer");
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
        private RegionExample example;

        protected Criteria(RegionExample example) {
            super();
            this.example = example;
        }

        public RegionExample example() {
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