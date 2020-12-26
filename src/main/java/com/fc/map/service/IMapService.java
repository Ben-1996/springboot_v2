package com.fc.map.service;

import com.fc.map.model.ComboModel;
import com.fc.map.model.Map;
import com.fc.map.service.impl.MapServiceImpl;
import com.fc.test.model.custom.Tablepar;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IMapService {
    PageInfo<Map> list(Tablepar tablepar, Map map);
    int deleteByPrimaryKey(int id);
    int updateByPrimaryKeySelective(Map map);
    int insert(Map map);
    Map selectByPrimaryKey(int id);
    List<Map> mapviewlist(Map map);
    List<ComboModel> programList();
    List<ComboModel> provinceList();
    List<ComboModel> allProvinceList();
}
