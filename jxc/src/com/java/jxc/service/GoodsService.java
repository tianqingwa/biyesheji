/**
 * 
 */
package com.java.jxc.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.jxc.model.Goods;
import com.java.jxc.model.PageBean;
import com.java.jxc.util.DBUtil;
import com.java.jxc.util.StringUtil;

/**
 * 商品模块实现类
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-11-11
 */
public class GoodsService {

	DBUtil dbUtil = new DBUtil();
	ResultSet rs = null;
	
	/**
	 * 商品记录
	 * @param pageBean
	 * @param goods
	 * @return
	 */
	public ResultSet goodsList (PageBean pageBean, Goods goods) {
		Connection conn = dbUtil.getConnection();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_goods");
		if(goods != null && StringUtil.isNotEmpty(goods.getGoodsname())) {
			sb.append(" AND goodsname LIKE '%"+ goods.getGoodsname() +"%'");
		}
		if(goods != null && StringUtil.isNotEmpty(goods.getSupplier())) {
			sb.append(" AND supplier LIKE '%"+ goods.getSupplier() +"%'");
		}
		if(goods != null && StringUtil.isNotEmpty(goods.getType())) {
			sb.append(" AND type LIKE '%"+ goods.getType() +"%'");
		}
		if(pageBean!=null){
			sb.append(" LIMIT "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sb.toString().replaceFirst("AND", "WHERE"));
			return pstm.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}/* finally {
			dbUtil.close(conn, pstm, rs);
		}*/
		return null;
	}
	
	/**
	 * 商品记录条数
	 * @param goods
	 * @return
	 */
	public int goodsListTotal (Goods goods) {
		Connection conn = dbUtil.getConnection();
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) AS total FROM t_goods");
		if(goods != null && StringUtil.isNotEmpty(goods.getGoodsname())) {
			sb.append(" AND goodsname LIKE '%"+ goods.getGoodsname() +"%'");
		}
		if(goods != null && StringUtil.isNotEmpty(goods.getSupplier())) {
			sb.append(" AND supplier LIKE '%"+ goods.getSupplier() +"%'");
		}
		if(goods != null && StringUtil.isNotEmpty(goods.getType())) {
			sb.append(" AND type LIKE '%"+ goods.getType() +"%'");
		}
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sb.toString().replaceFirst("AND", "WHERE"));
			rs = pstm.executeQuery();
			if(rs.next()) {
				return rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstm, rs);
		}
		return 0;
	}
	
	/**
	 * 批量删除记录
	 * @param delIds
	 * @return
	 */
	public int goodsDelete (String delIds) {
		int num = 0;
		Connection conn = dbUtil.getConnection();
		String sql = "DELETE FROM t_goods WHERE goodsid IN ("+ delIds +")";
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			num = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstm, rs);
		}
		return num;
	}
	
	/**
	 * 添加商品记录
	 * @param goods
	 * @return
	 */
	public int goodsAdd(Goods goods) {
		int num = 0;
		Connection conn = dbUtil.getConnection();
		String sql = "INSERT INTO t_goods (goodsname, cost, sell, supplierId, supplier, typeId, type) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, goods.getGoodsname());
			pstm.setFloat(2, goods.getCost());
			pstm.setFloat(3, goods.getSell());
			pstm.setInt(4, goods.getSupplierId());
			pstm.setString(5, goods.getSupplier());
			pstm.setInt(6, goods.getTypeId());
			pstm.setString(7, goods.getType());
			num = pstm.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstm, rs);
		}
		return num;
	}
	
	/**
	 * 修改商品记录
	 * @param goods
	 * @return
	 */
	public int goodsModify (Goods goods) {
		int num = 0;
		Connection conn = dbUtil.getConnection();
		String sql = "UPDATE t_goods SET goodsname = ? , cost = ? , sell = ? , supplierId = ? , supplier = ? , typeId = ? , type = ? WHERE goodsid = ?";
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, goods.getGoodsname());
			pstm.setFloat(2, goods.getCost());
			pstm.setFloat(3, goods.getSell());
			pstm.setInt(4, goods.getSupplierId());
			pstm.setString(5, goods.getSupplier());
			pstm.setInt(6, goods.getTypeId());
			pstm.setString(7, goods.getType());
			pstm.setInt(8, goods.getGoodsid());
			num = pstm.executeUpdate();
			} catch (Exception e) {
		} finally {
			dbUtil.close(conn, pstm, rs);
		}
		return num;
	}

	/**
	 * 查找商品记录 通过数据字典ID
	 * @param conn
	 * @param dictionaryId
	 * @return
	 * @throws SQLException
	 */
	public boolean getGoodsByDictionaryId(Connection conn, String dictionaryId) throws SQLException {
		String sql = "SELECT * FROM t_goods WHERE typeid = ? or supplierid = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		int id = Integer.parseInt(dictionaryId);
		pstm.setInt(1, id);
		pstm.setInt(2, id);
		ResultSet rs = pstm.executeQuery();
		if(rs.next()) {
			return true;
		} else {
			return false;
		}
	}
}
