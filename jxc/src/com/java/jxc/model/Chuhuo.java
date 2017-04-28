package com.java.jxc.model;

import java.util.Date;

/**
 * 出货单实体类
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-11-23
 */
public class Chuhuo {
	
	private int id; // 编号
	
	private int goodsid = -1; // 商品ID
	
	private int quantity; // 数量
	
	private Date outtime; //日期
	
	private String goodsname; // 商品名称
	
	/**************GETTER/SETTER**************/

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

	public Date getOuttime() {
		return outtime;
	}

	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
}
