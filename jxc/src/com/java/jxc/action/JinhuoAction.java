package com.java.jxc.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.java.jxc.model.Jinhuo;
import com.java.jxc.model.Kucun;
import com.java.jxc.model.PageBean;
import com.java.jxc.service.JinhuoService;
import com.java.jxc.service.KucunService;
import com.java.jxc.util.DBUtil;
import com.java.jxc.util.JsonUtil;
import com.java.jxc.util.ResponseUtil;
import com.java.jxc.util.StringUtil;


/**
 * 进货单模块控制实现类
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-11-19
 */
public class JinhuoAction {

	private String page; // 第几页
	private String rows; // 每页记录数
	private Jinhuo jinhuo; // 进货单
	private String id; // 进货单ID
	
	private String s_goodsid; // 搜索商品ID
	private String s_intodate; // 搜索下单日期
	
	DBUtil dbUtil = new DBUtil();
	ResultSet rs = null;
	JinhuoService jinhuoService = new JinhuoService();
	KucunService kucunService = new KucunService();
	
	public String execute() throws SQLException {
		Connection conn = dbUtil.getConnection();
		PageBean pageBean =  new PageBean (Integer.parseInt(page), Integer.parseInt(rows));
		JSONObject result = new JSONObject();
		JSONArray jsonArray;
		try {
			if(jinhuo == null){
				jinhuo = new Jinhuo();
			}
			if(StringUtil.isNotEmpty(s_goodsid)){
				jinhuo.setGoodsid(Integer.parseInt(s_goodsid));
			}
			jsonArray = JsonUtil.formatRsToJsonArray(jinhuoService.jinhuoList(conn, pageBean, jinhuo, s_intodate));
			int total = jinhuoService.jinhuoListTotal(conn, jinhuo, s_intodate);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, null, rs);
		}
		return null;
	}
	
	public String save() throws Exception {
		Connection conn = dbUtil.getConnection();
		Kucun currentkucun = null;
		currentkucun = kucunService.queryKucunByGoodsid(conn, jinhuo.getGoodsid()); // 查找库存中该商品库存记录
		if(currentkucun == null){ // 没有则创建
			currentkucun = new Kucun(jinhuo.getGoodsid(), jinhuo.getQuantity(), jinhuo.getIntodate());
			kucunService.kucunAdd(conn, currentkucun);
		} else { // 库存中已存在该记录，则修改库存量
			currentkucun.setEndDate(jinhuo.getIntodate());
			currentkucun.setInventory(currentkucun.getInventory()+jinhuo.getQuantity());
			kucunService.kucunModify(conn, currentkucun);
		}
		if (StringUtil.isNotEmpty(id)){
			jinhuo.setId(Integer.parseInt(id));
		}
		int saveNums = 0;
		JSONObject result = new JSONObject();
		if (StringUtil.isNotEmpty(id)) {
			saveNums = jinhuoService.jinhuoModify(conn, jinhuo);
		} else {
			saveNums = jinhuoService.jinhuoAdd(conn, jinhuo);
		}
		if (saveNums > 0) {
			result.put("success", "true");
		} else {
			result.put("success", "ture");
			result.put("errorMsg", "保存失败");
		}
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			dbUtil.close(conn, null, rs);
		}
		return null;
	}
	
	/*********************GETTER/SETTER*******************/
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
	public Jinhuo getJinhuo() {
		return jinhuo;
	}
	public void setJinhuo(Jinhuo jinhuo) {
		this.jinhuo = jinhuo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getS_goodsid() {
		return s_goodsid;
	}
	public void setS_goodsid(String s_goodsid) {
		this.s_goodsid = s_goodsid;
	}
	public String getS_intodate() {
		return s_intodate;
	}
	public void setS_intodate(String s_intodate) {
		this.s_intodate = s_intodate;
	}
	
}
