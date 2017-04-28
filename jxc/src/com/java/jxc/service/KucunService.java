/**
 * 
 */
package com.java.jxc.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.jxc.model.Kucun;
import com.java.jxc.model.PageBean;
import com.java.jxc.util.DateUtil;

/**
 * 库存实现类
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-11-20
 */
public class KucunService {

	/**
	 * 库存记录
	 * @param conn
	 * @param pageBean
	 * @param kucun
	 * @return
	 * @throws SQLException
	 */
	public ResultSet kucunList (Connection conn, PageBean pageBean, Kucun kucun) throws SQLException {
		StringBuffer sb = new StringBuffer("SELECT * FROM t_kucun k , t_goods g WHERE k.goodsid = g.goodsid");
		if(pageBean != null){
			sb.append(" LIMIT "+ pageBean.getStart() +","+ pageBean.getRows());
		}
		PreparedStatement pstm = conn.prepareStatement(sb.toString());
		return pstm.executeQuery();
	}
	
	/**
	 * 库存记录条数
	 * @param conn
	 * @param kucun
	 * @return
	 * @throws SQLException
	 */
	public int kucunListTotal (Connection conn, Kucun kucun) throws SQLException {
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) AS total FROM t_kucun k , t_goods g WHERE k.goodsid = g.goodsid");
		PreparedStatement pstm = conn.prepareStatement(sb.toString());
		ResultSet rs = pstm.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
	
	/**
	 * 查找库存中是否用进货单中的商品
	 * @param conn
	 * @param goodsid
	 * @return
	 * @throws SQLException
	 */
	public Kucun queryKucunByGoodsid (Connection conn, int goodsid) throws SQLException {
		Kucun currentkucun = null;
		String sql = "SELECT * from t_kucun k WHERE goodsid = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, goodsid);
		ResultSet rs = pstm.executeQuery();
		if(rs.next()){
			currentkucun = new Kucun();
			currentkucun.setId(rs.getInt(1));
			currentkucun.setGoodsid(rs.getInt(2));
			currentkucun.setInventory(rs.getInt(3));
			currentkucun.setEndDate(rs.getDate(4));
		}
		return currentkucun;
	}
	
	/**
	 * 增加库存记录通过进货单
	 * @param conn
	 * @param kucun
	 * @throws SQLException
	 */
	public void kucunAdd (Connection conn, Kucun kucun) throws SQLException {
		String sql = "INSERT INTO t_kucun (goodsid, inventory, endDate) VALUES (?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, kucun.getGoodsid());
		pstm.setInt(2, kucun.getInventory());
		pstm.setString(3, DateUtil.formatDate(kucun.getEndDate(), "yyyy-MM-dd"));
		pstm.executeUpdate();
	}
	
	/**
	 * 修改库存记录通过进货单/出货单
	 * @param conn
	 * @param kucun
	 * @throws SQLException
	 */
	public void kucunModify(Connection conn, Kucun kucun) throws SQLException {
		String sql = "UPDATE t_kucun SET inventory = ? , endDate = ? WHERE id = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, kucun.getInventory());
		pstm.setString(2, DateUtil.formatDate(kucun.getEndDate(), "yyyy-MM-dd"));
		pstm.setInt(3, kucun.getId());
		pstm.executeUpdate();
	}
}
