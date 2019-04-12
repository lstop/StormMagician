package org.flowable.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnUtil {
	public static Connection getConnection() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.78.128:3306/flowable6?useUnicode=true&characterEncoding=utf-8";
		String user = "root";
		String passwd = "root";
		
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
