package com.yin.wechat.weixin.app.controller;

import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.message.Article;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.NewsMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.MenuEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import com.yin.wechat.service.WeixinUserInfoService;
import com.yin.wechat.weixin.app.config.WxConfig;
import com.yin.wechat.weixin.app.model.WeixinUserInfo;
import com.yin.wechat.weixin.app.utils.CommonUtil;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.Date;
@RestController
@RequestMapping("/weixin")
public class WeixinController extends WeixinControllerSupport {
    private static final Logger LOG = LoggerFactory.getLogger(WeixinController.class);
    
    @Autowired
    private WeixinUserInfoService weixinUserInfoService;
  
 // 重写父类方法处理关注事件
	protected BaseMsg handleSubscribe(BaseEvent event){
		// 图文消息ge
		System.out.println(event.getFromUserName());
		WeixinUserInfo weixinUserInfo=weixinUserInfoService.getWeixinUserInfoByopenId(event.getFromUserName());
		if(null==weixinUserInfo)
		{
			// 获取用户信息
			String requestUrl = WxConfig.USER_INFO_URL.replace("ACCESS_TOKEN", WxConfig.token.getAccessToken()).replace("OPENID", event.getFromUserName());
			System.out.println("获取个人资料:"+requestUrl);
		    JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl, "GET", null);
		    if (null != jsonObject) {
		        try {
		          weixinUserInfo = new WeixinUserInfo();
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
		          weixinUserInfoService.saveUser(weixinUserInfo);
		        } catch (Exception e) {
		          if (0 == weixinUserInfo.getSubscribe()) {
		        	 LOG.error("用户{}已取消关注", weixinUserInfo.getOpenId());
		          } else {
		            int errorCode = jsonObject.getInt("errcode");
		            String errorMsg = jsonObject.getString("errmsg");
		            LOG.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
		          }
		        }
		    }
		}
        String picUrl = "http://image.17car.com.cn/image/20120810/20120810092133_13289.jpg"; // 消息中显示的图片
        String url = "http://news.17car.com.cn/saishi/20120810/336283.html"; // 点击消息后跳转的网页的地址
        String description = "700 马力道路赛车 DDMWorks 打造最强 Atom";
        return new NewsMsg(Arrays.asList(new Article("Atom", description, picUrl, url), new Article("Atom", description, picUrl, url)));
		}
		
    // 重写父类方法，处理对应的微信消息
    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String content = msg.getContent(); // content 是用户输入的信息
        switch (content.toUpperCase()) {
            case "URL":
                // 消息中有链接
                return new TextMsg("你好: <a href=\"http://www.baidu.com\">百度</a>");
            case "ATOM":
                // 图文消息
                String picUrl = "http://image.17car.com.cn/image/20120810/20120810092133_13289.jpg"; // 消息中显示的图片
                String url = "http://news.17car.com.cn/saishi/20120810/336283.html"; // 点击消息后跳转的网页的地址
                String description = "700 马力道路赛车 DDMWorks 打造最强 Atom";
                return new NewsMsg(Arrays.asList(new Article("Atom", description, picUrl, url), new Article("Atom", description, picUrl, url)));
            default:
                return new TextMsg("不识别的命令, 您输入的内容是: " + content);
        }
    }
    @Override
    protected BaseMsg handleMenuClickEvent(MenuEvent event) {
        String key = event.getEventKey();
        switch (key.toUpperCase()) {
            case "MAIN1":
                return new TextMsg("点击按钮");
            case "SUB1":
                return new TextMsg("2016 语文");
            case "SUB2":
                return new TextMsg("2016 数学");
            default:
                return new TextMsg("不识别的菜单命令");
        }
    }
    // 设置 TOKEN，用于绑定微信服务器
    @Override
    protected String getToken() {
        return WxConfig.TOKEN;
    }
    // 获取 access token: http://localhost:8080/weixin/access-token
    @GetMapping("/access-token")
    @ResponseBody
    public String getAccessToken() {
//        ApiConfig config = new ApiConfig(WxConfig.APP_ID, WxConfig.APP_SECRET);
//        return config.getAccessToken();
    	return WxConfig.token.getAccessToken();
    }
    // 创建菜单, 访问  http://localhost:8080/weixincreate-menu 就会把菜单信息发送给微信服务器
    @GetMapping("/create-menu")
    @ResponseBody
    public String createMenu() {
        // 准备一级主菜单
        MenuButton main1 = new MenuButton();
        main1.setType(MenuType.CLICK); // 可点击的菜单
        main1.setKey("main1");
        main1.setName("主菜单一");
        MenuButton main2 = new MenuButton();
        main2.setType(MenuType.VIEW); // 链接的菜单，点击后跳转到对应的 URL
        main2.setName("授权登录");
        main2.setUrl(WxConfig.OAUTH2_URL.replace("APPID", WxConfig.APP_ID).replace("REDIRECT_URI", "http://yin.tunnel.2bdata.com/yin/weixin/getOpenid").replace("SCOPE", "snsapi_base"));
        MenuButton main3 = new MenuButton();
        main3.setType(MenuType.CLICK); // 带有子菜单
        main3.setName("真题");
        // 带有子菜单
        MenuButton sub1 = new MenuButton();
        sub1.setType(MenuType.CLICK);
        sub1.setName("2016 语文");
        sub1.setKey("sub1");
        MenuButton sub2 = new MenuButton();
        sub2.setType(MenuType.CLICK);
        sub2.setName("2016 数学");
        sub2.setKey("sub2");
        main3.setSubButton(Arrays.asList(sub1, sub2));
        Menu menu = new Menu();
        menu.setButton(Arrays.asList(main1, main2, main3));
        //创建菜单
        ApiConfig config = new ApiConfig(WxConfig.APP_ID, WxConfig.APP_SECRET);
        MenuAPI menuAPI = new MenuAPI(config);
        ResultType resultType = menuAPI.createMenu(menu);
        return resultType.toString();
    }
}