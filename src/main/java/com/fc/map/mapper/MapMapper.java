package com.fc.map.mapper;


import com.fc.map.model.ComboModel;
import com.fc.map.model.Map;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

public interface MapMapper {
    List<Map> selectMap(@Param("map") Map map);
    Map selectByPrimaryKey(@Param("id")int id);
    int deleteByPrimaryKey(@Param("id")int id);
    int updateByPrimaryKeySelective(@Param("map") Map map);
    int insert(@Param("map") Map map);
    List<ComboModel> programList();
    List<ComboModel> provinceList();
    List<ComboModel> allProvinceList();
}