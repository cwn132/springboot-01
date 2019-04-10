package com.cwn.springboot.api;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cwn.springboot.config.utils.JwtUtil;
import com.cwn.springboot.filter.JwtAuthenticationFilter;
import io.swagger.annotations.Api;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/loginapi")
@Api("登陆api")
public class loginApi {

    @RequestMapping(value="check", method=RequestMethod.POST)
    @ApiOperation(value="登入身份验证（JWT验证）", notes="登入")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名称",required = true,paramType = "query"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,paramType = "query")
    })
//    public Object getLoginInfo(HttpServletResponse response,HttpServletRequest request) {
    public Object getLoginInfo(@RequestParam String username,@RequestParam String password) {
        Account account = new Account();
//        account.setUsername(request.getParameter("username").toString());
//        account.setPassword(request.getParameter("password").toString());
        account.setUsername(username);
        account.setPassword(password);

        if(isValidPassword(account)) {
            String jwt = JwtUtil.generateToken(account.username);
            return new HashMap<String,String>(){{
                put("token", jwt);
                System.out.println("token:"+jwt);
            }};
        }else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        registrationBean.setFilter(filter);
        return registrationBean;
    }

    //密码是否正确
    private boolean isValidPassword(Account ac) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("userName", ac.getUsername());
        param.put("password", ac.getPassword());

        return ("test".equals(param.get("userName")))&&("123".equals(param.get("password")));

    }


    public static class Account {
        private String username;
        private String password;
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }

}
