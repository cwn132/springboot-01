package com.cwn.springboot.controller;

import com.cwn.springboot.bean.UserVO;
import com.cwn.springboot.service.IndexService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import java.util.logging.Logger;
@Controller
public class LoginController {

    Logger logger = Logger.getLogger(String.valueOf(LoginController.class));


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

        //加密
        String encodedata= encode(password);
//        System.out.println(encodedata);

        UserVO userVO= indexService.getUserByUP(username,encodedata);
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
     * 登陆页面
     * @param
     * @return
     */
    @PostMapping(value="/loginin")
    //@RequestMapping(value="/user/login",method = RequestMethod.POST)
    public HttpSession loginin(@RequestParam("username") String username,
                               @RequestParam("password") String password){

        //加密
        String encodedata= encode(password);
System.out.println(encodedata);

        UserVO userVO= indexService.getUserByUP(username,encodedata);
        HttpSession   session=null;
        session.setAttribute("loginUser",userVO.getUsername());

     return session;
    }


    /**
     * 将 BASE64 编码的字符串 s 进行加码
     *
     * @return String
     */
    public static String encode(String s) {
        if (s == null)
            return null;
        String res = "";
        try {
            res = new sun.misc.BASE64Encoder().encode(s.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 将 BASE64 编码的字符串 s 进行解码
     *
     * @return String
     */
    public static String decode(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b,"UTF-8");
        } catch (Exception e) {
            return null;
        }
    }
}
