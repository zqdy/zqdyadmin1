package com.zqdy.dao;

public class NameValueBo {
	
	public String name;
	
	private String value;
	
	public NameValueBo(String name,String value){
		this.name = name;
		this.value = value;
	}
	public NameValueBo(){
		
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
