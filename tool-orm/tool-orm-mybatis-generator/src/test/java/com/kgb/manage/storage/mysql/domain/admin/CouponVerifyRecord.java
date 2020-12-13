package com.kgb.manage.storage.mysql.domain.admin;

import com.bruce.tool.orm.mybatis.core.annotation.Column;
import com.bruce.tool.orm.mybatis.core.annotation.Id;
import com.bruce.tool.orm.mybatis.core.annotation.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
@Table("coupon_verify_record")
public class CouponVerifyRecord implements Serializable {
    @Id("id")
    private Long id;

    @Column("source")
    private Integer source;

    @Column("trade_id")
    private Long tradeId;

    @Column("trade_no")
    private String tradeNo;

    @Column("uuid")
    private String uuid;

    @Column("coupon_no")
    private String couponNo;

    @Column("coupon_type")
    private Integer couponType;

    @Column("coupon_name")
    private String couponName;

    @Column("brand_identy")
    private Long brandIdenty;

    @Column("shop_identy")
    private Long shopIdenty;

    @Column("status")
    private Integer status;

    @Column("status_desc")
    private String statusDesc;

    @Column("verify_status")
    private Integer verifyStatus;

    @Column("verify_status_desc")
    private String verifyStatusDesc;

    @Column("operator_id")
    private String operatorId;

    @Column("operator_name")
    private String operatorName;

    @Column("operator_type")
    private Integer operatorType;

    @Column("device_no")
    private String deviceNo;

    @Column("server_create_time")
    private Date serverCreateTime;

    @Column("server_update_time")
    private Date serverUpdateTime;

    @Column("threshold")
    private Integer threshold;

    @Column("face_value")
    private BigDecimal faceValue;

    @Column("deduct_per_threshold")
    private Integer deductPerThreshold;

    @Column("validity_start_time")
    private Date validityStartTime;

    @Column("validity_end_time")
    private Date validityEndTime;

    @Column("consume_time")
    private Date consumeTime;

    @Column("lock_time")
    private Date lockTime;

    private static final long serialVersionUID = 1L;

    public enum Fields {
        ID("id","id"),
        SOURCE("source","source"),
        TRADE_ID("tradeId","trade_id"),
        TRADE_NO("tradeNo","trade_no"),
        UUID("uuid","uuid"),
        COUPON_NO("couponNo","coupon_no"),
        COUPON_TYPE("couponType","coupon_type"),
        COUPON_NAME("couponName","coupon_name"),
        BRAND_IDENTY("brandIdenty","brand_identy"),
        SHOP_IDENTY("shopIdenty","shop_identy"),
        STATUS("status","status"),
        STATUS_DESC("statusDesc","status_desc"),
        VERIFY_STATUS("verifyStatus","verify_status"),
        VERIFY_STATUS_DESC("verifyStatusDesc","verify_status_desc"),
        OPERATOR_ID("operatorId","operator_id"),
        OPERATOR_NAME("operatorName","operator_name"),
        OPERATOR_TYPE("operatorType","operator_type"),
        DEVICE_NO("deviceNo","device_no"),
        SERVER_CREATE_TIME("serverCreateTime","server_create_time"),
        SERVER_UPDATE_TIME("serverUpdateTime","server_update_time"),
        THRESHOLD("threshold","threshold"),
        FACE_VALUE("faceValue","face_value"),
        DEDUCT_PER_THRESHOLD("deductPerThreshold","deduct_per_threshold"),
        VALIDITY_START_TIME("validityStartTime","validity_start_time"),
        VALIDITY_END_TIME("validityEndTime","validity_end_time"),
        CONSUME_TIME("consumeTime","consume_time"),
        LOCK_TIME("lockTime","lock_time");

        private String field;

        private String column;

        Fields(String field, String column) {
            this.field = field;
            this.column = column;
        }

        public String field() {
            return this.field;
        }

        public String column() {
            return this.column;
        }
    }
}