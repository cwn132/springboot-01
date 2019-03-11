package com.cwn.springboot.controller;

import com.cwn.springboot.bean.UserVO;
import com.cwn.springboot.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 主页面
     * @param
     * @return
     */
//  @RequestMapping("/index.html")
@RequestMapping(value="/index",method=RequestMethod.GET)
  public String success(@RequestParam("pp") String  username,
                        Map<String,Object> map,
                        Model model,
                        HttpServletRequest request,
                        HttpServletResponse response){
//      System.out.println("pp:"+ username);
      map.put("user",username);
    Object loginUser=  request.getSession().getAttribute("loginUser");

      UserVO userVO= indexService.getUserInfo(username);

    //没有此帐户返回首页
    if(userVO==null){
        return "redirect:index.html";
    }else{//有此帐户获取个人资源
        String headimg =indexService.getHeadImgpath(username);
        List  imgpathList = indexService.getImgpathListbyUser (username);

        map.put("usersigninfo",userVO.getUsersigninfo());
        map.put("headimg",headimg);
        model.addAttribute("imgpathList",imgpathList); //显示帐户图片连接
        map.put("loginUser",loginUser);

        return  "index";
    }

  }


    /**
     * 关于个人页面
     * @param
     * @return
     */
    @RequestMapping(value="/about",method=RequestMethod.GET)
    public String about(@RequestParam("pp") String  username, Map<String,Object> map,
                        Model model,
                        HttpServletRequest request,
                        HttpServletResponse response){
        map.put("user",username);

        UserVO userVO= indexService.getUserInfo(username);
        Object loginUser=  request.getSession().getAttribute("loginUser");

        //没有此帐户返回首页
        if(userVO==null){
            return "redirect:about.html";
        }else{//有此帐户获取个人资源

            String headimg =indexService.getHeadImgpath(username);
            List teamlist=indexService.getTeams();

            map.put("usersigninfo",userVO.getUsersigninfo());
            map.put("about",userVO.getAbout());
            map.put("headimg",headimg);
            model.addAttribute("teamlist",teamlist); //团队成员
            map.put("loginUser",loginUser);


            return  "about";
        }
    }

    /**
     * 关于联系我页面
     * @param
     * @return
     */
    @RequestMapping(value="/contact",method=RequestMethod.GET)
    public String contact(@RequestParam("pp") String  username, Map<String,Object> map,
                          Model model,
                          HttpServletRequest request,
                          HttpServletResponse response){
        map.put("user",username);
        UserVO userVO= indexService.getUserInfo(username);
        Object loginUser=  request.getSession().getAttribute("loginUser");
        //没有此帐户返回首页
        if(userVO==null){
            return "redirect:contact.html";
        }else{//有此帐户获取个人资源

            String headimg =indexService.getHeadImgpath(username);

            map.put("usersigninfo",userVO.getUsersigninfo());
            map.put("headimg",headimg);
            map.put("loginUser",loginUser);
            return  "contact";
        }
    }

    /**
     * 图片展示页面
     * @param
     * @return
     */
    @RequestMapping(value="/single",method=RequestMethod.GET)
    public String single(@RequestParam("pp") String  username, @RequestParam("documentfileid") Long  documentfileid,
                         Map<String,Object> map,
                         Model model,
                         HttpServletRequest request,
                         HttpServletResponse response){
        map.put("user",username);
        map.put("documentfileid",documentfileid);
        UserVO userVO= indexService.getUserInfo(username);
        Object loginUser=  request.getSession().getAttribute("loginUser");
        //没有此帐户返回首页
        if(userVO==null){
            return "redirect:index.html";
        }else{//有此帐户获取个人资源
            List imgpathmsg=indexService.getImgpathListbyDocumentfile(documentfileid);
            String headimg =indexService.getHeadImgpath(username);

            map.put("headimg",headimg);
            map.put("usersigninfo",userVO.getUsersigninfo());
            model.addAttribute("imgpathmsg",imgpathmsg); //图片信息
            map.put("loginUser",loginUser);
            return  "single";
        }
    }

    /**
     * 作品集页面
     * @param
     * @return
     */
    @RequestMapping(value="/portfolio",method=RequestMethod.GET)
    public String portfolio(@RequestParam("pp") String  username, Map<String,Object> map, Model model){
        map.put("user",username);
        UserVO userVO= indexService.getUserInfo(username);

        //没有此帐户返回首页
        if(userVO==null){
            return "redirect:index.html";
        }else{//有此帐户获取个人资源

            String headimg =indexService.getHeadImgpath(username);

            map.put("usersigninfo",userVO.getUsersigninfo());

            return  "portfolio";
        }
    }


    /**
     * 图片展示
     * @param
     * @return
     */
    @RequestMapping(value="/photoshow",method=RequestMethod.GET)
    public String photoshow(@RequestParam("pp") String  username, @RequestParam("documentfileid") Long  documentfileid,
                            Map<String,Object> map,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response){
        map.put("user",username);
        map.put("documentfileid",documentfileid);

        UserVO userVO= indexService.getUserInfo(username);
        Object loginUser=  request.getSession().getAttribute("loginUser");
        map.put("loginUser",loginUser);
        //没有此帐户返回首页
        if(userVO==null){
            return "redirect:index.html";
        }else{//有此帐户获取个人资源

            return  "photoshow";
        }
    }
//    @RequestMapping("/listUser")
//    public List<Map<String,Object>> listUser(){
//        return userService.listUser();
//    }

//    public UserVO getUserInfo(String username ) {
//
//        return  userService.getUserInfo(username);
//    }



    @RequestMapping(value="/getUserInfo",method=RequestMethod.GET)
    public UserVO getUserInfo(HttpServletRequest request, String  username){
        UserVO user = this.indexService.getUserInfo(username);
        return user;
    }

}
