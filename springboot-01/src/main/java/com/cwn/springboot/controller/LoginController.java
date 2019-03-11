package com.cwn.springboot.controller;

import com.cwn.springboot.bean.UserVO;
import com.cwn.springboot.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {


    @Autowired
    private IndexService indexService;

    /**
     * 登陆页面
     * @param
     * @return
     */
    @PostMapping(value="/login")
    //@RequestMapping(value="/user/login",method = RequestMethod.POST)
    public String lgoin(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session){

        UserVO userVO= indexService.getUserByUP(username,password);
        String loginUser= (String) session.getAttribute("loginUser");
        //设置session
         session.setAttribute("loginUser",username);
        if(userVO!=null){
            if(username.equals("admin")){//管理员登陆

                return "redirect:adminback/adminindex?pp="+loginUser;
            }else{

            //登陆成功
            String headimg =indexService.getHeadImgpath(username);



            map.put("user",userVO.getUsername());
            map.put("usersigninfo",userVO.getUsersigninfo());
            map.put("headimg",headimg);
            map.put("loginUser",loginUser);

            return "redirect:accountmain?pp="+loginUser+"&l=zh_CH";
            }
        } else{
           //登陆失败
            map.put("msg","用户名或用户密码错误！");
            return "login";
        }


    }



    /**
     * 登陆页面1234
     * @param
     * @return
     */
    @PostMapping(value="/loginin")
    //@RequestMapping(value="/user/login",method = RequestMethod.POST)
    public HttpSession loginin(@RequestParam("username") String username,
                               @RequestParam("password") String password){

        UserVO userVO= indexService.getUserByUP(username,password);
        HttpSession   session=null;
        session.setAttribute("loginUser",userVO.getUsername());

     return session;
    }
}
