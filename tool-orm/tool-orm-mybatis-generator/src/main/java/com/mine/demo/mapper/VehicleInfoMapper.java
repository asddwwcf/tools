package com.mine.demo.mapper;

import com.mine.demo.model.VehicleInfo;
import com.mine.demo.model.VehicleInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VehicleInfoMapper {
    int deleteByPrimaryKey(String vid);

    int insert(VehicleInfo record);

    int insertSelective(VehicleInfo record);

    VehicleInfo selectOneByExample(VehicleInfoExample example);

    List<VehicleInfo> selectByExample(VehicleInfoExample example);

    VehicleInfo selectByPrimaryKey(String vid);

    int updateByExampleSelective(@Param("record") VehicleInfo record, @Param("example") VehicleInfoExample example);

    int updateByExample(@Param("record") VehicleInfo record, @Param("example") VehicleInfoExample example);

    int updateByPrimaryKeySelective(VehicleInfo record);

    int updateByPrimaryKey(VehicleInfo record);

    int batchInsert(@Param("list") List<VehicleInfo> list);

    int batchInsertSelective(@Param("list") List<VehicleInfo> list, @Param("selective") VehicleInfo.Column ... selective);
}