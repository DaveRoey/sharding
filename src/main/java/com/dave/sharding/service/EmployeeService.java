package com.dave.sharding.service;

import com.dave.sharding.entity.Employee;

import java.util.List;

/**
 * Created by Dave on 2018/12/20
 * Describes
 */
public interface EmployeeService {

    void addEmployee(Employee employee);

    void batchSave(List<Employee> employees);

    Employee findEmployeeById(Long empId);

    Employee findEmployeeByCode(String  code);
}
