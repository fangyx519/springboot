package com.fyx.springboot.service.impl;


import com.fyx.springboot.annotation.ServiceLog;
import com.fyx.springboot.dao.ManagerDao;
import com.fyx.springboot.entity.Manager;
import com.fyx.springboot.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private ManagerDao managerDao;

	@Override
	@ServiceLog(description = "注册")
	public boolean register(Manager mgr) {
		return managerDao.insert(mgr) > 0 ? true : false;
	}

	@Transactional(propagation= Propagation.SUPPORTS , readOnly=true)
	@Override
	@ServiceLog(description = "登录")
	public Manager login(String name, String pwd) {
		Manager mgr = managerDao.selectByName(name);
		if(mgr != null && mgr.getMgrpwd().equals(pwd)){
            return mgr;
		}
		return null;
	}

	@Override
	@ServiceLog(description = "根据主键查询管理员信息")
	public Manager selectByPrimaryKey(int mgrid) {
		return managerDao.selectByPrimaryKey(mgrid);
	}

	@Override
	public boolean insert(Manager manager) {
		return managerDao.insert(manager) > 0  ? true : false ;
	}

	@Override
	public boolean deleteByPrimaryKey(int mgrid) {
		return managerDao.deleteByPrimaryKey(mgrid) > 0 ? true : false;
	}

	@Override
	public boolean updateByPrimaryKey(Manager manager) {
		return managerDao.updateByPrimaryKey(manager) > 0 ? true : false;
	}

}
