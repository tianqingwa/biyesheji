/**
 * 
 */
package com.java.jxc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.jxc.model.PageBean;
import com.java.jxc.model.User;
import com.java.jxc.util.DBUtil;
import com.java.jxc.util.StringUtil;


/**
 * 管理员模块实现类
 * 
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-10-26
 */
public class UserService {
	
	DBUtil dbUtil = new DBUtil();
	ResultSet rs = null;
	
	/**
	 * 登录验证
	 * 
	 * @param user
	 * @return User
	 */
	public User login (User user) {
		Connection conn = dbUtil.getConnection();
		String sql = "select * from t_user where username=? and password=?";
		User u = null;
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUsername());
			pstm.setString(2, user.getPassword());
			rs = pstm.executeQuery();
			while(rs.next()) {
				u = new User();
				u.initFormRs(rs);
			} 
		}catch (SQLException e) {
				e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstm, rs);
		}
		return u;
	}
	
	public String logout() {
		return null;
	}
	
	/**
	 * 管理员记录数
	 * @param currentuser
	 * @return
	 */
	public int getTotal(User currentuser, String s_username) {
		int total = 0;
		Connection conn = dbUtil.getConnection();
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) AS total FROM t_user");
		if(currentuser != null && StringUtil.isNotEmpty(s_username)) {
			sb.append(" AND username LIKE '%"+ s_username +"%'");
		}
		sb.append(" WHERE id != ?");
		String sql = sb.toString().replaceFirst("AND", "WHERE");
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, currentuser.getId());
			rs = pstm.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstm, rs);
		}
		return total;
	}
	
	/**
	 * 保存
	 * 
	 * @param user
	 */
	public int insert(User user) {
		int num = 0;
		Connection conn = dbUtil.getConnection();
		String sql = "insert into t_user (username, password) values (?,?)";
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUsername());
			pstm.setString(2, user.getPassword());
			num = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstm, rs);
		}
		return num;
	}
	
	/**
	 * 更新
	 * 
	 * @param user
	 */
	public int update(User user) {
		int num = 0;
		Connection conn = dbUtil.getConnection();
		String sql = "update t_user set username=? , password=? where id = ?";
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUsername());
			pstm.setString(2, user.getPassword());
			pstm.setInt(3, user.getId());
			num = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstm, rs);
		}
		return num;
	}
	
	public int delete (String delIds) {
		int num = 0;
		Connection conn = dbUtil.getConnection();
		String sql = "DELETE FROM t_user WHERE id IN ("+ delIds +")";
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
	
//	/**
//	 * 通过ID删除
//	 * 
//	 * @param id
//	 */
//	public void deleteById(int id) {
//		Connection conn = dbUtil.getConnection();
//		String sql = "delete from t_user where id = ?";
//		PreparedStatement pstm = null;
//		try {
//			pstm = conn.prepareStatement(sql);
//			pstm.setInt(1, id);
//			pstm.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbUtil.close(conn, pstm, rs);
//		}
//	}
//	
//	/**
//	 * 通过User删除
//	 * 
//	 * @param user
//	 */
//	public void deleteById(User user) {
//		Connection conn = dbUtil.getConnection();
//		String sql = "delete from t_user where id = ?";
//		PreparedStatement pstm = null;
//		try {
//			pstm = conn.prepareStatement(sql);
//			pstm.setInt(1, user.getId());
//			pstm.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbUtil.close(conn, pstm, rs);
//		}
//	}
	
	/**
	 * 获取管理员记录
	 * 
	 * @param user
	 * @return
	 */
	public ResultSet queryForAll(User currentuser, PageBean pageBean, String s_username) {
		Connection conn = dbUtil.getConnection();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_user");
		
		if(currentuser != null && StringUtil.isNotEmpty(s_username)) {
			sb.append(" AND username LIKE '%"+ s_username +"%'");
		}
		
		//不查找自己
		sb.append(" AND id != "+ currentuser.getId());
		
		if(pageBean != null) {
			sb.append(" LIMIT "+ pageBean.getStart() +","+ pageBean.getRows());
		}
		
		String sql = sb.toString().replaceFirst("AND", "WHERE");
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 检测用户是否存在
	 * 
	 * @param username
	 * @return
	 */
	public boolean checkUser (String username) {
		boolean flag = false;
		Connection conn = dbUtil.getConnection();
		String sql = "select * from t_user where username = ?";
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			rs = pstm.executeQuery();
			if(!rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstm, rs);
		}
		return flag;
	}
	
	public boolean checkUser (User user) {
		boolean flag = false;
		Connection conn = dbUtil.getConnection();
		String sql = "select * from t_user where username = ? and id != ? ";
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUsername());
			pstm.setInt(2, user.getId());
			rs = pstm.executeQuery();
			if(!rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstm, rs);
		}
		return flag;
	}
	
}

