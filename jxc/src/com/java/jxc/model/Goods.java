package com.java.jxc.model;

/**
 * 商品信息实体类
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-11-11
 */
public class Goods {
	
	private int goodsid; // 编号
	
	private String goodsname; // 商品名
	
	private float cost; // 成本
	
	private float sell; // 售价
	
	private int supplierId; // 供应商ID
	
	private String supplier; // 供应商名
	
	private int typeId; // 商品类型ID
	
	private String type; // 商品类型名

	/**************** GETTER/SETTER ***************/
	public int getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getSell() {
		return sell;
	}

	public void setSell(float sell) {
		this.sell = sell;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
