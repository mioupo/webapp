package com.web.base.redis.dao;

import java.util.List;
import java.util.Map;

import com.web.base.redis.entity.RedisUser;

public interface IRedisUserDao {
	/** 
     * 新增 
     * <br>------------------------------<br> 
     * @param user 
     * @return 
     */  
    boolean add(RedisUser user);  
      
    /** 
     * 批量新增 使用pipeline方式 
     * <br>------------------------------<br> 
     * @param list 
     * @return 
     */  
    boolean add(List<RedisUser> list);  
      
    /** 
     * 删除 
     * <br>------------------------------<br> 
     * @param key 
     */  
    void delete(String key);  
      
    /** 
     * 删除多个 
     * <br>------------------------------<br> 
     * @param keys 
     */  
    void delete(List<String> keys);  
      
    /** 
     * 修改 
     * <br>------------------------------<br> 
     * @param user 
     * @return  
     */  
    boolean update(RedisUser user);  
  
    /** 
     * 通过key获取 
     * <br>------------------------------<br> 
     * @param keyId 
     * @return  
     */  
    RedisUser get(String keyId);
    
    public String getTest(String keyId);
    
    public List<RedisUser> findAll(int page,int pageSize,Map<String,Object> condition);
    
    public void saveRows();
}
