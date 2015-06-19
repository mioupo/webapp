package com.web.redis.common;

public class CacheKey {
	private static String CACHE_KEY ;;
	
	private static String getCacheKey(){
		if (null==CACHE_KEY) {
			CACHE_KEY = "cache";
		}
		return CACHE_KEY;
	}
	
	/**
	 * @param userId
	 * @return
	 * 根据实体获取key
	 */
	public static String getEntityInfoKey(String entityId){
		return getCacheKey()+"_entityInfoById_"+entityId;
	}
}
