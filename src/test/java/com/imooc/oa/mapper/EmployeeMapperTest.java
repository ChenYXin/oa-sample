package com.imooc.oa.mapper;

import com.imooc.oa.entity.Employee;
import com.imooc.oa.utils.MyBatisUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class EmployeeMapperTest {

    @Test
    public void selectById() {
        Employee emp = (Employee) MyBatisUtils.executeQuery(sqlSession -> {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.selectById(4l);
            System.out.println(employee);
            return employee;
        });
    }

    @Test
    public void selectByParams1() {
        Map params = new HashMap<>();
        params.put("level", 7);
        params.put("departmentId",2);
        MyBatisUtils.executeQuery(sqlSession -> {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> employee = employeeMapper.selectByParams(params);
            System.out.println(employee);
            return employee;
        });
    }

    @Test
    public void selectByParams2() {
        Map params = new HashMap<>();
        params.put("level", 8);
        MyBatisUtils.executeQuery(sqlSession -> {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> employee = employeeMapper.selectByParams(params);
            System.out.println(employee);
            return employee;
        });
    }
}