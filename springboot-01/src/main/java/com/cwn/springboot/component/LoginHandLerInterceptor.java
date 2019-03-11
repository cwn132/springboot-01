package com.cwn.springboot.component;

import org.springframework.http.HttpRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
登陆拦截器
 */
public class LoginHandLerInterceptor implements HandlerInterceptor {

 @Override
public boolean preHandle(HttpServletRequest request,
                         HttpServletResponse response,
                         Object handler) throws ServletException, IOException {

  Object user=  request.getSession().getAttribute("loginUser");

  if(user==null){
      //未登陆
      request.setAttribute("msg","用户已退出，请重新登陆!");
      //返回主页
      request.getRequestDispatcher("/login.html").forward(request,response);
      return false;
  }else{
      //已登陆后台操作
     // request.getRequestDispatcher("/accountmain.html").forward(request,response);
      return true;
  }

}


}
