package com.mine.tool.orm.mybatis.generator.domain;

import com.mine.tool.orm.mybatis.core.annotation.Column;
import com.mine.tool.orm.mybatis.core.annotation.Id;
import com.mine.tool.orm.mybatis.core.annotation.Table;
import java.io.Serializable;
import lombok.Data;

@Data
@Table("admin_role")
public class AdminRole implements Serializable {
    @Id("role_id")
    private Integer roleId;

    @Column("role_name")
    private String roleName;

    @Column("role_describe")
    private String roleDescribe;

    @Column("role_type")
    private Integer roleType;

    @Column("is_delete")
    private Long isDelete;

    private static final long serialVersionUID = 1L;

    public enum Fields {
        ROLE_ID("roleId","role_id"),
        ROLE_NAME("roleName","role_name"),
        ROLE_DESCRIBE("roleDescribe","role_describe"),
        ROLE_TYPE("roleType","role_type"),
        IS_DELETE("isDelete","is_delete");

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