package com.main.tgcareer.modules.salary.service;

import com.main.tgcareer.modules.salary.dao.SalaryDao;
import com.main.tgcareer.modules.salary.entity.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SalaryService {

    @Autowired
    SalaryDao salaryDao;

    public List<Salary> getSalary(Map map){
        return salaryDao.getSalary(map);
    }

    @Transactional
    public void saveSalary(Salary salary){
        salaryDao.saveSalary(salary);
    }

    @Transactional
    public void updateSalary(Salary salary){
        salaryDao.updateSalary(salary);
    }

    @Transactional
    public void deleteSalary(String id){
        salaryDao.deleteSalary(id);
    }
}
