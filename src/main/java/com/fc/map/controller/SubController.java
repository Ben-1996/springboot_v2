package com.fc.map.controller;
import com.fc.map.model.ComboModel;
import com.fc.map.model.Subject;
import com.fc.map.service.SubService;
import com.fc.test.common.base.BaseController;
import com.fc.test.common.domain.AjaxResult;
import com.fc.test.model.auto.SysArea;
import com.fc.test.model.custom.TableSplitResult;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.TitleVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sub")
public class SubController extends BaseController{
    private String prefix = "sub";
    @Resource
    private SubService subService;
    @GetMapping("/initpage")
    @RequiresPermissions("sub:list")
    public String view(ModelMap model)
    {
        String str = "科目设置";
        setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/sub";
    }
    @PostMapping("/list")
    @ResponseBody
    public Object list(Tablepar tablepar, Subject sub){
        PageInfo<Subject> page=subService.List(tablepar,sub) ;
        TableSplitResult<Subject> result=new TableSplitResult<Subject>(page.getPageNum(), page.getTotal(), page.getList());
        return  result;
    }
    @ApiOperation(value="新增跳转",notes="新增跳转")
    @GetMapping("/add")
    public String add(ModelMap model){
        return prefix + "/subAdd";
    }
    @ApiOperation(value="新增",notes="新增")
    @GetMapping("addsub")
    @RequiresPermissions("sub:list")
    @ResponseBody
    public AjaxResult add(Subject sub){
        int b = subService.insert(sub);
        if(b>0){
            return success();
        }
        else {
            return error();
        }
    }

    @ApiOperation(value="删除",notes = "删除")
    @PostMapping("/remove")
    @RequiresPermissions("sub:list")
    @ResponseBody
    public AjaxResult remove(Integer ids){
        int b = subService.deleteByPrimaryKey(ids);
        if(b>0){
            return success();
        }
        else{
            return error();
        }
    }
    @ApiOperation(value="编辑跳转",notes="编辑跳转")
    @GetMapping("/edit")
    public String edit(Integer id, ModelMap mmap)
    {
        mmap.put("SubData", subService.selectByPrimaryKey(id));
        return prefix + "/edit";
    }
    @ApiOperation(value="修改保存",notes = "修改保存")
    @RequiresPermissions("sub:list")
    @PostMapping("/editsub")
    @ResponseBody
    public AjaxResult editSave(Subject sub) {return toAjax(subService.updateByPrimaryKeySelective(sub));}
}
