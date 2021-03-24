package com.mine.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author admin
 * @date 2021/02/27
 */
public class VehicleStatistics implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 车辆品牌分布
     */
    private String brandDistribution;

    /**
     * 里程区间条件
     */
    private Integer rangeCondition;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getBrandDistribution() {
        return brandDistribution;
    }

    public void setBrandDistribution(String brandDistribution) {
        this.brandDistribution = brandDistribution == null ? null : brandDistribution.trim();
    }

    public Integer getRangeCondition() {
        return rangeCondition;
    }

    public void setRangeCondition(Integer rangeCondition) {
        this.rangeCondition = rangeCondition;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        VehicleStatistics other = (VehicleStatistics) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getBrandDistribution() == null ? other.getBrandDistribution() == null : this.getBrandDistribution().equals(other.getBrandDistribution()))
            && (this.getRangeCondition() == null ? other.getRangeCondition() == null : this.getRangeCondition().equals(other.getRangeCondition()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getBrandDistribution() == null) ? 0 : getBrandDistribution().hashCode());
        result = prime * result + ((getRangeCondition() == null) ? 0 : getRangeCondition().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", customerName=").append(customerName);
        sb.append(", amount=").append(amount);
        sb.append(", brandDistribution=").append(brandDistribution);
        sb.append(", rangeCondition=").append(rangeCondition);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static VehicleStatistics.Builder builder() {
        return new VehicleStatistics.Builder();
    }

    public static class Builder {
        private VehicleStatistics obj;

        public Builder() {
            this.obj = new VehicleStatistics();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder customerName(String customerName) {
            obj.setCustomerName(customerName);
            return this;
        }

        public Builder amount(Integer amount) {
            obj.setAmount(amount);
            return this;
        }

        public Builder brandDistribution(String brandDistribution) {
            obj.setBrandDistribution(brandDistribution);
            return this;
        }

        public Builder rangeCondition(Integer rangeCondition) {
            obj.setRangeCondition(rangeCondition);
            return this;
        }

        public VehicleStatistics build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "BIGINT", false),
        customerName("customer_name", "customerName", "VARCHAR", false),
        amount("amount", "amount", "INTEGER", false),
        brandDistribution("brand_distribution", "brandDistribution", "VARCHAR", false),
        rangeCondition("range_condition", "rangeCondition", "INTEGER", false);

        private static final String BEGINNING_DELIMITER = "\"";

        private static final String ENDING_DELIMITER = "\"";

        private final String column;

        private final boolean isColumnNameDelimited;

        private final String javaProperty;

        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public static Column[] all() {
            return Column.values();
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}