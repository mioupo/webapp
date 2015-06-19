package com.web.base.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.base.redis.entity.RedisUser;
import com.web.base.redis.service.RedisUserSerivce;
import com.web.base.vo.ResultModel;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RestController
@Api(value = "redisUserTest", description = "redis用户管理", position = 1)  
@RequestMapping("redisUserTest")
public class RedisUserControllerTest {

	@Autowired
	private RedisUserSerivce service;
	
	@RequestMapping("save")
	@ApiOperation(value = "向缓存中加入数据", httpMethod = "POST", response = RedisUser.class)
	public @ResponseBody String save(
			@ApiParam(value="用户id") @RequestParam String id,
			@ApiParam(value="用户名") @RequestParam String name
			){
		RedisUser user = new RedisUser();
		user.setId(id);
		user.setUserName(name);
		service.save(user);
		return "{\"result\":\"ok\"}";
	}
	
	@RequestMapping("get")
	@ApiOperation(value = "获取R详情", httpMethod = "GET", response = RedisUser.class)
	public @ResponseBody String getOne(
			@ApiParam(value="用户id") @RequestParam String id
			){
		return service.getTest(id);
	}
	
	@RequestMapping("saveRows")
	@ApiOperation(value = "向缓存中加入用户列表", httpMethod = "POST", response = RedisUser.class)
	public @ResponseBody String saveRow(
			){
		service.saveRows();
		return "{\"result\":\"ok\"}";
	}
	
	@RequestMapping("findUsers")
	@ApiOperation(value = "查询用户列表", httpMethod = "POST", response = RedisUser.class)
	public @ResponseBody ResultModel showAll(
			@ApiParam(value="页号") @RequestParam Integer page,
			@ApiParam(value="条数") @RequestParam Integer pageSize,
			@ApiParam(value="集合key") @RequestParam String key
			){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("key", key);
		return service.findByCondition(page, pageSize, condition);
	}
}
