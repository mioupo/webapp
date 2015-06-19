package com.web.generate;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLInitConnectionImpl extends InitConnectionImpl{
	@Override
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, username, password);
			System.out.println("success to connect the database");
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
		}
		return con;
	}
}
