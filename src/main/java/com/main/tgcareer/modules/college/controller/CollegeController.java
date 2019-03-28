package com.main.tgcareer.modules.college.controller;

import com.main.tgcareer.common.jason.Ajax;
import com.main.tgcareer.common.jason.AjaxJson;
import com.main.tgcareer.common.redis.RedisServiceImpl;
import com.main.tgcareer.common.web.BaseController;
import com.main.tgcareer.modules.college.entity.College;
import com.main.tgcareer.modules.college.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("college")

public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    @Autowired
    private RedisServiceImpl redisService;
    /**
     * 获取College信息
     */
    @RequestMapping(method = RequestMethod.GET,value = "college/{id}")
    public AjaxJson college(@PathVariable String id){
        College college = collegeService.getCollege(id);
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("college",college);
        return Ajax.success(map);
    }
    /**
     * 新建College信息
     */
    @RequestMapping(method = RequestMethod.POST,value = "college")
    public AjaxJson newCollege(@RequestBody College college){
        collegeService.saveCollege(college);
        return Ajax.success();
    }
    /**
     * 更新College信息
     */
    @RequestMapping(method = RequestMethod.PUT,value = "college")
    public AjaxJson updateCollege(@RequestBody College college){
        collegeService.updateCollege(college);
        return Ajax.success();
    }

    /*
     * 删除College信息
     */
    @RequestMapping(method = RequestMethod.DELETE,value = "college{id}")
    public AjaxJson deleteCollege(@PathVariable String id){
        collegeService.deleteCollege(id);
        return Ajax.success();
    }
}
