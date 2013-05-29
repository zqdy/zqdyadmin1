package com.zqdy.service;

import com.zqdy.dao.po.TUser;

public interface LoginService {
	
	public TUser login(String userCode,String password); 
	

}
