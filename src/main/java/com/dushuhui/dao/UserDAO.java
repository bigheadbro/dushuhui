package com.dushuhui.dao;

import com.dushuhui.entity.UserEntity;

public interface UserDAO {
	/**
	 * @param id
	 * @return
	 */
	UserEntity queryUserEntityById(int id);
	
	UserEntity queryUserEntityByName(String name);

	UserEntity queryUserEntityByMail(String mail);
	
	int updateUserPwdById(UserEntity user);
	
	int insertUserEntity(UserEntity user);
}
