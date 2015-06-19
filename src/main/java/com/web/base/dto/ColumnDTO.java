package com.web.base.dto;

public class ColumnDTO {
	/**
	 * 列名称
	 */
	private String columnName;
	/**
	 * 类型
	 */
	private String columnType;
	/**
	 * 注释
	 */
	private String columnDesc;
	
	/**
	 * 是否外键
	 */
	private String isFk;
	/**
	 * 外键关联的主表对应的entity名称
	 */
	private String fkEntity;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getColumnDesc() {
		return columnDesc;
	}
	public void setColumnDesc(String columnDesc) {
		this.columnDesc = columnDesc;
	}
	public String getIsFk() {
		return isFk;
	}
	public void setIsFk(String isFk) {
		this.isFk = isFk;
	}
	public String getFkEntity() {
		return fkEntity;
	}
	public void setFkEntity(String fkEntity) {
		this.fkEntity = fkEntity;
	}
	
	
}
