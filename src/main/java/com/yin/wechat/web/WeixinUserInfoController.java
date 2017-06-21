package com.yin.wechat.web;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yin.wechat.model.UserInfo;
import com.yin.wechat.service.WeixinUserInfoService;
import com.yin.wechat.utils.HttpRequestParam;
import com.yin.wechat.weixin.app.config.WxConfig;
import com.yin.wechat.weixin.app.controller.WeixinController;
import com.yin.wechat.weixin.app.model.WeixinUserInfo;
import com.yin.wechat.weixin.app.utils.CommonUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/weixinUserInfo")
@RequiresPermissions("weixinUserInfo:view")//微信用户管理权限;
public class WeixinUserInfoController {
	 private static final Logger LOG = LoggerFactory.getLogger(WeixinUserInfoController.class);
	@Autowired
	private WeixinUserInfoService weixinUserInfoService;
	/**
	 * 用户查询.
	 * @return
	 */
	@RequestMapping("/view")
	public String userInfo(){
		return "app/wexin_user";
	}
	@RequestMapping( value = "/page")
	@ResponseBody
	public Map<String, Object> getOrderList(HttpServletRequest request,HttpResponse response) throws UnsupportedEncodingException {
		
		//获取http 参数
		request.setCharacterEncoding("UTF-8");
		Map paraMap = HttpRequestParam.showParams(request);
		int pageSize=Integer.valueOf((String) paraMap.get("limit"));
		int page=Integer.valueOf((String) paraMap.get("offset"))/pageSize;
		String search=(String) paraMap.get("search");
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		 Pageable pageable =new PageRequest(page, pageSize,sort);
		 WeixinUserInfo weixinUserInfo=new WeixinUserInfo();
		 if(null!=search&&"昵称或OEENID查询".indexOf(search)<0)
		 {
			 weixinUserInfo.setOpenId(search);
			 weixinUserInfo.setNickname(search);
		 }
		
		 Page<WeixinUserInfo> datas = weixinUserInfoService.pageUserByParam(weixinUserInfo, pageable);
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("rows", datas.getContent());
		map.put("total", datas.getTotalElements());
		return map;		
	}
	/**
	 * 拉黑取消拉黑操作
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping( value = "/updateStateById")
	@ResponseBody
	public Map<String, Object> updateStateById(HttpServletRequest request,HttpResponse response) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String, Object>();
		//获取http 参数
		request.setCharacterEncoding("UTF-8");
		Map paraMap = HttpRequestParam.showParams(request);
		Integer id=Integer.valueOf((String)paraMap.get("id"));
		String state=(String)paraMap.get("state");
		int count=weixinUserInfoService.updateStateById(id, state);
		if(count>0){
			map.put("DATA_CODE", "SUCCESS");
		}
		else{
			map.put("DATA_CODE", "error");
		}
		return map;	
	}
	/**
	 * 微信刷新用户资料同步微信
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping( value = "/refresh")
	@ResponseBody
	public Map<String, Object> refresh(HttpServletRequest request,HttpResponse response) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String, Object>();
		//获取http 参数
		request.setCharacterEncoding("UTF-8");
		Map paraMap = HttpRequestParam.showParams(request);
		Integer id=Integer.valueOf((String)paraMap.get("id"));
		//1根据主键查到个人信息
		WeixinUserInfo 	weixinUserInfo=weixinUserInfoService.getWeixinUserInfoById(id);
		//2获取用户信息
		String requestUrl = WxConfig.USER_INFO_URL.replace("ACCESS_TOKEN", WxConfig.token.getAccessToken()).replace("OPENID", weixinUserInfo.getOpenId());
		System.out.println("获取个人资料:"+requestUrl);
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl, "GET", null);
		   if (null != jsonObject) {
		        try {
		          // 用户的标识
		          weixinUserInfo.setOpenId(jsonObject.getString("openid"));
		          weixinUserInfo.setState("1");
		          weixinUserInfo.setSubscribeDate(new Date());
		          // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
		          weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
		          // 用户关注时间
		          weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
		          // 昵称
		          weixinUserInfo.setNickname(jsonObject.getString("nickname"));
		          // 用户的性别（1是男性，2是女性，0是未知）
		          weixinUserInfo.setSex(jsonObject.getInt("sex"));
		          // 用户所在国家
		          weixinUserInfo.setCountry(jsonObject.getString("country"));
		          // 用户所在省份
		          weixinUserInfo.setProvince(jsonObject.getString("province"));
		          // 用户所在城市
		          weixinUserInfo.setCity(jsonObject.getString("city"));
		          // 用户的语言，简体中文为zh_CN
		          weixinUserInfo.setLanguage(jsonObject.getString("language"));
		          // 用户头像
		          weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
		          int upCount= weixinUserInfoService.refreshWeixinUserInfo(weixinUserInfo);
		          map.put("count", upCount);
		          
		        } catch (Exception e) {
		        	map.put("count", 0);
		          if (0 == weixinUserInfo.getSubscribe()) {
		        	 LOG.error("用户{}已取消关注", weixinUserInfo.getOpenId());
		          } else {
		            int errorCode = jsonObject.getInt("errcode");
		            String errorMsg = jsonObject.getString("errmsg");
		            LOG.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
		          }
		        }
		    }
		return map;	
	}
}
