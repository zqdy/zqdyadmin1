package com.zqdy.dao.po;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */

public class TUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userName;
	private String password;
	private Integer loginCount;
	private String userCode;
	private Integer status;

	// Constructors

	/** default constructor */
	public TUser() {
	}

	/** minimal constructor */
	public TUser(String userName) {
		this.userName = userName;
	}

	/** full constructor */
	public TUser(String userName, String password, Integer loginCount,
			String userCode, Integer status) {
		this.userName = userName;
		this.password = password;
		this.loginCount = loginCount;
		this.userCode = userCode;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}