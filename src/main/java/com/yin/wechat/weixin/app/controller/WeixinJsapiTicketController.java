package com.yin.wechat.weixin.app.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yin.wechat.weixin.app.config.WxConfig;
import com.yin.wechat.weixin.app.utils.CommonUtil;

/**
 * 
 * @author yinleilei
 *
 */
@RestController
@RequestMapping("/weixin")
public class WeixinJsapiTicketController {
	
	@RequestMapping(value="/ticket")
	public Map<String,String> getJsapiTicket(HttpServletResponse response,HttpServletRequest request){
	
		String url=WxConfig.js_api_ticket_url.replace("ACCESS_TOKEN",WxConfig.token.getAccessToken());
    	Map<String,String> map=CommonUtil.getJsapiTicket(url);
    	String ticket=map.get("ticket");
    	System.out.println("获取ticket="+ticket);
    	String fullPath=request.getContextPath();
    	Map<String, String> returnMap=sign(ticket, ticket);
    	return returnMap;
	}
	 public static Map<String, String> sign(String jsapi_ticket, String url) {
	        Map<String, String> ret = new HashMap<String, String>();
	        String nonce_str = create_nonce_str();
	        String timestamp = create_timestamp();
	        String string1;
	        String signature = "";

	        //注意这里参数名必须全部小写，且必须有序
	        string1 = "jsapi_ticket=" + jsapi_ticket +
	                  "&noncestr=" + nonce_str +
	                  "&timestamp=" + timestamp +
	                  "&url=" + url;
	        System.out.println(string1);

	        try
	        {
	            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	            crypt.reset();
	            crypt.update(string1.getBytes("UTF-8"));
	            signature = byteToHex(crypt.digest());
	        }
	        catch (NoSuchAlgorithmException e)
	        {
	            e.printStackTrace();
	        }
	        catch (UnsupportedEncodingException e)
	        {
	            e.printStackTrace();
	        }

	        ret.put("url", url);
	        ret.put("jsapi_ticket", jsapi_ticket);
	        ret.put("nonceStr", nonce_str);
	        ret.put("timestamp", timestamp);
	        ret.put("signature", signature);
	        ret.put("appId", WxConfig.APP_ID);

	        return ret;
	    }

	    private static String byteToHex(final byte[] hash) {
	        Formatter formatter = new Formatter();
	        for (byte b : hash)
	        {
	            formatter.format("%02x", b);
	        }
	        String result = formatter.toString();
	        formatter.close();
	        return result;
	    }

	    private static String create_nonce_str() {
	        return UUID.randomUUID().toString();
	    }

	    private static String create_timestamp() {
	        return Long.toString(System.currentTimeMillis() / 1000);
	    }
	}

