/**
 * 
 */
package com.java.jxc.model;

/**
 * 数据字典信息实体类
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-11-13
 */
public class Dictionary {

	private int id; // 编号
	
	private String name; // 所属表的名称
	
	private String value; // 名称
	
	private String remark; // 备注，选填

	private String tel;
	
	/******************** GETTER/SETTER *******************/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
}
