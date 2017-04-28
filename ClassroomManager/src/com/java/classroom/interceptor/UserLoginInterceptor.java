package com.java.classroom.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.java.classroom.bean.User;

public class UserLoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		System.out.println("preHandle");
        //return false表示拦截，不向下执行
         //return true表示放行
		User user=(User) request.getSession().getAttribute("currentUser");
//		System.out.println(user);
		if(user==null){
			 response.sendRedirect("/jsp/login.jsp");
			 return false;
		}
		return true;
	}

}
