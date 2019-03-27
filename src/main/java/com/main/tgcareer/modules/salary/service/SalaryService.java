package com.main.tgcareer.modules.salary.service;

import com.main.tgcareer.modules.salary.dao.SalaryDao;
import com.main.tgcareer.modules.salary.entity.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SalaryService {

    @Autowired
    SalaryDao salaryDao;

    public Salary getSalary(String id){
        return salaryDao.getSalary(id);
    }

    public List<Salary> getAllSalary(Map map){
        return salaryDao.getAllSalary(map);
    }

    public void saveSalary(Salary salary){
        salaryDao.saveSalary(salary);
    }

    public void updateSalary(Salary salary){
        salaryDao.updateSalary(salary);
    }

    public void deleteSalary(String id){
        salaryDao.deleteSalary(id);
    }
}
