package com.cwn.springboot.controller;


import com.cwn.springboot.Mapper.AccountMapper;
import com.cwn.springboot.bean.AjaxResponseBody;
import com.cwn.springboot.bean.Documentfile;
import com.cwn.springboot.bean.Img;
import com.cwn.springboot.bean.UserVO;
import com.cwn.springboot.service.AccountService;
import com.cwn.springboot.service.IndexService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class AccountController {

//    protected static Logger log = (Logger) LoggerFactory.getLogger(AccountController.class);
//protected static Logger logger = (Logger) LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private IndexService indexService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountService  accountService;

    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${web.uploadpath}")
    private String uploadpath;
    @Value("${file.deluploadFolder}")
    private String deluploadFolder;

    /**
     * 用户中心
     * @param
     * @return
     */
    @RequestMapping(value="/accountmain",method= RequestMethod.GET)
    public String success(@RequestParam("pp") String  username, Map<String,Object> map,
                          Model model,
                          HttpSession session,
                          HttpServletRequest request,
                          HttpServletResponse response){
//        String loginUser= (String) session.getAttribute("loginUser");
        Object loginUser=  request.getSession().getAttribute("loginUser");
        UserVO userVO= indexService.getUserInfo(username);
        //没有此帐户返回首页
        if(userVO==null){
            return "redirect:login.html";
        }else{//有此帐户获取个人资源

            //获取的用户与session不一致不能访问
            if(!userVO.getUsername().equals(loginUser)){
                session.invalidate();//使Session变成无效，及用户退出
                map.put("msg","用户已退出！请重新登陆");
                return "redirect:login.html";
            }

            String headimg =indexService.getHeadImgpath(username);

            map.put("userid",userVO.getUserid());
            map.put("loginUser",loginUser);
            map.put("user",userVO.getUsername());
            map.put("usersigninfo",userVO.getUsersigninfo());
            map.put("headimg",headimg);
            map.put("age",userVO.getAge());
            map.put("sex",userVO.getSex());
            map.put("email",userVO.getEmail());
            map.put("firstname",userVO.getFirstname());
            map.put("lastname",userVO.getLastname());

            return  "accountmain";
        }

    }

    /**
     * 注销登录
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session,Map<String,Object> map){
        session.invalidate();//使Session变成无效，及用户退出
        map.put("msg","用户已退出！请重新登陆");
//        return "login";
        return "redirect:login.html";
    }

    /**
     * 保存个人信息
     * @param
     * @return
     */
    @PostMapping(value="/savepersoninfo")
    public String savepersoninfo(@RequestParam("sessionuser") String  username,
                                 @RequestParam("userid") Integer  userid,
                                 @RequestParam("firstname") String firstname,
                                 @RequestParam("lastname") String lastname,
                                 @RequestParam("age") Integer age,
                                 @RequestParam("sex") Integer sex,
                                 @RequestParam("email") String email,
                                 @RequestParam("usersigninfo") String usersigninfo,
                                 Map<String,Object> map,
                                 HttpSession session,
                                 HttpServletRequest request,
                                 HttpServletResponse response){

        Object loginUser=  request.getSession().getAttribute("loginUser");

        UserVO userVO= indexService.getUserInfo(username);

        //获取的用户与session不一致不能访问
        if(!userVO.getUsername().equals(loginUser)){
            session.invalidate();//使Session变成无效，及用户退出
            map.put("msg","用户已退出！请重新登陆");
            return "redirect:login.html";
        }else {

            UserVO user=new UserVO();
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setSex(sex);
            user.setAge(age);
            user.setEmail(email);
            user.setUsersigninfo(usersigninfo);
            user.setUserid(userid);

            accountMapper.updateUserInfo(user);
            return "redirect:accountmain?pp="+loginUser+"&l=zh_CH";
        }


    }


    /**
     * 修改头像图片
     * @param
     * @return
     */
    @RequestMapping(value="/changeheadimg",method= RequestMethod.GET)
    public String changeheadimg(@RequestParam("pp") String  username,
                                Map<String,Object> map,
                                Model model,
                                HttpSession session,
                                HttpServletRequest request,
                                HttpServletResponse response){
//        String loginUser= (String) session.getAttribute("loginUser");
        Object loginUser=  request.getSession().getAttribute("loginUser");
        UserVO userVO= indexService.getUserInfo(username);

        //没有此帐户返回首页
        if(userVO==null){
            return "redirect:login.html";
        }else{//有此帐户获取个人资源

            //获取的用户与session不一致不能访问
            if(!userVO.getUsername().equals(loginUser)){
                session.invalidate();//使Session变成无效，及用户退出
                map.put("msg","用户已退出！请重新登陆");
                return "redirect:login.html";
            }
            //String headimg =indexService.getHeadImgpath(username);
            List headmsgList =indexService.getHeadImgpathList(username);

            map.put("userid",userVO.getUserid());
            map.put("loginUser",loginUser);
            map.put("user",userVO.getUsername());
            map.put("usersigninfo",userVO.getUsersigninfo());
            //map.put("headimg",headimg);
            map.put("age",userVO.getAge());
            map.put("sex",userVO.getSex());
            map.put("email",userVO.getEmail());
            map.put("firstname",userVO.getFirstname());
            map.put("lastname",userVO.getLastname());
            model.addAttribute("headmsgList",headmsgList); //获取头像ID及连接


            return  "changeheadimg";
        }

    }







    /**
     * 上传头像图片
     * @param
     * @return
     */
    @PostMapping(value="/changehead")
    public String changehead(@RequestParam("username") String username,
                        @RequestParam("imgid") Integer  imgid,
                        Map<String,Object> map,
                        HttpSession session,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(value = "file") MultipartFile file){



        Object loginUser=  request.getSession().getAttribute("loginUser");
        UserVO userVO= indexService.getUserInfo(username);
        String uploadpaths="";

        if(userVO!=null){
            //获取的用户与session不一致不能访问
            if(!userVO.getUsername().equals(loginUser)){
                session.invalidate();//使Session变成无效，及用户退出
                map.put("msg","用户已退出！请重新登陆");
                return "redirect:login.html";
            }

            Img img=new Img();
            if(imgid!=null&&file.isEmpty()){ //存在头像图片并且上传文件为空时，更新空头像图片
                img.setImgid(imgid);
                accountMapper.updateHeadImgEmpty(img);

            }else if(imgid!=null&&!file.isEmpty()){//存在头像图片并且上传文件不为空时,更新头像图片
//                MultipartConfigFactory factory = new MultipartConfigFactory();
//                factory.setLocation(uploadFolder);
//                //文件最大
//                factory.setMaxFileSize("5MB");
//                // 设置总上传数据总大小
//                factory.setMaxRequestSize("10MB");


//                String fileName = file.getOriginalFilename();  // 文件名
//                String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
                String filePath = uploadFolder+"//"+loginUser+"//"; // 上传后的路径
                String fileName = "logo"+".jpg"; // 新文件名
                File dest = new File(filePath + fileName);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {

                    file.transferTo(dest);
                    uploadpaths=uploadpath+loginUser+"/"+fileName;
                    img.setImgpath(uploadpaths);
                    img.setImgid(imgid);
                    accountMapper.updateHeadImg(img);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{


            }

            return "redirect:accountmain?pp="+loginUser+"&l=zh_CH";

        } else{

            return "redirect:login.html";
        }


    }



    /**
     * 文件管理
     * @param
     * @return
     */
    @RequestMapping(value="/file",method= RequestMethod.GET)
    public String file(@RequestParam("pp") String  username, Map<String,Object> map,
                          Model model,
                          HttpSession session,
                          HttpServletRequest request,
                          HttpServletResponse response){
//        String loginUser= (String) session.getAttribute("loginUser");
        Object loginUser=  request.getSession().getAttribute("loginUser");
        UserVO userVO= indexService.getUserInfo(username);
        //没有此帐户返回首页
        if(userVO==null){
            return "redirect:login.html";
        }else{//有此帐户获取个人资源

            //获取的用户与session不一致不能访问
            if(!userVO.getUsername().equals(loginUser)){
                session.invalidate();//使Session变成无效，及用户退出
                map.put("msg","用户已退出！请重新登陆");
                return "redirect:login.html";
            }
            List documentfile=  accountService.getDocumentfileByUser(userVO.getUserid());

            String headimg =indexService.getHeadImgpath(username);

            map.put("userid",userVO.getUserid());
            map.put("loginUser",loginUser);
            map.put("headimg",headimg);
            map.put("user",userVO.getUsername());
            map.put("usersigninfo",userVO.getUsersigninfo());
            model.addAttribute("documentfile",documentfile);

            return  "file";
        }

    }


    /**
     * 新建文件夹
     * @param
     * @return
     */
    @PostMapping(value="/adddocumentfile")
    public ResponseEntity<AjaxResponseBody> adddocumentfile(@RequestParam("userid") Integer  userid,
                                                            Map<String,Object> map,
                                                            Model model,
                                                            HttpSession session,
                                                            HttpServletRequest request,
                                                            HttpServletResponse response){
//        String loginUser= (String) session.getAttribute("loginUser");
        Object loginUser=  request.getSession().getAttribute("loginUser");
        UserVO userVO= indexService.getUserInfoById(userid);

        AjaxResponseBody result = new AjaxResponseBody();

        //没有此帐户返回首页
        if(userVO==null){
            result.setMsg("没有此帐户返回首页！");

            return ResponseEntity.ok(result);
        }else{//有此帐户获取个人资源

            //获取的用户与session不一致不能访问
            if(!userVO.getUsername().equals(loginUser)){
                session.invalidate();//使Session变成无效，及用户退出
                result.setMsg("用户已退出！请重新登陆!");

                return ResponseEntity.ok(result);
            }


            Documentfile df=new Documentfile();

            df.setDocumentfilename("新建文件夹");
            df.setUserid(userVO.getUserid());
            df.setUsername(userVO.getUsername());
            accountService.insertDocumentfileByUser(df);


            List documentfile=  accountService.getDocumentfileByUser(userVO.getUserid());
            String headimg =indexService.getHeadImgpath(userVO.getUsername());

            map.put("userid",userVO.getUserid());
            map.put("loginUser",loginUser);
            map.put("headimg",headimg);
            map.put("user",userVO.getUsername());
            map.put("usersigninfo",userVO.getUsersigninfo());
            model.addAttribute("documentfile",documentfile);

            result.setMsg("保存成功！");

            return ResponseEntity.ok(result);
//            return "redirect:file?pp="+loginUser+"&l=zh_CH";
        }

    }


    /**
     * 更新文件夹
     * @param
     * @return
     */
    @PostMapping(value="/updatedocumentfile")
    public ResponseEntity<AjaxResponseBody> updatedocumentfile(@RequestParam("documentfileid") Integer  documentfileid,
                                                               @RequestParam("newfilename") String  documentfilename,
                                                            Map<String,Object> map,
                                                            Model model,
                                                            HttpSession session,
                                                            HttpServletRequest request,
                                                            HttpServletResponse response){
//        String loginUser= (String) session.getAttribute("loginUser");
        String loginUser= (String) request.getSession().getAttribute("loginUser");
        UserVO userVO= indexService.getUserInfo(loginUser);

        AjaxResponseBody result = new AjaxResponseBody();

        //没有此帐户返回首页
        if(userVO==null){
            result.setMsg("没有此帐户返回首页！");

            return ResponseEntity.ok(result);
        }else{//有此帐户获取个人资源

            //获取的用户与session不一致不能访问
            if(!userVO.getUsername().equals(loginUser)){
                session.invalidate();//使Session变成无效，及用户退出
                result.setMsg("用户已退出！请重新登陆!");
                return ResponseEntity.ok(result);
            }

          boolean upflag=accountService.updateDocumentfileById(documentfileid,documentfilename);
            if(upflag==true){
                result.setMsg("更新成功！");
            }



            return ResponseEntity.ok(result);
//            return "redirect:file?pp="+loginUser+"&l=zh_CH";
        }

    }


    /**
     * 删除文件夹
     * @param
     * @return
     */
    @PostMapping(value="/deldocumentfile")
    public ResponseEntity<AjaxResponseBody> deldocumentfile(@RequestParam("documentfileid") Integer  documentfileid,
                                                            Map<String,Object> map,
                                                            Model model,
                                                            HttpSession session,
                                                            HttpServletRequest request,
                                                            HttpServletResponse response){
//        String loginUser= (String) session.getAttribute("loginUser");
        String loginUser= (String) request.getSession().getAttribute("loginUser");
        UserVO userVO= indexService.getUserInfo(loginUser);

        AjaxResponseBody result = new AjaxResponseBody();

        //没有此帐户返回首页
        if(userVO==null){
            result.setMsg("没有此帐户返回首页！");

            return ResponseEntity.ok(result);
        }else{//有此帐户获取个人资源

            //获取的用户与session不一致不能访问
            if(!userVO.getUsername().equals(loginUser)){
                session.invalidate();//使Session变成无效，及用户退出
                result.setMsg("用户已退出！请重新登陆!");

                return ResponseEntity.ok(result);
            }

            boolean delflag= false;
            try {
                delflag = accountService.deleteDocumentfileById(documentfileid);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(delflag==true){
              String  uploadpaths=deluploadFolder+loginUser+"/"+documentfileid;
                //删除文件夹
                delFolder(uploadpaths);


            }

            result.setMsg("删除成功！");

            return ResponseEntity.ok(result);
//            return "redirect:file?pp="+loginUser+"&l=zh_CH";
        }

    }

//删除文件夹
//param folderPath 文件夹完整绝对路径

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //删除指定文件夹下所有文件
//param path 文件夹完整绝对路径
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }




    /**
     * 相册管理
     * @param
     * @return
     */
    @RequestMapping(value="/gallery",method= RequestMethod.GET)
    public String gallery(@RequestParam("pp") String  username,
                          @RequestParam("documentfileid") Integer  documentfileid,
                          Map<String,Object> map,
                          Model model,
                          HttpSession session,
                          HttpServletRequest request,
                          HttpServletResponse response){
//        String loginUser= (String) session.getAttribute("loginUser");
        Object loginUser=  request.getSession().getAttribute("loginUser");
        UserVO userVO= indexService.getUserInfo(username);
        //没有此帐户返回首页
        if(userVO==null){
            return "redirect:login.html";
        }else{//有此帐户获取个人资源

            //获取的用户与session不一致不能访问
            if(!userVO.getUsername().equals(loginUser)){
                session.invalidate();//使Session变成无效，及用户退出
                map.put("msg","用户已退出！请重新登陆");
                return "redirect:login.html";
            }

            String headimg =indexService.getHeadImgpath(username);
            List imgmsgList= (List) accountService.getImgByDocumentfile(documentfileid);

            map.put("userid",userVO.getUserid());
            map.put("loginUser",loginUser);
            map.put("user",userVO.getUsername());
            map.put("usersigninfo",userVO.getUsersigninfo());
            map.put("headimg",headimg);
            map.put("documentfileid",documentfileid);
            model.addAttribute("imgmsgList",imgmsgList);

            return  "gallery";
        }

    }

}
