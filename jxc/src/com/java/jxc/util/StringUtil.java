/**
 * 
 */
package com.java.jxc.util;

/**
 * 判断是否为空工具类
 * 
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-10-26
 */
public class StringUtil {

	public static boolean isEmpty(String str){
		if("".equals(str)|| str==null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null){
			return true;
		}else{
			return false;
		}
	}
}
