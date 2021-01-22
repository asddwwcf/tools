package com.mine.tool.orm.mybatis.generator.domain;

import com.mine.tool.orm.mybatis.core.annotation.Column;
import com.mine.tool.orm.mybatis.core.annotation.Id;
import com.mine.tool.orm.mybatis.core.annotation.Table;
import java.io.Serializable;
import lombok.Data;

@Data
@Table("admin_menu")
public class AdminMenu implements Serializable {
    @Id("id")
    private Integer id;

    @Column("parent_id")
    private Integer parentId;

    @Column("level")
    private Integer level;

    @Column("menu_name")
    private String menuName;

    @Column("icon")
    private String icon;

    @Column("href")
    private String href;

    @Column("is_delete")
    private Long isDelete;

    private static final long serialVersionUID = 1L;

    public enum Fields {
        ID("id","id"),
        PARENT_ID("parentId","parent_id"),
        LEVEL("level","level"),
        MENU_NAME("menuName","menu_name"),
        ICON("icon","icon"),
        HREF("href","href"),
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