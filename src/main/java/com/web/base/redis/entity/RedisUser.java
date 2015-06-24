package com.web.base.redis.entity;

import java.io.Serializable;


public class RedisUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id; 
	
	private String userName;
	
	private String password;
	
	private School school;
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public RedisUser() {
		super();
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
}
