package com.mine.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author admin
 * @date 2021/02/04
 */
public class VehicleInfo implements Serializable {
    /**
     * 车辆id
     */
    private String vid;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 车辆品牌
     */
    private String vehicleBrand;

    /**
     * 里程数
     */
    private Double mileage;

    /**
     * 月份
     */
    private Integer month;

    private static final long serialVersionUID = 1L;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid == null ? null : vid.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand == null ? null : vehicleBrand.trim();
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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
        VehicleInfo other = (VehicleInfo) that;
        return (this.getVid() == null ? other.getVid() == null : this.getVid().equals(other.getVid()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getVehicleBrand() == null ? other.getVehicleBrand() == null : this.getVehicleBrand().equals(other.getVehicleBrand()))
            && (this.getMileage() == null ? other.getMileage() == null : this.getMileage().equals(other.getMileage()))
            && (this.getMonth() == null ? other.getMonth() == null : this.getMonth().equals(other.getMonth()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVid() == null) ? 0 : getVid().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getVehicleBrand() == null) ? 0 : getVehicleBrand().hashCode());
        result = prime * result + ((getMileage() == null) ? 0 : getMileage().hashCode());
        result = prime * result + ((getMonth() == null) ? 0 : getMonth().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", vid=").append(vid);
        sb.append(", customerName=").append(customerName);
        sb.append(", vehicleBrand=").append(vehicleBrand);
        sb.append(", mileage=").append(mileage);
        sb.append(", month=").append(month);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static VehicleInfo.Builder builder() {
        return new VehicleInfo.Builder();
    }

    public static class Builder {
        private VehicleInfo obj;

        public Builder() {
            this.obj = new VehicleInfo();
        }

        public Builder vid(String vid) {
            obj.setVid(vid);
            return this;
        }

        public Builder customerName(String customerName) {
            obj.setCustomerName(customerName);
            return this;
        }

        public Builder vehicleBrand(String vehicleBrand) {
            obj.setVehicleBrand(vehicleBrand);
            return this;
        }

        public Builder mileage(Double mileage) {
            obj.setMileage(mileage);
            return this;
        }

        public Builder month(Integer month) {
            obj.setMonth(month);
            return this;
        }

        public VehicleInfo build() {
            return this.obj;
        }
    }

    public enum Column {
        vid("vid", "vid", "VARCHAR", false),
        customerName("customer_name", "customerName", "VARCHAR", false),
        vehicleBrand("vehicle_brand", "vehicleBrand", "VARCHAR", false),
        mileage("mileage", "mileage", "DOUBLE", false),
        month("month", "month", "INTEGER", false);

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