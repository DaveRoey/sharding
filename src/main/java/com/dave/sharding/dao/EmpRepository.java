package com.dave.sharding.dao;

import com.dave.sharding.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author Dave
 * @Date 2018/12/23
 * @Description
 */
@org.springframework.stereotype.Repository
public interface EmpRepository extends CrudRepository<Employee,Long> {

    List<Employee> findEmployeesByCode(String code);
}
