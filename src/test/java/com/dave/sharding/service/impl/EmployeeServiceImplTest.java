package com.dave.sharding.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dave.sharding.custom.route.CommonDataBaseStrategy;
import com.dave.sharding.custom.utils.MurmurHashUtils;
import com.dave.sharding.dao.EmpRepository;
import com.dave.sharding.entity.Employee;
import com.dave.sharding.mapper.EmpMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.Assert.*;

/**
 * @Author Dave
 * @Date 2018/12/23
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpRepository empRepository;

    @Test
    public void addEmployee() {
        List<Employee> employees = empMapper.selectList(null);
        Assert.assertEquals(1, employees.size());
        employees.forEach(System.out::println);

    }

    public static void main(String[] args) {

    }


    @Test
    public void batchSave() {
        List<Employee> employees = new ArrayList<>();
        LongStream.range(1, 10000)
                .forEach(e -> {
                    Employee employee = new Employee();
                    employee.setId(e);
                    employee.setCode(e + "");
                    employee.setCreateTime(new Date());
                    employees.add(employee);
                });

        empMapper.batchSaveEmp(employees);
    }


    @Test
    public void selectPageByVo() {
        Page<Employee> page = new Page<>();
        page.setSize(10);
        IPage<Employee> employeeIPage = empMapper.selectPageVo(page, new Date());
        System.out.println("size:" + employeeIPage.getRecords().size());
    }


    @Test
    public void getEmpsFromEsByCode() {
        String code = "13";
        List<Employee> employees = empRepository.findEmployeesByCode(code);
        Assert.assertEquals(3, employees.size());

    }

    @Test
    public void writeTest() {

        ArrayList<Employee> employees = new ArrayList<>();
        LongStream.range(20000, 25000)
                .forEach(e -> {
                    Employee employee = new Employee();
                    employee.setId(e);
                    employee.setCode(e * e + "");
                    employee.setCreateTime(new Date());
                    employees.add(employee);

                });

        empRepository.saveAll(employees);

    }


}