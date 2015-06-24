package com.web.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.base.entity.MobileUser;
import com.web.base.service.CmsUserService;
import com.web.base.util.WebUitl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RestController
@Api(value = "/r/mysqlUserTest", description = "mysql用户管理", position = 1)  
@RequestMapping("/r/mysqlUserTest")
public class MsqlUserControllerTest {

	@Autowired
	private CmsUserService userService;
	
	@RequestMapping(value="show", method={RequestMethod.GET,RequestMethod.POST} )
	@ApiOperation(value = "显示用户列表", httpMethod ="POST", response = Page.class)
	public @ResponseBody Page<MobileUser> show(
			@ApiParam(value="下一页") @RequestParam Integer nextPage,
			@ApiParam(value="每页显示多少条") @RequestParam Integer pageSize,
			@ApiParam(value="排序字段") @RequestParam(required =false) String sortDate
			){
		
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortDate);
		return userService.findAll(pageable);
	}
	
	@RequestMapping("get")
	@ApiOperation(value = "获取用户详情", httpMethod = "GET", response = MobileUser.class)
	public @ResponseBody MobileUser getOne(
			@ApiParam(value="用户id") @RequestParam Integer id
			){
		
		return userService.getOne(id);
	}
	
	@RequestMapping("getByUserName")
	@ApiOperation(value = "根据用户名查找用户", httpMethod = "GET", response = MobileUser.class)
	public @ResponseBody MobileUser getByUserName(
			@ApiParam(value="用户名") @RequestParam String userName
			){
		
		return userService.findByUserName(userName);
	}
}
