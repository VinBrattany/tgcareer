package com.main.tgcareer.modules.salary.dao;

import com.main.tgcareer.modules.salary.entity.Salary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface SalaryDao {

    public List<Salary> getSalary(Map map);

    public void saveSalary(Salary salary);

    public void updateSalary(Salary salary);

    public void deleteSalary(String id);
}
