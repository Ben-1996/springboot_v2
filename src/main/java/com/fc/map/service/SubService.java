package com.fc.map.service;
import com.fc.map.model.ComboModel;
import com.fc.map.model.Map;
import com.fc.map.model.Subject;
import com.fc.map.service.impl.MapServiceImpl;
import com.fc.test.model.custom.Tablepar;
import com.github.pagehelper.PageInfo;

import java.util.List;
public interface SubService {
    PageInfo<Subject> List(Tablepar tablepar, Subject sub);
    int deleteByPrimaryKey(int id);
    int updateByPrimaryKeySelective(Subject sub);
    int insert(Subject sub);
    Subject selectByPrimaryKey(int id);
    List<Subject> mapviewlist(Subject sub);
    List<ComboModel> programList();
    List<ComboModel> provinceList();
    List<ComboModel> allProvinceList();
    List<ComboModel> subList();
}
