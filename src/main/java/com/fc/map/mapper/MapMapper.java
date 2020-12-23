package com.fc.map.mapper;


import com.fc.map.model.Map;
import org.mapstruct.Mapper;

import java.util.List;

public interface MapMapper {
    List<Map> selectMap(Map map);

}