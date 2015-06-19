package com.web.base.redis.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.query.SortQuery;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.web.base.entity.BaseEntity;
import com.web.base.redis.dao.IRedisUserDao;
import com.web.base.redis.entity.RedisUser;
import com.web.base.redis.entity.School;
import com.web.base.util.Bean2MapUtil;
import com.web.base.vo.PageModel;
import com.web.base.vo.ResultModel;
import com.web.redis.common.AbstractBaseRedisDao;

@Repository
public class RedisUserDaoImpl extends AbstractBaseRedisDao<String,RedisUser> implements IRedisUserDao {

	@Override
	public boolean add(RedisUser user) {
		boolean result = true;
		School sc = new School();
		sc.setSchoolId(redisTemplate.randomKey());
		sc.setSchoolName("彩虹");
		sc.setSchooladdress("咸阳");
		user.setSchool(sc);
		//ValueOperations<String, String> lop = redisTemplate.opsForValue();
		/*HashOperations<String, String,Object> lop = redisTemplate.opsForHash();
		lop.put(user.getUid(), "username", user.getUserName());
		lop.put(user.getUid(), "school", user.getSchool());*/
		//ListOperations<String,String> lop = redisTemplate.opsForList();
		//lop.leftPush(user.getUid(),JSON.toJSON(user).toString());
		//lop.set(user.getUid(), JSON.toJSONString(user));
		return result;
	}

	@Override
	public boolean add(List<RedisUser> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(List<String> keys) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(RedisUser user) {
		//ValueOperations<String, RedisUser> value = redisTemplate.opsForValue();
		return false;
	}

	@Override
	public RedisUser get(String keyId) {
		//ValueOperations<String, String> lop = redisTemplate.opsForValue();
		//RedisUser result = (RedisUser) JSON.parse(lop.get(keyId));
		return null; 
	}

	@Override
	public String getTest(String keyId) {
		//ValueOperations<String, String> lop = redisTemplate.opsForValue();
		return null; 
	}
	
	public List<RedisUser> findAll(int page,int pageSize,Map<String,Object> condition){
		ListOperations<String, RedisUser> operations = redisTemplate.opsForList();
		int start = (page-1)*pageSize;
		int end = pageSize*page;
		List<RedisUser> content = operations.range(condition.get("key").toString(), start, end);
		return content;
	}

	@Override
	public void saveRows() {
		ListOperations<String, RedisUser> operations = redisTemplate.opsForList();
		//RedisUser[] users = new RedisUser[100];
		Map<RedisUser,RedisUser> map = new HashMap<RedisUser,RedisUser>();
		for(int i=0;i<100;i++){
			RedisUser user = new RedisUser();
			user.setId("z="+i);
			user.setUserName("y="+i);
			//Bean2MapUtil.transMap2Bean2(map, user);
		}
		//operations.leftPushAll("userlist", users);
		HashOperations<String, RedisUser, RedisUser> oo = redisTemplate.opsForHash();
		oo.putAll("userlist", map);
	}
}
