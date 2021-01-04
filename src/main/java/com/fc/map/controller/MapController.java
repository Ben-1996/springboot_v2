package com.fc.map.controller;

import com.fc.map.model.ComboModel;
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

import java.util.ArrayList;
import java.util.List;

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
    @PostMapping("/addmap")
    @RequiresPermissions("map:list")
    @ResponseBody
    public AjaxResult add(Map map){
        if(map.getDate().equals("")){
            map.setDate(null);
        }
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
    @RequiresPermissions("map:list")
    @ResponseBody
    public AjaxResult remove(Integer ids){
        int b=imapService.deleteByPrimaryKey(ids);
        if(b>0){
            return success();
        }else{
            return error();
        }
    }

    /*@ApiOperation(value = "修改跳转", notes = "修改跳转")
    @GetMapping("/edit")
    public String edit(Integer id,Map mmap)
    {
        mmap.put("SysArea", sysAreaService.selectByPrimaryKey(id));
        Map map1=imapService.selectByPrimaryKey(id);
        return prefix + "/mapEdit?name="+map1.getName()+"&x="+map1.getX()+"&y="+map1.getY()+"&id="+ map1.getId()
                +"&tel="+ map1.getTel()+"&address="+ map1.getAddress()+"&province="+ map1.getProvince()
                +"&joinindex="+ map1.getJoinindex()+"&program="+ map1.getProgram()+"&date="+map1.getDate();
    }*/
    @ApiOperation(value = "修改跳转", notes = "修改跳转")
    @GetMapping("/edit")
    public String edit(Integer id, ModelMap mmap)
    {
        mmap.put("MapData", imapService.selectByPrimaryKey(id));
        return prefix + "/mapEdit";
    }
    /**
     * 修改保存
     */
    //@Log(title = "地区设置修改", action = "111")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @RequiresPermissions("map:list")
    @PostMapping("/editmap")
    @ResponseBody
    public AjaxResult editSave(Map map) {
        if(map.getDate().equals("")){
            map.setDate(null);
        }
        return toAjax(imapService.updateByPrimaryKey(map));
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
    @PostMapping("/provincelist")
    @ResponseBody
    public List<ComboModel> provincelist(){
        List<ComboModel> list= imapService.provinceList();
        return list;
    }
    @PostMapping("/programlist")
    @ResponseBody
    public List<ComboModel> programlist(Tablepar tablepar, Map map){
        List<ComboModel> list= imapService.programList();
        return list;
    }
    @PostMapping("/allProvincelist")
    @ResponseBody
    public List<ComboModel> allProvincelist(Tablepar tablepar, Map map){
        List<ComboModel> list= imapService.allProvinceList();
        return list;
    }
}
