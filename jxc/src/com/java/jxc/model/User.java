/**
 * 
 */
package com.java.jxc.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 管理员信息实体
 * 
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-10-26
 */
public class User {

	private int id; // 编号
	
	private String username; // 账号
	
	private String password; // 秘密

	private String age;  //年龄
	
	private String sex; //性别
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	//根据rs初始数据
	public void initFormRs(ResultSet rs) {
		try {
			setId(rs.getInt(1));
			setUsername(rs.getString(2));
			setPassword(rs.getString(3));
			setAge(rs.getString(4));
			setSex(rs.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
