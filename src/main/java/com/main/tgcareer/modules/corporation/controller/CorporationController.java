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
     * 获取用户信息
     */
    @RequestMapping(method = RequestMethod.GET,value = "corporation")
    public AjaxJson corporation(@RequestParam String corId){
        Corporation corporation = corporationService.getCorporation(corId);
//        corporation.setOpenid("");
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("corporation",corporation);
        return Ajax.success(map);
    }
    /**
     * 新建用户信息
     */
    @RequestMapping(method = RequestMethod.POST,value = "corporation")
    public AjaxJson newCorporation(@RequestBody Corporation corporation){
//        user.setOpenid(redisService.get(httpServletRequest.getParameter("token")).toString());
        corporationService.saveCorporation(corporation);
        return Ajax.success();
    }
    /**
     * 更新用户信息
     */
    @RequestMapping(method = RequestMethod.PUT,value = "corporation")
    public AjaxJson updateCorporation(@RequestBody Corporation corporation){
        corporationService.updateCorporation(corporation);

        //返回用户信息
        Corporation newCorporation = corporationService.getCorporation(corporation.getId());
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("corporation",newCorporation);
        return Ajax.success(map);
    }
    /**
     * 删除用户信息
     */
//    @RequestMapping(method = RequestMethod.DELETE,value = "corporation")
//    public AjaxJson deleteCorporation(){
//        return Ajax.success();
//    }
}
