package com.fc.map.controller;

import com.fc.map.service.IMapService;
import com.fc.test.model.custom.TitleVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map")
public class MapController {
    private String prefix = "map";
    @Autowired
    private IMapService imapService;
    @GetMapping("/list")
    @RequiresPermissions("map:list")
    public String view(ModelMap model)
    {
        String str="地图设置";
        setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/map";
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
