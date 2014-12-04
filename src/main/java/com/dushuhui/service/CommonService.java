package com.dushuhui.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.dushuhui.common.Result;
import com.dushuhui.dao.UserDAO;
import com.dushuhui.entity.UserEntity;
import com.dushuhui.form.LoginForm;
import com.dushuhui.util.StringUtil;


/**
 * @author guichaoqun
 *
 */
@Service("commonService")
public class CommonService {

	@Qualifier("userDAO")
	private UserDAO userDAO;

}