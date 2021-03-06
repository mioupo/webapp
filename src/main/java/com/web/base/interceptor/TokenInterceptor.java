package com.web.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.web.base.service.impl.AssistService;

public class TokenInterceptor implements HandlerInterceptor {

	 private Logger log = Logger.getLogger(this.getClass());
	 
	 @Autowired
	 private AssistService assistService;
	 
	 /** 
	     * 在业务处理器处理请求之前被调用 
	     * 如果返回false 
	     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
	     *  
	     * 如果返回true 
	     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
	     *    再执行被拦截的Controller 
	     *    然后进入拦截器链, 
	     *    从最后一个拦截器往回执行所有的postHandle() 
	     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
	     */  
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.debug("interceptor");
		boolean flag = false;
		String token = request.getParameter("token");
		if(null!=token&&!token.equals(""))
			
		if(flag)
			response.sendRedirect(request.getContextPath()+"/redisUserTest/error");
		else
			flag = true;
		return true;
	}
	//在业务处理器处理请求执行完成后,生成视图之前执行的动作   
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
