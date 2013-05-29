package com.zqdy.action;
 
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.zqdy.core.action.BaseAction;
import com.zqdy.core.common.Constants;
import com.zqdy.dao.po.TUser;
import com.zqdy.service.LoginService; 
 

@SuppressWarnings("serial")
public class LoginAction extends BaseAction {
	private static Logger logger;

	static {
		logger = Logger.getLogger(LoginAction.class);
	}
	
	private LoginService loginService;

	private String userCode;

	private String password;
 

	public String goLogin() {
		 
		return SUCCESS;
	}

	public String doLogin() throws Exception {
		// this.checkRigtht(request,response, "123-654-987_560");
		logger.info("doLogin method is start....");
		this.request = ServletActionContext.getRequest();
		this.session = request.getSession();
		 
		String userCode = request.getParameter("userCode");
		String password = request.getParameter("password");
		// try {
		// password = Crypt.unilateral(password.getBytes());
		// } catch (Exception e) {
		// throw new BaseAppException(e.toString());
		// }
		 
		TUser user = loginService.login(userCode, password);
		
		if(user==null){
			logger.info(userCode+"登录失败");
			return "error";
		}else{
			session.setAttribute("userCode", user.getUserCode());
			logger.info(userCode+"登录成功");
		}
		
		session.setAttribute("user", user);
		return SUCCESS;
	}

	public String goChangPwd() {
		init();
		flag = "-1";
		return SUCCESS;
	}

	public String doChangPwd() {
//		String userCode = user.getUserCode();
//		String oldPwd = user.getOldPwd();
//		String newPwd = user.getPwd();
//		String saveFlag = loginService.saveChangPwd(userCode, oldPwd, newPwd);
//		if ("1".equals(saveFlag)) {
//			flag = "1";
//			msg = "修改密码成功!";
//		} else if ("2".equals(saveFlag)) {
//			flag = "0";
//			msg = "此用户已被管理员冻结,不能修改密码,请与管理员联系!";
//		} else if ("0".equals(saveFlag)) {
//			flag = "0";
//			msg = "原密码错误!";
//		}
		return SUCCESS;
	}

	/**
	 * 锟矫伙拷锟斤拷陆
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String loginOut() throws Exception {
		logger.info("loginOut method is start....");
		init();
		flag = "-1";
		session.setAttribute("rightCodes", null);
		session.setAttribute("raterCode", null);
		session.setAttribute("accountForm", null);
		
		session.setAttribute(Constants.SESSION_USER, null);
		session = null;
		logger.info("loginOut method end");
		return SUCCESS;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	 

}
