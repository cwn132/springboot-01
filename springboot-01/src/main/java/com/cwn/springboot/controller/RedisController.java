package com.cwn.springboot.controller;



import com.cwn.springboot.bean.UserVO;
import com.cwn.springboot.config.utils.RedisConstants;
import com.cwn.springboot.config.utils.RedisUtil;
import com.cwn.springboot.config.utils.SerializeUtil;
import com.cwn.springboot.config.utils.StateParameter;
import com.cwn.springboot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/redis")
public class RedisController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "getRedis",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap getRedis() throws Exception {

        //存储String
//        redisUtil.set("20182019","这是一条测试数据1", RedisConstants.datebase1);
//        Long resExpire = redisUtil.expire("20182018", 60, RedisConstants.datebase1);//设置key过期时间
//        String res = redisUtil.get("20182019", RedisConstants.datebase1);
//        String name = redisUtil.get("name", RedisConstants.datebase0);

//        //存储hash-map
//        Map<String, String> map=new HashMap<String,String>();
//        map.put("a","a1");
//        map.put("b","a2");
//        map.put("c","a2");
//        redisUtil.hmset("dd",map,RedisConstants.datebase1);

//        List<String> list111 = (List<String>) redisUtil.getList("testlist");
//        System.out.println(list111);

        //存储list serialize
//       List<UserVO>  userList=accountService.selsetUserList();
//       System.out.println(userList);
//       for(UserVO user:userList){
//           redisUtil.lpush(RedisConstants.datebase1,"userList",user.getUserid()+"");
//           redisUtil.hset("user:"+user.getUserid(),"userId",user.getUserid()+"");
//           redisUtil.hset("user:"+user.getUsername(),"userName",user.getUsername()+"");
//           redisUtil.hset("user:"+user.getEmail(),"usereEail",user.getEmail()+"");
//
//       }

        //获取List数据
      String userId=  redisUtil.hget("userList:"+1,"userId");
      String userName=  redisUtil.hget("userList:"+1,"userName");
      String userEmail=  redisUtil.hget("userList:"+1,"userEmail");
        System.out.println(userId);
        System.out.println(userName);
        System.out.println(userEmail);

        return null;

    }




}
