package com.main.tgcareer.modules.corporation.controller;

import com.main.tgcareer.common.jason.Ajax;
import com.main.tgcareer.common.jason.AjaxJson;
import com.main.tgcareer.common.redis.RedisServiceImpl;
import com.main.tgcareer.common.web.BaseController;
import com.main.tgcareer.modules.corporation.entity.Corporation;
import com.main.tgcareer.modules.corporation.service.CorporationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("corporation")

public class CorporationController {
    @Autowired
    private CorporationService corporationService;

    @Autowired
    private RedisServiceImpl redisService;
    /**
     * 获取
     */
    @RequestMapping(method = RequestMethod.GET,value = "corporation{id}")
    public AjaxJson corporation(@PathVariable String id){
        Corporation corporation = corporationService.getCorporation(id);
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("corporation",corporation);
        return Ajax.success(map);
    }
    /**
     * 新建
     */
    @RequestMapping(method = RequestMethod.POST,value = "corporation")
    public AjaxJson newCorporation(@RequestBody Corporation corporation){
        corporationService.saveCorporation(corporation);
        return Ajax.success();
    }
    /**
     * 更新
     */
    @RequestMapping(method = RequestMethod.PUT,value = "corporation")
    public AjaxJson updateCorporation(@RequestBody Corporation corporation){
        corporationService.updateCorporation(corporation);
        return Ajax.success();
    }
    /**
     * 删除
     */
    @RequestMapping(method = RequestMethod.DELETE,value = "corporation{id}")
    public AjaxJson deleteCorporation(@PathVariable String id){
        corporationService.deleteCorporation(id);
        return Ajax.success();
    }
}
