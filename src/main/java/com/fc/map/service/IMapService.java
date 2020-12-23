package com.fc.map.service;

import com.fc.map.model.Map;
import com.fc.map.service.impl.MapServiceImpl;
import com.fc.test.model.custom.Tablepar;
import com.github.pagehelper.PageInfo;

public interface IMapService {
    PageInfo<Map> list(Tablepar tablepar, Map map);
}
