package com.mine.tool.orm.mybatis.generator.domain;

import com.bruce.tool.orm.mybatis.core.annotation.Column;
import com.bruce.tool.orm.mybatis.core.annotation.Id;
import com.bruce.tool.orm.mybatis.core.annotation.Table;
import java.io.Serializable;
import lombok.Data;

@Data
@Table("global_region_copy")
public class RegionCopy implements Serializable {
    @Id("id")
    private Integer id;

    @Column("level")
    private Integer level;

    @Column("is_direct")
    private Integer isDirect;

    @Column("pcode")
    private String pcode;

    @Column("code")
    private String code;

    @Column("name")
    private String name;

    @Column("href")
    private String href;

    @Column("referer")
    private String referer;

    @Column("longitude")
    private Double longitude;

    @Column("latitude")
    private Double latitude;

    @Column("success")
    private Boolean success;

    @Column("type")
    private Integer type;

    @Column("texts")
    private String texts;

    private static final long serialVersionUID = 1L;

    public enum Fields {
        ID("id","id"),
        LEVEL("level","level"),
        IS_DIRECT("isDirect","is_direct"),
        PCODE("pcode","pcode"),
        CODE("code","code"),
        NAME("name","name"),
        HREF("href","href"),
        REFERER("referer","referer"),
        LONGITUDE("longitude","longitude"),
        LATITUDE("latitude","latitude"),
        SUCCESS("success","success"),
        TYPE("type","type"),
        TEXTS("texts","texts");

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