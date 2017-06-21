package com.yin.wechat.utils;

import java.math.BigDecimal;

public class LeantechUtils {
	public static BigDecimal ConvertResponseDecimal(BigDecimal d) {
		return d.multiply(new BigDecimal(100)).setScale(0);
	}
}
