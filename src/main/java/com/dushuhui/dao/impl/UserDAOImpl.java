package com.dushuhui.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.dushuhui.dao.UserDAO;
import com.dushuhui.entity.UserEntity;

@Repository("userDAO")
public class UserDAOImpl extends SqlSessionDaoSupport implements UserDAO {

	@Override
	public UserEntity queryUserEntityById(int id) {
		return this.getSqlSession().selectOne("queryUserEntityById", id);
	}
	
	@Override
	public UserEntity queryUserEntityByName(String name)
	{
		return this.getSqlSession().selectOne("queryUserEntityByName", name);
	}
	
	@Override
	public UserEntity queryUserEntityByMail(String mail) {
		return this.getSqlSession().selectOne("queryUserEntityByMail", mail);
	}

	@Override
	public int updateUserPwdById(UserEntity user)
	{
		return this.getSqlSession().update("updateUserPwdById", user);
	}
	
	@Override
	public int insertUserEntity(UserEntity user)
	{
		return this.getSqlSession().insert("insertUserEntity",user);
	}
}
