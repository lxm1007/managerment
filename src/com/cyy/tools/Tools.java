package com.cyy.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


public final class Tools
{
	
	/**
	 * DTO生成方法
	 * <
	 *   将页面数据封装到Map
	 * >
	 * @param request
	 * @return
	 */
	public static Map<String,Object> createdto(HttpServletRequest request)
	{
		//1.获取页面数据,形成Map--该Map为只读的,不可以更新
		Map<String,String[]> tem=request.getParameterMap();
		//2.计算DTO的初始容量
		int initSize=((int)(tem.size()/0.75))+1;
		//3.创建DTO,指定初始容量
		Map<String,Object> dto=new HashMap<>(initSize);
		//4.导出tem中所有的键值对,形成Set
		Set<Map.Entry<String, String[]>> entrySet=tem.entrySet();
		//5..定义表示Entry的value部分的数组变量
		String val[]=null;
		//6.遍历entrySet获取每个Eenty对象(键/值对对象)
		for(Map.Entry<String, String[]>  entry:entrySet)
		{
			//7.获取Enetry的value部分,形成数组
			val=entry.getValue();
			//8.通过数组长度判断控件类型
			if(val.length==1)  //非checkbox
			{
				//9.向DTO写入页面数据
				dto.put(entry.getKey(), val[0]);
			}
			else    //checkbox
			{
				dto.put(entry.getKey(), val);
			}	
		}
		dto.put("contextpath", request.getContextPath());
		return dto;
	}
	/***
	 * 密码进行MD5处理后存入数据库
	 * 位数为32位 登录时 同样要获取对应密码的额MD5值 与数据库中的密码进行校验 
	 * 如果校验成功 则可以进行登录
	 * 
	 * @param str 用户的密码
	 * @return String
	 * @throws NoSuchAlgorithmException 
	 */
	public static final String getMD5String(String str) throws NoSuchAlgorithmException {
	        if(str != null){  
	            StringBuilder su = new StringBuilder();  
	            MessageDigest md = MessageDigest.getInstance("MD5");  
	            md.update(str.getBytes());  
	            byte [] b = md.digest();  
	            int temp = 0;  
	            for(int offset = 0,bLen = b.length; offset < bLen; offset++){  
	                temp = b[offset];  
	                if(temp < 0){  
	                    temp += 256;  
	                }  
	                if(temp < 16){  
	                    su.append("0");  
	                }  
	                su.append(Integer.toHexString(temp));  
	            }  
	            return su.toString();  
	        }else{  
	            return null;  
	        }  
	    } 
		
}