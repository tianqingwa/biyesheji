package com.java.jxc.util;

/**
 * 
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-10-29
 */
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtil {

	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	
	
	public static Date formatString(String str,String format) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
}

