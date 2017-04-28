/**
 * 
 */
package com.java.jxc.action;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.java.jxc.model.Goods;
import com.java.jxc.model.PageBean;
import com.java.jxc.service.GoodsService;
import com.java.jxc.util.JsonUtil;
import com.java.jxc.util.ResponseUtil;
import com.java.jxc.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 商品模块控制实现
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-11-12
 */
@SuppressWarnings("serial")
public class GoodsAction extends ActionSupport{
	
	private String page; // 第几页
	private String rows; // 每页记录数
	private String s_goodsname = ""; // 搜索商品名
	private String s_supplier = ""; // 搜索供应商
	private String s_type = ""; // 搜索商品类型
	private Goods goods; // 商品
	private String delIds; // 批量删除ID号
	private String goodsid; // 商品ID
	
	GoodsService goodsService = new GoodsService();
	
	public String execute() throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		
		if (goods == null) {
			goods = new Goods();
		}
		if(StringUtil.isNotEmpty(s_goodsname)){
			goods.setGoodsname(s_goodsname);
		}
		if(StringUtil.isNotEmpty(s_supplier)){
			goods.setSupplier(s_supplier);
		}
		if(StringUtil.isNotEmpty(s_type)){
			goods.setType(s_type);
		}
		
		int total = goodsService.goodsListTotal(goods);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = null;
		try {
			jsonArray = JsonUtil.formatRsToJsonArray(goodsService.goodsList(pageBean, goods)); // 将结果集转为json数组
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public String delete() throws Exception {
		JSONObject result = new JSONObject();
		int delNums = goodsService.goodsDelete(delIds);
		if (delNums > 0) {
			result.put("success", "true");
			result.put("delNums", delNums);
		} else {
			result.put("errorMsg", "删除失败");
		}
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
		return null;
	}
	
	public String save() throws Exception {
		if (StringUtil.isNotEmpty(goodsid)) {
			goods.setGoodsid(Integer.parseInt(goodsid));
		}
		int saveNums = 0;
		JSONObject result = new JSONObject();
		if (StringUtil.isNotEmpty(goodsid)) {
			saveNums = goodsService.goodsModify(goods);
		} else {
			saveNums = goodsService.goodsAdd(goods);
		}
		if (saveNums > 0) {
			result.put("success", "true");
		} else {
			result.put("success", "true");
			result.put("errorMsg", "保存失败");
		}
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
		return null;
	}
	
	public String goodsComboList() throws Exception{
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("goodsid", "");
		jsonObject.put("goodsname", "请选择...");
		jsonArray.add(jsonObject);
		try {
			jsonArray.addAll(JsonUtil.formatRsToJsonArray(goodsService.goodsList(null, null)));
			ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**************** GETTER/SETTER ******************/
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getS_goodsname() {
		return s_goodsname;
	}
	public void setS_goodsname(String s_goodsname) {
		this.s_goodsname = s_goodsname;
	}
	public String getS_supplier() {
		return s_supplier;
	}
	public void setS_supplier(String s_supplier) {
		this.s_supplier = s_supplier;
	}
	public String getS_type() {
		return s_type;
	}
	public void setS_type(String s_type) {
		this.s_type = s_type;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public String getDelIds() {
		return delIds;
	}
	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	
}
