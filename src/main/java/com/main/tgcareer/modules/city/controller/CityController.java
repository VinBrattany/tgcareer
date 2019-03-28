package com.main.tgcareer.modules.city.controller;


import com.main.tgcareer.common.jason.Ajax;
import com.main.tgcareer.common.jason.AjaxJson;
import com.main.tgcareer.common.redis.RedisServiceImpl;
import com.main.tgcareer.common.web.BaseController;
import com.main.tgcareer.modules.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.main.tgcareer.modules.city.entity.City;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("city")

public class CityController {
    @Autowired
    private CityService cityService;

    @Autowired
    private RedisServiceImpl redisService;
    /**
     * 获取City信息
     */
    @RequestMapping(method = RequestMethod.GET,value = "city{id}")
    public AjaxJson college(@PathVariable String id){
        City city = cityService.getCity(id);
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("city",city);
        return Ajax.success(map);
    }
    /**
     * 新建City信息
     */
    @RequestMapping(method = RequestMethod.POST,value = "city")
    public AjaxJson newCity(@RequestBody City city){
        cityService.saveCity(city);
        return Ajax.success();
    }
    /**
     * 更新City信息
     */
    @RequestMapping(method = RequestMethod.PUT,value = "city")
    public AjaxJson updateCity(@RequestBody City city){
        cityService.updateCity(city);
        return Ajax.success();
    }

    /*
     * 删除City信息
     */
    @RequestMapping(method = RequestMethod.DELETE,value = "city{id}")
    public AjaxJson deleteCity(@PathVariable String id){
        cityService.deleteCity(id);
        return Ajax.success();
    }
}
