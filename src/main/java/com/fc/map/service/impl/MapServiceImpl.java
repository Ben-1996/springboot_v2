package com.fc.map.service.impl;

import com.fc.map.mapper.MapMapper;
import com.fc.map.model.Map;
import com.fc.map.service.IMapService;
import com.fc.test.common.support.ConvertUtil;
import com.fc.test.model.auto.SysArea;
import com.fc.test.model.auto.SysAreaExample;
import com.fc.test.model.custom.Tablepar;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class MapServiceImpl implements IMapService {
    @Resource
    MapMapper mapMapper;

    public PageInfo<Map> list(Tablepar tablepar, Map map){
        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        List<Map> list= mapMapper.selectMap(map);
        PageInfo<Map> pageInfo = new PageInfo<Map>(list);
        return  pageInfo;
    }
    public List<Map> mapviewlist(Map map){
        List<Map> maplist= mapMapper.selectMap(map);
        return  maplist;
    }
    public int deleteByPrimaryKey(int id) {
        return mapMapper.deleteByPrimaryKey(id);
    }
    public int updateByPrimaryKeySelective(Map map){
        return mapMapper.updateByPrimaryKeySelective(map);
    }
    public int insert(Map map){
        return mapMapper.insert(map);
    }
    public Map selectByPrimaryKey(int id){
        return mapMapper.selectByPrimaryKey(id);
    };
}
