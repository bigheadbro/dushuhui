package com.dushuhui.entity;

import java.io.Serializable;

public class UserEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	private int userId;
	//
	private String userName;
	//
	private String loginName;
	//
	private String password;
	//
	private String studentId;
	//
	private int userSex;
	//
	private String userBirthday;
	//
	private String schoolName;
	//
	private String classId;
	//
	private String userCity;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public int getUserSex() {
		return userSex;
	}

	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
}