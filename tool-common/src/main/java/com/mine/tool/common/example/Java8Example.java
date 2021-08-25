package com.mine.tool.common.example;

import com.google.common.collect.Lists;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Title: Java8Example
 * Description:
 * Created by admin on 2021/8/3.
 */
public class Java8Example {
    public static void main(String[] args) {
        //分组求和
        List<OperationDaily> list = Lists.newArrayList();
        OperationDaily daily1 = new OperationDaily();
        daily1.setDate("2020-01-01");
        daily1.setVehicleTotal(1);
        list.add(daily1);
        OperationDaily daily2 = new OperationDaily();
        daily2.setDate("2020-01-01");
        daily2.setVehicleTotal(2);
        list.add(daily2);
        Map<String, IntSummaryStatistics> majorOperation = list.stream().collect(Collectors.groupingBy(OperationDaily::getDate, Collectors.summarizingInt(OperationDaily::getVehicleTotal)));
        System.out.println(majorOperation.get("2020-01-01").getSum());
        Map<String, Integer> collect = list.stream().collect(Collectors.groupingBy(OperationDaily::getDate, Collectors.reducing(0, OperationDaily::getVehicleTotal, Integer::sum)));
        System.out.println(collect.get("2020-01-01"));
    }

    static class OperationDaily{
        private String date;
        private int vehicleTotal;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getVehicleTotal() {
            return vehicleTotal;
        }

        public void setVehicleTotal(int vehicleTotal) {
            this.vehicleTotal = vehicleTotal;
        }
    }

}
