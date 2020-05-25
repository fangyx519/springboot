package com.fyx.springboot.service.impl;

import com.fyx.springboot.annotation.ServiceLog;
import com.fyx.springboot.dao.EmployeeDao;
import com.fyx.springboot.entity.Employee;
import com.fyx.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	@ServiceLog(description = "添加员工")
	public boolean addEmployee(Employee emp) {
		return employeeDao.insert(emp) > 0;
	}

	@Override
	@ServiceLog(description = "删除员工")
	public boolean removeEmployee(Integer id) {
		return employeeDao.deleteByPrimaryKey(id) > 0;
	}

	@Override
	@ServiceLog(description = "修改员工信息")
	public boolean modifyEmployee(Employee emp) throws Exception {
        throw new Exception("出错了！");
		/*return employeeDao.updateByPrimaryKey(emp) > 0;*/
	}

	@Transactional(propagation= Propagation.SUPPORTS , readOnly=true)
	@Override
	@ServiceLog(description = "通过姓名查询员工信息")
	public List<Employee> selectByName(String name) {
		List<Employee> emp = employeeDao.selectByName(name);
		return emp;
	}

	@Transactional(propagation= Propagation.SUPPORTS , readOnly=true)
	@Override
	@ServiceLog(description = "通过id查询员工信息")
	public Employee selectById(Integer id) {
		Employee emp = employeeDao.selectById(id);
		return emp;
	}

	@Transactional(propagation= Propagation.SUPPORTS , readOnly=true)
	@Override
	@ServiceLog(description = "查询所有员工")
	public List<Employee> selectAllEmplyee() {
		List<Employee> emps = employeeDao.selectAllEmp();
		return emps;
	}

}
