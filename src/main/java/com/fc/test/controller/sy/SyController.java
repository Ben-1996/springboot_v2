package com.fc.test.controller.sy;

import com.fc.map.model.Map;
import com.fc.map.service.IMapService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/syController")
public class SyController {
    @Autowired
    IMapService iMapService;
    @ApiOperation(value="前台",notes="前台")
    @GetMapping("/mapdata")
    public Object list(Map map){
        List<Map> result=iMapService.mapviewlist(map) ;
        return  result;
    }
}
