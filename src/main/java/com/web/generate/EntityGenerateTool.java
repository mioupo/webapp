package com.web.generate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.web.base.dto.ColumnDTO;
import com.web.base.util.DateUtils;
import com.web.base.util.FileUtils;
import com.web.base.util.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

/**
 * @author heqian 生成实体
 */
public class EntityGenerateTool {
	public static Properties loadProperties;
	public static String templatePath;
	public static String basedir;
	public static String bussiPackage;
	public static String columnIgnore;
	public static String basetable;
	public static String tableprefix;
	static {
		Resource resource = new ClassPathResource("/generate.properties");
		try {
			if (null == loadProperties) {
				loadProperties = PropertiesLoaderUtils.loadProperties(resource);
			}
			templatePath = loadProperties.getProperty("templatepath");
			bussiPackage = loadProperties.getProperty("bussiPackage");
			basedir = loadProperties.getProperty("basedir");
			columnIgnore = loadProperties.getProperty("column_ignore");
			basetable = loadProperties.getProperty("basetable");
			tableprefix = loadProperties.getProperty("tableprefix");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void generatEntity(Connection connection) {
		System.out.println("start to generate entity ============");
		Configuration cfg = new Configuration(new Version("2.3.22"));
		cfg.setLocale(Locale.CHINA);
		cfg.setDefaultEncoding("utf-8");
		cfg.setEncoding(Locale.CHINA, "utf-8");
		cfg.setClassForTemplateLoading(EntityGenerateTool.class, templatePath);
		cfg.setSharedVariable("cap_first", new UpperFirstCharacter());
		try {
			Template t = cfg.getTemplate("entityTemplate.ftl");
			// 获取数据库连接，获取所有表
			List<Map<String, String>> allTableName = getAllTableName(connection);
			// 遍历表
			for (Map<String, String> map : allTableName) {
				String entitystr = map.get("name").replace(tableprefix, "");
				String tname = StringUtils.capAndUpperCase(entitystr);
				String bizStr = "";
				String[] split = bussiPackage.split("\\.");
				for (String string : split) {
					bizStr += string + "/";
				}
				String dir = basedir  + bizStr+"entity";
				String fileName = "/"+tname + "Entity.java";
				boolean dirNotExistCreate = FileUtils.dirNotExistCreate(dir);
				if (!dirNotExistCreate) {
					System.out.println("创建文件夹失败....");
					throw new RuntimeException("创建文件夹失败....");
				}
				boolean fileIfNotCreate = FileUtils.fileIfNotCreate(dir + fileName);
				if (!fileIfNotCreate) {
					System.out.println("创建文件失败....");
					throw new RuntimeException("创建文件失败....");
				}
				// 判断文件夹是否存在，
				FileOutputStream fos = new FileOutputStream(new File(dir
						+ fileName));
				Map data = new HashMap();
				data.put("bussiPackage", bussiPackage);
				data.put("ftl_create_time", DateUtils.currentDate());
				data.put("ftl_description", map.get("remark"));
				data.put("tableName", map.get("name"));
				data.put("entityName", tname);
				List<Map<String, String>> pros = new ArrayList<Map<String, String>>();
				// 获取每一列以及类型生成实体
				List<ColumnDTO> columnInfo = getColumnInfoByTable(connection,
						map.get("name"));
				for (ColumnDTO columnDTO : columnInfo) {
					Map<String, String> map2 = new HashMap<String, String>();
					map2.put("fieldType", columnDTO.getColumnType());
					map2.put("filedComment", columnDTO.getColumnDesc());
					map2.put("fieldDbName", columnDTO.getColumnName());
					map2.put("isFk", columnDTO.getIsFk());
					if (null!=columnDTO.getFkEntity()) {
						map2.put("fkEntity",  StringUtils.capAndUpperCase(columnDTO.getFkEntity().replace(tableprefix, "")));
						map2.put("fkEntityFirstlower", StringUtils.capAndUpperCase(columnDTO.getFkEntity().replace(tableprefix, "")).toLowerCase());
					}
					
					if (!StringUtils.isEmpty(columnDTO.getColumnName())) {
						String str = StringUtils.capAndUpperCase(columnDTO
								.getColumnName());
						String substring = str.substring(0, 1);
						String substring2 = str.substring(1, str.length());
						map2.put("fieldName", substring.toLowerCase()
								+ substring2);
					}
					pros.add(map2);
				}
				data.put("originalColumns", pros);
				t.process(data, new OutputStreamWriter(fos, "utf-8"));//
				fos.flush();
				fos.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
		System.out.println("success...");
	}

	/**
	 * @param connection
	 * @return
	 * @throws SQLException
	 *             获取连接下所有表名称
	 */
	public List<Map<String, String>> getAllTableName(Connection connection)
			throws SQLException {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		if (connection.isClosed()) {
			connection = DBFactoryForConnection.getConnectionI()
					.getConnection();
		}
		DatabaseMetaData metaData = connection.getMetaData();
		ResultSet rs = null;
		String[] typeList = new String[] { "TABLE" };
		rs = metaData.getTables(null, "%", "%", typeList);
		for (boolean more = rs.next(); more; more = rs.next()) {
			String s = rs.getString("TABLE_NAME");
			String type = rs.getString("TABLE_TYPE");
			String remark = rs.getString("REMARKS");
			if (null == remark) {
				remark = "";
			}
			// 排除掉系统表和基础表
			if (type.equalsIgnoreCase("table") && s.indexOf("$") == -1
					&& !s.equals(basetable)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", s);
				map.put("remark", remark);
				result.add(map);
			}
		}
		if (null != rs) {
			rs.close();
		}
		return result;
	}

	public List<ColumnDTO> getColumnInfoByTable(Connection connection,
			String tableName) throws SQLException {
		List<ColumnDTO> result = new ArrayList<ColumnDTO>();
		if (connection.isClosed()) {
			connection = DBFactoryForConnection.getConnectionI()
					.getConnection();
		}
		
		DatabaseMetaData metaData = connection.getMetaData();
		//获取外键
		ResultSet fkKeys = metaData.getImportedKeys(null,null,tableName);  
		Map<String, String> fkMap = new HashMap<String, String>();
		while (fkKeys.next()) {
			 String fkColumnName = fkKeys.getString("FKCOLUMN_NAME");  
			 String pkTablenName = fkKeys.getString("PKTABLE_NAME");  
			 if (!pkTablenName.equalsIgnoreCase(basetable)) {
				 fkMap.put(fkColumnName, pkTablenName);
			}
		}
		ResultSet rs = metaData.getColumns(null, "%", tableName, "%");
		while (rs.next()) {
			String columnName = rs.getString("COLUMN_NAME");
			if (!columnIgnore.contains(columnName)) {
				String columnComment = rs.getString("REMARKS");
				if (null == columnComment) {
					columnComment = "";
				}
				String sqlType = rs.getString("TYPE_NAME");
				ColumnDTO dto = new ColumnDTO();
				dto.setColumnName(columnName);
				dto.setColumnType(transferDbType(sqlType));
				dto.setColumnDesc(columnComment);
				if (null!=fkMap.get(columnName)) {
					dto.setIsFk("1");
					dto.setFkEntity(fkMap.get(columnName));
				}else {
					dto.setIsFk("0");
				}
				result.add(dto);
			}
		}
		if (null != rs) {
			rs.close();
		}
		return result;
	}
	
	/**
	 * @param dbtype
	 * @return
	 * 先处理这么多，后续维护
	 */
	public String transferDbType(String dbtype){
		String result= "String";
		if (!StringUtils.isEmpty(dbtype)) {
			if (dbtype.toLowerCase().equals("varchar")||dbtype.toLowerCase().equals("varchar2")) {
				result = "String";
			}else if (dbtype.toLowerCase().equals("date")||dbtype.toLowerCase().equals("timestamp")||dbtype.toLowerCase().equals("time")) {
				result = "Date" ;
			}else if (dbtype.toLowerCase().equals("float")) {
				result = "Float";
			}else if (dbtype.toLowerCase().equals("double")) {
				result = "Double";
			}else if (dbtype.toLowerCase().equals("int")||dbtype.toLowerCase().equals("integer")) {
				result = "Integer";
			}else if (dbtype.toLowerCase().equals("decimal")) {
				result = "BigDecimal";
			}else if (dbtype.toLowerCase().equals("long")) {
				result = "Long";
			}
		}
		return result;
	}
}
