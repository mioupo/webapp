package com.web.redis.common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.web.base.entity.BaseEntity;
import com.web.base.exception.BusinessException;
import com.web.base.vo.PageModel;

@Service("redisService")
public class RedisServiceImpl extends AbstractBaseRedisDao<String, BaseEntity>
		implements RedisService {

	@Override
	public void add(BaseEntity obj, Integer timeout) {
		if (null != obj) {
			ValueOperations<String, BaseEntity> operations = this.redisTemplate
					.opsForValue();
			// 设置过期时间
			operations.set(CacheKey.getEntityInfoKey(obj.getId().toString()), obj,
					timeout, TimeUnit.SECONDS);
		}
	}

	@Override
	public void addList(String key, List<BaseEntity> obj, Integer timeout) {
		ListOperations<String, BaseEntity> operations = redisTemplate
				.opsForList();
		operations.leftPushAll(key, obj.toArray(new BaseEntity[obj.size()]));
		redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}

	@Override
	public void addHash(String key, BaseEntity obj, Integer timeout) {
		SetOperations<String, BaseEntity> opsForSet = redisTemplate.opsForSet();
		opsForSet.add(key, obj);
		redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}

	@Override
	public PageModel getList(int gotoPage, int pageSize, String key) {
		PageModel pageModel = new PageModel();
		pageModel.setGotoPage(gotoPage);
		pageModel.setPageSize(pageSize);
		int start = (gotoPage - 1) * pageSize;
		int end = pageSize * gotoPage;
		ListOperations<String, BaseEntity> operations = redisTemplate
				.opsForList();
		List<BaseEntity> content = operations.range(key, start, end);
		pageModel.setGotoPage(gotoPage);
		pageModel.setPageSize(pageSize);
		pageModel.setObjs(content);
		return pageModel;
	}

	@Override
	public BaseEntity get(BaseEntity obj) throws BusinessException {
		ValueOperations<String, BaseEntity> operations = this.redisTemplate
				.opsForValue();
		BaseEntity baseEntity = operations.get(CacheKey.getEntityInfoKey(obj
				.getId().toString()));
		return baseEntity;
	}

	@Override
	public BaseEntity getHash(String key) {
		return null;
	}

}
