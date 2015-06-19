package com.web.base.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.base.vo.ResultModel;

@Controller
@RequestMapping("baseController")
public class BaseController {
	
	public @ResponseBody Map<String,Object> error(String errorMsg,Integer errorCode){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("errorMsg", errorMsg);
		result.put("errorCode", errorCode);
		return result;
	}
	
	public @ResponseBody Map<String,Object> success(String resultMsg,ResultModel resultModel){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("msg", resultMsg);
		result.put("result", resultModel);
		return result;
	}
}
