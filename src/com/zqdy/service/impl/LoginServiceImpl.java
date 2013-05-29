package com.zqdy.service.impl;

import com.zqdy.dao.CommonDao;
import com.zqdy.dao.po.TUser;
import com.zqdy.service.LoginService;

public class LoginServiceImpl implements LoginService {
	
	private CommonDao commonDao;

	public TUser login(String userCode, String password) {
		
		return commonDao.getUserByUserCodeAndPassword(userCode, password);
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	 

}
