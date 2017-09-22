package com.zhi.crawler.jd;

import org.apache.commons.lang3.StringUtils;

public class Tools {
	public static boolean isEmpty(String str){
		return StringUtils.isBlank(str)||"null".equals(str);
	}
	
	public static boolean notEmpty(String str){
		return !StringUtils.isBlank(str)&&!"null".equals(str);
	}
}
