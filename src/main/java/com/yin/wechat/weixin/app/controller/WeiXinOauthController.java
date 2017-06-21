package com.yin.wechat.weixin.app.controller;

import java.io.IOException;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

import com.yin.wechat.service.WeixinUserInfoService;
import com.yin.wechat.weixin.app.config.WxConfig;
import com.yin.wechat.weixin.app.model.WeixinUserInfo;
import com.yin.wechat.weixin.app.utils.CommonUtil;


/***
 * 授权回调获取 
 *
 */
@RestController
@RequestMapping("/weixin")
public class WeiXinOauthController {
	@Autowired
	WeixinUserInfoService weixinUserInfoService;
	/** 
     * 
     * 殷雷雷
     * @param response 
     * @throws IOException 
     */  
    @RequestMapping(value = "/getOpenid")  
    public void getCode(HttpServletResponse response,HttpServletRequest request) throws IOException { 
    	String code = request.getParameter("code");
    	String pageName = request.getParameter("state");
    	String url=WxConfig.ACCESS_TOKEN.replace("APPID", WxConfig.APP_ID).replace("SECRET", WxConfig.APP_SECRET).replaceAll("CODE", code);
    	Map<String,String> map=CommonUtil.getOppenidByCode(url);
    	String openId=map.get("openId");
    	System.out.println("获取openId="+openId);
    	request.getSession().setAttribute("openId", openId);
    	WeixinUserInfo weixinUserInfo=weixinUserInfoService.getWeixinUserInfoByopenId(openId);
    	if("1".equals(weixinUserInfo.getState()))
    	{
    		response.sendRedirect("http://yin.tunnel.2bdata.com/yin/weixin/jssdk.html"); 
    	}
    	else{
    		response.sendRedirect("http://yin.tunnel.2bdata.com/yin/weixin/error.html"); 
    	}
    } 
    
}
