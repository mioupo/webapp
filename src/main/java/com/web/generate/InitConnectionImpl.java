package com.web.generate;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


public abstract class InitConnectionImpl implements InitConnectionI {
	public static String username;
	public static String password;
	public static String url;
	public static String driver;
	public static Properties loadProperties;
	static {
		Resource resource = new ClassPathResource("/database.properties");
		try {
			if (null == loadProperties) {
				loadProperties = PropertiesLoaderUtils.loadProperties(resource);
			}
			username = loadProperties.getProperty("jdbc.username");
			password = loadProperties.getProperty("jdbc.password");
			url = loadProperties.getProperty("jdbc.url");
			driver = loadProperties.getProperty("jdbc.driver");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public abstract Connection getConnection() ;
}
