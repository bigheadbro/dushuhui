package com.dushuhui.dao;

import com.dushuhui.entity.UserEntity;

public interface UserDAO {
	/**
	 * @param id
	 * @return
	 */
	UserEntity queryUserEntityById(int id);

	UserEntity queryUserEntityByMail(String cn);
}
