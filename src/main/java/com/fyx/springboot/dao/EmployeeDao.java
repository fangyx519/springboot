package com.fyx.springboot.dao;

import com.fyx.springboot.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface EmployeeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByName(String name);

    Employee selectById(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    
    List<Employee> selectAllEmp();
}