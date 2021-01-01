package com.fc.map.service.impl;

import com.fc.map.mapper.MapMapper;
import com.fc.map.mapper.SubMapper;
import com.fc.map.model.ComboModel;
import com.fc.map.model.Map;
import com.fc.map.model.Subject;
import com.fc.map.service.SubService;
import com.fc.test.model.custom.Tablepar;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public  class SubServiceImpl implements SubService {
    @Resource
    SubMapper subMapper;

    public PageInfo<Subject> List(Tablepar tablepar, Subject sub){
        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        List<Subject> list= subMapper.selectTable(sub);
        PageInfo<Subject> pageInfo = new PageInfo<Subject>(list);
        return  pageInfo;
    }
    public List<Subject> mapviewlist(Subject sub){
        List<Subject> maplist= subMapper.selectTable(sub);
        return  maplist;
    }

    public int deleteByPrimaryKey(int id) {
        return subMapper.deleteByPrimaryKey(id);
    }
    public int updateByPrimaryKeySelective(Subject sub){
        return subMapper.updateByPrimaryKeySelective(sub);
    }
    public int insert(Subject sub){
        return subMapper.insert(sub);
    }
    public Subject selectByPrimaryKey(int id){
        return subMapper.selectByPrimaryKey(id);
    };
    public List<ComboModel> programList(){
        return subMapper.programList();
    };
    public List<ComboModel> provinceList(){
        return subMapper.provinceList();
    };
    public List<ComboModel> allProvinceList(){
        return subMapper.allProvinceList();
    };
}
