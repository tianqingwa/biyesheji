/**
 * 
 */
package com.java.jxc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.jxc.model.Dictionary;
import com.java.jxc.model.PageBean;
import com.java.jxc.util.StringUtil;


/**
 * 数据字典实现类
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-11-13
 */
public class DictionaryService {
	
	/**
	 * 数据字典记录
	 * @param conn
	 * @param pageBean
	 * @param dictionary
	 * @return
	 * @throws SQLException
	 */
	public ResultSet dictionaryList (Connection conn, PageBean pageBean, Dictionary dictionary) throws SQLException {
		
		StringBuffer sb = new StringBuffer("SELECT * FROM t_dictionary");
		if(dictionary != null && StringUtil.isNotEmpty(dictionary.getName())){
			sb.append(" AND name LIKE '%"+ dictionary.getName() +"%'");
		}
		if(dictionary != null && StringUtil.isNotEmpty(dictionary.getValue())){
			sb.append(" AND value LIKE '%"+ dictionary.getValue() +"%'");
		}
		if(pageBean != null) {
			sb.append(" LIMIT "+ pageBean.getStart() +","+ pageBean.getRows());
		}
		PreparedStatement pstm = conn.prepareStatement(sb.toString().replaceFirst("AND", "WHERE"));
		return pstm.executeQuery();
	}
	
	/**
	 * 数据字典记录条数
	 * @param conn
	 * @param dictionary
	 * @return
	 * @throws SQLException
	 */
	public int dictionaryListTotal(Connection conn, Dictionary dictionary) throws SQLException {
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) AS total FROM t_dictionary");
		if(dictionary != null && StringUtil.isNotEmpty(dictionary.getName())){
			sb.append(" AND name LIKE '%"+ dictionary.getName() +"%'");
		}
		if(dictionary != null && StringUtil.isNotEmpty(dictionary.getValue())){
			sb.append(" AND value LIKE '%"+ dictionary.getValue() +"%'");
		}
		PreparedStatement pstm = conn.prepareStatement(sb.toString().replaceFirst("AND", "WHERE"));
		ResultSet rs = pstm.executeQuery();
		if (rs.next()){
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
	
	/**
	 * 删除数据字典记录
	 * @param conn
	 * @param delIds
	 * @return
	 * @throws SQLException
	 */
	public int dictionaryDelete(Connection conn, String delIds) throws SQLException {
		String sql = "DELETE FROM t_dictionary WHERE id IN ("+ delIds +")";
		PreparedStatement pstm = conn.prepareStatement(sql);
		return pstm.executeUpdate();
	}
	
	/**
	 * 添加数据字典记录
	 * @param conn
	 * @param dictionary
	 * @return
	 * @throws SQLException
	 */
	public int dictionaryAdd(Connection conn, Dictionary dictionary) throws SQLException {
		String sql = "INSERT INTO t_dictionary (name, value, remark) VALUES (?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, dictionary.getName());
		pstm.setString(2, dictionary.getValue());
		pstm.setString(3, dictionary.getRemark());
		return pstm.executeUpdate();
	}
	
	/**
	 * 修改数据字典记录
	 * @param conn
	 * @param dictionary
	 * @return
	 * @throws SQLException
	 */
	public int dictionaryModify(Connection conn, Dictionary dictionary) throws SQLException {
		String sql = "UPDATE t_dictionary SET name = ?, value = ?, remark = ? WHERE id = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, dictionary.getName());
		pstm.setString(2, dictionary.getValue());
		pstm.setString(3, dictionary.getRemark());
		pstm.setInt(4, dictionary.getId());
		return pstm.executeUpdate();
	}
}
