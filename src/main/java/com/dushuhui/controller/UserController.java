package com.dushuhui.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import com.dushuhui.common.Account;
import com.dushuhui.common.Constant;
import com.dushuhui.common.Result;
import com.dushuhui.entity.UserEntity;
import com.dushuhui.form.RegForm;
import com.dushuhui.service.CommonService;
import com.dushuhui.service.UserService;
import com.dushuhui.util.CookieUtil;
import com.dushuhui.util.StringUtil;
import com.dushuhui.util.Util;


@Controller
@RequestMapping("/user")
@SessionAttributes({"account"})
public class UserController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	/**
	 * 通用URL跳转， 统一将  /user/*** 等未映射的URL重定向到login页面
	 * @return
	 */
	@RequestMapping(value = "/*")
	public ModelAndView common()
	{
		return new ModelAndView(new RedirectView("user/main")); 
	}

	@RequestMapping(value = "/logoff")
	public ModelAndView logoff(final HttpServletRequest request,final HttpServletResponse response, Model model) {
		CookieUtil.removeCookie(request, response, Constant.REMEMBER_ME);
		request.getSession().invalidate();
		model.asMap().remove("account");
		return new ModelAndView(new RedirectView("/index"));
		
	}
	
	@RequestMapping(value="/reg")
	public ModelAndView reg(final HttpServletRequest request,
			final HttpServletResponse response, @ModelAttribute("form")RegForm form, BindingResult result) 
	{
		Account accnt = (Account) WebUtils.getSessionAttribute(request, "account");
		if(accnt != null && accnt.isLogin())
		{
			return new ModelAndView(new RedirectView("/user/main")); 
		}
		if(isDoSubmit(request))
		{
			Result re = userService.register(form, result);
			
			if(!result.hasErrors())
			{
				UserEntity user = (UserEntity)re.get("user");
				Account account = new Account();
				account.setLogin(true); // 登录成功标识
				account.setUserName(user.name); // 用户登录名
				account.setMail(user.mail);
				account.setUserId(user.id); // 用户ID
				request.getSession().setAttribute("account", account);
				// 注册成功， 跳转到登陆页面
				return new ModelAndView(new RedirectView("/user/main")); 
			}
			else
			{
				// 注册失败， 返回注册页面，并显示出错提示信息
				ModelAndView model = new ModelAndView("/user/reg");
				return model;
			}
		}
		else
		{
			ModelAndView model = new ModelAndView("/user/reg");
			return model;
		}
	}
}
