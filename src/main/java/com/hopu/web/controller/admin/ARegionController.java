package com.hopu.web.controller.admin;


import com.hopu.pojo.Region;
import com.hopu.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/region")
public class ARegionController {
    @Autowired
    private RegionService regionService;

    /*分页查询所有房屋讯息*/
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Region> list(){
        List<Region> regionList=regionService.findAll();

        return regionList;
    }
}
