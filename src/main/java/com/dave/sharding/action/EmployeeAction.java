package com.dave.sharding.action;

import com.dave.sharding.custom.algorithm.PartitionByMurmurHash;
import com.dave.sharding.entity.Employee;
import com.dave.sharding.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dave on 2018/12/20
 * Describes
 */
@RestController
@RequestMapping(value = "/emp/*")
public class EmployeeAction extends BaseAction {

    @Autowired
    private EmployeeService employeeService;


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addEmp(@RequestBody Employee employee) {
        HashMap<String, Object> result = new HashMap<>();
        employeeService.addEmployee(employee);
        result.put("result", "success");

        return getSuccessMap(result, HttpStatus.OK);
    }



    @RequestMapping(value = "find", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> find(@RequestBody Employee employee) {
        HashMap<String, Object> result = new HashMap<>();
        Employee emp = employeeService.findEmployeeByIdAndCode(employee.getId(), employee.getCode());
        result.put("result", emp);

        return getSuccessMap(result, HttpStatus.OK);
    }


    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> fifindByIdnd(@RequestBody Employee employee) {
        HashMap<String, Object> result = new HashMap<>();
        Employee emp = employeeService.findEmployeeById(employee.getId());
        result.put("result", emp);
        return getSuccessMap(result, HttpStatus.OK);
    }


    @RequestMapping(value = "findByCode", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findByCode(@RequestBody Employee employee) {
        HashMap<String, Object> result = new HashMap<>();
        Employee emp = employeeService.findEmpByCode(employee.getCode());
        result.put("result", emp);
        return getSuccessMap(result, HttpStatus.OK);
    }

}
