package com.fyx.springboot.controller;

import com.fyx.springboot.config.exception.BizException;
import com.fyx.springboot.entity.Employee;
import com.fyx.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestExceptionHandlerController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("showAllEmp")
    public @ResponseBody List<Employee> showAllEmp(){
		return employeeService.selectAllEmplyee();
	}

	@DeleteMapping("delete/{id}")
    public boolean removeEmp(@PathVariable("id") Integer id){
		System.out.println("开始删除...");
		//这里故意造成一个异常，并且不进行处理
		Integer.parseInt("abc123");
		return true;
	}

	@PutMapping("update")
    public boolean modifyEmp(@RequestBody Employee employee) throws Exception {
		System.out.println("开始更新...");
		//这里故意造成一个空指针的异常，并且不进行处理
		String str=null;
		str.equals("111");
		return true;
	}

	@PostMapping("insert")
    public boolean addEmp(@RequestBody Employee employee){
		System.out.println("开始新增...");
		//如果姓名为空就手动抛出一个自定义的异常！
		if(employee.getName()==null){
			throw new BizException("-1","用户姓名不能为空！");
		}
		return true;
	}

}
