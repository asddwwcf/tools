package com.kgb.manage.storage.mysql.domain.admin;

import com.bruce.tool.common.util.DateUtils;;
import com.bruce.tool.orm.mybatis.core.query.Example;
import com.bruce.tool.orm.mybatis.core.util.TableUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Date;;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("all")
public class CouponVerifyRecordExample implements Example<CouponVerifyRecord> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CouponVerifyRecordExample() {
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
        List line = (List) values;
        Object first = line.get(0);
        if(first instanceof String){
            return condition + " \"" + StringUtils.join((List)values,"\" or " + condition + " \"") + "\"";
        }
        if(first instanceof Date){
            List<String> valuestr = new ArrayList<>(line.size());
            for (Object date : line){
                valuestr.add(DateUtils.format((Date) date));
            }
            return condition + " \"" + StringUtils.join((List)valuestr,"\" or " + condition + " \"") + "\"";
        }
        return condition + " \"" + StringUtils.join((List)values," or " + condition + " ") + "";
    }

    private Object transfer(Object value) {
        if(value instanceof String){
            return "\""+value+"\"";
        }
        if(value instanceof Date){
            return "\""+ DateUtils.format((Date) value)+"\"";
        }
        if(value instanceof List){
            List line = (List) value;
            Object first = line.get(0);
            if(first instanceof String){
                return "\"" + StringUtils.join((List)value,"\",\"")+ "\"";
            }else if(value instanceof Date){
                List<String> valuestr = new ArrayList<>(line.size());
                for (Object date : line){
                    valuestr.add(DateUtils.format((Date) date));
                }
                return "\"" + StringUtils.join((List) valuestr, "\",\"")+ "\"";
            }else{
                return StringUtils.join((List)value,",");
            }
        }
        return value;
    }

    @Override
    public String getCondition() {
        String tableName = TableUtils.getTableName(CouponVerifyRecord.class);
        String columnMapping = TableUtils.getColumnMapping(CouponVerifyRecord.class);
        StringBuilder sql = new StringBuilder("select ").append(columnMapping).append(" from ").append(tableName);
        StringBuilder condition = new StringBuilder();
        for (CouponVerifyRecordExample.Criteria criteria : this.oredCriteria){
            List<CouponVerifyRecordExample.Criterion> criterions = criteria.getAllCriteria();
            if(null==criterions||criterions.isEmpty()){ continue; }
            int index = this.oredCriteria.indexOf(criteria);
            StringBuilder conditions = new StringBuilder();
            for (CouponVerifyRecordExample.Criterion criterion : criterions){
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
            if((value instanceof String) && ((String)value).trim().length() == 0){
                return;
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                return;
            }
            if((value1 instanceof String) && ((String)value1).trim().length() == 0){
                return;
            }
            if((value2 instanceof String) && ((String)value2).trim().length() == 0){
                return;
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andBrandIdentyBetween(Long value1, Long value2) {
            addCriterion("brand_identy between", value1, value2, "brandIdenty");
            return (Criteria) this;
        }

        public Criteria andBrandIdentyEqualTo(Long value) {
            addCriterion("brand_identy =", value, "brandIdenty");
            return (Criteria) this;
        }

        public Criteria andBrandIdentyGreaterThan(Long value) {
            addCriterion("brand_identy >", value, "brandIdenty");
            return (Criteria) this;
        }

        public Criteria andBrandIdentyGreaterThanOrEqualTo(Long value) {
            addCriterion("brand_identy >=", value, "brandIdenty");
            return (Criteria) this;
        }

        public Criteria andBrandIdentyIn(List<Long> values) {
            addCriterion("brand_identy in", values, "brandIdenty");
            return (Criteria) this;
        }

        public Criteria andBrandIdentyIsNotNull() {
            addCriterion("brand_identy is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdentyIsNull() {
            addCriterion("brand_identy is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdentyLessThan(Long value) {
            addCriterion("brand_identy <", value, "brandIdenty");
            return (Criteria) this;
        }

        public Criteria andBrandIdentyLessThanOrEqualTo(Long value) {
            addCriterion("brand_identy <=", value, "brandIdenty");
            return (Criteria) this;
        }

        public Criteria andBrandIdentyNotBetween(Long value1, Long value2) {
            addCriterion("brand_identy not between", value1, value2, "brandIdenty");
            return (Criteria) this;
        }

        public Criteria andBrandIdentyNotEqualTo(Long value) {
            addCriterion("brand_identy <>", value, "brandIdenty");
            return (Criteria) this;
        }

        public Criteria andBrandIdentyNotIn(List<Long> values) {
            addCriterion("brand_identy not in", values, "brandIdenty");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeBetween(Date value1, Date value2) {
            addCriterion("consume_time between", value1, value2, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeEqualTo(Date value) {
            addCriterion("consume_time =", value, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeGreaterThan(Date value) {
            addCriterion("consume_time >", value, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("consume_time >=", value, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeIn(List<Date> values) {
            addCriterion("consume_time in", values, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeIsNotNull() {
            addCriterion("consume_time is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeIsNull() {
            addCriterion("consume_time is null");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeLessThan(Date value) {
            addCriterion("consume_time <", value, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeLessThanOrEqualTo(Date value) {
            addCriterion("consume_time <=", value, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeNotBetween(Date value1, Date value2) {
            addCriterion("consume_time not between", value1, value2, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeNotEqualTo(Date value) {
            addCriterion("consume_time <>", value, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeNotIn(List<Date> values) {
            addCriterion("consume_time not in", values, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andCouponNameBetween(String value1, String value2) {
            addCriterion("coupon_name between", value1, value2, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameEqualTo(String value) {
            addCriterion("coupon_name =", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameGreaterThan(String value) {
            addCriterion("coupon_name >", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_name >=", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameIn(List<String> values) {
            addCriterion("coupon_name in", values, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameIsNotNull() {
            addCriterion("coupon_name is not null");
            return (Criteria) this;
        }

        public Criteria andCouponNameIsNull() {
            addCriterion("coupon_name is null");
            return (Criteria) this;
        }

        public Criteria andCouponNameLessThan(String value) {
            addCriterion("coupon_name <", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameLessThanOrEqualTo(String value) {
            addCriterion("coupon_name <=", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("coupon_name like", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("coupon_name like", likeValues, "coupon_name");
            return (Criteria) this;
        }

        public Criteria andCouponNameLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("coupon_name like", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("coupon_name like", likeValues, "coupon_name");
            return (Criteria) this;
        }

        public Criteria andCouponNameLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("coupon_name like", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("coupon_name like", likeValues, "coupon_name");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotBetween(String value1, String value2) {
            addCriterion("coupon_name not between", value1, value2, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotEqualTo(String value) {
            addCriterion("coupon_name <>", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotIn(List<String> values) {
            addCriterion("coupon_name not in", values, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotLike(String value) {
            addCriterion("coupon_name not like", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNoBetween(String value1, String value2) {
            addCriterion("coupon_no between", value1, value2, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoEqualTo(String value) {
            addCriterion("coupon_no =", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoGreaterThan(String value) {
            addCriterion("coupon_no >", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_no >=", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoIn(List<String> values) {
            addCriterion("coupon_no in", values, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoIsNotNull() {
            addCriterion("coupon_no is not null");
            return (Criteria) this;
        }

        public Criteria andCouponNoIsNull() {
            addCriterion("coupon_no is null");
            return (Criteria) this;
        }

        public Criteria andCouponNoLessThan(String value) {
            addCriterion("coupon_no <", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoLessThanOrEqualTo(String value) {
            addCriterion("coupon_no <=", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("coupon_no like", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("coupon_no like", likeValues, "coupon_no");
            return (Criteria) this;
        }

        public Criteria andCouponNoLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("coupon_no like", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("coupon_no like", likeValues, "coupon_no");
            return (Criteria) this;
        }

        public Criteria andCouponNoLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("coupon_no like", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("coupon_no like", likeValues, "coupon_no");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotBetween(String value1, String value2) {
            addCriterion("coupon_no not between", value1, value2, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotEqualTo(String value) {
            addCriterion("coupon_no <>", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotIn(List<String> values) {
            addCriterion("coupon_no not in", values, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotLike(String value) {
            addCriterion("coupon_no not like", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponTypeBetween(Integer value1, Integer value2) {
            addCriterion("coupon_type between", value1, value2, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeEqualTo(Integer value) {
            addCriterion("coupon_type =", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeGreaterThan(Integer value) {
            addCriterion("coupon_type >", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_type >=", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIn(List<Integer> values) {
            addCriterion("coupon_type in", values, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIsNotNull() {
            addCriterion("coupon_type is not null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIsNull() {
            addCriterion("coupon_type is null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLessThan(Integer value) {
            addCriterion("coupon_type <", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_type <=", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_type not between", value1, value2, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotEqualTo(Integer value) {
            addCriterion("coupon_type <>", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotIn(List<Integer> values) {
            addCriterion("coupon_type not in", values, "couponType");
            return (Criteria) this;
        }

        public Criteria andDeductPerThresholdBetween(Integer value1, Integer value2) {
            addCriterion("deduct_per_threshold between", value1, value2, "deductPerThreshold");
            return (Criteria) this;
        }

        public Criteria andDeductPerThresholdEqualTo(Integer value) {
            addCriterion("deduct_per_threshold =", value, "deductPerThreshold");
            return (Criteria) this;
        }

        public Criteria andDeductPerThresholdGreaterThan(Integer value) {
            addCriterion("deduct_per_threshold >", value, "deductPerThreshold");
            return (Criteria) this;
        }

        public Criteria andDeductPerThresholdGreaterThanOrEqualTo(Integer value) {
            addCriterion("deduct_per_threshold >=", value, "deductPerThreshold");
            return (Criteria) this;
        }

        public Criteria andDeductPerThresholdIn(List<Integer> values) {
            addCriterion("deduct_per_threshold in", values, "deductPerThreshold");
            return (Criteria) this;
        }

        public Criteria andDeductPerThresholdIsNotNull() {
            addCriterion("deduct_per_threshold is not null");
            return (Criteria) this;
        }

        public Criteria andDeductPerThresholdIsNull() {
            addCriterion("deduct_per_threshold is null");
            return (Criteria) this;
        }

        public Criteria andDeductPerThresholdLessThan(Integer value) {
            addCriterion("deduct_per_threshold <", value, "deductPerThreshold");
            return (Criteria) this;
        }

        public Criteria andDeductPerThresholdLessThanOrEqualTo(Integer value) {
            addCriterion("deduct_per_threshold <=", value, "deductPerThreshold");
            return (Criteria) this;
        }

        public Criteria andDeductPerThresholdNotBetween(Integer value1, Integer value2) {
            addCriterion("deduct_per_threshold not between", value1, value2, "deductPerThreshold");
            return (Criteria) this;
        }

        public Criteria andDeductPerThresholdNotEqualTo(Integer value) {
            addCriterion("deduct_per_threshold <>", value, "deductPerThreshold");
            return (Criteria) this;
        }

        public Criteria andDeductPerThresholdNotIn(List<Integer> values) {
            addCriterion("deduct_per_threshold not in", values, "deductPerThreshold");
            return (Criteria) this;
        }

        public Criteria andDeviceNoBetween(String value1, String value2) {
            addCriterion("device_no between", value1, value2, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoEqualTo(String value) {
            addCriterion("device_no =", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoGreaterThan(String value) {
            addCriterion("device_no >", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoGreaterThanOrEqualTo(String value) {
            addCriterion("device_no >=", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoIn(List<String> values) {
            addCriterion("device_no in", values, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoIsNotNull() {
            addCriterion("device_no is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNoIsNull() {
            addCriterion("device_no is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLessThan(String value) {
            addCriterion("device_no <", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLessThanOrEqualTo(String value) {
            addCriterion("device_no <=", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("device_no like", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("device_no like", likeValues, "device_no");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("device_no like", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("device_no like", likeValues, "device_no");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("device_no like", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("device_no like", likeValues, "device_no");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotBetween(String value1, String value2) {
            addCriterion("device_no not between", value1, value2, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotEqualTo(String value) {
            addCriterion("device_no <>", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotIn(List<String> values) {
            addCriterion("device_no not in", values, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotLike(String value) {
            addCriterion("device_no not like", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andFaceValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("face_value between", value1, value2, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueEqualTo(BigDecimal value) {
            addCriterion("face_value =", value, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueGreaterThan(BigDecimal value) {
            addCriterion("face_value >", value, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("face_value >=", value, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueIn(List<BigDecimal> values) {
            addCriterion("face_value in", values, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueIsNotNull() {
            addCriterion("face_value is not null");
            return (Criteria) this;
        }

        public Criteria andFaceValueIsNull() {
            addCriterion("face_value is null");
            return (Criteria) this;
        }

        public Criteria andFaceValueLessThan(BigDecimal value) {
            addCriterion("face_value <", value, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("face_value <=", value, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("face_value not between", value1, value2, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueNotEqualTo(BigDecimal value) {
            addCriterion("face_value <>", value, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueNotIn(List<BigDecimal> values) {
            addCriterion("face_value not in", values, "faceValue");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
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

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
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

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andLockTimeBetween(Date value1, Date value2) {
            addCriterion("lock_time between", value1, value2, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeEqualTo(Date value) {
            addCriterion("lock_time =", value, "lockTime");
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

        public Criteria andLockTimeIn(List<Date> values) {
            addCriterion("lock_time in", values, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeIsNotNull() {
            addCriterion("lock_time is not null");
            return (Criteria) this;
        }

        public Criteria andLockTimeIsNull() {
            addCriterion("lock_time is null");
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

        public Criteria andLockTimeNotBetween(Date value1, Date value2) {
            addCriterion("lock_time not between", value1, value2, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotEqualTo(Date value) {
            addCriterion("lock_time <>", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotIn(List<Date> values) {
            addCriterion("lock_time not in", values, "lockTime");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(String value1, String value2) {
            addCriterion("operator_id between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdEqualTo(String value) {
            addCriterion("operator_id =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(String value) {
            addCriterion("operator_id >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("operator_id >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<String> values) {
            addCriterion("operator_id in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNotNull() {
            addCriterion("operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNull() {
            addCriterion("operator_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(String value) {
            addCriterion("operator_id <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(String value) {
            addCriterion("operator_id <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("operator_id like", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("operator_id like", likeValues, "operator_id");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("operator_id like", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("operator_id like", likeValues, "operator_id");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("operator_id like", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("operator_id like", likeValues, "operator_id");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(String value1, String value2) {
            addCriterion("operator_id not between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(String value) {
            addCriterion("operator_id <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<String> values) {
            addCriterion("operator_id not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotLike(String value) {
            addCriterion("operator_id not like", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorNameBetween(String value1, String value2) {
            addCriterion("operator_name between", value1, value2, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameEqualTo(String value) {
            addCriterion("operator_name =", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameGreaterThan(String value) {
            addCriterion("operator_name >", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("operator_name >=", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIn(List<String> values) {
            addCriterion("operator_name in", values, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIsNotNull() {
            addCriterion("operator_name is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIsNull() {
            addCriterion("operator_name is null");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLessThan(String value) {
            addCriterion("operator_name <", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLessThanOrEqualTo(String value) {
            addCriterion("operator_name <=", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("operator_name like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("operator_name like", likeValues, "operator_name");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("operator_name like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("operator_name like", likeValues, "operator_name");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("operator_name like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("operator_name like", likeValues, "operator_name");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotBetween(String value1, String value2) {
            addCriterion("operator_name not between", value1, value2, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotEqualTo(String value) {
            addCriterion("operator_name <>", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotIn(List<String> values) {
            addCriterion("operator_name not in", values, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotLike(String value) {
            addCriterion("operator_name not like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeBetween(Integer value1, Integer value2) {
            addCriterion("operator_type between", value1, value2, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeEqualTo(Integer value) {
            addCriterion("operator_type =", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeGreaterThan(Integer value) {
            addCriterion("operator_type >", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("operator_type >=", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeIn(List<Integer> values) {
            addCriterion("operator_type in", values, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeIsNotNull() {
            addCriterion("operator_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeIsNull() {
            addCriterion("operator_type is null");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeLessThan(Integer value) {
            addCriterion("operator_type <", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeLessThanOrEqualTo(Integer value) {
            addCriterion("operator_type <=", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("operator_type not between", value1, value2, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotEqualTo(Integer value) {
            addCriterion("operator_type <>", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotIn(List<Integer> values) {
            addCriterion("operator_type not in", values, "operatorType");
            return (Criteria) this;
        }

        public Criteria andServerCreateTimeBetween(Date value1, Date value2) {
            addCriterion("server_create_time between", value1, value2, "serverCreateTime");
            return (Criteria) this;
        }

        public Criteria andServerCreateTimeEqualTo(Date value) {
            addCriterion("server_create_time =", value, "serverCreateTime");
            return (Criteria) this;
        }

        public Criteria andServerCreateTimeGreaterThan(Date value) {
            addCriterion("server_create_time >", value, "serverCreateTime");
            return (Criteria) this;
        }

        public Criteria andServerCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("server_create_time >=", value, "serverCreateTime");
            return (Criteria) this;
        }

        public Criteria andServerCreateTimeIn(List<Date> values) {
            addCriterion("server_create_time in", values, "serverCreateTime");
            return (Criteria) this;
        }

        public Criteria andServerCreateTimeIsNotNull() {
            addCriterion("server_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andServerCreateTimeIsNull() {
            addCriterion("server_create_time is null");
            return (Criteria) this;
        }

        public Criteria andServerCreateTimeLessThan(Date value) {
            addCriterion("server_create_time <", value, "serverCreateTime");
            return (Criteria) this;
        }

        public Criteria andServerCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("server_create_time <=", value, "serverCreateTime");
            return (Criteria) this;
        }

        public Criteria andServerCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("server_create_time not between", value1, value2, "serverCreateTime");
            return (Criteria) this;
        }

        public Criteria andServerCreateTimeNotEqualTo(Date value) {
            addCriterion("server_create_time <>", value, "serverCreateTime");
            return (Criteria) this;
        }

        public Criteria andServerCreateTimeNotIn(List<Date> values) {
            addCriterion("server_create_time not in", values, "serverCreateTime");
            return (Criteria) this;
        }

        public Criteria andServerUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("server_update_time between", value1, value2, "serverUpdateTime");
            return (Criteria) this;
        }

        public Criteria andServerUpdateTimeEqualTo(Date value) {
            addCriterion("server_update_time =", value, "serverUpdateTime");
            return (Criteria) this;
        }

        public Criteria andServerUpdateTimeGreaterThan(Date value) {
            addCriterion("server_update_time >", value, "serverUpdateTime");
            return (Criteria) this;
        }

        public Criteria andServerUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("server_update_time >=", value, "serverUpdateTime");
            return (Criteria) this;
        }

        public Criteria andServerUpdateTimeIn(List<Date> values) {
            addCriterion("server_update_time in", values, "serverUpdateTime");
            return (Criteria) this;
        }

        public Criteria andServerUpdateTimeIsNotNull() {
            addCriterion("server_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andServerUpdateTimeIsNull() {
            addCriterion("server_update_time is null");
            return (Criteria) this;
        }

        public Criteria andServerUpdateTimeLessThan(Date value) {
            addCriterion("server_update_time <", value, "serverUpdateTime");
            return (Criteria) this;
        }

        public Criteria andServerUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("server_update_time <=", value, "serverUpdateTime");
            return (Criteria) this;
        }

        public Criteria andServerUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("server_update_time not between", value1, value2, "serverUpdateTime");
            return (Criteria) this;
        }

        public Criteria andServerUpdateTimeNotEqualTo(Date value) {
            addCriterion("server_update_time <>", value, "serverUpdateTime");
            return (Criteria) this;
        }

        public Criteria andServerUpdateTimeNotIn(List<Date> values) {
            addCriterion("server_update_time not in", values, "serverUpdateTime");
            return (Criteria) this;
        }

        public Criteria andShopIdentyBetween(Long value1, Long value2) {
            addCriterion("shop_identy between", value1, value2, "shopIdenty");
            return (Criteria) this;
        }

        public Criteria andShopIdentyEqualTo(Long value) {
            addCriterion("shop_identy =", value, "shopIdenty");
            return (Criteria) this;
        }

        public Criteria andShopIdentyGreaterThan(Long value) {
            addCriterion("shop_identy >", value, "shopIdenty");
            return (Criteria) this;
        }

        public Criteria andShopIdentyGreaterThanOrEqualTo(Long value) {
            addCriterion("shop_identy >=", value, "shopIdenty");
            return (Criteria) this;
        }

        public Criteria andShopIdentyIn(List<Long> values) {
            addCriterion("shop_identy in", values, "shopIdenty");
            return (Criteria) this;
        }

        public Criteria andShopIdentyIsNotNull() {
            addCriterion("shop_identy is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdentyIsNull() {
            addCriterion("shop_identy is null");
            return (Criteria) this;
        }

        public Criteria andShopIdentyLessThan(Long value) {
            addCriterion("shop_identy <", value, "shopIdenty");
            return (Criteria) this;
        }

        public Criteria andShopIdentyLessThanOrEqualTo(Long value) {
            addCriterion("shop_identy <=", value, "shopIdenty");
            return (Criteria) this;
        }

        public Criteria andShopIdentyNotBetween(Long value1, Long value2) {
            addCriterion("shop_identy not between", value1, value2, "shopIdenty");
            return (Criteria) this;
        }

        public Criteria andShopIdentyNotEqualTo(Long value) {
            addCriterion("shop_identy <>", value, "shopIdenty");
            return (Criteria) this;
        }

        public Criteria andShopIdentyNotIn(List<Long> values) {
            addCriterion("shop_identy not in", values, "shopIdenty");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(Integer value1, Integer value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Integer value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Integer value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Integer> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(Integer value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Integer value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Integer value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Integer> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusDescBetween(String value1, String value2) {
            addCriterion("status_desc between", value1, value2, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescEqualTo(String value) {
            addCriterion("status_desc =", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescGreaterThan(String value) {
            addCriterion("status_desc >", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescGreaterThanOrEqualTo(String value) {
            addCriterion("status_desc >=", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescIn(List<String> values) {
            addCriterion("status_desc in", values, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescIsNotNull() {
            addCriterion("status_desc is not null");
            return (Criteria) this;
        }

        public Criteria andStatusDescIsNull() {
            addCriterion("status_desc is null");
            return (Criteria) this;
        }

        public Criteria andStatusDescLessThan(String value) {
            addCriterion("status_desc <", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescLessThanOrEqualTo(String value) {
            addCriterion("status_desc <=", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("status_desc like", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("status_desc like", likeValues, "status_desc");
            return (Criteria) this;
        }

        public Criteria andStatusDescLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("status_desc like", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("status_desc like", likeValues, "status_desc");
            return (Criteria) this;
        }

        public Criteria andStatusDescLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("status_desc like", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("status_desc like", likeValues, "status_desc");
            return (Criteria) this;
        }

        public Criteria andStatusDescNotBetween(String value1, String value2) {
            addCriterion("status_desc not between", value1, value2, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescNotEqualTo(String value) {
            addCriterion("status_desc <>", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescNotIn(List<String> values) {
            addCriterion("status_desc not in", values, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescNotLike(String value) {
            addCriterion("status_desc not like", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andThresholdBetween(Integer value1, Integer value2) {
            addCriterion("threshold between", value1, value2, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdEqualTo(Integer value) {
            addCriterion("threshold =", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdGreaterThan(Integer value) {
            addCriterion("threshold >", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdGreaterThanOrEqualTo(Integer value) {
            addCriterion("threshold >=", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdIn(List<Integer> values) {
            addCriterion("threshold in", values, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdIsNotNull() {
            addCriterion("threshold is not null");
            return (Criteria) this;
        }

        public Criteria andThresholdIsNull() {
            addCriterion("threshold is null");
            return (Criteria) this;
        }

        public Criteria andThresholdLessThan(Integer value) {
            addCriterion("threshold <", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdLessThanOrEqualTo(Integer value) {
            addCriterion("threshold <=", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdNotBetween(Integer value1, Integer value2) {
            addCriterion("threshold not between", value1, value2, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdNotEqualTo(Integer value) {
            addCriterion("threshold <>", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdNotIn(List<Integer> values) {
            addCriterion("threshold not in", values, "threshold");
            return (Criteria) this;
        }

        public Criteria andTradeIdBetween(Long value1, Long value2) {
            addCriterion("trade_id between", value1, value2, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdEqualTo(Long value) {
            addCriterion("trade_id =", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdGreaterThan(Long value) {
            addCriterion("trade_id >", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("trade_id >=", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdIn(List<Long> values) {
            addCriterion("trade_id in", values, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdIsNotNull() {
            addCriterion("trade_id is not null");
            return (Criteria) this;
        }

        public Criteria andTradeIdIsNull() {
            addCriterion("trade_id is null");
            return (Criteria) this;
        }

        public Criteria andTradeIdLessThan(Long value) {
            addCriterion("trade_id <", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLessThanOrEqualTo(Long value) {
            addCriterion("trade_id <=", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotBetween(Long value1, Long value2) {
            addCriterion("trade_id not between", value1, value2, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotEqualTo(Long value) {
            addCriterion("trade_id <>", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotIn(List<Long> values) {
            addCriterion("trade_id not in", values, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeNoBetween(String value1, String value2) {
            addCriterion("trade_no between", value1, value2, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoEqualTo(String value) {
            addCriterion("trade_no =", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoGreaterThan(String value) {
            addCriterion("trade_no >", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoGreaterThanOrEqualTo(String value) {
            addCriterion("trade_no >=", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoIn(List<String> values) {
            addCriterion("trade_no in", values, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoIsNotNull() {
            addCriterion("trade_no is not null");
            return (Criteria) this;
        }

        public Criteria andTradeNoIsNull() {
            addCriterion("trade_no is null");
            return (Criteria) this;
        }

        public Criteria andTradeNoLessThan(String value) {
            addCriterion("trade_no <", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLessThanOrEqualTo(String value) {
            addCriterion("trade_no <=", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("trade_no like", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("trade_no like", likeValues, "trade_no");
            return (Criteria) this;
        }

        public Criteria andTradeNoLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("trade_no like", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("trade_no like", likeValues, "trade_no");
            return (Criteria) this;
        }

        public Criteria andTradeNoLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("trade_no like", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("trade_no like", likeValues, "trade_no");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotBetween(String value1, String value2) {
            addCriterion("trade_no not between", value1, value2, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotEqualTo(String value) {
            addCriterion("trade_no <>", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotIn(List<String> values) {
            addCriterion("trade_no not in", values, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotLike(String value) {
            addCriterion("trade_no not like", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("uuid like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("uuid like", likeValues, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("uuid like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("uuid like", likeValues, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("uuid like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("uuid like", likeValues, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("uuid not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andValidityEndTimeBetween(Date value1, Date value2) {
            addCriterion("validity_end_time between", value1, value2, "validityEndTime");
            return (Criteria) this;
        }

        public Criteria andValidityEndTimeEqualTo(Date value) {
            addCriterion("validity_end_time =", value, "validityEndTime");
            return (Criteria) this;
        }

        public Criteria andValidityEndTimeGreaterThan(Date value) {
            addCriterion("validity_end_time >", value, "validityEndTime");
            return (Criteria) this;
        }

        public Criteria andValidityEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("validity_end_time >=", value, "validityEndTime");
            return (Criteria) this;
        }

        public Criteria andValidityEndTimeIn(List<Date> values) {
            addCriterion("validity_end_time in", values, "validityEndTime");
            return (Criteria) this;
        }

        public Criteria andValidityEndTimeIsNotNull() {
            addCriterion("validity_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andValidityEndTimeIsNull() {
            addCriterion("validity_end_time is null");
            return (Criteria) this;
        }

        public Criteria andValidityEndTimeLessThan(Date value) {
            addCriterion("validity_end_time <", value, "validityEndTime");
            return (Criteria) this;
        }

        public Criteria andValidityEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("validity_end_time <=", value, "validityEndTime");
            return (Criteria) this;
        }

        public Criteria andValidityEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("validity_end_time not between", value1, value2, "validityEndTime");
            return (Criteria) this;
        }

        public Criteria andValidityEndTimeNotEqualTo(Date value) {
            addCriterion("validity_end_time <>", value, "validityEndTime");
            return (Criteria) this;
        }

        public Criteria andValidityEndTimeNotIn(List<Date> values) {
            addCriterion("validity_end_time not in", values, "validityEndTime");
            return (Criteria) this;
        }

        public Criteria andValidityStartTimeBetween(Date value1, Date value2) {
            addCriterion("validity_start_time between", value1, value2, "validityStartTime");
            return (Criteria) this;
        }

        public Criteria andValidityStartTimeEqualTo(Date value) {
            addCriterion("validity_start_time =", value, "validityStartTime");
            return (Criteria) this;
        }

        public Criteria andValidityStartTimeGreaterThan(Date value) {
            addCriterion("validity_start_time >", value, "validityStartTime");
            return (Criteria) this;
        }

        public Criteria andValidityStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("validity_start_time >=", value, "validityStartTime");
            return (Criteria) this;
        }

        public Criteria andValidityStartTimeIn(List<Date> values) {
            addCriterion("validity_start_time in", values, "validityStartTime");
            return (Criteria) this;
        }

        public Criteria andValidityStartTimeIsNotNull() {
            addCriterion("validity_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andValidityStartTimeIsNull() {
            addCriterion("validity_start_time is null");
            return (Criteria) this;
        }

        public Criteria andValidityStartTimeLessThan(Date value) {
            addCriterion("validity_start_time <", value, "validityStartTime");
            return (Criteria) this;
        }

        public Criteria andValidityStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("validity_start_time <=", value, "validityStartTime");
            return (Criteria) this;
        }

        public Criteria andValidityStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("validity_start_time not between", value1, value2, "validityStartTime");
            return (Criteria) this;
        }

        public Criteria andValidityStartTimeNotEqualTo(Date value) {
            addCriterion("validity_start_time <>", value, "validityStartTime");
            return (Criteria) this;
        }

        public Criteria andValidityStartTimeNotIn(List<Date> values) {
            addCriterion("validity_start_time not in", values, "validityStartTime");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusBetween(Integer value1, Integer value2) {
            addCriterion("verify_status between", value1, value2, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescBetween(String value1, String value2) {
            addCriterion("verify_status_desc between", value1, value2, "verifyStatusDesc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescEqualTo(String value) {
            addCriterion("verify_status_desc =", value, "verifyStatusDesc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescGreaterThan(String value) {
            addCriterion("verify_status_desc >", value, "verifyStatusDesc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescGreaterThanOrEqualTo(String value) {
            addCriterion("verify_status_desc >=", value, "verifyStatusDesc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescIn(List<String> values) {
            addCriterion("verify_status_desc in", values, "verifyStatusDesc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescIsNotNull() {
            addCriterion("verify_status_desc is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescIsNull() {
            addCriterion("verify_status_desc is null");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescLessThan(String value) {
            addCriterion("verify_status_desc <", value, "verifyStatusDesc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescLessThanOrEqualTo(String value) {
            addCriterion("verify_status_desc <=", value, "verifyStatusDesc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("verify_status_desc like", value, "verifyStatusDesc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("verify_status_desc like", likeValues, "verify_status_desc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("verify_status_desc like", value, "verifyStatusDesc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("verify_status_desc like", likeValues, "verify_status_desc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("verify_status_desc like", value, "verifyStatusDesc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("verify_status_desc like", likeValues, "verify_status_desc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescNotBetween(String value1, String value2) {
            addCriterion("verify_status_desc not between", value1, value2, "verifyStatusDesc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescNotEqualTo(String value) {
            addCriterion("verify_status_desc <>", value, "verifyStatusDesc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescNotIn(List<String> values) {
            addCriterion("verify_status_desc not in", values, "verifyStatusDesc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusDescNotLike(String value) {
            addCriterion("verify_status_desc not like", value, "verifyStatusDesc");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusEqualTo(Integer value) {
            addCriterion("verify_status =", value, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusGreaterThan(Integer value) {
            addCriterion("verify_status >", value, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("verify_status >=", value, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusIn(List<Integer> values) {
            addCriterion("verify_status in", values, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusIsNotNull() {
            addCriterion("verify_status is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusIsNull() {
            addCriterion("verify_status is null");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusLessThan(Integer value) {
            addCriterion("verify_status <", value, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusLessThanOrEqualTo(Integer value) {
            addCriterion("verify_status <=", value, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("verify_status not between", value1, value2, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusNotEqualTo(Integer value) {
            addCriterion("verify_status <>", value, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusNotIn(List<Integer> values) {
            addCriterion("verify_status not in", values, "verifyStatus");
            return (Criteria) this;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        public boolean isValid() {
            return criteria.size() > 0;
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
        private CouponVerifyRecordExample example;

        protected Criteria(CouponVerifyRecordExample example) {
            super();
            this.example = example;
        }

        public CouponVerifyRecordExample example() {
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