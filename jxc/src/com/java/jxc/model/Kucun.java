
package com.java.jxc.model;

import java.util.Date;

/**
 * 库存信息实体类
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-11-20
 */
public class Kucun {

	private int id; // 库存编号
	
	private int goodsid; // 商品ID
	
	private int inventory; // 库存量
	
	private Date endDate; // 日期
	
	private String goodsname; // 商品名称

	
	
	/**
	 * 
	 */
	public Kucun() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param goodsid
	 * @param inventory
	 * @param date
	 */
	public Kucun(int goodsid, int inventory, Date endDate) {
		super();
		this.goodsid = goodsid;
		this.inventory = inventory;
		this.endDate = endDate;
	}

	/***********************GETTER/SETTER**********************/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
}
