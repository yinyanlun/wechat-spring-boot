package com.yin.wechat.web;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping({"/","/index"})
	public String index(){
		return "app/main";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.isAuthenticated());
		if(subject.isAuthenticated())
		{
		 return "redirect:/";
		}
		return "login";
	}
	@RequestMapping(value="/403",method=RequestMethod.GET)
	public String erro403(){
		return "403";
	}
	
	// 登录提交地址和applicationontext-shiro.xml配置的loginurl一致。 (配置文件方式的说法)
		@RequestMapping(value="/login",method=RequestMethod.POST)
		public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {
			System.out.println("HomeController.login()");
			// 登录失败从request中获取shiro处理的异常信息。
			// shiroLoginFailure:就是shiro异常类的全类名.
			String exception = (String) request.getAttribute("shiroLoginFailure");
			HttpSession session=request.getSession();

			System.out.println("exception=" + exception.toString());
			String msg = "";
			if (exception != null) {
				if (UnknownAccountException.class.getName().equals(exception)) {
					System.out.println("UnknownAccountException -- > 账号不存在：");
					msg = "账号不存在";
				} else if (IncorrectCredentialsException.class.getName().equals(exception)) {
					System.out.println("IncorrectCredentialsException -- > 密码不正确：");
					msg = "密码不正确：";
				} else if ("kaptchaValidateFailed".equals(exception)) {
					System.out.println("kaptchaValidateFailed -- > 验证码错误");
					msg = "验证码错误";
				} else {
					msg = "else >> "+exception;
					System.out.println("else -- >" + exception);
				}
			}
			map.put("msg", msg);
			// 此方法不处理登录成功,由shiro进行处理.
			return "/login";
		}
		   @RequestMapping("/logOut")
		    public String logOut(HttpSession session) {
		        Subject subject = SecurityUtils.getSubject();
		        subject.logout();
//		        session.removeAttribute("user");
		        return "redirect:/login";
		    }
}
