package com.dave.sharding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dave.sharding.entity.Employee;
import com.dave.sharding.mapper.EmpMapper;
import com.dave.sharding.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dave on 2018/12/20
 * Describes
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addEmployee(Employee employee) {
        empMapper.insert(employee);
    }

    @Override
    public void batchSave(List<Employee> employees) {
        empMapper.batchSaveEmp(employees);
    }

    @Override
    public Employee findEmployeeById(Long empId) {
        return empMapper.selectById(empId);
    }

    @Override
    public Employee findEmployeeByIdAndCode(long id, String code) {

        String key = String.format("shard:%s:%s", id, code);
        Employee value = (Employee) redisTemplate.opsForValue().get(key);
        if (null == value) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("code", code);
            value = empMapper.selectOne(new QueryWrapper<Employee>().allEq(map));
            if (value != null) {
                redisTemplate.opsForValue().set(key, value);
            }
        }
        return value;


    }

    @Override
    public Employee findEmpByCode(String code) {
        return empMapper.selectOne(new LambdaQueryWrapper<Employee>().eq(Employee::getCode, code));
    }

    @Override
    public IPage<Employee> selectPageByVo(Page<Employee> page, Date createTime) {
        return empMapper.selectPageVo(page, createTime);
    }
}
