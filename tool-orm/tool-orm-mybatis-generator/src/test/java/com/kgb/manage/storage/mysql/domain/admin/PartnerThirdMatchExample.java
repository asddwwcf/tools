package com.kgb.manage.storage.mysql.domain.admin;

import com.bruce.tool.common.util.DateUtils;;
import com.bruce.tool.orm.mybatis.core.query.Example;
import com.bruce.tool.orm.mybatis.core.util.TableUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Date;;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("all")
public class PartnerThirdMatchExample implements Example<PartnerThirdMatch> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PartnerThirdMatchExample() {
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
        String tableName = TableUtils.getTableName(PartnerThirdMatch.class);
        String columnMapping = TableUtils.getColumnMapping(PartnerThirdMatch.class);
        StringBuilder sql = new StringBuilder("select ").append(columnMapping).append(" from ").append(tableName);
        StringBuilder condition = new StringBuilder();
        for (PartnerThirdMatchExample.Criteria criteria : this.oredCriteria){
            List<PartnerThirdMatchExample.Criterion> criterions = criteria.getAllCriteria();
            if(null==criterions||criterions.isEmpty()){ continue; }
            int index = this.oredCriteria.indexOf(criteria);
            StringBuilder conditions = new StringBuilder();
            for (PartnerThirdMatchExample.Criterion criterion : criterions){
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

        public Criteria andDeliveryPlatformBetween(Integer value1, Integer value2) {
            addCriterion("delivery_platform between", value1, value2, "deliveryPlatform");
            return (Criteria) this;
        }

        public Criteria andDeliveryPlatformEqualTo(Integer value) {
            addCriterion("delivery_platform =", value, "deliveryPlatform");
            return (Criteria) this;
        }

        public Criteria andDeliveryPlatformGreaterThan(Integer value) {
            addCriterion("delivery_platform >", value, "deliveryPlatform");
            return (Criteria) this;
        }

        public Criteria andDeliveryPlatformGreaterThanOrEqualTo(Integer value) {
            addCriterion("delivery_platform >=", value, "deliveryPlatform");
            return (Criteria) this;
        }

        public Criteria andDeliveryPlatformIn(List<Integer> values) {
            addCriterion("delivery_platform in", values, "deliveryPlatform");
            return (Criteria) this;
        }

        public Criteria andDeliveryPlatformIsNotNull() {
            addCriterion("delivery_platform is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryPlatformIsNull() {
            addCriterion("delivery_platform is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryPlatformLessThan(Integer value) {
            addCriterion("delivery_platform <", value, "deliveryPlatform");
            return (Criteria) this;
        }

        public Criteria andDeliveryPlatformLessThanOrEqualTo(Integer value) {
            addCriterion("delivery_platform <=", value, "deliveryPlatform");
            return (Criteria) this;
        }

        public Criteria andDeliveryPlatformNotBetween(Integer value1, Integer value2) {
            addCriterion("delivery_platform not between", value1, value2, "deliveryPlatform");
            return (Criteria) this;
        }

        public Criteria andDeliveryPlatformNotEqualTo(Integer value) {
            addCriterion("delivery_platform <>", value, "deliveryPlatform");
            return (Criteria) this;
        }

        public Criteria andDeliveryPlatformNotIn(List<Integer> values) {
            addCriterion("delivery_platform not in", values, "deliveryPlatform");
            return (Criteria) this;
        }

        public Criteria andExt1Between(String value1, String value2) {
            addCriterion("ext1 between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1EqualTo(String value) {
            addCriterion("ext1 =", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThan(String value) {
            addCriterion("ext1 >", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThanOrEqualTo(String value) {
            addCriterion("ext1 >=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1In(List<String> values) {
            addCriterion("ext1 in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1IsNotNull() {
            addCriterion("ext1 is not null");
            return (Criteria) this;
        }

        public Criteria andExt1IsNull() {
            addCriterion("ext1 is null");
            return (Criteria) this;
        }

        public Criteria andExt1LessThan(String value) {
            addCriterion("ext1 <", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThanOrEqualTo(String value) {
            addCriterion("ext1 <=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Like(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("ext1 like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Like(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("ext_1 like", likeValues, "ext_1");
            return (Criteria) this;
        }

        public Criteria andExt1LikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("ext1 like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("ext_1 like", likeValues, "ext_1");
            return (Criteria) this;
        }

        public Criteria andExt1LikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("ext1 like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("ext_1 like", likeValues, "ext_1");
            return (Criteria) this;
        }

        public Criteria andExt1NotBetween(String value1, String value2) {
            addCriterion("ext1 not between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotEqualTo(String value) {
            addCriterion("ext1 <>", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotIn(List<String> values) {
            addCriterion("ext1 not in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotLike(String value) {
            addCriterion("ext1 not like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt2Between(String value1, String value2) {
            addCriterion("ext2 between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2EqualTo(String value) {
            addCriterion("ext2 =", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThan(String value) {
            addCriterion("ext2 >", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThanOrEqualTo(String value) {
            addCriterion("ext2 >=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2In(List<String> values) {
            addCriterion("ext2 in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2IsNotNull() {
            addCriterion("ext2 is not null");
            return (Criteria) this;
        }

        public Criteria andExt2IsNull() {
            addCriterion("ext2 is null");
            return (Criteria) this;
        }

        public Criteria andExt2LessThan(String value) {
            addCriterion("ext2 <", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThanOrEqualTo(String value) {
            addCriterion("ext2 <=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Like(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("ext2 like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Like(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("ext_2 like", likeValues, "ext_2");
            return (Criteria) this;
        }

        public Criteria andExt2LikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("ext2 like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("ext_2 like", likeValues, "ext_2");
            return (Criteria) this;
        }

        public Criteria andExt2LikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("ext2 like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("ext_2 like", likeValues, "ext_2");
            return (Criteria) this;
        }

        public Criteria andExt2NotBetween(String value1, String value2) {
            addCriterion("ext2 not between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotEqualTo(String value) {
            addCriterion("ext2 <>", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotIn(List<String> values) {
            addCriterion("ext2 not in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotLike(String value) {
            addCriterion("ext2 not like", value, "ext2");
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

        public Criteria andOrderModeBetween(Integer value1, Integer value2) {
            addCriterion("order_mode between", value1, value2, "orderMode");
            return (Criteria) this;
        }

        public Criteria andOrderModeEqualTo(Integer value) {
            addCriterion("order_mode =", value, "orderMode");
            return (Criteria) this;
        }

        public Criteria andOrderModeGreaterThan(Integer value) {
            addCriterion("order_mode >", value, "orderMode");
            return (Criteria) this;
        }

        public Criteria andOrderModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_mode >=", value, "orderMode");
            return (Criteria) this;
        }

        public Criteria andOrderModeIn(List<Integer> values) {
            addCriterion("order_mode in", values, "orderMode");
            return (Criteria) this;
        }

        public Criteria andOrderModeIsNotNull() {
            addCriterion("order_mode is not null");
            return (Criteria) this;
        }

        public Criteria andOrderModeIsNull() {
            addCriterion("order_mode is null");
            return (Criteria) this;
        }

        public Criteria andOrderModeLessThan(Integer value) {
            addCriterion("order_mode <", value, "orderMode");
            return (Criteria) this;
        }

        public Criteria andOrderModeLessThanOrEqualTo(Integer value) {
            addCriterion("order_mode <=", value, "orderMode");
            return (Criteria) this;
        }

        public Criteria andOrderModeNotBetween(Integer value1, Integer value2) {
            addCriterion("order_mode not between", value1, value2, "orderMode");
            return (Criteria) this;
        }

        public Criteria andOrderModeNotEqualTo(Integer value) {
            addCriterion("order_mode <>", value, "orderMode");
            return (Criteria) this;
        }

        public Criteria andOrderModeNotIn(List<Integer> values) {
            addCriterion("order_mode not in", values, "orderMode");
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

        public Criteria andSourceBetween(Short value1, Short value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceChildBetween(Short value1, Short value2) {
            addCriterion("source_child between", value1, value2, "sourceChild");
            return (Criteria) this;
        }

        public Criteria andSourceChildEqualTo(Short value) {
            addCriterion("source_child =", value, "sourceChild");
            return (Criteria) this;
        }

        public Criteria andSourceChildGreaterThan(Short value) {
            addCriterion("source_child >", value, "sourceChild");
            return (Criteria) this;
        }

        public Criteria andSourceChildGreaterThanOrEqualTo(Short value) {
            addCriterion("source_child >=", value, "sourceChild");
            return (Criteria) this;
        }

        public Criteria andSourceChildIn(List<Short> values) {
            addCriterion("source_child in", values, "sourceChild");
            return (Criteria) this;
        }

        public Criteria andSourceChildIsNotNull() {
            addCriterion("source_child is not null");
            return (Criteria) this;
        }

        public Criteria andSourceChildIsNull() {
            addCriterion("source_child is null");
            return (Criteria) this;
        }

        public Criteria andSourceChildLessThan(Short value) {
            addCriterion("source_child <", value, "sourceChild");
            return (Criteria) this;
        }

        public Criteria andSourceChildLessThanOrEqualTo(Short value) {
            addCriterion("source_child <=", value, "sourceChild");
            return (Criteria) this;
        }

        public Criteria andSourceChildNotBetween(Short value1, Short value2) {
            addCriterion("source_child not between", value1, value2, "sourceChild");
            return (Criteria) this;
        }

        public Criteria andSourceChildNotEqualTo(Short value) {
            addCriterion("source_child <>", value, "sourceChild");
            return (Criteria) this;
        }

        public Criteria andSourceChildNotIn(List<Short> values) {
            addCriterion("source_child not in", values, "sourceChild");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Short value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Short value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Short value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Short> values) {
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

        public Criteria andSourceLessThan(Short value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Short value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Short value1, Short value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Short value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Short> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
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

        public Criteria andStatusNameBetween(String value1, String value2) {
            addCriterion("status_name between", value1, value2, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameEqualTo(String value) {
            addCriterion("status_name =", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameGreaterThan(String value) {
            addCriterion("status_name >", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("status_name >=", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameIn(List<String> values) {
            addCriterion("status_name in", values, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameIsNotNull() {
            addCriterion("status_name is not null");
            return (Criteria) this;
        }

        public Criteria andStatusNameIsNull() {
            addCriterion("status_name is null");
            return (Criteria) this;
        }

        public Criteria andStatusNameLessThan(String value) {
            addCriterion("status_name <", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLessThanOrEqualTo(String value) {
            addCriterion("status_name <=", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("status_name like", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("status_name like", likeValues, "status_name");
            return (Criteria) this;
        }

        public Criteria andStatusNameLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("status_name like", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("status_name like", likeValues, "status_name");
            return (Criteria) this;
        }

        public Criteria andStatusNameLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("status_name like", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("status_name like", likeValues, "status_name");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotBetween(String value1, String value2) {
            addCriterion("status_name not between", value1, value2, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotEqualTo(String value) {
            addCriterion("status_name <>", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotIn(List<String> values) {
            addCriterion("status_name not in", values, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotLike(String value) {
            addCriterion("status_name not like", value, "statusName");
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

        public Criteria andThirdAccountBetween(String value1, String value2) {
            addCriterion("third_account between", value1, value2, "thirdAccount");
            return (Criteria) this;
        }

        public Criteria andThirdAccountEqualTo(String value) {
            addCriterion("third_account =", value, "thirdAccount");
            return (Criteria) this;
        }

        public Criteria andThirdAccountGreaterThan(String value) {
            addCriterion("third_account >", value, "thirdAccount");
            return (Criteria) this;
        }

        public Criteria andThirdAccountGreaterThanOrEqualTo(String value) {
            addCriterion("third_account >=", value, "thirdAccount");
            return (Criteria) this;
        }

        public Criteria andThirdAccountIn(List<String> values) {
            addCriterion("third_account in", values, "thirdAccount");
            return (Criteria) this;
        }

        public Criteria andThirdAccountIsNotNull() {
            addCriterion("third_account is not null");
            return (Criteria) this;
        }

        public Criteria andThirdAccountIsNull() {
            addCriterion("third_account is null");
            return (Criteria) this;
        }

        public Criteria andThirdAccountLessThan(String value) {
            addCriterion("third_account <", value, "thirdAccount");
            return (Criteria) this;
        }

        public Criteria andThirdAccountLessThanOrEqualTo(String value) {
            addCriterion("third_account <=", value, "thirdAccount");
            return (Criteria) this;
        }

        public Criteria andThirdAccountLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("third_account like", value, "thirdAccount");
            return (Criteria) this;
        }

        public Criteria andThirdAccountLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("third_account like", likeValues, "third_account");
            return (Criteria) this;
        }

        public Criteria andThirdAccountLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("third_account like", value, "thirdAccount");
            return (Criteria) this;
        }

        public Criteria andThirdAccountLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("third_account like", likeValues, "third_account");
            return (Criteria) this;
        }

        public Criteria andThirdAccountLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("third_account like", value, "thirdAccount");
            return (Criteria) this;
        }

        public Criteria andThirdAccountLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("third_account like", likeValues, "third_account");
            return (Criteria) this;
        }

        public Criteria andThirdAccountNotBetween(String value1, String value2) {
            addCriterion("third_account not between", value1, value2, "thirdAccount");
            return (Criteria) this;
        }

        public Criteria andThirdAccountNotEqualTo(String value) {
            addCriterion("third_account <>", value, "thirdAccount");
            return (Criteria) this;
        }

        public Criteria andThirdAccountNotIn(List<String> values) {
            addCriterion("third_account not in", values, "thirdAccount");
            return (Criteria) this;
        }

        public Criteria andThirdAccountNotLike(String value) {
            addCriterion("third_account not like", value, "thirdAccount");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyBetween(String value1, String value2) {
            addCriterion("third_brand_identy between", value1, value2, "thirdBrandIdenty");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyEqualTo(String value) {
            addCriterion("third_brand_identy =", value, "thirdBrandIdenty");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyGreaterThan(String value) {
            addCriterion("third_brand_identy >", value, "thirdBrandIdenty");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyGreaterThanOrEqualTo(String value) {
            addCriterion("third_brand_identy >=", value, "thirdBrandIdenty");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyIn(List<String> values) {
            addCriterion("third_brand_identy in", values, "thirdBrandIdenty");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyIsNotNull() {
            addCriterion("third_brand_identy is not null");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyIsNull() {
            addCriterion("third_brand_identy is null");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyLessThan(String value) {
            addCriterion("third_brand_identy <", value, "thirdBrandIdenty");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyLessThanOrEqualTo(String value) {
            addCriterion("third_brand_identy <=", value, "thirdBrandIdenty");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("third_brand_identy like", value, "thirdBrandIdenty");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("third_br_identy like", likeValues, "third_br_identy");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("third_brand_identy like", value, "thirdBrandIdenty");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("third_br_identy like", likeValues, "third_br_identy");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("third_brand_identy like", value, "thirdBrandIdenty");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("third_br_identy like", likeValues, "third_br_identy");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyNotBetween(String value1, String value2) {
            addCriterion("third_brand_identy not between", value1, value2, "thirdBrandIdenty");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyNotEqualTo(String value) {
            addCriterion("third_brand_identy <>", value, "thirdBrandIdenty");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyNotIn(List<String> values) {
            addCriterion("third_brand_identy not in", values, "thirdBrandIdenty");
            return (Criteria) this;
        }

        public Criteria andThirdBrandIdentyNotLike(String value) {
            addCriterion("third_brand_identy not like", value, "thirdBrandIdenty");
            return (Criteria) this;
        }

        public Criteria andThirdSecretBetween(String value1, String value2) {
            addCriterion("third_secret between", value1, value2, "thirdSecret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretEqualTo(String value) {
            addCriterion("third_secret =", value, "thirdSecret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretGreaterThan(String value) {
            addCriterion("third_secret >", value, "thirdSecret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretGreaterThanOrEqualTo(String value) {
            addCriterion("third_secret >=", value, "thirdSecret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretIn(List<String> values) {
            addCriterion("third_secret in", values, "thirdSecret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretIsNotNull() {
            addCriterion("third_secret is not null");
            return (Criteria) this;
        }

        public Criteria andThirdSecretIsNull() {
            addCriterion("third_secret is null");
            return (Criteria) this;
        }

        public Criteria andThirdSecretLessThan(String value) {
            addCriterion("third_secret <", value, "thirdSecret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretLessThanOrEqualTo(String value) {
            addCriterion("third_secret <=", value, "thirdSecret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("third_secret like", value, "thirdSecret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("third_secret like", likeValues, "third_secret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("third_secret like", value, "thirdSecret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("third_secret like", likeValues, "third_secret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("third_secret like", value, "thirdSecret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("third_secret like", likeValues, "third_secret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretNotBetween(String value1, String value2) {
            addCriterion("third_secret not between", value1, value2, "thirdSecret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretNotEqualTo(String value) {
            addCriterion("third_secret <>", value, "thirdSecret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretNotIn(List<String> values) {
            addCriterion("third_secret not in", values, "thirdSecret");
            return (Criteria) this;
        }

        public Criteria andThirdSecretNotLike(String value) {
            addCriterion("third_secret not like", value, "thirdSecret");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdBetween(String value1, String value2) {
            addCriterion("third_shop_id between", value1, value2, "thirdShopId");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdEqualTo(String value) {
            addCriterion("third_shop_id =", value, "thirdShopId");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdGreaterThan(String value) {
            addCriterion("third_shop_id >", value, "thirdShopId");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdGreaterThanOrEqualTo(String value) {
            addCriterion("third_shop_id >=", value, "thirdShopId");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdIn(List<String> values) {
            addCriterion("third_shop_id in", values, "thirdShopId");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdIsNotNull() {
            addCriterion("third_shop_id is not null");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdIsNull() {
            addCriterion("third_shop_id is null");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdLessThan(String value) {
            addCriterion("third_shop_id <", value, "thirdShopId");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdLessThanOrEqualTo(String value) {
            addCriterion("third_shop_id <=", value, "thirdShopId");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("third_shop_id like", value, "thirdShopId");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("third_shop_id like", likeValues, "third_shop_id");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("third_shop_id like", value, "thirdShopId");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("third_shop_id like", likeValues, "third_shop_id");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("third_shop_id like", value, "thirdShopId");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("third_shop_id like", likeValues, "third_shop_id");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdNotBetween(String value1, String value2) {
            addCriterion("third_shop_id not between", value1, value2, "thirdShopId");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdNotEqualTo(String value) {
            addCriterion("third_shop_id <>", value, "thirdShopId");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdNotIn(List<String> values) {
            addCriterion("third_shop_id not in", values, "thirdShopId");
            return (Criteria) this;
        }

        public Criteria andThirdShopIdNotLike(String value) {
            addCriterion("third_shop_id not like", value, "thirdShopId");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameBetween(String value1, String value2) {
            addCriterion("third_shop_name between", value1, value2, "thirdShopName");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameEqualTo(String value) {
            addCriterion("third_shop_name =", value, "thirdShopName");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameGreaterThan(String value) {
            addCriterion("third_shop_name >", value, "thirdShopName");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("third_shop_name >=", value, "thirdShopName");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameIn(List<String> values) {
            addCriterion("third_shop_name in", values, "thirdShopName");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameIsNotNull() {
            addCriterion("third_shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameIsNull() {
            addCriterion("third_shop_name is null");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameLessThan(String value) {
            addCriterion("third_shop_name <", value, "thirdShopName");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameLessThanOrEqualTo(String value) {
            addCriterion("third_shop_name <=", value, "thirdShopName");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameLike(String value) {
            if(null == value || value.trim().length() == 0 ){ return (Criteria) this; }
            if(value.indexOf("%") < 0 ){ value = "%"+value+"%"; }
            addCriterion("third_shop_name like", value, "thirdShopName");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameLike(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"around");
            addCriterion("third_shop_name like", likeValues, "third_shop_name");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameLikePrefix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = value+"%";
            addCriterion("third_shop_name like", value, "thirdShopName");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameLikePrefix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"prefix");
            addCriterion("third_shop_name like", likeValues, "third_shop_name");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameLikeSuffix(String value) {
            if(null==value || value.trim().length() == 0){ return (Criteria) this; }
            value = "%"+value;
            addCriterion("third_shop_name like", value, "thirdShopName");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameLikeSuffix(List<String> values) {
            if(null==values || values.isEmpty()){ return (Criteria) this; }
            List<String> likeValues = transferLikeValue(values,"suffix");
            addCriterion("third_shop_name like", likeValues, "third_shop_name");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameNotBetween(String value1, String value2) {
            addCriterion("third_shop_name not between", value1, value2, "thirdShopName");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameNotEqualTo(String value) {
            addCriterion("third_shop_name <>", value, "thirdShopName");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameNotIn(List<String> values) {
            addCriterion("third_shop_name not in", values, "thirdShopName");
            return (Criteria) this;
        }

        public Criteria andThirdShopNameNotLike(String value) {
            addCriterion("third_shop_name not like", value, "thirdShopName");
            return (Criteria) this;
        }

        public Criteria andThirdShopUrlBetween(Long value1, Long value2) {
            addCriterion("third_shop_url between", value1, value2, "thirdShopUrl");
            return (Criteria) this;
        }

        public Criteria andThirdShopUrlEqualTo(Long value) {
            addCriterion("third_shop_url =", value, "thirdShopUrl");
            return (Criteria) this;
        }

        public Criteria andThirdShopUrlGreaterThan(Long value) {
            addCriterion("third_shop_url >", value, "thirdShopUrl");
            return (Criteria) this;
        }

        public Criteria andThirdShopUrlGreaterThanOrEqualTo(Long value) {
            addCriterion("third_shop_url >=", value, "thirdShopUrl");
            return (Criteria) this;
        }

        public Criteria andThirdShopUrlIn(List<Long> values) {
            addCriterion("third_shop_url in", values, "thirdShopUrl");
            return (Criteria) this;
        }

        public Criteria andThirdShopUrlIsNotNull() {
            addCriterion("third_shop_url is not null");
            return (Criteria) this;
        }

        public Criteria andThirdShopUrlIsNull() {
            addCriterion("third_shop_url is null");
            return (Criteria) this;
        }

        public Criteria andThirdShopUrlLessThan(Long value) {
            addCriterion("third_shop_url <", value, "thirdShopUrl");
            return (Criteria) this;
        }

        public Criteria andThirdShopUrlLessThanOrEqualTo(Long value) {
            addCriterion("third_shop_url <=", value, "thirdShopUrl");
            return (Criteria) this;
        }

        public Criteria andThirdShopUrlNotBetween(Long value1, Long value2) {
            addCriterion("third_shop_url not between", value1, value2, "thirdShopUrl");
            return (Criteria) this;
        }

        public Criteria andThirdShopUrlNotEqualTo(Long value) {
            addCriterion("third_shop_url <>", value, "thirdShopUrl");
            return (Criteria) this;
        }

        public Criteria andThirdShopUrlNotIn(List<Long> values) {
            addCriterion("third_shop_url not in", values, "thirdShopUrl");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
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
        private PartnerThirdMatchExample example;

        protected Criteria(PartnerThirdMatchExample example) {
            super();
            this.example = example;
        }

        public PartnerThirdMatchExample example() {
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