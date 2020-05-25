package com.fyx.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.fyx.springboot.entity.Manager;
import com.fyx.springboot.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/mgr")
public class ManagerController {

	@Autowired
	ManagerService managerService;

	@PostMapping("/login")
    public JSONObject login(@RequestBody Manager manager, HttpServletRequest request){
		System.out.println("login");
		HttpSession session = request.getSession();
        JSONObject jsonObject = new JSONObject();
		Manager manager1 = managerService.login(manager.getMgrname(),manager.getMgrpwd());
		if(!StringUtils.isEmpty(manager1)){
		    session.setAttribute("manager",manager1);
			jsonObject.put("result",true);
		}else {
			jsonObject.put("result",false);
		}
        return jsonObject;
	}

	@PostMapping("/register")
    public JSONObject register(@RequestBody Manager manager){
		boolean result = managerService.register(manager);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result",result);
		return jsonObject;
	}

}
