package com.dushuhui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import com.dushuhui.common.Account;
import com.dushuhui.common.Result;

import com.dushuhui.entity.UserEntity;
import com.dushuhui.form.LoginForm;
import com.dushuhui.service.CommonService;

@Controller
@RequestMapping("/")
@SessionAttributes({"account"})
public class CommonController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(CommonController.class);
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	
	@RequestMapping(value="/")
	public ModelAndView main(final HttpServletRequest request,final HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/main/index");

		return mv;
	}
	
	@RequestMapping(value="/index")
	public ModelAndView index(final HttpServletRequest request,final HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/main/index");

		return mv;
	}
}
