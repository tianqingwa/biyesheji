/**
 * 
 */
package com.java.jxc.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.struts2.ServletActionContext;

import com.java.jxc.model.Kucun;
import com.java.jxc.model.PageBean;
import com.java.jxc.service.KucunService;
import com.java.jxc.util.DBUtil;
import com.java.jxc.util.JsonUtil;
import com.java.jxc.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 库存模块控制实现类
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-11-20
 */
public class KucunAction {

	private String page; // 第几页
	private String rows; // 每页记录数
	private Kucun kucun; // 库存实体
	private String id; // 库存ID
	private String delIds; // 批量删除ID
	
	DBUtil dbUtil = new DBUtil();
	ResultSet rs = null;
	KucunService kucunService = new KucunService();
	
	public String execute() throws SQLException{
		Connection conn = dbUtil.getConnection();
		PageBean pageBean = new PageBean (Integer.parseInt(page), Integer.parseInt(rows));
		try {
			if(kucun == null) {
				kucun = new Kucun();
			}
			JSONObject result = new JSONObject();
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(kucunService.kucunList(conn, pageBean, kucun));
			int total = kucunService.kucunListTotal(conn, kucun);
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
	
	/*******************GETTER/SETTER*********************/

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

	public Kucun getKucun() {
		return kucun;
	}

	public void setKucun(Kucun kucun) {
		this.kucun = kucun;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDelIds() {
		return delIds;
	}

	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}

	public DBUtil getDbUtil() {
		return dbUtil;
	}

	public void setDbUtil(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	
}
