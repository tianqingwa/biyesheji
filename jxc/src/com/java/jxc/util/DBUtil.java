/**
 * 
 */
package com.java.jxc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.log4j.Logger;

/**
 * JDBC工具类
 * 
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-10-26
 * 
 */
public class DBUtil {

	protected final Logger logger = Logger.getLogger(getClass());
	
	ConnectionPool pool = ConnectionPool.getInstance(); // 连接池
	Connection conn = null; // 数据库连接
	PreparedStatement pstmt = null; // 用于执行一条静态的 SQL 语句并获取它产生的结果
	
	/** 获取连接对象 */
	public Connection getConnection() {
		conn = pool.getConnection();
		return conn;
	}

	/** 关闭流,释放连接 */
	public void close(Connection connection, PreparedStatement statement, ResultSet result) {
		try {
			if (result != null)
				result.close();
			if (statement != null)
				statement.close();
			if (conn != null) 
				this.pool.release(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("关闭数据库连接时出现异常！");
		}
	}
	
}

