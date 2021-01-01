package com.fc.map.mapper;

import com.fc.map.model.ComboModel;
import com.fc.map.model.Map;
import com.fc.map.model.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubMapper {
    List<Subject> selectTable(@Param("sub") Subject sub);
    Subject selectByPrimaryKey(@Param("id")int id);
    int deleteByPrimaryKey(@Param("id")int id);
    int updateByPrimaryKeySelective(@Param("sub") Subject sub);
    int insert(@Param("sub") Subject sub);
    List<ComboModel> programList();
    List<ComboModel> provinceList();
    List<ComboModel> allProvinceList();
}