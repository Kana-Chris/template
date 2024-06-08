package com.diworksdev.template.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private static String driverName = "org.mariadb.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/ecsite?useUnicode=true&characterEncoding=UTF8";
	private static String user = "root";
	private static String password ="";
	
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driverName);
			con = (Connection) DriverManager.getConnection(url,user,password);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}
