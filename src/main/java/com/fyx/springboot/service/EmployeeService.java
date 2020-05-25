package com.fyx.springboot.service;
import com.fyx.springboot.entity.Employee;

import java.util.List;

public interface EmployeeService {

	boolean addEmployee(Employee emp);

	boolean removeEmployee(Integer employeeId);

	boolean modifyEmployee(Employee emp) throws Exception;

	Employee selectById(Integer id);

	List<Employee> selectByName(String name);

	List<Employee> selectAllEmplyee();

}
