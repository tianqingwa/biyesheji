package com.java.jxc.model;

import java.util.Date;

/**
 * 进货单实体类
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-11-19
 */
public class Jinhuo {

	private int id; // 编号
	
	private int goodsid = -1; // 商品实体对象ID
	
	private int quantity; // 进货数量
	
	private Date intodate; // 进货时间
	
	private String goodsname; // 商品名称
	
	/**********************GETTER/SETTER***********************/

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getIntodate() {
		return intodate;
	}

	public void setIntodate(Date intodate) {
		this.intodate = intodate;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
}
