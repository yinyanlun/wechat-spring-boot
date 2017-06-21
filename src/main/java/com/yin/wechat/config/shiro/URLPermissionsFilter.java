package com.yin.wechat.config.shiro;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.stereotype.Component;

import com.yin.wechat.model.SysPermission;
import com.yin.wechat.model.SysRole;
import com.yin.wechat.model.UserInfo;
import com.yin.wechat.service.UserInfoService;


@Component("urlPermissionsFilter")
public class URLPermissionsFilter extends PermissionsAuthorizationFilter{
//	@Resource
//	private UserInfoService userInfoService;
//
//	@Override
//	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
//		String curUrl = getRequestUrl(request);
//		Subject subject = SecurityUtils.getSubject();
//		if(subject.getPrincipal() == null 
//				|| StringUtils.endsWithAny(curUrl, ".js",".css",".html")
//				|| StringUtils.endsWithAny(curUrl, ".jpg",".png",".gif", ".jpeg")
//				|| StringUtils.equals(curUrl, "/unauthor")) {
//			return true;
//		}
//		List<String> urls = new ArrayList<>();
//		
//		UserInfo	userInfo=userInfoService.getUserInfoByUserName(subject.getPrincipal().toString());
//		List<SysRole> list=userInfo.getRoleList();
//		System.out.println(list.size());
//	      for(SysRole tmp:list){
//	            List<SysPermission> SysPermissionList=tmp.getPermissions();
//	            for(SysPermission tmp2:SysPermissionList){
//	            	 System.out.println(tmp2.getUrl());
//	            	 urls.add(tmp2.getUrl());
//	            }
//	        }
//		return urls.contains(curUrl);
//	}
//	
//	/**
//	 * 获取当前URL+Parameter
//	 * @author lance
//	 * @since  2014年12月18日 下午3:09:26
//	 * @param request	拦截请求request
//	 * @return			返回完整URL
//	 */
//	private String getRequestUrl(ServletRequest request) {
//		HttpServletRequest req = (HttpServletRequest)request;
//		String queryString = req.getQueryString();
//
//		queryString = StringUtils.isBlank(queryString)?"": "?"+queryString;
//		return req.getRequestURI()+queryString;
//	}
}