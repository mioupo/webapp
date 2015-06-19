package com.web.base.util;

import java.io.File;
import java.io.IOException;

public abstract class FileUtils {
	/**
	 * @param filePath
	 *            判断文件是否存在，如果不存在则创建
	 */
	public static boolean fileIfNotCreate(String filePath){
		File file=new File(filePath);    
		if(file.exists())    
		{  
			file.delete();
		}else {
			try {    
				return file.createNewFile();    
			} catch (IOException e) {    
			}    
		} 
		return true;
	}

	/**
	 * @param dir
	 *            判断文件夹是否存在，如果不存在则创建文加件
	 */
	public static boolean dirNotExistCreate(String dir) {
		File file = new File(dir);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			return file.mkdirs();
		}
		return true;
	}
	
}
