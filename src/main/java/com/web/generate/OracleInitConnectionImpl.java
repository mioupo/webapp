package com.web.generate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class OracleInitConnectionImpl extends InitConnectionImpl{

	@Override
	public Connection getConnection() {
		Connection con = null;
		try {
			Properties props = new Properties();
			props.put("remarksReporting", "true");
			props.put("user", username);
			props.put("password", password);
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, props);
			System.out.println("success to connect the database");
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
		}
		return con;
	}

}
