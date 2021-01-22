package com.kgb.manage.storage.mysql.domain.admin;

import com.mine.tool.orm.mybatis.core.annotation.Column;
import com.mine.tool.orm.mybatis.core.annotation.Id;
import com.mine.tool.orm.mybatis.core.annotation.Table;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@Table("partner_third_match")
public class PartnerThirdMatch implements Serializable {
    @Id("id")
    private Long id;

    @Column("brand_identy")
    private Long brandIdenty;

    @Column("shop_identy")
    private Long shopIdenty;

    @Column("source")
    private Short source;

    @Column("source_child")
    private Short sourceChild;

    @Column("third_shop_id")
    private String thirdShopId;

    @Column("third_shop_name")
    private String thirdShopName;

    @Column("third_account")
    private String thirdAccount;

    @Column("third_secret")
    private String thirdSecret;

    @Column("status")
    private Integer status;

    @Column("status_name")
    private String statusName;

    @Column("delivery_platform")
    private Integer deliveryPlatform;

    @Column("order_mode")
    private Integer orderMode;

    @Column("third_shop_url")
    private Long thirdShopUrl;

    @Column("server_create_time")
    private Date serverCreateTime;

    @Column("server_update_time")
    private Date serverUpdateTime;

    @Column("type")
    private Integer type;

    @Column("third_brand_identy")
    private String thirdBrandIdenty;

    @Column("ext1")
    private String ext1;

    @Column("ext2")
    private String ext2;

    private static final long serialVersionUID = 1L;

    public enum Fields {
        ID("id","id"),
        BRAND_IDENTY("brandIdenty","brand_identy"),
        SHOP_IDENTY("shopIdenty","shop_identy"),
        SOURCE("source","source"),
        SOURCE_CHILD("sourceChild","source_child"),
        THIRD_SHOP_ID("thirdShopId","third_shop_id"),
        THIRD_SHOP_NAME("thirdShopName","third_shop_name"),
        THIRD_ACCOUNT("thirdAccount","third_account"),
        THIRD_SECRET("thirdSecret","third_secret"),
        STATUS("status","status"),
        STATUS_NAME("statusName","status_name"),
        DELIVERY_PLATFORM("deliveryPlatform","delivery_platform"),
        ORDER_MODE("orderMode","order_mode"),
        THIRD_SHOP_URL("thirdShopUrl","third_shop_url"),
        SERVER_CREATE_TIME("serverCreateTime","server_create_time"),
        SERVER_UPDATE_TIME("serverUpdateTime","server_update_time"),
        TYPE("type","type"),
        THIRD_BRAND_IDENTY("thirdBrandIdenty","third_brand_identy"),
        EXT_1("ext1","ext1"),
        EXT_2("ext2","ext2");

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