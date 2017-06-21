package com.yin.wechat.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yin.wechat.model.User;
import com.yin.wechat.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


/**
 *其中@ApiOperation和@ApiParam为添加的API相关注解，各参数说明如下： 
 *@ApiOperation(value = “接口说明”, httpMethod = “接口请求方式”, response = “接口返回参数类型”, notes = “接口发布说明”；其他参数可参考源码； 
 *@ApiParam(required = “是否必须参数”, name = “参数名称”, value = “参数具体描述” 
		  
 * @author yinleilei
 *
 */
@RestController
@RequestMapping("wechatApi/1.0/users")
public class UserController {
	
	@Resource
	private UserService userservice;
		
	@ApiOperation(value="查询用户", notes="根据主键查询用户") 
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public User getUserById(@PathVariable Integer id){
		return userservice.getUserById(id);
	}
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")  
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(method=RequestMethod.POST)
	public User saveUser(@RequestBody User user){
		User result=userservice.saveUser(user);
		return result;
	}
	@ApiOperation(value="分页查询用户", notes="通过用户名分页查询") 
	@RequestMapping(value="/{page}/{size}/{UserName}",method=RequestMethod.GET)
	public Page<User>pageUser(@PathVariable Integer page,@PathVariable Integer size,@PathVariable String UserName){
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		 Pageable pageable =new PageRequest(page, size,sort);
		 Page<User> datas = userservice.findAll(pageable);
	// Page<User> datas=userservice.pageQueryUser(UserName, pageable);
		return datas;
		
	}
	@ApiOperation(value="分页查询用户", notes="通过用户名密码分页查询")  
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = false, dataType = "User")
	@RequestMapping(value="/{page}/{size}/{UserName}/{password}",method=RequestMethod.GET)
	public Page<User>pageUserByParam(
			@PathVariable Integer page,
			@PathVariable Integer size,
			@PathVariable String UserName,
			@PathVariable String password){
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		 Pageable pageable =new PageRequest(page, size,sort);
		 User user=new User();
		 user.setPassword(password);
		 user.setUserName(UserName);
		 Page<User> datas = userservice.pageUserByParam(user,pageable);
	// Page<User> datas=userservice.pageQueryUser(UserName, pageable);
		return datas;
		
	}
//	@RequestMapping(value="/ep")
//	public void ex(HttpServletResponse response){
//		List<User> list=userservice.getallUser();
//		LinkedHashMap<String, String> map=new LinkedHashMap<String, String>();
//		map.put("id", "id");
//		map.put("userName", "用户名");
//		ExcelUtil.export("ceshi", list, map, response);
//	}
}
