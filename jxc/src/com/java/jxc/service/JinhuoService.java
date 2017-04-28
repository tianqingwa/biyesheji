/**
 * 
 */
package com.java.jxc.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.jxc.model.Jinhuo;
import com.java.jxc.model.PageBean;
import com.java.jxc.util.DateUtil;
import com.java.jxc.util.StringUtil;

/**
 * 进货单模块实现类
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-11-19
 */
public class JinhuoService {

	/**
	 * 进货单记录
	 * @param conn
	 * @param pageBean
	 * @param jinhuo
	 * @return
	 * @throws SQLException
	 */
	public ResultSet jinhuoList(Connection conn, PageBean pageBean, Jinhuo jinhuo, String intodate) throws SQLException {
		StringBuffer sb = new StringBuffer("SELECT * FROM t_jinhuo j , t_goods g WHERE j.goodsid = g.goodsid ");
		if(jinhuo.getGoodsid() != -1){
			sb.append("AND j.goodsid = "+ jinhuo.getGoodsid());
		}
		if(StringUtil.isNotEmpty(intodate)){
			sb.append(" AND TO_DAYS(j.intodate)=TO_DAYS('"+intodate+"')");
		}
		if(pageBean != null) {
			sb.append(" LIMIT "+ pageBean.getStart() +","+ pageBean.getRows());
		}
		PreparedStatement pstm = conn.prepareStatement(sb.toString());
		return pstm.executeQuery();
	}
	
	/**
	 * 进货单记录条数
	 * @param conn
	 * @param jinhuo
	 * @return
	 * @throws SQLException
	 */
	public int jinhuoListTotal(Connection conn, Jinhuo jinhuo, String intodate) throws SQLException {
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) AS total FROM t_jinhuo j , t_goods g WHERE j.goodsid = g.goodsid ");
		if(jinhuo.getGoodsid() != -1){
			sb.append("AND j.goodsid = "+ jinhuo.getGoodsid());
		}
		if(StringUtil.isNotEmpty(intodate)){
			sb.append(" AND TO_DAYS(j.intodate)=TO_DAYS('"+intodate+"')");
		}
		PreparedStatement pstm = conn.prepareStatement(sb.toString());
		ResultSet rs = pstm.executeQuery();		
		if (rs.next()){
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
	
	/**
	 * 添加进货单记录
	 * @param conn
	 * @param jinhuo
	 * @return
	 * @throws SQLException
	 */
	public int jinhuoAdd(Connection conn, Jinhuo jinhuo) throws SQLException {
		String sql = "INSERT INTO t_jinhuo VALUES (NULL, ?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, jinhuo.getGoodsid());
		pstm.setInt(2, jinhuo.getQuantity());
		pstm.setString(3, DateUtil.formatDate(jinhuo.getIntodate(), "yyyy-MM-dd"));
		return pstm.executeUpdate();
	}
	
	/**
	 * 修改进货单记录
	 * @param conn
	 * @param jinhuo
	 * @return
	 * @throws SQLException
	 */
	public int jinhuoModify(Connection conn, Jinhuo jinhuo) throws SQLException {
		String sql = "UPDATE t_jinhuo SET goodsid = ?, quantity = ?, intodate = ? WHERE id = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, jinhuo.getGoodsid());
		pstm.setInt(2, jinhuo.getQuantity());
		pstm.setString(3, DateUtil.formatDate(jinhuo.getIntodate(), "yyyy-MM-dd"));
		pstm.setInt(4, jinhuo.getId());
		return pstm.executeUpdate();
	}
}
