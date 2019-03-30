package com.main.tgcareer.modules.salary.controller;

import com.main.tgcareer.common.jason.Ajax;
import com.main.tgcareer.common.jason.AjaxJson;
import com.main.tgcareer.modules.salary.entity.Salary;
import com.main.tgcareer.modules.salary.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("salary")
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @RequestMapping(method = RequestMethod.GET,value = "salary")
    public AjaxJson salary(@RequestParam Map<String,String> params){
        LinkedHashMap<String,Object> maps = new LinkedHashMap<>();
        maps.put("data",salaryService.getSalary(params));
        return Ajax.success(maps);
    }

    @RequestMapping(method = RequestMethod.POST,value = "salary")
    public AjaxJson newUser(@RequestBody Salary salary){
        salaryService.saveSalary(salary);
        return Ajax.success();
    }

    @RequestMapping(method = RequestMethod.PUT,value = "salary")
    public AjaxJson updateUser(@RequestBody Salary salary){
        salaryService.updateSalary(salary);
        return Ajax.success();
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "salary{id}")
    public AjaxJson deleteSalary(@PathVariable String id){
        salaryService.deleteSalary(id);
        return Ajax.success();
    }
}
