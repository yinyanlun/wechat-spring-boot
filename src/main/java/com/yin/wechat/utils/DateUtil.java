package com.yin.wechat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	public static String formatYmdhmsm(Date date) {
		// TODO Auto-generated method stub
		return sdf.format(date);
	}


}
