package com.mine.demo.mapper;

import com.mine.demo.model.VehicleStatistics;
import com.mine.demo.model.VehicleStatisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VehicleStatisticsMapper {
    int deleteByExample(VehicleStatisticsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VehicleStatistics record);

    int insertSelective(VehicleStatistics record);

    VehicleStatistics selectOneByExample(VehicleStatisticsExample example);

    List<VehicleStatistics> selectByExample(VehicleStatisticsExample example);

    VehicleStatistics selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VehicleStatistics record, @Param("example") VehicleStatisticsExample example);

    int updateByExample(@Param("record") VehicleStatistics record, @Param("example") VehicleStatisticsExample example);

    int updateByPrimaryKeySelective(VehicleStatistics record);

    int updateByPrimaryKey(VehicleStatistics record);

    int batchInsert(@Param("list") List<VehicleStatistics> list);

    int batchInsertSelective(@Param("list") List<VehicleStatistics> list, @Param("selective") VehicleStatistics.Column ... selective);
}