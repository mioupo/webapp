package com.web.base.redis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

import com.web.base.entity.BaseEntity;
import com.web.base.redis.dao.IRedisUserDao;
import com.web.base.redis.entity.RedisUser;
import com.web.base.redis.service.RedisUserSerivce;
import com.web.base.vo.PageModel;
import com.web.base.vo.ResultModel;

@Service("redisUserService")
public class RedisUserServiceImpl implements RedisUserSerivce {

	@Autowired
	private IRedisUserDao dao;
	
	@Override
	public void save(RedisUser user) {
		dao.add(user);
	}

	@Override
	public RedisUser get(String id) {
		return dao.get(id);
	}

	@Override
	public String getTest(String id) {
		return dao.getTest(id);
	}

	@Override
	public ResultModel findByCondition(int page, int pageSize,
			Map<String, Object> condition) {
		ResultModel result = new ResultModel();
		PageModel pageModel = new PageModel();
		pageModel.setGotoPage(page);
		pageModel.setPageSize(pageSize);
		result.setResultCode(1);
		result.setResultData(dao.findAll(page, pageSize, condition));
		result.setResultMessage("success");
		return result;
	}
	@Override
	public void saveRows(){
		dao.saveRows();
	}

}
