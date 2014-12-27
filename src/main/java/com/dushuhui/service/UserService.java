package com.dushuhui.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.dushuhui.common.Result;
import com.dushuhui.dao.UserDAO;
import com.dushuhui.entity.UserEntity;
import com.dushuhui.form.LoginForm;
import com.dushuhui.form.RegForm;
import com.dushuhui.util.StringUtil;

/**
 * @author guichaoqun
 *
 */
@Service("userService")
public class UserService {
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	@Qualifier("userDAO")
	private UserDAO userDAO;
		
	public Result register(RegForm form, Errors errors)
	{
		logger.debug("before register");
		Result result = new Result();
		
		if(StringUtil.isEmpty(form.getName()))// 第一步， 判断注册名是否为空
		{
			errors.rejectValue("name", "USER_NAME_IS_NOT_NULL");
			return result;
		}
		else if(StringUtil.isIlegal(form.getName()))
		{
			errors.rejectValue("name", "USER_NAME_IS_ILLEGAL");
			return result;
		}
		else if(userDAO.queryUserEntityByName(form.getName().trim()) != null) 
		{
			errors.rejectValue("name", "USER_NAME_IS_EXISTS");
			return result;
		}
		else if(userDAO.queryUserEntityByMail(form.getMail()) != null ) // 第二步，判断注册用户名是否存在
		{
			errors.rejectValue("mail", "MAIL_IS_EXISTS");
			return result;
		}
		if(StringUtil.isEmpty(form.getPwd())) //  第三步，判断密码不能为空
		{
			errors.rejectValue("pwd", "PASSWORD_IS_NOT_NULL");
			return result;
		}
		
		UserEntity user = new UserEntity();
		user.name = form.getName();
		user.password = StringUtil.encrypt(form.getPwd()); // 对密码加密
		user.mail = form.getMail(); // 设置邮箱地址

		logger.debug("before insertUserEntity");
		userDAO.insertUserEntity(user);
		logger.debug("after insertUserEntity");
		result.add("user", user);
		return result;
	}

	public Result checkLogin(LoginForm form, Errors errors)
	{
		Result result = new Result();
		if(StringUtil.isEmpty(form.getMail()))
		{
			errors.rejectValue("mail", "MAIL_IS_NOT_NULL"); // 邮箱不能为空
			return result;
		}
		UserEntity user = userDAO.queryUserEntityByMail(form.getMail());
		if(user == null)
		{
			errors.rejectValue("mail", "MAIL_IS_NOT_EXISTS"); // 邮箱不存在
			return result;
		}
		if(StringUtil.isNotEqual(user.password, StringUtil.encrypt(form.getPassword())))
		{
			errors.rejectValue("password", "PASSWORD_ERROR"); // 密码错误
			return result;
		}
		result.add("user", user);
		return result;
	}
	
	public void changePwd(RegForm form, Errors errors)
	{
		if(StringUtil.isEmpty(form.getPwd())) //  第三步，判断密码不能为空
		{
			errors.rejectValue("pwd", "PASSWORD_IS_NOT_NULL");
			return;
		}
		if(StringUtil.isEmpty(form.getPwd1())) //  第三步，判断新密码不能为空
		{
			errors.rejectValue("pwd1", "PASSWORD_IS_NOT_NULL");
			return;
		}
		if(StringUtil.isEmpty(form.getPwd2())) //  第三步，判断重复新密码不能为空
		{
			errors.rejectValue("pwd2", "PASSWORD_IS_NOT_NULL");
			return;
		}
		
		if(!StringUtil.isEqual(form.getPwd1(), form.getPwd2()))
		{
			errors.rejectValue("pwd2", "PASSWORD_IS_NOT_SAME"); // 两次新密码输入不一致
			return;
		}
		
		UserEntity user = userDAO.queryUserEntityById(form.getUserid());
		if(StringUtil.isNotEqual(StringUtil.encrypt(form.getPwd()), user.password))
		{
			errors.rejectValue("pwd", "OLD_PASSWORD_ERROR"); // 旧密码输入不正确
			return;
		}
		user.password = (StringUtil.encrypt(form.getPwd1())); // 新密码
		userDAO.updateUserPwdById(user);
	}

	public Result getUserEntity(int userId)
	{
		Result result = new Result();
		UserEntity user = userDAO.queryUserEntityById(userId);
		result.add("user", user);
		return result;
	}
	
}
