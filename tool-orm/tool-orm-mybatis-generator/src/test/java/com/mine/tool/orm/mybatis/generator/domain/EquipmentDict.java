package com.mine.tool.orm.mybatis.generator.domain;

import com.bruce.tool.orm.mybatis.core.annotation.Column;
import com.bruce.tool.orm.mybatis.core.annotation.Id;
import com.bruce.tool.orm.mybatis.core.annotation.Table;
import java.io.Serializable;
import lombok.Data;

@Data
@Table("per_equipment_dict")
public class EquipmentDict implements Serializable {
    @Id("id")
    private Integer id;

    @Column("key")
    private String key;

    @Column("name")
    private String name;

    @Column("default_value")
    private String defaultValue;

    @Column("scope_low")
    private String scopeLow;

    @Column("scope_high")
    private String scopeHigh;

    @Column("note")
    private String note;

    @Column("key_group")
    private Integer keyGroup;

    @Column("key_type")
    private Integer keyType;

    @Column("device_model_no")
    private String deviceModelNo;

    @Column("device_type")
    private Integer deviceType;

    @Column("readwrite")
    private Integer readwrite;

    @Column("order")
    private Integer order;

    private static final long serialVersionUID = 1L;

    public enum Fields {
        ID("id","id"),
        KEY("key","key"),
        NAME("name","name"),
        DEFAULT_VALUE("defaultValue","default_value"),
        SCOPE_LOW("scopeLow","scope_low"),
        SCOPE_HIGH("scopeHigh","scope_high"),
        NOTE("note","note"),
        KEY_GROUP("keyGroup","key_group"),
        KEY_TYPE("keyType","key_type"),
        DEVICE_MODEL_NO("deviceModelNo","device_model_no"),
        DEVICE_TYPE("deviceType","device_type"),
        READWRITE("readwrite","readwrite"),
        ORDER("order","order");

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