package com.dave.sharding.service.impl;

import com.dave.sharding.dao.EmployeeRepository;
import com.dave.sharding.entity.Employee;
import com.dave.sharding.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dave on 2018/12/20
 * Describes
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeDao;

    @Override
    public void addEmployee(Employee employee) {
         employeeDao.insert(employee);
    }

    @Override
    public void batchSave(List<Employee> employees) {
        employeeDao.saveAll(employees);
    }

    @Override
    public Employee findEmployeeById(Long empId) {
        return employeeDao.getOne(empId);
    }

    @Override
    public Employee findEmployeeByCode(String code) {
        return employeeDao.findEmployeeByCode(code);
    }
}
