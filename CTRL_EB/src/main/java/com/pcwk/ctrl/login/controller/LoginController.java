package com.pcwk.ctrl.login.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller("loginController")
@RequestMapping("login")
public class LoginController {

	final Logger LOG = LogManager.getLogger(getClass());
	
	public LoginController () {}
	
	@RequestMapping(value = "/login.do",method = RequestMethod.GET)
	public String loginPageGET() {
		LOG.debug("=================================");
		LOG.debug("loginPageGET()");
		LOG.debug("=================================");
		
		return "login/naver_login";
	}
	
	@RequestMapping(value = "/callback.do")
	public String naverLogin(HttpServletRequest req) throws Exception {
		LOG.debug("=================================");
		LOG.debug("naverLogin()");
		LOG.debug("=================================");
		
		return "login/naver_callback";
	}


	
}
