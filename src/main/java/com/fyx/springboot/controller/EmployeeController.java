package com.fyx.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.fyx.springboot.entity.Employee;
import com.fyx.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("showAllEmp")
    public @ResponseBody List<Employee> showAllEmp(){
		return employeeService.selectAllEmplyee();
	}

	@DeleteMapping("delete/{id}")
    public JSONObject removeEmp(@PathVariable("id") Integer id){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result",employeeService.removeEmployee(id));
		return jsonObject;
	}

	@GetMapping("queryByName/{name}")
    public List<Employee> queryByName(@PathVariable("name") String name){
		List<Employee> empList ;
		if(name != ""){
			empList = employeeService.selectByName(name);
		}else{
			empList = employeeService.selectAllEmplyee();
		}
		return empList;
	}

	@GetMapping("queryByName")
	public @ResponseBody List<Employee> queryByName(){
		return employeeService.selectAllEmplyee();
	}

	@GetMapping("{id}")
    public @ResponseBody Employee showModifyEmp(@PathVariable("id") Integer id){
		return employeeService.selectById(id);
	}

	@PutMapping("update")
    public JSONObject modifyEmp(@RequestBody Employee employee) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result",employeeService.modifyEmployee(employee));
		return jsonObject;
	}

	@PostMapping("insert")
    public JSONObject addEmp(@RequestBody Employee employee){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result",employeeService.addEmployee(employee));
		return jsonObject;
	}

}
