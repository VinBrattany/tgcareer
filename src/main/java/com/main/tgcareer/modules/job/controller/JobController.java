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
    @RequestMapping(method = RequestMethod.GET,value = "job")
    public AjaxJson job(@RequestParam String jobid){
        Job job = jobService.getJob(jobid);
//        job.setOpenid("");
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("job",job);
        return Ajax.success(map);
    }
    /**
     * 新建job信息
     */
    @RequestMapping(method = RequestMethod.POST,value = "job")
    public AjaxJson newJob(@RequestBody Job job){
//        user.setOpenid(redisService.get(httpServletRequest.getParameter("token")).toString());
        jobService.saveJob(job);
        return Ajax.success();
    }
    /**
     * 更新job信息
     */
    @RequestMapping(method = RequestMethod.PUT,value = "job")
    public AjaxJson updateJob(@RequestBody Job job){
        jobService.updateJob(job);

        //返回job信息
        Job newJob = jobService.getJob(job.getId());
//        newUser.setOpenid("");
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("job",newJob);
        return Ajax.success(map);
    }

    /**
     * 删除用户信息
     */
//    @RequestMapping(method = RequestMethod.DELETE,value = "job")
//    public AjaxJson deleteJob(@RequestParam String jobid){
//        jobService.deleteJob(jobid);
//        return Ajax.success();
//    }
}
