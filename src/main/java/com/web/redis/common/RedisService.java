package com.web.redis.common;


import java.util.List;

import com.web.base.entity.BaseEntity;
import com.web.base.exception.BusinessException;
import com.web.base.vo.PageModel;


public interface RedisService {
	
	
	/**
	 * @param obj
	 * @param timeout 过期时间，单位 秒
	 */
	public void add(BaseEntity obj,Integer timeout);
	
	public void addList(String key,List<BaseEntity> obj,Integer timeout);
	
	public void addHash(String key,BaseEntity obj, Integer timeout);
	
	public BaseEntity get(BaseEntity obj) throws BusinessException;
	
	public PageModel getList(int gotoPage,int pageSize,String key) throws BusinessException;
	
	public BaseEntity getHash(String key) throws BusinessException;
}
