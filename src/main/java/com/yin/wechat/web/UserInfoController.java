package com.yin.wechat.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yin.wechat.model.User;
import com.yin.wechat.model.UserInfo;
import com.yin.wechat.service.UserInfoService;
import com.yin.wechat.utils.HttpRequestParam;

@Controller
@RequestMapping("/userInfo")
@RequiresPermissions("userInfo:view")//用户管理权限;
public class UserInfoController {
	@Autowired
	private UserInfoService userInfoService;
	/**
	 * 用户查询.
	 * @return
	 */
	@RequestMapping("/view")
	public String userInfo(){
		return "app/user_info_list";
	}
	@RequestMapping( value = "/pageUser")
	@ResponseBody
	public Map<String, Object> getOrderList(HttpServletRequest request,HttpResponse response) throws UnsupportedEncodingException {
		
		//获取http 参数
		request.setCharacterEncoding("UTF-8");
		Map paraMap = HttpRequestParam.showParams(request);
		int pageSize=Integer.valueOf((String) paraMap.get("limit"));
		int page=Integer.valueOf((String) paraMap.get("offset"))/pageSize;
		String search=(String) paraMap.get("search");
		Sort sort = new Sort(Sort.Direction.DESC, "uid");
		 Pageable pageable =new PageRequest(page, pageSize,sort);
		 UserInfo userInfo=new UserInfo();
		 if(null!=search&&"请输入用户名查询".indexOf(search)<0)
		 {
			 userInfo.setUsername(search);
			 userInfo.setRealName(search);
		 }
		
		 Page<UserInfo> datas = userInfoService.pageUserInfoByParam(userInfo,pageable);
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("rows", datas.getContent());
		map.put("total", datas.getTotalElements());
		return map;		
	}
}
