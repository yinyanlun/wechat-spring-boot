package com.yin.wechat.utils;

import java.util.Date;
import java.util.Random;

public class Uuid {

	/** 
	 * 创建UUID 
	 * @return 
	 */  
	public static synchronized String makeUUID() {  
	    Date date = new Date();  
	    StringBuffer s = new StringBuffer(DateUtil.formatYmdhmsm(date));  
	    return s.append((new Random().nextInt(900) + 100)).toString();  
	}  
}
