package com.web.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "tb_mobile_user")
@ApiModel(value = "A CmsUser is a representation of greeting")  
@Inheritance(strategy = InheritanceType.JOINED)
public class MobileUser extends BaseEntity{

	@ApiModelProperty(value = "登录账号", required = true)
	@Column(name = "user_name", nullable = false, length = 20)
	private String userName;
	@Column(name = "password", nullable = false, length = 20)
	private String password;
	@Column(name = "password", nullable = false, length = 1)
	private String status;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
