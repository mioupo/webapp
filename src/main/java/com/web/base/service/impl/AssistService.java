package com.web.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.base.service.BaseService;
import com.web.redis.common.RedisService;

@Service
public class AssistService implements BaseService {

	@Autowired
	private RedisService redisService;
	
	public boolean checkToken(String token){
		boolean flag = false;
		return flag;
	}
}
