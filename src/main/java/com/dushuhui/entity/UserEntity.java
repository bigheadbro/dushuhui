package com.dushuhui.entity;

import java.io.Serializable;

public class UserEntity implements Serializable
{
	private static final long serialVersionUID = 1L;
	//
	public int id;
	//
	public String name;
	
	public String mail;
	//
	public String password;
	//
	public String gmtCreate;
	//
	public String gmtModify;
}