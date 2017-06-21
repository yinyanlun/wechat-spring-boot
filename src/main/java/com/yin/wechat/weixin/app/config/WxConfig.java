package com.yin.wechat.weixin.app.config;

import com.yin.wechat.weixin.app.model.Token;

public class WxConfig {
    public static final String TOKEN      = "weixinpay"; // 你的 token
    public static final String APP_ID     = "wx36a12f634161506e"; // 你的 appID
    public static final String APP_SECRET = "cd18606e984bdc1a04159b532fe0acd7"; // 你的 appsecret
    public static Token token; //统一token 通过定时器自动刷新
    // 获取token（GET）
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //用户授权
	public static final String OAUTH2_URL="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=1#wechat_redirect";
	//通过code获取oppenid
	public static final String ACCESS_TOKEN="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	//jssdk
	public static final String js_api_ticket_url ="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	//获取用户的微信用户的基本信息
	public static final String USER_INFO_URL="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
}
