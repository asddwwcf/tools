package com.mine.tool.orm.mybatis.generator.domain;

import com.mine.tool.orm.mybatis.core.annotation.Column;
import com.mine.tool.orm.mybatis.core.annotation.Id;
import com.mine.tool.orm.mybatis.core.annotation.Table;
import java.io.Serializable;
import lombok.Data;

@Data
@Table("admin_user")
public class AdminUser implements Serializable {
    @Id("user_id")
    private Integer userId;

    @Column("username")
    private String username;

    @Column("password")
    private String password;

    @Column("u_name")
    private String uName;

    @Column("u_status")
    private Integer uStatus;

    @Column("last_login")
    private Integer lastLogin;

    @Column("last_login_ip")
    private String lastLoginIp;

    @Column("is_delete")
    private Long isDelete;

    @Column("device_id")
    private String deviceId;

    @Column("create_time")
    private Long createTime;

    @Column("status")
    private Integer status;

    @Column("header")
    private String header;

    @Column("nick_name")
    private String nickName;

    @Column("expire_date")
    private Long expireDate;

    @Column("app_key")
    private String appKey;

    @Column("app_secret")
    private String appSecret;

    @Column("creater")
    private Integer creater;

    private static final long serialVersionUID = 1L;

    public enum Fields {
        USER_ID("userId","user_id"),
        USERNAME("username","username"),
        PASSWORD("password","password"),
        U_NAME("uName","u_name"),
        U_STATUS("uStatus","u_status"),
        LAST_LOGIN("lastLogin","last_login"),
        LAST_LOGIN_IP("lastLoginIp","last_login_ip"),
        IS_DELETE("isDelete","is_delete"),
        DEVICE_ID("deviceId","device_id"),
        CREATE_TIME("createTime","create_time"),
        STATUS("status","status"),
        HEADER("header","header"),
        NICK_NAME("nickName","nick_name"),
        EXPIRE_DATE("expireDate","expire_date"),
        APP_KEY("appKey","app_key"),
        APP_SECRET("appSecret","app_secret"),
        CREATER("creater","creater");

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