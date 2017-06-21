package com.yin.wechat.timer;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yin.wechat.weixin.app.config.WxConfig;
import com.yin.wechat.weixin.app.model.Token;
import com.yin.wechat.weixin.app.utils.CommonUtil;



/**
 * 定时器
 * 殷雷雷
 * 定时刷新AccessToken
 * （防止AccessToken过期和过去上限）
 * */


@Component
@Configurable
@EnableScheduling
public class AccessTokenTimer{
	 /*
     * 定时刷新微信Access：
     * 1.官方给出的超时时间是7200秒，这里用7100秒来做，防止出现已经过期的情况
     */
    @Scheduled(fixedRate = 1000 * 7100)
    public void reportCurrentTime(){
    	Token token=CommonUtil.getToken(WxConfig.APP_ID, WxConfig.APP_SECRET);
    	if(null==token)
    	{
    		token=CommonUtil.getToken(WxConfig.APP_ID, WxConfig.APP_SECRET);
    	}
    	WxConfig.token=token;
        System.out.println (WxConfig.token.toString());
    }
}