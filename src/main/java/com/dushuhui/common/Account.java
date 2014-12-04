package com.dushuhui.common;

import java.io.Serializable;

public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5522426479916132746L;
	private boolean isLogin;
	private String cardno;
	private String userName;
	private String password;
	private int userId;
	private int gender;
	private String school;
	private int totalTime;
	private int q14time;
	private int q16time;
	//回答到第几题，避免修改之前问题
	private int state;

	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}
	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * @return the totalTime
	 */
	public int getTotalTime() {
		return totalTime;
	}
	/**
	 * @param totalTime the totalTime to set
	 */
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	/**
	 * @return the q14time
	 */
	public int getQ14time() {
		return q14time;
	}
	/**
	 * @param q14time the q14time to set
	 */
	public void setQ14time(int q14time) {
		this.q14time = q14time;
	}
	/**
	 * @return the q16time
	 */
	public int getQ16time() {
		return q16time;
	}
	/**
	 * @param q16time the q16time to set
	 */
	public void setQ16time(int q16time) {
		this.q16time = q16time;
	}
}
