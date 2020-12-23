package com.fc.map.mapper;


import com.fc.map.model.Map;
import org.mapstruct.Mapper;

import java.util.List;

public interface MapMapper {
    List<Map> selectMap(Map map);
    Map selectByPrimaryKey(int id);
    int deleteByPrimaryKey(int id);
    int updateByPrimaryKeySelective(Map map);
    int insert(Map map);
}