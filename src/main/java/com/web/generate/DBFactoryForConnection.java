package com.web.generate;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.web.base.util.Baseconst;


public  class DBFactoryForConnection {
	private static InitConnectionI connectionI;
	private static String dialect;
	public static Properties loadProperties;
	static {
		Resource resource = new ClassPathResource("/database.properties");
		try {
			if (null == loadProperties) {
				loadProperties = PropertiesLoaderUtils.loadProperties(resource);
			}
			dialect = loadProperties.getProperty("hibernate.dialect");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static InitConnectionI getConnectionI() {
		if (null==connectionI) {
			if (null==dialect||Baseconst.SQL_DIALECT_TYPE_MYSQL.equals(dialect)) {
				connectionI = new MySQLInitConnectionImpl();
			}else if (Baseconst.SQL_DIALECT_TYPE_ORACLE.equals(dialect)) {
				connectionI = new OracleInitConnectionImpl();
			}
		}
		return connectionI;
	}
}
