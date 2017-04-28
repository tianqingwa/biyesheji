package com.java.jxc.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.struts2.ServletActionContext;

import com.java.jxc.model.Dictionary;
import com.java.jxc.model.PageBean;
import com.java.jxc.service.DictionaryService;
import com.java.jxc.service.GoodsService;
import com.java.jxc.util.DBUtil;
import com.java.jxc.util.JsonUtil;
import com.java.jxc.util.ResponseUtil;
import com.java.jxc.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 数据字典模块控制实现类
 */
public class DictionaryAction {

	private String page; // 第几页
	private String rows; // 每页记录数
	private String s_name="";
	private String s_value="";
	private Dictionary dictionary;
	private String delIds;
	private String id;
	private String name;
	
	
	DBUtil dbUtil = new DBUtil();
	ResultSet rs = null;
	DictionaryService dictionaryService = new DictionaryService();
	GoodsService goodsService = new GoodsService();
	
	public String execute() throws SQLException {
		Connection conn = dbUtil.getConnection();
		PageBean pageBean = new PageBean (Integer.parseInt(page), Integer.parseInt(rows));
		try {
			if(dictionary == null) {
				dictionary = new Dictionary();
			}
			
			dictionary.setName(s_name);
			dictionary.setValue(s_value);
			
			JSONObject result = new JSONObject();
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(dictionaryService.dictionaryList(conn, pageBean, dictionary));
			int total = dictionaryService.dictionaryListTotal(conn, dictionary);
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
	
	public String delete () throws SQLException {
		Connection conn = dbUtil.getConnection();
		try {
			JSONObject result = new JSONObject();
			String str [] = delIds.split(",");
			for(int i = 0; i < str.length; i++) {
				boolean f = goodsService.getGoodsByDictionaryId(conn, str[i]);
				if(f) {
					result.put("errorIndex", i);
					result.put("errorMsg", "该数据下面有商品，不能删除！");
					ResponseUtil.write(ServletActionContext.getResponse(), result);
					return null;
				}
			} 
			int delNums = dictionaryService.dictionaryDelete(conn, delIds);
			if(delNums > 0 ) {
				result.put("success", "true");
				result.put("delNums", delNums);
			} else {
				result.put("errorMsg", "删除失败");
			}
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, null, rs);
		}
		return null;
	}
	
	public String save() throws SQLException {
		Connection conn = dbUtil.getConnection();
		try {
			int saveNums = 0;
			JSONObject result = new JSONObject();
			if(StringUtil.isNotEmpty(id)) {
				dictionary.setId(Integer.parseInt(id));
				saveNums = dictionaryService.dictionaryModify(conn, dictionary);
			} else {
				saveNums = dictionaryService.dictionaryAdd(conn, dictionary);
			}
			if(saveNums > 0 ) {
				result.put("success", "true");
			} else {
				result.put("success", "true");
				result.put("errorMsg", "保存失败");
			}
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, null, rs);
		}
		return null;
	}
	
	public String dictionaryComboList() throws SQLException {
		Connection conn = dbUtil.getConnection();
		try {
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("value", "");
			jsonArray.add(jsonObject);
			
			Dictionary dy = new Dictionary();
			if(dictionary == null) {
				
				if(name.equals("1")){
					dy.setName("供应商");
				}
				else{
					dy.setName("商品类型");
				}
				
				jsonArray.addAll(JsonUtil.formatRsToJsonArray(dictionaryService.dictionaryList(conn, null, dy)));
			} else {
				jsonArray.addAll(JsonUtil.formatRsToJsonArray(dictionaryService.dictionaryList(conn, null, dictionary)));
			
			}
			
			
			ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, null, rs);
		}
		return null;
	}
	
	/*************** GETTER/SETTER ***************/
	
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
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_value() {
		return s_value;
	}
	public void setS_value(String s_value) {
		this.s_value = s_value;
	}
	public Dictionary getDictionary() {
		return dictionary;
	}
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	public String getDelIds() {
		return delIds;
	}
	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
