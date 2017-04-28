package com.java.jxc.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

/**
 * 连接池工具类
 * 
 * @author lch
 * @email 840446169@qq.com
 * @create 2013-10-26
 */
public class ConnectionPool {
	
	private Vector<Connection> pool; // 用Vector保存Connection对象

	private String url; // 路径
	
	private String username; // 账户
	
	private String password; // 密码
	
	private String driverClassName; // 驱动类型
	
	private int poolSize = 20; // 连接池的大小，也就是连接池中有多少个数据库连接

	private static ConnectionPool instance = null ; 
	
	/**
	 * 私有的构造方法，禁止外部创建本类的对象，要想获得本类的对象，通过<code>getInstance</code>方法。
	 * 使用了设计模式中的单太模式。
	 */
	private ConnectionPool() {
		init();
	}
	
	/**
	 * 连接池初始化方法，读取属性文件的内容 建立连接池中的初始连接
	 */
	private void init() {
		pool = new Vector<Connection>(poolSize);
		readConfig(); // 读取配置文件
		addConnection(); // 添加指定数目的连接
	}
	
	/**
	 * 返回连接到连接池中
	 * @param conn
	 */
	public synchronized void release(Connection conn) {
		pool.add(conn);
	}
	
	/**
	 * 关闭连接池中的所有数据库连接
	 */
	public synchronized void closePool() {
		for (int i = 0; i < poolSize; i++) {
			if(pool.get(i) != null){
				try {
					((Connection)pool.get(i)).close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// pool.remove(i);
			}
		}
		pool.clear(); //从此向量中移除所有元素。
	}
	
	/**
	 * 返回连接池中的一个数据库连接
	 * @return
	 */
	public synchronized Connection getConnection() {
		if (pool.size() > 0) {
			Connection conn = pool.get(0);
			pool.remove(conn);
			return conn;
		} else {
			return null;
		}
	}
	
	/**
	 * 在连接池中创建初始设置的数据库连接
	 */
	private void addConnection() {
		Connection conn = null;
		for (int i = 0; i < poolSize; i++) {
			try {
				Class.forName(driverClassName);
				conn = java.sql.DriverManager.getConnection(url, username, password);
				pool.add(conn);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void readConfig() {
		try {
			//String path = System.getProperty("user.dir") + "\\src\\dbpool.properties";  // 路径
			
			/**
			 *  加载配置文件
			 */
		//	FileInputStream is = new FileInputStream(path); 
			Properties props = new Properties();
			props.load(getClass().getResourceAsStream("/dbpool.properties"));
			
			/**
			 * 读取属性值
			 */
			this.driverClassName = props.getProperty("driverClassName"); // 读取数据库类型
			this.username = props.getProperty("username"); // 读取账户
			this.url = props.getProperty("url"); // 读取路径
			this.password = props.getProperty("password"); // 读取密码
			this.poolSize = Integer.parseInt(props.getProperty("poolSize")); // 读取数据库连接个数
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("读取属性文件出错。");
		}
	}
	
	/**
	 * 返回当前连接池的一个对象
	 * @return
	 */
	public static ConnectionPool getInstance() {
		if (instance == null) { // 如果当前没有连接池对象
			instance = new ConnectionPool(); // 创建连接池对象
		}
		return instance;
	}
}
