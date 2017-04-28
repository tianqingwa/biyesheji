/**
 * 
 */
package com.java.jxc.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.jxc.model.Chuhuo;
import com.java.jxc.model.PageBean;
import com.java.jxc.util.DateUtil;
import com.java.jxc.util.StringUtil;

/**
 * 出货单模块实现类
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-11-23
 */
public class ChuhuoService {

	/**
	 * 出货单记录
	 * @param conn
	 * @param pageBean
	 * @param chuhuo
	 * @retur	 * @throws SQLException
	 */
	public ResultSet chuhuoList(Connection conn, PageBean pageBean, Chuhuo chuhuo, String outtime) throws SQLException {
		StringBuffer sb = new StringBuffer("SELECT * FROM t_chuhuo c , t_goods g WHERE c.goodsid = g.goodsid");
		if(chuhuo.getGoodsid() != -1){
			sb.append(" AND c.goodsid = "+ chuhuo.getGoodsid());
		}
		if(StringUtil.isNotEmpty(outtime)){
			sb.append(" AND TO_DAYS(c.outtime)=TO_DAYS('"+outtime+"')");
		}
		if(pageBean != null) {
			sb.append(" LIMIT "+ pageBean.getStart() +","+ pageBean.getRows());
		}
		PreparedStatement pstm = conn.prepareStatement(sb.toString());
		return pstm.executeQuery();
	} 
	
	/**
	 * 出货单记录条数
	 * @param conn
	 * @param chuhuo
	 * @return
	 * @throws SQLException
	 */
	public int chuhuoListTotal(Connection conn, Chuhuo chuhuo, String outtime) throws SQLException{
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) AS total FROM t_chuhuo c , t_goods g WHERE c.goodsid = g.goodsid");
		if(chuhuo.getGoodsid() != -1){
			sb.append(" AND c.goodsid = "+ chuhuo.getGoodsid());
		}
		if(StringUtil.isNotEmpty(outtime)){
			sb.append(" AND TO_DAYS(c.outtime)=TO_DAYS('"+outtime+"')");
		}
		PreparedStatement pstm = conn.prepareStatement(sb.toString());
		ResultSet rs = pstm.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
	
	/**
	 * 添加出货单记录
	 * @param conn
	 * @param chuhuo
	 * @return
	 * @throws SQLException
	 */
	public int chuhuoAdd(Connection conn, Chuhuo chuhuo) throws SQLException {
		String sql = "INSERT INTO t_chuhuo (goodsid, quantity, outtime) VALUES (?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, chuhuo.getGoodsid());
		pstm.setInt(2, chuhuo.getQuantity());
		pstm.setString(3, DateUtil.formatDate(chuhuo.getOuttime(), "yyyy-MM-dd"));
		return pstm.executeUpdate();
	}
}
