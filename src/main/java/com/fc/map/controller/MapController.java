package com.fc.map.controller;

import com.fc.map.model.Map;
import com.fc.map.service.IMapService;
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

import static com.fc.test.common.domain.AjaxResult.error;
import static com.fc.test.common.domain.AjaxResult.success;

@Controller
@RequestMapping("/map")
public class MapController extends BaseController {
    private String prefix = "map";
    @Autowired
    private IMapService imapService;
    @GetMapping("/initpage")
    @RequiresPermissions("map:list")
    public String view(ModelMap model)
    {
        String str="地图设置";
        setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/map";
    }
    @PostMapping("/list")
    @ResponseBody
    public Object list(Tablepar tablepar, Map map){
        PageInfo<Map> page=imapService.list(tablepar,map) ;
        TableSplitResult<Map> result=new TableSplitResult<Map>(page.getPageNum(), page.getTotal(), page.getList());
        return  result;
    }

    @ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        return prefix + "/mapAdd";
    }

    //@Log(title = "地区设置新增", action = "111")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("map:add")
    @ResponseBody
    public AjaxResult add(Map map){
        int b=imapService.insert(map);
        if(b>0){
            return success();
        }else{
            return error();
        }
    }

    //@Log(title = "地区设置删除", action = "111")
    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping("/remove")
    @RequiresPermissions("map:remove")
    @ResponseBody
    public AjaxResult remove(int id){
        int b=imapService.deleteByPrimaryKey(id);
        if(b>0){
            return success();
        }else{
            return error();
        }
    }

    @ApiOperation(value = "修改跳转", notes = "修改跳转")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        mmap.put("mapdata", imapService.selectByPrimaryKey(id));

        return prefix + "/edit";
    }

    /**
     * 修改保存
     */
    //@Log(title = "地区设置修改", action = "111")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @RequiresPermissions("map:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Map map) {
        return toAjax(imapService.updateByPrimaryKeySelective(map));
    }


    public void setTitle(ModelMap map,TitleVo titleVo){
        //标题
        map.put("title",titleVo.getTitle());
        map.put("parenttitle",titleVo.getParenttitle());
        //是否打开欢迎语
        map.put("isMsg",titleVo.isMsg());
        //欢迎语
        map.put("msgHTML",titleVo.getMsgHtml());
        //小控件
        map.put("isControl",titleVo.isControl());
        map.put("isribbon", titleVo.isIsribbon());
    }
}
