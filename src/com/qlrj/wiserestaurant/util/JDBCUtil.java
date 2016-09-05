package com.qlrj.wiserestaurant.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	public static Connection getConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
//		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/wiserestaurant","root","123456");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/wiserestaurant?user=root&password=123456&useUnicode=true&characterEncoding=utf-8");
//		Connection conn=DriverManager.getConnection("jdbc:mysql://121.42.198.119:3306/wiserestaurant","root","12345678");
		return conn;
	}
	
	public static void close(Connection conn,Statement stmt,ResultSet res) throws Exception{
		res.close();
		res=null;
		stmt.close();
		stmt=null;
		conn.close();
		conn=null;
	}
	public static void close(Connection conn,Statement stmt) throws Exception{
		stmt.close();
		stmt=null;
		conn.close();
		stmt=null;
	}
	
}
