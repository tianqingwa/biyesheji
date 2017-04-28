/**
 * 
 */
package com.java.jxc.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.struts2.ServletActionContext;

import com.java.jxc.model.Chuhuo;
import com.java.jxc.model.Kucun;
import com.java.jxc.model.PageBean;
import com.java.jxc.service.ChuhuoService;
import com.java.jxc.service.KucunService;
import com.java.jxc.util.DBUtil;
import com.java.jxc.util.JsonUtil;
import com.java.jxc.util.ResponseUtil;
import com.java.jxc.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 出货单模块控制实现类
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-11-23
 */
public class ChuhuoAction extends ActionSupport {

	private String page; // 第几页
	private String rows; // 每页记录数
	private Chuhuo chuhuo; // 出货单
	private String id; // 出货单ID
	
	private String s_goodsid; // 搜索商品ID
	private String s_outtime; // 下单日期
	
	DBUtil dbUtil = new DBUtil();
	ResultSet rs = null;
	ChuhuoService chuhuoService = new ChuhuoService();
	KucunService kucunService = new KucunService();
	
	public String execute() throws SQLException {
		Connection conn = dbUtil.getConnection();
		PageBean pageBean = new PageBean (Integer.parseInt(page), Integer.parseInt(rows));
		JSONObject result = new JSONObject();
		JSONArray jsonArray;
		try {
			if(chuhuo == null){
				chuhuo = new Chuhuo();
			}
			if(StringUtil.isNotEmpty(s_goodsid)){
				chuhuo.setGoodsid(Integer.parseInt(s_goodsid));
			}
			jsonArray = JsonUtil.formatRsToJsonArray(chuhuoService.chuhuoList(conn, pageBean, chuhuo, s_outtime));
			int total = chuhuoService.chuhuoListTotal(conn, chuhuo, s_outtime);
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
		int saveNums = 0;
		JSONObject result = new JSONObject();
		Kucun currentkucun = null;
		try {
			currentkucun = kucunService.queryKucunByGoodsid(conn, chuhuo.getGoodsid());
			if(currentkucun == null){ // 库存中没有该商品时
				result.put("success", "true");
				result.put("errorMsg", "保存失败,该商品暂无库存");
			//	return null;
			}
			if(currentkucun.getInventory() < chuhuo.getQuantity()){ // 库存量小于出货量
				result.put("success", "true"); 
				result.put("errorMsg", "保存失败,该商品的库存量小于出货量，请查看库存");
			//	return null;
			} else {
				currentkucun.setEndDate(chuhuo.getOuttime());
				currentkucun.setInventory(currentkucun.getInventory()-chuhuo.getQuantity());
				kucunService.kucunModify(conn, currentkucun);
				saveNums = chuhuoService.chuhuoAdd(conn, chuhuo);
			}
			if (saveNums > 0) {
				result.put("success", "true");
				result.put("errorMsg", "保存成功");
			} else {
				result.put("success", "true");  // true是技术上的，表示返回成功
				result.put("errorMsg", "保存失败");
			}
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			dbUtil.close(conn, null, rs);
		}
		return null;
	}

	
	/**********************GETTER/SETTER***********************/
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

	public Chuhuo getChuhuo() {
		return chuhuo;
	}

	public void setChuhuo(Chuhuo chuhuo) {
		this.chuhuo = chuhuo;
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

	public String getS_outtime() {
		return s_outtime;
	}

	public void setS_outtime(String s_outtime) {
		this.s_outtime = s_outtime;
	}
	
}

