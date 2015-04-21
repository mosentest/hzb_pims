package com.hzb.util;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DBUtil {
	// 驱动程序名
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	// URL指向要访问的数据库名db_pims
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/db_pims?useUnicode=true&characterEncoding=utf8";
	// MySQL配置时的用户名
	private final static String USER = "root";
	// MySQL配置时的密码
	private final static String PASSWORD = "root";
	
	private DBUtil(){}
	
	public static Connection getConnetion() {
		Connection conn = null;
		try {
			// 加载驱动程序
			Class.forName(DRIVER);
			// 连续数据库
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"大哥，你数据库装了没有啊，脚本导入了没有啊","提示",JOptionPane.INFORMATION_MESSAGE);
			throw new RuntimeException("连接数据库失败");
		}
		return conn;
	}
}
