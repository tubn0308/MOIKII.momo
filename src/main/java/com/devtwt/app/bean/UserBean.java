package com.devtwt.app.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userId = "";
	private String userName = "";
	private String userPassword = "";
	private String roleId = "";
	private String roleName = "";
	private String createId = "";
	private String createDate = "";
	private String updateId = "";
	private String updateDate = "";
	private String deleteFlag = "";
	
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", userName=" + userName
				+ ", userPassword=" + userPassword + ", roleId=" + roleId
				+ ", roleName=" + roleName + ", createId=" + createId
				+ ", createDate=" + createDate + ", updateId=" + updateId
				+ ", updateDate=" + updateDate + ", deleteFlag=" + deleteFlag
				+ "]";
	}

}
