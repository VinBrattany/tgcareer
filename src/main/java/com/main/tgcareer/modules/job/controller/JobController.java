package com.main.tgcareer.modules.job.controller;

import com.main.tgcareer.common.jason.Ajax;
import com.main.tgcareer.common.jason.AjaxJson;
import com.main.tgcareer.common.redis.RedisServiceImpl;
import com.main.tgcareer.common.web.BaseController;
import com.main.tgcareer.modules.job.entity.Job;
import com.main.tgcareer.modules.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("job")
public class JobController extends BaseController{
    @Autowired
    private JobService jobService;

    @Autowired
    private RedisServiceImpl redisService;

    /*
    * 获取job信息
     */
    @RequestMapping(method = RequestMethod.GET,value = "job{id}")
    public AjaxJson job(@PathVariable String id){
        Job job = jobService.getJob(id);
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("job",job);
        return Ajax.success(map);
    }
    /**
     * 新建job信息
     */
    @RequestMapping(method = RequestMethod.POST,value = "job")
    public AjaxJson newJob(@RequestBody Job job){
        jobService.saveJob(job);
        return Ajax.success();
    }
    /**
     * 更新job信息
     */
    @RequestMapping(method = RequestMethod.PUT,value = "job")
    public AjaxJson updateJob(@RequestBody Job job){
        jobService.updateJob(job);
        return Ajax.success();
    }

    /**
     * 删除用户信息
     */
    @RequestMapping(method = RequestMethod.DELETE,value = "job{id}")
    public AjaxJson deleteJob(@PathVariable String id){
        jobService.deleteJob(id);
        return Ajax.success();
    }
}
