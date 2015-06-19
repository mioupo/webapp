package com.web.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.base.entity.BaseEntity;
import com.web.base.exception.BusinessException;
import com.web.base.redis.dao.IRedisUserDao;
import com.web.base.redis.entity.RedisUser;
import com.web.redis.common.RedisService;
import com.web.test.base.TestBase;


public class RedisTest extends TestBase{

	@Autowired
	private IRedisUserDao dao;
	@Autowired
	private RedisService service;
//	@Test
//	public void addRedisUser(){
//		RedisUser user = new RedisUser();
//		user.setId("1");
//		user.setUserName("test");
//		dao.add(user);
//	}
	@Test
	public void testAddEntity(){
		long start = System.currentTimeMillis();
		RedisUser user = new RedisUser();
		for (int i = 0; i < 10000; i++) {
			user.setId(String.valueOf(i));
			user.setUserName("test"+i);
			service.add(user, 3600);
		}
		long end = System.currentTimeMillis();
		System.out.println("-------------insert 10000 data need "+(end-start)/1000+" second");
	}
	
	public void testTimeOut() throws BusinessException{
		RedisUser user = new RedisUser();
		user.setId("1");
		RedisUser baseEntity = (RedisUser)service.get(user);
		System.out.println("-----------------"+baseEntity.getUserName());
		try {  
            Thread.sleep(70000);  
        } catch (InterruptedException e) {  
        } 
		RedisUser baseEntity2 = (RedisUser)service.get(user);
		if (null!=baseEntity2) {
			System.out.println("-----------------"+baseEntity2.getUserName());
		}else {
			System.out.println("-------------------the cache is expired");
		}
	}
}
