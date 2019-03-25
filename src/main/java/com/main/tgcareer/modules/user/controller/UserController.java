package com.main.tgcareer.modules.user.controller;

import com.main.tgcareer.common.jason.Ajax;
import com.main.tgcareer.common.jason.AjaxJson;
import com.main.tgcareer.common.redis.RedisServiceImpl;
import com.main.tgcareer.common.web.BaseController;
import com.main.tgcareer.modules.user.entity.User;
import com.main.tgcareer.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @Autowired
    private RedisServiceImpl redisService;
    /**
     * 获取用户信息
     */
    @RequestMapping(method = RequestMethod.GET,value = "user")
    public AjaxJson user(@RequestParam String userid){
        User user = userService.getUser(userid);
        user.setOpenid("");
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("user",user);
        return Ajax.success(map);
    }
    /**
     * 新建用户信息
     */
    @RequestMapping(method = RequestMethod.POST,value = "user")
    public AjaxJson newUser(@RequestBody User user){
//        user.setOpenid(redisService.get(httpServletRequest.getParameter("token")).toString());
        userService.saveUser(user);
        return Ajax.success();
    }
    /**
     * 更新用户信息
     */
    @RequestMapping(method = RequestMethod.PUT,value = "user")
    public AjaxJson updateUser(@RequestBody User user){
        userService.updateUser(user);

        //返回用户信息
        User newUser = userService.getUser(user.getOpenid());
        newUser.setOpenid("");
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("user",newUser);
        return Ajax.success(map);
    }
    /**
     * 删除用户信息
     */
//    @RequestMapping(method = RequestMethod.DELETE,value = "user")
//    public AjaxJson deleteUser(){
//        return Ajax.success();
//    }

}
