package com.web.base.redis.service;

import java.util.Map;

import com.web.base.redis.entity.RedisUser;
import com.web.base.vo.ResultModel;


public interface RedisUserSerivce {

	public void save(RedisUser user);
	
	public RedisUser get(String id);
	
	public String getTest(String id);
	
	public ResultModel findByCondition(int page,int pageSize,Map<String,Object> condition);
	
	public void saveRows();
}
